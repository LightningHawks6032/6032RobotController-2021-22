package org.firstinspires.ftc.teamcode6032.debug.comms.data;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * A command send from the app to the robot as either a request for data or a movement command.
 */
abstract public class Command {
    /** A list of all the created commands in the app. */
    public static ArrayList<Command> commands = new ArrayList<>();

    // Static initializer to load all commands exactly once.
    static {
        // Control commands, no function, but used in command sender.
        create("WAIT",p->null);

        // Functional commands, handling or requesting data from the robot.
        //// TODO: add commands
    }

    /** Add a command that was created to the command registry */
    public static void create(String id, Function<Packet,?> handler) {
        commands.removeIf(v -> v.id.equals(id));
        // Create and add a the command with the handler.
        commands.add(new Command(id) {
            @Override public void handle(Packet packet) { handler.apply(packet); }
        });
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

    private Command(String id) {
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
}
