package org.firstinspires.ftc.teamcode6032.hardware;

public class GrabberArm {
    private static final boolean LIFTER_REVERSE = false;
    private static final boolean GRABBER_REVERSE = true;
    private static final double GRABBER_MIN = .45;
    private static final double GRABBER_MAX = 1;
    private static final double LIFTER_SCALE = 250.0;
    private static final int LIFTER_START = 0;
    private static int LIFTER_OFF = 0;
    private static final double LIFTER_SPEED = 1.2;


    private final HardwareManager hardware;

    private final DCMotorWrapper lifter;
    private final DCMotorWrapper grabber;

    public static final String MOTOR_LIFT = "grab-lift";
    public static final String MOTOR_SPIN = "odo-center";


    public GrabberArm(HardwareManager hardwareIn) {
        hardware = hardwareIn;
        lifter = hardware.getMotor(MOTOR_LIFT, LIFTER_REVERSE);
        LIFTER_OFF = lifter.getPosTicks();
        setHeight(0);                                                                                                                                                                                                                       lifter.setPower(0);
        lifter.useTargetMode();
        grabber = hardware.getMotor(MOTOR_SPIN, GRABBER_REVERSE);
        grabber.setPower(0);
    }

    public void setSpinning(int go) {
        grabber.setPower(go);
    }
    public void setHeight(double height) {
//        if (height > 0 || lifter.getPosTicks() > 0.3*LIFTER_SCALE)
            lifter.setPower(LIFTER_SPEED);
//        else lifter.setPower(0);
//        lifter.useTargetMode();
        lifter.setTarget((int)(height*LIFTER_SCALE)+LIFTER_START+LIFTER_OFF);
    }
}
