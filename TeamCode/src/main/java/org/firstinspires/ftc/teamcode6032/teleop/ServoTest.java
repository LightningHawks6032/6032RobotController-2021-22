package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode6032.hardware.wrapper.ServoWrapper;

@Disabled
@TeleOp(group = TeleOpNames.TEST_GROUP, name = "ServoTester")
public class ServoTest extends OpMode {
    private int posI = 0;
    boolean isPressed = false;

    private int n = 0;

    ServoWrapper servo;
    HardwareManager hardware;

    @Override
    public void init() {
        hardware = new HardwareManager(hardwareMap);
        servo = hardware.getServo("test-servo",false);
        servo.setRange(0.65,1);
    }


    @Override
    public void loop() {
        double pos = gamepad1.right_stick_x;
        servo.setTarget(pos);
        telemetry.addLine("POS:"+pos);
    }
}
