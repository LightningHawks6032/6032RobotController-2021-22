package org.firstinspires.ftc.teamcode6032.debug.log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;
import java.util.List;

public class LogManager {
    public static final LogGroup globalGroup = new LogGroup();
    public static final List<LogGroup> groups = new ArrayList<>();
    public static final List<LogGroup> closedGroups = new ArrayList<>();

    /** Create a new group and add it */
    public static void createGroup(OpMode op) {
        LogGroup g = new LogGroup(op);
        groups.add(g);
    }

    /** Make a group no longer writable */
    public static void closeGroup(LogGroup group) {
        if (!groups.contains(group))
            throw new IllegalArgumentException("Cannot close group that is not open and associated with OpMode");
        groups.remove(group);
        group.closed = true;
        closedGroups.add(group);
    }

    /** Get an array containing all the LogGroups in the manager */
    public static LogGroup[] allLogGroups() {
        List<LogGroup> list = new ArrayList<>();
        list.add(globalGroup);
        list.addAll(groups);
        list.addAll(closedGroups);
        return (LogGroup[]) list.toArray();
    }
    /** Get a log group by it's name */
    public static LogGroup getByName(String name) {
        for (LogGroup g : allLogGroups()) {
            if (g.name.equals(name))
                return g;
        }
        return null;
    }

}
