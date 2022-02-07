package org.firstinspires.ftc.teamcode6032.teleop;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.AssertPosCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.ExecCodeCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.TargetCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.WaitAbsoluteCommand;
import org.firstinspires.ftc.teamcode6032.hardware.CommonHardwareInit;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;

import java.util.ArrayList;
import java.util.List;

public class AutoCommon {
    public static List<PathCommand> storageUnitPath(Alliance alliance) {
        // 1. Measure duck, (ram duck out of way with side of robot?), place block.
        // 2. goto duck spinner, spin the duck spinner
        // 3. at 28.5s park.
        // >end
        return null;
    }
    public static List<PathCommand> warehousePath(Alliance alliance) {
        // 1. Measure duck, (ram duck out of way with side of robot?), place block.
        // 2. lift arm, move back and ram over wall.
        // >end
        return null;
    }
    public static List<PathCommand> remoteEventPath(CommonHardwareInit chi) {
        List<PathCommand> p = new ArrayList<>();
        p.add(new AssertPosCommand(new Pos(72f-7.5f,-36f,-1f*Math.PI)));

        // 1. Measure duck [and conditional place block]

        // TODO: measure ducks

        // 2. Spin ducks.
        p.add(new TargetCommand(new Pos(72f-7.5f-5f+1f,72f-7.5f-7.5f-1f,1f*Math.PI)));
        p.add(new ExecCodeCommand(()->chi.duckSpinner.setEnabled(1))); // TODO: check direction


        // 3. [@28s] Ram into warehouse
        p.add(new WaitAbsoluteCommand(28));
        p.add(new ExecCodeCommand(()->chi.duckSpinner.setEnabled(0)));
        p.add(new TargetCommand(new Pos(48f,-36f, -1f*Math.PI)));
        p.add(new TargetCommand(new Pos(48f,36f, -1f*Math.PI)));

        return p;
    }

    public static PathCommand[] genRemotePlaceSequence(int heightN) {
        // TODO: Impl
        return new PathCommand[0];
    }

    /** Measure the position of the duck, closest to warehouse is 0, furthest is 2. */
    public static int measureDuck(Alliance alliance) {
        return -1; // TODO
    }

    public enum Alliance {
        RED, BLUE
    }
}
