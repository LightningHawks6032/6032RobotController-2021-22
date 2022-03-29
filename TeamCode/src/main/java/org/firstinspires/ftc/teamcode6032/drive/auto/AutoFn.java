package org.firstinspires.ftc.teamcode6032.drive.auto;

@FunctionalInterface
public interface AutoFn {
    void run(AutoContext X) throws InterruptedException;
}
