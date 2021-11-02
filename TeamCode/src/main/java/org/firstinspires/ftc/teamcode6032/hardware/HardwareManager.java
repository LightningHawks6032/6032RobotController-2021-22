package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwareManager {

    public HardwareMap map;

    public HardwareManager(HardwareMap mapIn) {
        map = mapIn;
    }

    public DCMotorWrapper getMotor(String id, boolean reverse) {
        DcMotor motor = map.get(DcMotor.class, id);
        motor.setDirection(reverse ?
                DcMotorSimple.Direction.REVERSE :
                DcMotorSimple.Direction.FORWARD
        );
        return new DCMotorWrapper(motor);
    }


}
