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

    public static final String ODO_DEAD_WHEELS_ID = "odo";
    public static class DWIdPostfix {
        public static final String CENTER = "-center";
        public static final String LEFT = "-left";
        public static final String RIGHT = "-right";
    }

    public OdometryWheels(HardwareManager hardwareManager, Pos offsetIn, double radiusIn) {
        hardware = hardwareManager;
        offset = offsetIn.copy();
        radius = radiusIn;

        dwc = hardware.getDeadWheel(ODO_DEAD_WHEELS_ID+DWIdPostfix.CENTER,false);
        dwl = hardware.getDeadWheel(ODO_DEAD_WHEELS_ID+DWIdPostfix.LEFT,false);
        dwr = hardware.getDeadWheel(ODO_DEAD_WHEELS_ID+DWIdPostfix.RIGHT,false);
    }

    public void updateSize(Pos offsetIn, double radiusIn) {
        offset = offsetIn;
        radius = radiusIn;
    }

    public Pos getDeltaPos() {
        double nc = dwc.getPos(), nl = dwl.getPos(), nr = dwr.getPos();
        double dc = pc-nc, dl = pl-nl, dr = pr-nr;
        pc=nc; pl=nl; pr=nr;
        deltaPos = new Pos(
                dc,
                (dr+dl)/2,
                (dr-dl)/(2* radius),
                0
        );
        return deltaPos;
    }
}
