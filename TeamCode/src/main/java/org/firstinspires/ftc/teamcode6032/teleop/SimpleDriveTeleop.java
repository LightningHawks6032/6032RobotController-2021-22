package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

@TeleOp(name="SimpleDrive", group= TeleOpNames.TEST_GROUP)
public class SimpleDriveTeleop extends OpMode {

    HardwareManager hardware;
    MechanamMotors mecha;
//    PosIntegrator posIntegrator;

    @Override
    public void init() {
        hardware = new HardwareManager(hardwareMap);
        mecha = hardware.getMechanam(Math.PI/2);
//        posIntegrator = new PosIntegrator(hardware.getOdometry(Pos.ORIGIN,3.25));
    }

    @Override
    public void loop() {
        final float fwd = gamepad1.left_stick_y;
        final float strafe = gamepad1.left_stick_x;
        final float rot = gamepad1.right_stick_x;


        final Pos vel = new Pos(
                strafe,
                fwd,
                rot,
                -1
        );

        mecha.setPower(vel);
    }
}
