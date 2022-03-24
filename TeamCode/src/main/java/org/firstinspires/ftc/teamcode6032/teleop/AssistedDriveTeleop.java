package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareCommon;

@TeleOp(name = "AssistedDrive")
public class AssistedDriveTeleop extends OpMode {

    HardwareCommon hardware;

    @Override
    public void init() {
        hardware = new HardwareCommon(hardwareMap, this::getRuntime);

        hardware.posIntegrator.setCurrentPos(Vec.ORIGIN);
    }

    @Override
    public void loop() {
        final float fwd = -gamepad1.left_stick_y + (gamepad1.dpad_up ? 1 : 0) - (gamepad1.dpad_down ? 1 : 0);
        final float strafe = gamepad1.left_stick_x + (gamepad1.dpad_right ? 1 : 0) - (gamepad1.dpad_left ? 1 : 0);
        final boolean slow = gamepad1.left_bumper;


        final float rot = -(float)Math.min(1,Math.max(-1,hardware.posIntegrator.currentPos.r*.5));

        final Vec vel = Vec.rot(Vec.mul(
                new Vec(strafe, fwd, rot),
                slow ? .3 : 1
        ),-hardware.posIntegrator.currentPos.r,true);

        telemetry.addLine("("+vel.x+", "+vel.y+") "+vel.r+"rad");

        hardware.mechanam.setPower(vel);

        hardware.posIntegrator.updatePos();
        Vec p = hardware.posIntegrator.currentPos;
        telemetry.addLine("("+p.x+", "+p.y+") "+(p.r/Math.PI/2.0)+"turn");
    }
}
