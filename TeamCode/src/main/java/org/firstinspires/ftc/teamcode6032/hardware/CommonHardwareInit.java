package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;

public class CommonHardwareInit {
    public final MechanamMotors mechanam;
    public final HardwareManager hardware;
    public final OdometryWheels odometry;
    public final PosIntegrator posIntegrator;

//    public final DuckSpinner duckSpinner;

    public CommonHardwareInit(HardwareMap hardwareMap) {
        hardware = new HardwareManager(hardwareMap);
        mechanam = hardware.getMechanam(0);
        odometry = hardware.getOdometry(new Pos(0f,0f,0f), 5.125f);
        posIntegrator = new PosIntegrator(odometry);
//        duckSpinner = new DuckSpinner(hardware);
    }
}
