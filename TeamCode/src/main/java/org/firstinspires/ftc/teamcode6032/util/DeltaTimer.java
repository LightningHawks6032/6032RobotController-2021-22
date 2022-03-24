package org.firstinspires.ftc.teamcode6032.util;

public class DeltaTimer {
    private double time;
    private final GeneratorFn<Double> timeIn;
    public DeltaTimer(GeneratorFn<Double> timerIn) {
        time = timerIn.get();
        this.timeIn = timerIn;
    }
    public double get() {
        double lastT = time;
        time = timeIn.get();
        return time - lastT;
    }
}
