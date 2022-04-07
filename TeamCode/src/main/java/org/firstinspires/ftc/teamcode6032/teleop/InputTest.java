package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.debug.dbuginput.DBugData;
import org.firstinspires.ftc.teamcode6032.debug.dbuginput.DBugInput;

@TeleOp(name = "InputTest", group = "test")
public class InputTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DBugData a = new DBugData("A",0);
        DBugData b = new DBugData("B",true);
        DBugData c = new DBugData("C",0.2,2);
        DBugInput i = new DBugInput(telemetry,gamepad1,a,b);

        i.run(this::isStopRequested);

        telemetry.addLine("A="+a.getValueI()+";B="+b.getValueB()+";C="+c.getValueD());
        telemetry.update();
        Thread.sleep(5000);
    }
}
