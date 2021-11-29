package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoWrapper implements IMotorInfo {
    public final Servo motor;
    public final String motorId;

    public ServoWrapper(Servo motorIn, String id, boolean reverse) {
        motor = motorIn;
        motorId = id;
        motor.setDirection(reverse ?
                Servo.Direction.REVERSE :
                Servo.Direction.FORWARD
        );
    }

    public void setTarget(double target) { motor.setPosition(target); }
    public void setRange(double min, double max) { motor.scaleRange(min, max); }

    public double getPos() { return motor.getPosition(); }
    public boolean getIsReversed() { return motor.getDirection() == Servo.Direction.REVERSE; }
    public String getMotorId() { return motorId; }

    @Override
    public double getPower() { return 0; }

    @Override
    public MotorInfoType getType() { return MotorInfoType.SERVO; }
}
