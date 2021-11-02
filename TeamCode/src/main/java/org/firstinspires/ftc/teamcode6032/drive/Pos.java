package org.firstinspires.ftc.teamcode6032.drive;


public class Pos {
    public static final Pos ORIGIN = new Pos(0,0,0,0);

    public final int secondsPower;
    public final double x;
    public final double y;
    public final double r;

    public Pos(double xIn, double yIn, double rIn, int secondsPowerIn) {
        x = xIn;
        y = yIn;
        r = rIn;
        secondsPower = secondsPowerIn;
    }

    public static Pos add(Pos ...poss) {
        if (poss.length == 0) return null;

        int secondsPower = poss[0].secondsPower;
        double x = 0, y = 0, r = 0;
        for (Pos p : poss) {
            x += p.x;
            y += p.y;
            r += p.r;
            if (secondsPower != p.secondsPower)
                throw new Error("robot pos addition unit mix-up: `(in|rad)/s^"+secondsPower+"` and `(in|rad)/s^"+p.secondsPower+"`.");
        }
        return new Pos(x,y,r,secondsPower);
    }
    public static Pos mul(Pos p, double v, int scalarSecondsPower) {
        return new Pos(
            p.x * v, p.y * v, p.r * v,
            p.secondsPower + scalarSecondsPower
        );
    }


    public boolean isPosition() { return secondsPower == -0; }
    public boolean isVelocity() { return secondsPower == -1; }
    public boolean isAcceleration() { return secondsPower == -2; }

}
