package org.firstinspires.ftc.teamcode6032.debug.comms.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RequestInfo {
    public static final long EXPIRE_TIME = 10000;

    public final Packet requestPacket;
    private final List<Packet> responses = new ArrayList<>();
    private long lastUpdated;


    public RequestInfo(Packet requestPacket) {
        this.requestPacket = requestPacket;
        updateTime();
    }

    public void addResponse(Packet p) {
        if (p.type != Packet.Type.RESPONSE)
            throw new IllegalArgumentException("response packet must be of type response.");
        updateTime();
    }
    public Packet[] responses() {
        return (Packet[]) responses.toArray();
    }

    private long now() { return Calendar.getInstance().getTimeInMillis(); }
    public void updateTime() {
        lastUpdated = now();
    }
    public boolean checkExpired() {
        return checkExpired(now());
    }
    public boolean checkExpired(long now) {
        return now - lastUpdated > EXPIRE_TIME;
    }

}
