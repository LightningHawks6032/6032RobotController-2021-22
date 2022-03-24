package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathFollower;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.InitCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.TargetCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.WaitAbsoluteCommand;
import org.firstinspires.ftc.teamcode6032.drive.pathFollow.WaitCommand;
import org.firstinspires.ftc.teamcode6032.hardware.HardwareCommon;
import org.firstinspires.ftc.teamcode6032.hardware.subassembelyControl.core.MechanamMotors;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@Disabled
@Autonomous(name = "TestPath", group = TeleOpNames.TEST_GROUP)
public class TestPathAuto extends LinearOpMode {

    private PathFollower follower;
    private HardwareCommon chi;

    @Override
    public void runOpMode() throws InterruptedException {
        chi = new HardwareCommon(hardwareMap, this::getRuntime);
        MechanamMotors mechanam = chi.mechanam;
        PosIntegrator posIntegrator = chi.posIntegrator;

        follower = new PathFollower(posIntegrator, mechanam);

        List<PathCommand> path = new ArrayList<>();
        path.add(new InitCommand(Vec.ORIGIN));
        path.add(new TargetCommand(new Vec(0,0,Math.PI/2)));
        path.add(new TargetCommand(new Vec(0,60,0)));
        path.add(new WaitAbsoluteCommand(2));
//        path.add(new ExecCodeCommand(()->chi.duckSpinner.setEnabled(1)));

        path.add(new WaitCommand(3));
//        path.add(new ExecCodeCommand(()->chi.duckSpinner.setEnabled(0)));
        path.add(new WaitCommand(3));
        path.add(new TargetCommand(new Vec(0,12,Math.PI)));
        path.add(new WaitCommand(5));
        path.add(new TargetCommand(new Vec(0,24,Math.PI)));

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
