package org.firstinspires.ftc.teamcode6032.hardware.subassembelyControl;

import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode6032.hardware.wrapper.CRServoWrapper;

public class DuckSpinner {
    private static final boolean REVERSE = false;
    private static final double SPEED = 1.2;

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
