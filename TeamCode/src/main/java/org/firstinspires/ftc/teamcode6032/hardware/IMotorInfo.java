package org.firstinspires.ftc.teamcode6032.hardware;

public interface IMotorInfo {
    String getMotorId();
    double getPower();
    int getPos();
    boolean getIsReversed();
}
