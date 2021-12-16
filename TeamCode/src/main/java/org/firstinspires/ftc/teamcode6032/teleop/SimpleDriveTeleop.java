package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.hardware.DuckSpinner;
import org.firstinspires.ftc.teamcode6032.hardware.GrabberArm;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

@Disabled
@TeleOp(name="SimpleDrive", group= TeleOpNames.TEST_GROUP)
public class SimpleDriveTeleop extends OpMode {

    HardwareManager hardware;
    MechanamMotors mecha;
    GrabberArm arm;
    DuckSpinner duckSpinner;
//    PosIntegrator posIntegrator;

    @Override
    public void init() {
        hardware = new HardwareManager(hardwareMap);
        mecha = hardware.getMechanam(0);
        arm = new GrabberArm(hardware);
        duckSpinner = new DuckSpinner(hardware);
//        posIntegrator = new PosIntegrator(hardware.getOdometry(Pos.ORIGIN,3.25));
    }

    @Override
    public void loop() {
        final float fwd = gamepad1.left_stick_y;
        final float strafe = gamepad1.left_stick_x;
        final float rot = gamepad1.right_stick_x;
        final boolean slow = gamepad1.left_bumper;


        final boolean grabOpen = gamepad1.a;
        final float grabberPos = gamepad1.right_trigger;
        final boolean duckSpinL = gamepad1.left_bumper;
        final boolean duckSpinR = gamepad1.right_bumper;
        final int duckSpin = (duckSpinL?1:0)-(duckSpinR?1:0);


        Pos vel = new Pos(
                strafe,
                fwd,
                rot,
                -1
        );
        if (slow) vel = Pos.mul(vel,.3);

        mecha.setPower(vel);

        arm.setHeight(grabberPos);
        arm.setOpen(grabOpen);
        duckSpinner.setEnabled(duckSpin);
        telemetry.addLine("gpt: "+grabberPos+"; ds: "+duckSpin+"; go: "+grabOpen);
    }
}
