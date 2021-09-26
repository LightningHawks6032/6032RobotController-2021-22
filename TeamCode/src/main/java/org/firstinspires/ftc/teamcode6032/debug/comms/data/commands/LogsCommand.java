package org.firstinspires.ftc.teamcode6032.debug.comms.data.commands;

import org.firstinspires.ftc.teamcode6032.debug.comms.data.Command;
import org.firstinspires.ftc.teamcode6032.debug.comms.data.Packet;
import org.firstinspires.ftc.teamcode6032.debug.comms.data.PacketParam;
import org.firstinspires.ftc.teamcode6032.debug.log.LogGroup;
import org.firstinspires.ftc.teamcode6032.debug.log.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;


/**
 * Fetch the Robot's log data.
 *
 * `LOGS(string groupId, int maxRecords?)`
 * - Return the log records for the groups.
 *   Format: `{level}:{time-millis}:{msg}`
 * `LOGS()`
 * - Return `string[]` containing log group names
 */
public class LogsCommand extends Command {
    public LogsCommand() { super("LOGS"); }

    @Override
    public void handle(Packet packet) {
        if (packet.params.length == 0) {// >> NO PARAMS: get a list of groups
            List<PacketParam> params = new ArrayList<>();

            LogGroup[] groups = LogManager.allLogGroups();
            for (LogGroup group : groups)
                params.add(new PacketParam(group.name));

            packet.respond(get("LOGS"), (PacketParam[]) params.toArray());
        } else { // >> 1-2 PARAMS: get logs from a group
            // Get first parameter `groupId`
            PacketParam groupIdParam = getParam(packet,0,"groupId", PacketParam.Type.STRING, true);
            if (getParamThrewError(groupIdParam,true)) return;
            assert groupIdParam != null;

            // Get the second parameter `maxRecords` if it was provided.
            int maxRecords = Integer.MAX_VALUE;
            PacketParam maxRecordsParam = getParam(packet,1,"maxRecords", PacketParam.Type.INT,false);
            if (getParamThrewError(maxRecordsParam,false)) return;
            if (maxRecordsParam != null)
                maxRecords = maxRecordsParam.intData;

            // Get the log group from LogManager.
            LogGroup group = LogManager.getByName(groupIdParam.stringData);
            // If the group is null, respond with error.
            if (group == null) {
                respondWithError(packet,"Log group not found.");
                return;
            }

            int nLogs = group.logs.size();
            List<PacketParam> params = new ArrayList<>();

            for (int i = 0; i < maxRecords && i < nLogs; i++) {
                LogRecord log = group.logs.get(nLogs-1-i);
                String s = log.getLevel().getName() + ":" +
                        log.getMillis() + ":" +
                        log.getMessage();
                params.add(new PacketParam(s));
            }

            packet.respond(get("LOGS"), (PacketParam[]) params.toArray());
        }
    }
}
