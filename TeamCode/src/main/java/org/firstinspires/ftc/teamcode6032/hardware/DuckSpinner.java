package org.firstinspires.ftc.teamcode6032.hardware;

public class DuckSpinner {
    private static final boolean REVERSE = false;
    private static final double SPEED = 0.1;

    private final HardwareManager hardware;

    private final DCMotorWrapper spinner;

    public static final String MOTOR_ID = "spinner";


    public DuckSpinner(HardwareManager hardwareIn) {
        hardware = hardwareIn;
        spinner = hardware.getMotor(MOTOR_ID, REVERSE);
    }

    public void setEnabled(boolean enabled) {
        spinner.setPower(enabled ? SPEED : 0);
    }
}
