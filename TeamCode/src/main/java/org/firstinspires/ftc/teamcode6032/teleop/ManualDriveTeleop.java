package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.hardware.DuckSpinner;
import org.firstinspires.ftc.teamcode6032.hardware.GrabberArm;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

@TeleOp(name = "Drive")
public class ManualDriveTeleop extends OpMode {

    HardwareManager hardware;
    MechanamMotors mecha;
    GrabberArm arm;
    DuckSpinner duckSpinner;

    @Override
    public void init() {
        hardware = new HardwareManager(hardwareMap);
        mecha = hardware.getMechanam(0);
        arm = new GrabberArm(hardware);
        duckSpinner = new DuckSpinner(hardware);
    }

    @Override
    public void loop() {
        final float fwd = gamepad1.left_stick_y;
        final float strafe = gamepad1.left_stick_x;
        final float rot = gamepad1.right_stick_x;
        final boolean slow = gamepad1.left_bumper;


        final boolean grabOpen = gamepad2.a;
        final float grabberPos = gamepad2.right_trigger;
        final boolean duckSpin = gamepad2.right_bumper;


        final Pos vel = Pos.mul(
                new Pos(strafe, fwd, rot, -1),
                slow ? .3 : 1
        );

        mecha.setPower(vel);

        arm.setHeight(grabberPos);
        arm.setOpen(grabOpen);
        duckSpinner.setEnabled(duckSpin);
    }
}
