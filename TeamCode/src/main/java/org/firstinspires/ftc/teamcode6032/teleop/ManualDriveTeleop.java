package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareCommon;

@TeleOp(name = "Drive")
public class ManualDriveTeleop extends ManualOpMode {

    HardwareCommon hardware;

    @Override
    public void init() {
        hardware = new HardwareCommon(hardwareMap, telemetry, this::getRuntime, this::isStopRequested);

    }

    @Override
    public void loop() {
        final float fwd = -gamepad1.left_stick_y + (gamepad1.dpad_up ? 1 : 0) - (gamepad1.dpad_down ? 1 : 0);
        final float strafe = gamepad1.left_stick_x + (gamepad1.dpad_right ? 1 : 0) - (gamepad1.dpad_left ? 1 : 0);
        final float rot = -gamepad1.right_stick_x;
        final boolean slow = gamepad1.left_bumper;

        final Vec vel = Vec.mul(
                new Vec(strafe, fwd, rot),
                slow ? .3 : 1
        );
        telemetry.addLine("("+vel.x+", "+vel.y+") "+vel.r+"rad");

        hardware.mechanam.setPower(vel);


        hardware.posIntegrator.updatePos();
        Vec p = hardware.posIntegrator.currentPos;
        telemetry.addLine("("+p.x+", "+p.y+") "+(p.r/Math.PI/2.0)+"turn");
//        telemetry.addLine("("+p.x+", "+p.y+") "+p.r+"turn");
    }
}
