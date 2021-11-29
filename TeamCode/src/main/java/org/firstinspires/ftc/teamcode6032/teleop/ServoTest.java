package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode6032.hardware.ServoWrapper;

@TeleOp(group = TeleOpNames.TEST_GROUP, name = "ServoTester")
public class ServoTest extends OpMode {
    private double[] positions = {-1,1,0,-1,.5,-.5,-1};
    private int posI = 0;
    boolean isPressed = false;

    ServoWrapper servo;
    HardwareManager hardware;

    @Override
    public void init() {
        hardware = new HardwareManager(hardwareMap);
        servo = hardware.getServo("test-servo",false);
    }


    @Override
    public void loop() {
        if (!isPressed && gamepad1.a) nextPos();
        isPressed = gamepad1.a;

        double pos = gamepad1.right_stick_x*(gamepad1.b?2:1);
        servo.setTarget(pos);
        telemetry.addLine("POS:"+pos);
        telemetry.addLine("mn:"+currentMin()+"; mx:"+currentMax());
    }

    private double currentMax() { return Math.max(positions[posI],positions[posI+1]); }
    private double currentMin() { return Math.min(positions[posI],positions[posI+1]); }

    private void nextPos() {
        posI++;
        if (posI >= positions.length-1) posI = 0;
        servo.setRange(currentMin(), currentMax());
    }
}
