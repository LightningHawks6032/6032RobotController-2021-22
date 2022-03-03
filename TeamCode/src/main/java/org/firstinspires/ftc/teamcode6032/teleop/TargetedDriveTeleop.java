package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;
import org.firstinspires.ftc.teamcode6032.drive.RobotTargetMover;
import org.firstinspires.ftc.teamcode6032.hardware.CommonHardwareInit;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

@TeleOp(name = "TargetedDrive")
public class TargetedDriveTeleop extends OpMode {

    HardwareManager hardware;
    MechanamMotors mecha;

    CommonHardwareInit chi;
    PosIntegrator posInt;

    RobotTargetMover mover;

    private double lastT = 0;

    @Override
    public void init() {
        chi = new CommonHardwareInit(hardwareMap);
        hardware = chi.hardware;
        mecha = chi.mechanam;

        posInt = chi.posIntegrator;
        posInt.setCurrentPos(Pos.ORIGIN);

        mover = new RobotTargetMover(posInt,mecha);
    }

    @Override
    public void loop() {
        final double t = getRuntime();
        final double dt = t-lastT; lastT=t;

        final boolean slow = gamepad1.left_bumper;

        final float tpy = -gamepad1.left_stick_y*24;
        final float tpx = gamepad1.left_stick_x*24;
        final float tpr = -gamepad1.right_stick_x*(float)Math.PI;

        final Pos target = new Pos(tpx,tpy,tpr);

//        final Pos vel = Pos.rot(Pos.mul(
//                new Pos(strafe, fwd, rot),
//                slow ? .3 : 1
//        ),-posInt.currentPos.r,true);

//        telemetry.addLine("("+vel.x+", "+vel.y+") "+vel.r+"rad");

//        mecha.setPower(vel);

        mover.setTarget(target,true);

        posInt.updatePos();
        mover.update(dt);

        Pos p = posInt.currentPos;
        telemetry.addLine("T ("+target.x+", "+target.y+") "+(target.r/Math.PI/2.0)+"turn");
        telemetry.addLine("P ("+p.x+", "+p.y+") "+(p.r/Math.PI/2.0)+"turn");
    }
}
