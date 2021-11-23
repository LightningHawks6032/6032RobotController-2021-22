package org.firstinspires.ftc.teamcode6032.drive;


public class Pos {
    public static final Pos ORIGIN = new Pos(0,0,0,0);
    public static final Pos STATIONARY = new Pos(0,0,0,-1);

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
        if (poss.length == 0) throw new IllegalArgumentException("Pos.add must have at least one argument");

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
    public static Pos sub(Pos a, Pos b) {
        return Pos.add(a,Pos.mul(b,-1));
    }
    public static Pos mul(Pos p, double v, int scalarSecondsPower) {
        return new Pos(
            p.x * v, p.y * v, p.r * v,
            p.secondsPower + scalarSecondsPower
        );
    }
    public static Pos mul(Pos p, double v) { return mul(p,v,0); }
    public static Pos normLoc(Pos p) {
        return mul(p,1/locLen(p));
    }
    public static Pos normMechanam(Pos p) {
        return mul(p, 1/Math.max(Math.max(p.x,p.y),p.r));
    }
    public static double locLen(Pos p) {
        return Math.sqrt(dot(p,p));
    }
    public static Pos rot(Pos p, double angle, boolean dontChangeAngle) {
        return new Pos(
                Math.cos(angle) * p.x + Math.sin(angle) * p.y,
                -Math.sin(angle) * p.x + Math.cos(angle) * p.y,
                p.r + (dontChangeAngle ? 0 : angle),
                p.secondsPower
        );
    }
    public static double dot(Pos a, Pos b) {
        return a.x*b.x + a.y*b.y;
    }
    public static Pos rot(Pos p, double angle) {
        return rot(p,angle,false);
    }

    public static Pos project(Pos source, Pos target) {
        Pos targetNormNoRot = Pos.normLoc(new Pos(target.x,target.y,0,target.secondsPower));
        return Pos.mul(targetNormNoRot,Pos.dot(targetNormNoRot,source));
    }

    public double getRotCloseTo0() {
        return r - Math.round(r/(Math.PI*2))*(Math.PI*2);
    }

    public boolean isNotPosition() { return secondsPower != -0; }
    public boolean isNotVelocity() { return secondsPower != -1; }
    public boolean isNotAcceleration() { return secondsPower != -2; }


    public Pos copy() {
        return new Pos(x,y,r,secondsPower);
    }
}
