package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="SimpleDrive", group= TeleOpNames.TEST_GROUP)
public class SimpleDriveTeleop extends OpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;

    @Override
    public void init() {
        fl = hardwareMap.get(DcMotor.class,"fl");
        fr = hardwareMap.get(DcMotor.class,"fr");
        bl = hardwareMap.get(DcMotor.class,"bl");
        br = hardwareMap.get(DcMotor.class,"br");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        final float fwd = gamepad1.left_stick_y;
        final float strafe = gamepad1.left_stick_x;
        final float rot = gamepad1.right_stick_x;

        fl.setPower(fwd + strafe + rot);
        fr.setPower(fwd + strafe - rot);
        bl.setPower(fwd - strafe + rot);
        br.setPower(fwd - strafe - rot);
    }
}
