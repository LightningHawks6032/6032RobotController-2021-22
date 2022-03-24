package org.firstinspires.ftc.teamcode6032.drive.auto;

import org.firstinspires.ftc.teamcode6032.hardware.HardwareCommon;

public class AutoContext {
    public final AutoRunner runner;
    private final AwaitFn await;
    public AutoContext(AutoRunner runner, AwaitFn await) {
        this.runner = runner;
        this.await = await;

        hardware = runner.hardware;
    }
    public void await(AwaitConditionFn condition) {
        await.until(condition);
    }

    public final HardwareCommon hardware;
}
