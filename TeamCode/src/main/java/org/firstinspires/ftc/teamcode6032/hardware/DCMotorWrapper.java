package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DCMotorWrapper implements IMotorInfo {
    public final DcMotor motor;
    public final String motorId;

    public DCMotorWrapper(DcMotor motorIn, String id, boolean reverse) {
        motor = motorIn;
        motorId = id;
        motor.setDirection(reverse ?
                DcMotorSimple.Direction.REVERSE :
                DcMotorSimple.Direction.FORWARD
        );
        useUntrackedMode();
    }

    public void useTargetMode() { motor.setMode(DcMotor.RunMode.RUN_TO_POSITION); }
    public void useUntrackedMode() { motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); }
    public void useTrackedMode() { motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); }


    public void setPower(double power) {
        motor.setPower(power);
    }
    public void setTarget(int target) { motor.setTargetPosition(target); }

    public double getPower() { return motor.getPower(); }
    public int getPosTicks() { return motor.getCurrentPosition(); }
    public double getPos() { return getPosTicks(); }
    public boolean getIsReversed() { return motor.getDirection() == DcMotorSimple.Direction.REVERSE; }
    public String getMotorId() { return motorId; }

    @Override
    public MotorInfoType getType() { return MotorInfoType.DC_MOTOR; }
}
