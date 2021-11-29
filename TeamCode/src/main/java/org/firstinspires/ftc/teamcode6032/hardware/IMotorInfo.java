package org.firstinspires.ftc.teamcode6032.hardware;

public interface IMotorInfo {
    String getMotorId();
    double getPower();
    double getPos();
    boolean getIsReversed();
    MotorInfoType getType();
}
