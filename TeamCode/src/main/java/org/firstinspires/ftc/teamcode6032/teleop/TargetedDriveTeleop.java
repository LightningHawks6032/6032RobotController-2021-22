package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareCommon;

@TeleOp(name = "TargetedDrive")
public class TargetedDriveTeleop extends OpMode {

    HardwareCommon hardware;

    private double lastT = 0;

    @Override
    public void init() {
        hardware = new HardwareCommon(hardwareMap, this::getRuntime);
    }

    @Override
    public void loop() {
        final double t = getRuntime();
        final double dt = t-lastT; lastT=t;

        final boolean slow = gamepad1.left_bumper;

        final float tpy = -gamepad1.left_stick_y*24;
        final float tpx = gamepad1.left_stick_x*24;
        final float tpr = -gamepad1.right_stick_x*(float)Math.PI;

        final Vec target = new Vec(tpx,tpy,tpr);


        hardware.targetMover.setTarget(target,true);

        hardware.posIntegrator.updatePos();
        hardware.targetMover.update(dt);

        Vec p = hardware.posIntegrator.currentPos;
        telemetry.addLine("T ("+target.x+", "+target.y+") "+(target.r/Math.PI/2.0)+"turn");
        telemetry.addLine("P ("+p.x+", "+p.y+") "+(p.r/Math.PI/2.0)+"turn");
    }
}
