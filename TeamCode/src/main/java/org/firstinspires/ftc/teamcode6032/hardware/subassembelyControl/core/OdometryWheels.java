package org.firstinspires.ftc.teamcode6032.hardware.subassembelyControl.core;

import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareIds;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode6032.hardware.wrapper.DeadWheelWrapper;

public class OdometryWheels {
    public final double INCHES_PER_TICK = 0.1; // TODO

    private final HardwareManager hardware;

    private Vec offset;
    private double radius;

    private final DeadWheelWrapper dwc;
    private final DeadWheelWrapper dwl; // -x
    private final DeadWheelWrapper dwr; // +x

    private double pc = 0;
    private double pl = 0;
    private double pr = 0;

    private Vec deltaPos = Vec.ORIGIN;


    public OdometryWheels(HardwareManager hardwareManager, Vec offsetIn, double radiusIn) {
        hardware = hardwareManager;
        offset = offsetIn.copy();
        radius = radiusIn;

        dwc = hardware.getDeadWheel(HardwareIds.ODO_C,HardwareIds.ODO_C_REVERSE);
        dwl = hardware.getDeadWheel(HardwareIds.ODO_L,HardwareIds.ODO_L_REVERSE);
        dwr = hardware.getDeadWheel(HardwareIds.ODO_R,HardwareIds.ODO_R_REVERSE);
    }

    public void updateSize(Vec offsetIn, double radiusIn) {
        offset = offsetIn;
        radius = radiusIn;
    }

    public Vec getDeltaPos() {
        double nc = dwc.getPos(), nl = dwl.getPos(), nr = dwr.getPos();
        double dc = pc-nc, dl = pl-nl, dr = pr-nr;
        pc=nc; pl=nl; pr=nr;
        deltaPos = new Vec(
                dc,
                (dr+dl)/2,
                (dr-dl)/(2*radius)
        );
//        deltaPos = new Pos(dc,dl,dr);
        return deltaPos;
    }
}
