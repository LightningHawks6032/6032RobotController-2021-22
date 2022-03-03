package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;
import org.firstinspires.ftc.teamcode6032.hardware.CommonHardwareInit;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

@TeleOp(name = "AssistedDrive")
public class AssistedDriveTeleop extends OpMode {

    HardwareManager hardware;
    MechanamMotors mecha;
//    GrabberArm arm;
//    DuckSpinner duckSpinner;

    CommonHardwareInit chi;
    PosIntegrator posInt;

    private double grabberHeight = 0;
    private double lastT = 0;
    @Override
    public void init() {
        chi = new CommonHardwareInit(hardwareMap);
        hardware = chi.hardware;
        mecha = chi.mechanam;
//        arm = new GrabberArm(hardware);
//        duckSpinner = chi.duckSpinner;

        posInt = chi.posIntegrator;
        posInt.setCurrentPos(Pos.ORIGIN);
    }

    @Override
    public void loop() {
        final float fwd = -gamepad1.left_stick_y + (gamepad1.dpad_up ? 1 : 0) - (gamepad1.dpad_down ? 1 : 0);
        final float strafe = gamepad1.left_stick_x + (gamepad1.dpad_right ? 1 : 0) - (gamepad1.dpad_left ? 1 : 0);
//        final float rot = -gamepad1.right_stick_x;
        final boolean slow = gamepad1.left_bumper;
//        final double t = getRuntime();
//        final double dt = t-lastT;
//        lastT=t;

//        double v = slow?.3:1;
//        mecha.fl.setPower(gamepad1.dpad_up?v:0);
//        mecha.fr.setPower(gamepad1.dpad_down?v:0);
//        mecha.bl.setPower(gamepad1.dpad_left?v:0);
//        mecha.br.setPower(gamepad1.dpad_right?v:0);


//        final float grabberPosDelta = gamepad2.right_trigger-gamepad2.left_trigger;
//        final boolean grabberP = gamepad2.a;
//        final boolean grabberN = gamepad2.y;
//        final boolean duckSpinL = gamepad2.left_bumper;
//        final boolean duckSpinR = gamepad2.right_bumper;
//        final int duckSpin = (duckSpinL?1:0)-(duckSpinR?1:0);

//        grabberHeight += grabberPosDelta*dt*0.8;
//        grabberHeight = Math.min(Math.max(grabberHeight,0),0.67);

        /////// USE
        final float rot = -(float)Math.min(1,Math.max(-1,posInt.currentPos.r*.5));

        final Pos vel = Pos.rot(Pos.mul(
                new Pos(strafe, fwd, rot),
                slow ? .3 : 1
        ),-posInt.currentPos.r,true);

        telemetry.addLine("("+vel.x+", "+vel.y+") "+vel.r+"rad");

        mecha.setPower(vel);
        //////// \USE

//        arm.setHeight(grabberHeight);
//        arm.setSpinning((grabberP?1:0)-(grabberN?1:0));
//        duckSpinner.setEnabled(duckSpin);

        posInt.updatePos();
        Pos p = posInt.currentPos;
        telemetry.addLine(grabberHeight+"x");
        telemetry.addLine("("+p.x+", "+p.y+") "+(p.r/Math.PI/2.0)+"turn");
//        telemetry.addLine("("+p.x+", "+p.y+") "+p.r+"turn");
    }
}
