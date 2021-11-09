package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode6032.drive.Pos;

import java.util.ArrayList;
import java.util.List;

public class HardwareManager {
    // TODO: track motor information

    public List<IMotorInfo> motorInfoList = new ArrayList<>();
    public List<DeadWheel> deadWheelList = new ArrayList<>();
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
    public DeadWheel getDeadWheel(String id, boolean reverse) {
        DeadWheel motor = new DeadWheel(map.get(DcMotor.class, id),id,reverse);
        deadWheelList.add(motor);
        return motor;
    }
    public MechanamMotors getMechanam() {
        if (mechanamMotors == null)
            mechanamMotors =  new MechanamMotors(this);
        return mechanamMotors;
    }
    public OdometryWheels getOdometry(Pos offset, double radius) {
        if (odometryWheels == null)
            odometryWheels = new OdometryWheels(this,offset,radius);
        else
            odometryWheels.updateSize(offset,radius);
        return odometryWheels;
    }


}
