package org.firstinspires.ftc.teamcode6032.teleop;

public class AutoCommon {
    public void storageUnitPath(Alliance alliance) {
        // 1. Measure duck, (ram duck out of way with side of robot?), place block.
        // 2. goto duck spinner, spin the duck spinner
        // 3. at 28.5s park.
        // >end
    }
    public void warehousePath(Alliance alliance) {
        // 1. Measure duck, (ram duck out of way with side of robot?), place block.
        // 2. lift arm, move back and ram over wall.
        // >end
    }

    /** Measure the position of the duck, closest to warehouse is 0, furthest is 2. */
    public int measureDuck(Alliance alliance) {
        return -1; // TODO
    }

    public enum Alliance {
        RED, BLUE
    }
}
