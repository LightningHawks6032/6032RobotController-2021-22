package org.firstinspires.ftc.teamcode6032.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class ManualOpMode extends OpMode {
    private boolean stopRequested = false;

    @Override
    public void stop() {
        super.stop();
        stopRequested = true;
    }

    public boolean isStopRequested() {
        return stopRequested;
    }
}
