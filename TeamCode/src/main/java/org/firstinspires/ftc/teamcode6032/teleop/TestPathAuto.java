package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathFollower;
import org.firstinspires.ftc.teamcode6032.hardware.CommonHardwareInit;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;
import org.firstinspires.ftc.teamcode6032.hardware.OdometryWheels;

import java.nio.file.Path;

@Autonomous(name = "TestPath", group = TeleOpNames.TEST_GROUP)
public class TestPathAuto extends LinearOpMode {

    private PathFollower follower;

    @Override
    public void runOpMode() throws InterruptedException {
        CommonHardwareInit chi = new CommonHardwareInit(hardwareMap);
        MechanamMotors mechanam = chi.mechanam;
        PosIntegrator posIntegrator = chi.posIntegrator;

        PathFollower follower = new PathFollower(posIntegrator, mechanam);




        waitForStart();
        while (getRuntime() < 10.0) {
            time = getRuntime();
            update();
            //noinspection BusyWait
            Thread.sleep(1);
        }
    }

    private void update() {
        follower.update(time);
    }
}
