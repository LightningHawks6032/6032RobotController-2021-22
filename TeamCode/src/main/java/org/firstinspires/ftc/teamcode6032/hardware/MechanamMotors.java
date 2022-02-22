package org.firstinspires.ftc.teamcode6032.hardware;

import org.firstinspires.ftc.teamcode6032.drive.Pos;

public class MechanamMotors {
    private final HardwareManager hardware;

    public final DCMotorWrapper fl;
    public final DCMotorWrapper fr;
    public final DCMotorWrapper bl;
    public final DCMotorWrapper br;

    private final double motorsRotation;


    public MechanamMotors(HardwareManager hardwareIn, double motorsRotationIn) {
        hardware = hardwareIn;
        fl = hardware.getMotor(HardwareIds.DRIVE_MOTOR_FL,  HardwareIds.DRIVE_MOTOR_REVERSE);
        bl = hardware.getMotor(HardwareIds.DRIVE_MOTOR_BL,  HardwareIds.DRIVE_MOTOR_REVERSE);
        fr = hardware.getMotor(HardwareIds.DRIVE_MOTOR_FR, !HardwareIds.DRIVE_MOTOR_REVERSE);
        br = hardware.getMotor(HardwareIds.DRIVE_MOTOR_BR, !HardwareIds.DRIVE_MOTOR_REVERSE);
        motorsRotation = motorsRotationIn;
    }

    public void setPower(Pos vel) {
//        System.out.println("MSP [\n\t"+vel.x+"\n\t"+vel.y+"\n\t"+vel.r+"\n];");

        Pos velR = Pos.rot(vel,-motorsRotation,true);

        // + on +fwd (+y)
        // +FL on +strafe (+x)
        // +R on +rot (ccw)
//        double strafe = velR.x, fwd = -velR.y, rot = -velR.r;
        double strafe = velR.x, fwd = velR.y, rot = velR.r;

        fl.setPower(fwd + strafe - rot);
        fr.setPower(fwd - strafe + rot);
        bl.setPower(fwd - strafe - rot);
        br.setPower(fwd + strafe + rot);
    }
}
