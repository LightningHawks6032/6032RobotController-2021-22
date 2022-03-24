package org.firstinspires.ftc.teamcode6032.drive.auto;

@FunctionalInterface
public interface AwaitFn {
    void until(AwaitConditionFn condition);
}
