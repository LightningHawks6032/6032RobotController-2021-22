package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode6032.debug.comms.RemoteDebuggerThread;

@TeleOp(name = "Start Remote Debugger", group = "debug")
public class StartRemoteDebuggerTeleop extends LinearOpMode {
    @Override
    public void runOpMode() {
        RemoteDebuggerThread.startThread();
    }
}
