package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.BranchCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.ExecCodeCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.NoOpCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathFollower;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.InitCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.TargetCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.WaitAbsoluteCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.WaitCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.WaitConditionCommand;
import org.firstinspires.ftc.teamcode6032.hardware.CommonHardwareInit;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name = "TestPath", group = TeleOpNames.TEST_GROUP)
public class TestPathAuto extends LinearOpMode {

    private PathFollower follower;
    private CommonHardwareInit chi;

    @Override
    public void runOpMode() throws InterruptedException {
        chi = new CommonHardwareInit(hardwareMap);
        MechanamMotors mechanam = chi.mechanam;
        PosIntegrator posIntegrator = chi.posIntegrator;

        follower = new PathFollower(posIntegrator, mechanam);

        List<PathCommand> path = new ArrayList<>();
        path.add(new InitCommand(Pos.ORIGIN));
//        path.add(new TargetCommand(new Pos(0,0,Math.PI/2)));
//        path.add(new TargetCommand(new Pos(0,60,0)));
        path.add(new WaitAbsoluteCommand(2));
//        path.add(new ExecCodeCommand(()->chi.duckSpinner.setEnabled(1)));

        path.add(new WaitCommand(3));
//        path.add(new ExecCodeCommand(()->chi.duckSpinner.setEnabled(0)));
        path.add(new WaitCommand(3));
//        path.add(new TargetCommand(new Pos(0,12,Math.PI)));
//        path.add(new WaitCommand(5));
//        path.add(new TargetCommand(new Pos(0,24,Math.PI)));

//        List<PathCommand> path = new ArrayList<>();
//        path.add(new InitCommand(Pos.ORIGIN));
//        path.add(new BranchCommand(()->BranchCommand.callbackOut(0), new PathCommand[0]));
//        path.add(new NoOpCommand());
//        path.add(new WaitConditionCommand(()->true));
//        path.add(new TargetCommand(new Pos(0,12,0)));
//        path.add(new TargetCommand(new Pos(0,12,Math.PI)));
//        path.add(new TargetCommand(new Pos(0,24,Math.PI)));
//        path.add(new WaitCommand(5));
//        path.add(new TargetCommand(new Pos(0,0,Math.PI)));

        follower.setCommands(path);

        waitForStart();
        while (getRuntime() < 10.0) {
            time = getRuntime();
            update();
            chi.posIntegrator.updatePos();
            //noinspection BusyWait
            Thread.sleep(1);
        }
    }

    private void update() {
        if (follower.isComplete()) {
            requestOpModeStop();
            return;
        }
        telemetry.addLine(follower.state);
        telemetry.addLine("x:"+chi.posIntegrator.currentPos.x);
        telemetry.addLine("y:"+chi.posIntegrator.currentPos.y);
        telemetry.addLine("r:"+chi.posIntegrator.currentPos.r);
        telemetry.update();
        follower.update(time);
    }
}
