package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.hardware.subassembelyControl.core.MechanamMotors;
import org.firstinspires.ftc.teamcode6032.hardware.subassembelyControl.core.OdometryWheels;
import org.firstinspires.ftc.teamcode6032.hardware.wrapper.CRServoWrapper;
import org.firstinspires.ftc.teamcode6032.hardware.wrapper.DCMotorWrapper;
import org.firstinspires.ftc.teamcode6032.hardware.wrapper.DeadWheelWrapper;
import org.firstinspires.ftc.teamcode6032.hardware.wrapper.ServoWrapper;

import java.util.ArrayList;
import java.util.List;

public class HardwareManager {
    // TODO: track motor information

    public List<IMotorInfo> motorInfoList = new ArrayList<>();
    public List<DeadWheelWrapper> deadWheelList = new ArrayList<>();
    public MechanamMotors mechanamMotors = null;
    public OdometryWheels odometryWheels = null;

    public HardwareMap map;

    public HardwareManager(HardwareMap mapIn) {
        map = mapIn;
    }

    public DCMotorWrapper getMotor(String id, boolean reverse) {
        DCMotorWrapper motor = new DCMotorWrapper(map.get(DcMotor.class, id),id,reverse);
        motorInfoList.add(motor);
        return motor;
    }
    public ServoWrapper getServo(String id, boolean reverse) {
        ServoWrapper motor = new ServoWrapper(map.get(Servo.class, id),id,reverse);
        motorInfoList.add(motor);
        return motor;
    }
    public CRServoWrapper getCRServo(String id, boolean reverse) {
        CRServoWrapper motor = new CRServoWrapper(map.get(CRServo.class, id),id,reverse);
        motorInfoList.add(motor);
        return motor;
    }
    public DeadWheelWrapper getDeadWheel(String id, boolean reverse, double scale) {
        DeadWheelWrapper motor = new DeadWheelWrapper(map.get(DcMotor.class, id),id,reverse,scale);
        deadWheelList.add(motor);
        return motor;
    }
    public DeadWheelWrapper getDeadWheel(String id, boolean reverse) {
        // 1 in radius omni wheel, 8192 ticks/rev.
        return  getDeadWheel(id,reverse,Math.PI*2f/8192f);
    }
    public MechanamMotors getMechanam(double rotation) {
        if (mechanamMotors == null)
            mechanamMotors =  new MechanamMotors(this,rotation);
        return mechanamMotors;
    }
    public OdometryWheels getOdometry(Vec offset, double radius) {
        if (odometryWheels == null)
            odometryWheels = new OdometryWheels(this,offset,radius);
        else
            odometryWheels.updateSize(offset,radius);
        return odometryWheels;
    }


}
