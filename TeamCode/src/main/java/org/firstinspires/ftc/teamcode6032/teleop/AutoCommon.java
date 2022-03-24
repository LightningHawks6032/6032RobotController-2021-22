package org.firstinspires.ftc.teamcode6032.teleop;

import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.AssertPosCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.TargetCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.WaitAbsoluteCommand;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareCommon;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class AutoCommon {
    public static List<PathCommand> storageUnitPath(HardwareCommon chi, Alliance alliance) {
        boolean ali = alliance == Alliance.RED; // If x is +.
        int aSign = ali?1:-1;

        List<PathCommand> p = new ArrayList<>();
        p.add(new AssertPosCommand(new Vec(aSign*(72f-7.5f),-36f,(ali?0.5:-0.5)*Math.PI)));

        // 1. Measure duck, (ram duck out of way with side of robot?), place block.

        // TODO: measure duck

        // TO DO: place block

        // 2. goto duck spinner, spin the duck spinner

        // 2. Spin ducks.
        p.add(new TargetCommand(new Vec(aSign*(72f-7.5f-5f+1f),72f-7.5f-7.5f-1f,(ali?0:0.5)*Math.PI)));
//        p.add(new ExecCodeCommand(()->chi.duckSpinner.setEnabled(1))); // TO DO: check direction

        // 3. [@28s] Park in storage unit
        p.add(new WaitAbsoluteCommand(28));
//        p.add(new ExecCodeCommand(()->chi.duckSpinner.setEnabled(0)));
        p.add(new TargetCommand(new Vec(aSign*36,-50,(ali?0:0.5)*Math.PI)));

        // >end
        return p;
    }
    public static List<PathCommand> warehousePath(Alliance alliance) {
        // 1. Measure duck, (ram duck out of way with side of robot?), place block.
        // 2. lift arm, move back and ram over wall.
        // >end
        return null;
    }
    public static List<PathCommand> remoteEventPath(HardwareCommon chi) {
        List<PathCommand> p = new ArrayList<>();
        p.add(new AssertPosCommand(new Vec(72f-7.5f,-36f,-1f*Math.PI)));

        // 1. Measure duck [and conditional place block]

        // TO DO: measure ducks

        // 2. Spin ducks.
        p.add(new TargetCommand(new Vec(72f-7.5f-5f+1f,72f-7.5f-7.5f-1f,1f*Math.PI)));
//        p.add(new ExecCodeCommand(()->chi.duckSpinner.setEnabled(1))); // TO DO: check direction


        // 3. [@28s] Ram into warehouse
        p.add(new WaitAbsoluteCommand(28));
//        p.add(new ExecCodeCommand(()->chi.duckSpinner.setEnabled(0)));
        p.add(new TargetCommand(new Vec(48f,-36f, -1f*Math.PI)));
        p.add(new TargetCommand(new Vec(48f,36f, -1f*Math.PI)));

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
