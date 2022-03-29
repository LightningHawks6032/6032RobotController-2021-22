package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareCommon;

@Disabled
@TeleOp(name="SimpleDrive", group= TeleOpNames.TEST_GROUP)
public class SimpleDriveTeleop extends ManualOpMode {

    HardwareCommon hardware;

    @Override
    public void init() {
        hardware = new HardwareCommon(hardwareMap, telemetry, this::getRuntime, this::isStopRequested);
    }

    @Override
    public void loop() {
        final float fwd = gamepad1.left_stick_y+(gamepad1.dpad_up?1:0)+(gamepad1.dpad_down?-1:0);
        final float strafe = gamepad1.left_stick_x+(gamepad1.dpad_right?1:0)+(gamepad1.dpad_left?-1:0);
        final float rot = gamepad1.right_stick_x;
        final boolean slow = gamepad1.left_bumper;
        final boolean fast = gamepad1.right_bumper;


        final boolean grabOpen = gamepad1.a;
        final float grabberPos = gamepad1.right_trigger;
        final boolean duckSpinL = gamepad1.left_bumper;
        final boolean duckSpinR = gamepad1.right_bumper;
        final int duckSpin = (duckSpinL?1:0)-(duckSpinR?1:0);


        Vec vel = new Vec(
                strafe,
                fwd,
                rot
        );
        if (slow) vel = Vec.mul(vel,.4);
        if (!fast) vel = Vec.mul(vel,.7);

        hardware.mechanam.setPower(vel);

//        arm.setHeight(grabberPos);
//        arm.setSpinning(0);
//        duckSpinner.setEnabled(duckSpin);
        telemetry.addLine("gpt: "+grabberPos+"; ds: "+duckSpin+"; go: "+grabOpen);
    }
}
