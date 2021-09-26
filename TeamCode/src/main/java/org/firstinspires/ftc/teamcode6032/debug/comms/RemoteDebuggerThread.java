package org.firstinspires.ftc.teamcode6032.debug.comms;

import org.firstinspires.ftc.teamcode6032.debug.comms.data.Packet;
import org.firstinspires.ftc.teamcode6032.debug.comms.data.RequestInfo;
import org.firstinspires.ftc.teamcode6032.debug.log.LogManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class RemoteDebuggerThread extends Thread {
    /** The port the robot will serve for the client to connect. */
    public static final int PORT = 6032;
    /** The singleton instance of this RemoteDebuggerThread. */
    private static final RemoteDebuggerThread instance = new RemoteDebuggerThread();


    private boolean running = true;
    private boolean keepOpen = false;
    private final Queue<Packet> packetsReceived = new PriorityQueue<>();
    private final Queue<Packet> packetsToSend = new PriorityQueue<>();
    private final Map<Integer, RequestInfo> requests = new HashMap<>();

    private ServerSocket serverSocket;

    /** Begin serving running the debugger thread if it's not already connected. */
    public static void startThread() {
        if (!instance.isAlive())
            instance.start();
    }

    public static void stopThread() {
        instance.running = false;
        if (instance.keepOpen) {
            instance.keepOpen = false;
            instance.interrupt();
        } else {
            try {
                instance.serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeCurrentConnection() {
        instance.keepOpen = false;
        instance.interrupt();
    }

    public static void sendPacket(Packet packet) {}


    // Make constructor private, only this class can instance itself.
    private RemoteDebuggerThread() {}

    @Override
    public void run() {
        LogManager.globalGroup.info("RemoteDebugger initialized.");
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(10);
            Socket s;
            while (running) {
                try { s = serverSocket.accept(); }
                catch (SocketTimeoutException | SocketException e) { continue; }
                LogManager.globalGroup.info("RemoteDebugger connected.");
                keepOpen = true;
                DataInputStream dIn = new DataInputStream(s.getInputStream());
                DataOutputStream dOut = new DataOutputStream(s.getOutputStream());

                while (keepOpen) {
                    sendPackets(dOut);
                    readPackets(dIn);
                    processPackets();
                    waitTick();
                }
                LogManager.globalGroup.info("RemoteDebugger disconnected.");
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogManager.globalGroup.info("RemoteDebugger disconnected.");
    }

    /** Read in all the packets that were sent to the robot */
    private void readPackets(DataInputStream dIn) throws IOException {
        // TODO check that partially sent packets aren't possible.
        while (dIn.available() > 0)
            packetsReceived.add(Packet.codec.decode(dIn));
    }
    /** Send all packets which were queued for sending. */
    private void sendPackets(DataOutputStream dOut) throws IOException {
        while (!packetsToSend.isEmpty())
            Packet.codec.encode(Objects.requireNonNull(packetsToSend.poll()),dOut);
        dOut.flush();
    }
    /** Handle the packets which were loaded by readPackets. */
    private void processPackets() {
        pruneOldRequests();
        while (!packetsReceived.isEmpty()) {
            Packet pIn = Objects.requireNonNull(packetsReceived.poll());
            switch (pIn.type) {
                case REQUEST:
                    pIn.command.handle(pIn);
                    break;
                case RESPONSE:
                    RequestInfo req = requests.get(pIn.requestId);
                    req.addResponse(pIn);
                    req.requestPacket.command.handleResponse(req.requestPacket,req.responses());
                    break;
            }
        }
    }

    /** Remove request entries which are now too old to be reasonably expecting responses. */
    private void pruneOldRequests() {
        long now = Calendar.getInstance().getTimeInMillis();
        for (int id : requests.keySet()) {
            if (requests.get(id).checkExpired(now))
                requests.remove(id);
        }
    }

    private void waitTick() {
        try { sleep(1); }
        catch (InterruptedException ignored) {}
    }
}
