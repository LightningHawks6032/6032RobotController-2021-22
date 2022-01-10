package org.firstinspires.ftc.teamcode6032.teleop;

import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathCommand;

import java.util.List;

public class AutoCommon {
    public List<PathCommand> storageUnitPath(Alliance alliance) {
        // 1. Measure duck, (ram duck out of way with side of robot?), place block.
        // 2. goto duck spinner, spin the duck spinner
        // 3. at 28.5s park.
        // >end
        return null;
    }
    public List<PathCommand> warehousePath(Alliance alliance) {
        // 1. Measure duck, (ram duck out of way with side of robot?), place block.
        // 2. lift arm, move back and ram over wall.
        // >end
        return null;
    }
    public List<PathCommand> remoteEventPath() {
        // TODO: design path
        return null;
    }

    /** Measure the position of the duck, closest to warehouse is 0, furthest is 2. */
    public int measureDuck(Alliance alliance) {
        return -1; // TODO
    }

    public enum Alliance {
        RED, BLUE
    }
}
