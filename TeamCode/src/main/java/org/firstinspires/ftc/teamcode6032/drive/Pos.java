package org.firstinspires.ftc.teamcode6032.drive;


public class Pos {
    public static final Pos ORIGIN = new Pos(0,0,0);

    public final double x;
    public final double y;
    public final double r;

    public Pos(double xIn, double yIn, double rIn) {
        x = xIn;
        y = yIn;
        r = rIn;
    }

    public static Pos add(Pos ...poss) {
        if (poss.length == 0) throw new IllegalArgumentException("Pos.add must have at least one argument");

        double x = 0, y = 0, r = 0;
        for (Pos p : poss) {
            x += p.x;
            y += p.y;
            r += p.r;
        }
        return new Pos(x,y,r);
    }
    public static Pos sub(Pos a, Pos b) {
        return Pos.add(a,Pos.mul(b,-1));
    }
    public static Pos mul(Pos p, double v) {
        return new Pos(
            p.x * v, p.y * v, p.r * v
        );
    }
    public static Pos mulComp(Pos a, Pos b) {
        return new Pos(a.x*b.x,a.y*b.y,a.r*b.r);
    }
    public static Pos normLoc(Pos p) {
        double len = locLen(p);
        if (len < 0.001) return Pos.ORIGIN;
        return mul(p,1/len);
    }
    public static Pos normMechanam(Pos p) {
        double lenT = Math.max(Math.abs(p.x),Math.abs(p.y)), lenR = Math.abs(p.r);
        if (lenT < 0.001) lenT = 0; else lenT = 1.0/lenT;
        if (lenR < 0.001) lenR = 0; else lenR = 1.0/lenR;
        return mulComp(p, new Pos(lenT,lenT,lenR));
    }
    public static Pos minCutoff(Pos p,double cutoff,double cutoffR) {
        double lenT = Math.max(Math.abs(p.x),Math.abs(p.y)), lenR = Math.abs(p.r);
        if (lenT < cutoff) lenT = 0; else lenT = 1;
        if (lenR < cutoffR) lenR = 0; else lenR = 1;
        return mulComp(p, new Pos(lenT,lenT,lenR));
    }
    public static double locLen(Pos p) {
        return Math.sqrt(dot(p,p));
    }
    public static Pos rot(Pos p, double angle, boolean dontChangeAngle) {
        return new Pos(
                Math.cos(angle) * p.x - Math.sin(angle) * p.y,
                Math.sin(angle) * p.x + Math.cos(angle) * p.y,
                p.r + (dontChangeAngle ? 0 : angle)
        );
    }
    public static double dot(Pos a, Pos b) {
        return a.x*b.x + a.y*b.y;
    }
    public static Pos rot(Pos p, double angle) {
        return rot(p,angle,false);
    }

    public static Pos project(Pos source, Pos target) {
        Pos targetNormNoRot = Pos.normLoc(new Pos(target.x,target.y,0));
        return Pos.mul(targetNormNoRot,Pos.dot(targetNormNoRot,source));
    }

    public double getRotCloseTo0() {
        return r - Math.round(r/(Math.PI*2))*(Math.PI*2);
    }

    public Pos copy() {
        return new Pos(x,y,r);
    }

}
