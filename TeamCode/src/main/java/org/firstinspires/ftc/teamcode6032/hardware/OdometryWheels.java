package org.firstinspires.ftc.teamcode6032.hardware;

import org.firstinspires.ftc.teamcode6032.drive.Pos;

public class OdometryWheels {
    public final double INCHES_PER_TICK = 0.1; // TODO

    private final HardwareManager hardware;

    private Pos offset;
    private double radius;

    private final DeadWheel dwc;
    private final DeadWheel dwl; // -x
    private final DeadWheel dwr; // +x

    private double pc = 0;
    private double pl = 0;
    private double pr = 0;

    private Pos deltaPos = Pos.ORIGIN;


    public OdometryWheels(HardwareManager hardwareManager, Pos offsetIn, double radiusIn) {
        hardware = hardwareManager;
        offset = offsetIn.copy();
        radius = radiusIn;

        dwc = hardware.getDeadWheel(HardwareIds.ODO_C,HardwareIds.ODO_C_REVERSE);
        dwl = hardware.getDeadWheel(HardwareIds.ODO_L,HardwareIds.ODO_L_REVERSE);
        dwr = hardware.getDeadWheel(HardwareIds.ODO_R,HardwareIds.ODO_R_REVERSE);
    }

    public void updateSize(Pos offsetIn, double radiusIn) {
        offset = offsetIn;
        radius = radiusIn;
    }

    public Pos getDeltaPos() {
        double nc = dwc.getPosScaled(), nl = dwl.getPosScaled(), nr = dwr.getPosScaled();
        double dc = pc-nc, dl = pl-nl, dr = pr-nr;
        pc=nc; pl=nl; pr=nr;
        deltaPos = new Pos(
                dc,
                (dr+dl)/2,
                (dr-dl)/(2*radius)
        );
//        deltaPos = new Pos(dc,dl,dr);
        return deltaPos;
    }
}
