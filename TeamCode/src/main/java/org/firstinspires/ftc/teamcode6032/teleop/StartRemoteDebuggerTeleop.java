package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.debug.comms.RemoteDebuggerThread;

import java.util.logging.Logger;

@Disabled
@TeleOp(name = "Start Remote Debugger", group = "debug")
public class StartRemoteDebuggerTeleop extends LinearOpMode {
    @Override
    public void runOpMode() {
        Logger logger = Logger.getLogger("RemoteDebugger-Launcher");
        logger.info("Launching...");
        RemoteDebuggerThread.startThread();
        waitForStart();
    }
}
