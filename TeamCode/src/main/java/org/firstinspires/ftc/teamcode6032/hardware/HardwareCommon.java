package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode6032.drive.RobotTargetMover;
import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;
import org.firstinspires.ftc.teamcode6032.hardware.subassembelyControl.core.MechanamMotors;
import org.firstinspires.ftc.teamcode6032.hardware.subassembelyControl.core.OdometryWheels;
import org.firstinspires.ftc.teamcode6032.util.GeneratorFn;

public class HardwareCommon {
    public final MechanamMotors mechanam;
    public final HardwareManager hardware;
    public final OdometryWheels odometry;
    public final PosIntegrator posIntegrator;
    public final RobotTargetMover targetMover;

    private final GeneratorFn<Double> getTimeFn;
    private final GeneratorFn<Boolean> getStoppedFn;

    public final Telemetry telemetry;

    public HardwareCommon(HardwareMap hardwareMap, Telemetry telemetry, GeneratorFn<Double> getTimeFn, GeneratorFn<Boolean> getStoppedFn) {
        hardware = new HardwareManager(hardwareMap);
        mechanam = hardware.getMechanam(0);
        odometry = hardware.getOdometry(new Vec(0f,0f,0f), 5.125f);
        posIntegrator = new PosIntegrator(odometry);
        targetMover = new RobotTargetMover(posIntegrator, mechanam);

        this.getTimeFn = getTimeFn;
        this.getStoppedFn = getStoppedFn;

        this.telemetry = telemetry;
    }

    public double time() {
        return getTimeFn.get();
    }
    public boolean isStopped() {
        return getStoppedFn.get();
    }
}
