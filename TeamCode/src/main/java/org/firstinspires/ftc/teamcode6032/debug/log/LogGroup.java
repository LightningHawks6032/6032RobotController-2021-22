package org.firstinspires.ftc.teamcode6032.debug.log;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogGroup {
    public final OpMode associatedOpMode;
    public final GroupType type;
    public final Logger normalLogger;
    public final String name;

    public final List<LogRecord> logs = new ArrayList<>();

    boolean closed = false;

    private LogGroup(OpMode associatedOpMode, GroupType type, String name) {
        this.associatedOpMode = associatedOpMode;
        this.type = type;
        this.name = name;
        normalLogger = Logger.getLogger(name);
    }
    LogGroup() { this(null, GroupType.GLOBAL, "GLOBAL"); }
    LogGroup(OpMode opMode) { this(opMode, GroupType.OP_MODE, opMode.getClass().getName()+"-"+Long.toHexString(Calendar.getInstance().getTimeInMillis())); }


    public void log(Level level, String str) {
        if (closed && this != LogManager.globalGroup) {
            LogManager.globalGroup.warn("Attempt to log on closed group `"+name+"`");
            return;
        }
        LogRecord record = new LogRecord(level,str);
        logs.add(record);
        normalLogger.log(record);

    }
    public void severe(String str) { log(Level.SEVERE,str); }
    public void warn(String str) { log(Level.WARNING,str); }
    public void info(String str) { log(Level.INFO,str); }
    public void config(String str) { log(Level.CONFIG,str); }
    public void fine(String str) { log(Level.FINE,str); }
    public void finer(String str) { log(Level.FINER,str); }
    public void finest(String str) { log(Level.FINEST,str); }

    public enum GroupType { GLOBAL, OP_MODE }
}
