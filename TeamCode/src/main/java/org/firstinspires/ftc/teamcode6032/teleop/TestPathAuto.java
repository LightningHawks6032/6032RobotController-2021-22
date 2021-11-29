package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathFollower;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.InitCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.TargetCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.WaitCommand;
import org.firstinspires.ftc.teamcode6032.hardware.CommonHardwareInit;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

@Autonomous(name = "TestPath", group = TeleOpNames.TEST_GROUP)
public class TestPathAuto extends LinearOpMode {

    private PathFollower follower;

    @Override
    public void runOpMode() throws InterruptedException {
        CommonHardwareInit chi = new CommonHardwareInit(hardwareMap);
        MechanamMotors mechanam = chi.mechanam;
        PosIntegrator posIntegrator = chi.posIntegrator;

        PathFollower follower = new PathFollower(posIntegrator, mechanam);


        PathCommand[] path = new PathCommand[] {
                new InitCommand(Pos.ORIGIN),
                new TargetCommand(Pos.pos(0,12,0)),
                new TargetCommand(Pos.pos(0,12,Math.PI)),
                new TargetCommand(Pos.pos(0,24,Math.PI)),
                new WaitCommand(5),
                new TargetCommand(Pos.pos(0,0,Math.PI))
        };

        follower.setCommands(path);

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
