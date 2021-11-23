package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;
import org.firstinspires.ftc.teamcode6032.drive.RobotTargetMover;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

public class PathFollower {
    private double time;
    protected final RobotTargetMover targetMover;


    public PathFollower(PosIntegrator integrator, MechanamMotors mechanam) {
        targetMover = new RobotTargetMover(integrator, mechanam);
    }

    public double getTime() { return time; }

    public void update(double time) {
        this.time = time;
    }


}
