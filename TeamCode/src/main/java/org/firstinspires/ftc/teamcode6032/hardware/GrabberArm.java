package org.firstinspires.ftc.teamcode6032.hardware;

public class GrabberArm {
    private static final boolean LIFTER_REVERSE = false;
    private static final boolean GRABBER_REVERSE = false;
    private static final double GRABBER_MIN = 0.68;
    private static final double GRABBER_MAX = 1.0;
    private static final double LIFTER_SCALE = 120.0;

    private final HardwareManager hardware;

    private final DCMotorWrapper lifter;
    private final ServoWrapper grabber;

    public static final String MOTORS_ID = "grab";
    public static class MotorIdPostfix {
        public static final String LIFTER = "-lift";
        public static final String GRABBER = "-grab";
    }


    public GrabberArm(HardwareManager hardwareIn) {
        hardware = hardwareIn;
        lifter = hardware.getMotor(MOTORS_ID+MotorIdPostfix.LIFTER, LIFTER_REVERSE);
        lifter.useTargetMode();
        grabber = hardware.getServo(MOTORS_ID+MotorIdPostfix.GRABBER, GRABBER_REVERSE);
        grabber.setRange(GRABBER_MIN,GRABBER_MAX);
    }

    public void setOpen(boolean open) {
        grabber.setTarget(open?1:-1);
    }
    public void setHeight(double height) {
        lifter.setTarget((int)(height*LIFTER_SCALE));
    }
}