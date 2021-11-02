package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.ArrayList;
import java.util.List;

public class DCMotorWrapper {
    // TODO: track motor information

    public final DcMotor motor;

    public DCMotorWrapper(DcMotor motorIn) {
        motor = motorIn;
    }

    public boolean getIsReversed() {
        return motor.getDirection() == DcMotorSimple.Direction.REVERSE;
    }

    public void setPower(double power) {
        motor.setPower(power);
    }
}
