package org.firstinspires.ftc.teamcode6032.drive.auto;

import org.firstinspires.ftc.teamcode6032.hardware.HardwareCommon;
import org.firstinspires.ftc.teamcode6032.util.DeltaTimer;

import java.util.ArrayList;
import java.util.List;

public class AutoRunner {
    public final HardwareCommon hardware;
    public DeltaTimer dt = null;

    public AutoRunner(HardwareCommon hardwareIn) {
        hardware = hardwareIn;

    }

    private final List<AutoLoopActivity> autoLoopActivities = new ArrayList<>();


    private void awaitUntil(AwaitConditionFn cond) {
        while (!cond.canContinue())
            loop();
    }

    private void loop() {
        double dt = this.dt.get();
        // TODO: move robot and stuff
        for (AutoLoopActivity activity : autoLoopActivities)
            activity.run(this, dt);
    }

    public void runAuto(AutoFn auto){
        dt = new DeltaTimer(hardware::time);
        auto.run(new AutoContext(this, this::awaitUntil));
    }
    public void addLoopActivity(AutoLoopActivity activity) {
        autoLoopActivities.add(activity);
    }
    public void removeLoopActivity(AutoLoopActivity activity) {
        autoLoopActivities.remove(activity);
    }
}
