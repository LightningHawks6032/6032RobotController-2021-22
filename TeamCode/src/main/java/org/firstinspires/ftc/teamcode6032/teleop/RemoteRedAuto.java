package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode6032.drive.pathFollow.PathFollower;
import org.firstinspires.ftc.teamcode6032.hardware.CommonHardwareInit;

@Autonomous(name = "Remote - RedAlliance")
public class RemoteRedAuto extends LinearOpMode {

    private PathFollower follower;

    @Override
    public void runOpMode() throws InterruptedException {
        CommonHardwareInit chi = new CommonHardwareInit(hardwareMap);

        follower = new PathFollower(chi.posIntegrator, chi.mechanam);
        follower.setCommands(AutoCommon.remoteEventPath(chi));

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
        follower.update(time);

    }
}
