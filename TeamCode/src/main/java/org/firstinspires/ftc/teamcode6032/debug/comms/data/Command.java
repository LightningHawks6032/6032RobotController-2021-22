package org.firstinspires.ftc.teamcode6032.debug.comms.data;

import androidx.annotation.Nullable;

import org.firstinspires.ftc.teamcode6032.debug.comms.data.commands.CommandsInitializer;
import org.firstinspires.ftc.teamcode6032.debug.comms.data.commands.LogsCommand;
import org.firstinspires.ftc.teamcode6032.debug.log.LogManager;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * A command send from the app to the robot as either a request for data or a movement command.
 */
abstract public class Command {
    /** A list of all the created commands in the app. */
    public static ArrayList<Command> commands = new ArrayList<>();

    // Load the commands when the command system is loaded.
    static { CommandsInitializer.load(); }

    /** Add a command that was created to the command registry */
    public static void create(String id, Function<Packet,?> handler) {
        commands.removeIf(v -> v.id.equals(id));
        // Create and add a the command with the handler.
        commands.add(new Command(id) {
            @Override public void handle(Packet packet) { handler.apply(packet); }
        });
    }
    /** Add a command that was created to the command registry */
    public static void create(String id, Function<Packet,?> handler, BiFunction<Packet,Packet[],?> handleResponse) {
        commands.removeIf(v -> v.id.equals(id));
        // Create and add a the command with the handler.
        commands.add(new Command(id) {
            @Override public void handle(Packet packet) { handler.apply(packet); }
            @Override public void handleResponse(Packet packet, Packet[] responses) { handleResponse.apply(packet, responses); }
        });
    }
    public static void create(Command cmd) {
        commands.removeIf(v -> v.id.equals(cmd.id));
        commands.add(cmd);
    }
    /** Get a command by it's id. */
    public static Command get(String id) {
        for (Command cmd : commands) {
            if (cmd.id.equalsIgnoreCase(id))
                return cmd;
        }
        return null;
    }

    /** 4 letter code for identifying command. */
    final String id;

    protected Command(String id) {
        if (!verifyId(id))
            throw new IllegalArgumentException("Command id was not in format /^[A-Z_]{4}$/");
        this.id = id;
    }

    /** Verify a command's id. */
    private static boolean verifyId(String id) {
        Pattern e = Pattern.compile("^[A-Z_]{4}$");
        return e.matcher(id).find();
    }

    public abstract void handle(Packet packet);
    public void handleResponse(Packet packet, Packet[] responses) {}



    protected void respondWithError(Packet packet, String message) {
        packet.respond(get("FAIL"),new PacketParam[]{new PacketParam(message)});
        LogManager.globalGroup.warn("Failed to handle packet: "+message);
    }
    protected boolean assertParamType(Packet packet, PacketParam param, String paramName, int paramI, PacketParam.Type type) {
        if (param.type != type) {
            respondWithError(packet,"Parameter `"+paramName+"` ["+paramI+"] was not of type `"+type.name().toLowerCase()+"`");
            return true;
        } else return false;
    }

    @Nullable
    protected PacketParam getParam(Packet packet, int paramI, String paramName, PacketParam.Type type, boolean isRequired) {
        if (paramI >= packet.params.length) {
            if (isRequired)
                respondWithError(packet,"Parameter `"+paramName+"` was required but missing.");
            return null;
        } else {
            PacketParam param = packet.params[paramI];
            if (assertParamType(packet,param,paramName,paramI,type))
                return new PacketParam();
            return param;
        }
    }
    protected boolean getParamThrewError(PacketParam param, boolean isRequired) {
        if (isRequired)
            return param == null || param.type == null;
        else
            return param != null && param.type == null;
    }
}
