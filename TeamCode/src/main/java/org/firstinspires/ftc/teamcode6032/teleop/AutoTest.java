package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.drive.auto.Aut;
import org.firstinspires.ftc.teamcode6032.drive.auto.AutoContext;
import org.firstinspires.ftc.teamcode6032.drive.auto.AutoRunner;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareCommon;

@Autonomous(name = "TEST")
public class AutoTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        HardwareCommon hardware = new HardwareCommon(hardwareMap, this::getRuntime);

        AutoRunner R = new AutoRunner(hardware);
        R.runAuto(this::auto);
    }

    private void auto(AutoContext X) {
        Aut.driveStart(X);

        Aut.assertPos(X,new Vec(0,0,0));
        Aut.goTo(X,new Vec(0,12,0));

        Aut.wait(X,1.0);

        Aut.goTo(X,new Vec(0,0,0));

        Aut.wait(X,0.3);
        Aut.driveStop(X);
    }
}
