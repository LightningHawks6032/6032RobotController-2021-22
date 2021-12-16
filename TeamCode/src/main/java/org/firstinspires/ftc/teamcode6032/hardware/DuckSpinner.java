package org.firstinspires.ftc.teamcode6032.hardware;

public class DuckSpinner {
    private static final boolean REVERSE = false;
    private static final double SPEED = 0.25;

    private final HardwareManager hardware;

    private final CRServoWrapper spinner;

    public static final String MOTOR_ID = "spinner";


    public DuckSpinner(HardwareManager hardwareIn) {
        hardware = hardwareIn;
        spinner = hardware.getCRServo(MOTOR_ID, REVERSE);
    }

    public void setEnabled(int dir) {
        spinner.setPower(((float)dir) * SPEED);
    }
}
