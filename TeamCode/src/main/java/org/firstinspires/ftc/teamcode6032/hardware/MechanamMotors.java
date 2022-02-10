package org.firstinspires.ftc.teamcode6032.hardware;

import org.firstinspires.ftc.teamcode6032.drive.Pos;

public class MechanamMotors {
    private static final boolean LEFT_MOTORS_FORWARD = false;

    private final HardwareManager hardware;

    private final DCMotorWrapper fl;
    private final DCMotorWrapper fr;
    private final DCMotorWrapper bl;
    private final DCMotorWrapper br;

    private final double motorsRotation;

    public static final String MOTORS_ID = "drive";
    public static class MotorIdPostfix {
        public static final String FL = "-fl";
        public static final String FR = "-fr";
        public static final String BL = "-bl";
        public static final String BR = "-br";
    }


    public MechanamMotors(HardwareManager hardwareIn, double motorsRotationIn) {
        hardware = hardwareIn;
        fl = hardware.getMotor(MOTORS_ID+MotorIdPostfix.FL, LEFT_MOTORS_FORWARD);
        bl = hardware.getMotor(MOTORS_ID+MotorIdPostfix.BL, LEFT_MOTORS_FORWARD);
        fr = hardware.getMotor(MOTORS_ID+MotorIdPostfix.FR, !LEFT_MOTORS_FORWARD);
        br = hardware.getMotor(MOTORS_ID+MotorIdPostfix.BR, !LEFT_MOTORS_FORWARD);
        motorsRotation = motorsRotationIn;
    }

    public void setPower(Pos vel) {
//        System.out.println("MSP [\n\t"+vel.x+"\n\t"+vel.y+"\n\t"+vel.r+"\n];");

        Pos velR = Pos.rot(vel,-motorsRotation,true);

        // + on +fwd (+y)
        // +FL on +strafe (+x)
        // +R on +rot (ccw)
        double strafe = velR.x, fwd = -velR.y, rot = -velR.r;

        fl.setPower(fwd - strafe - rot);
        fr.setPower(fwd + strafe + rot);
        bl.setPower(fwd + strafe - rot);
        br.setPower(fwd - strafe + rot);
    }
}
