package org.firstinspires.ftc.teamcode6032.drive;


public class Vec {
    public static final Vec ZERO = new Vec(0,0,0);

    public final double x;
    public final double y;
    public final double r;

    public Vec(double xIn, double yIn, double rIn) {
        x = xIn;
        y = yIn;
        r = rIn;
    }

    public static Vec add(Vec...poss) {
        if (poss.length == 0) throw new IllegalArgumentException("Pos.add must have at least one argument");

        double x = 0, y = 0, r = 0;
        for (Vec p : poss) {
            x += p.x;
            y += p.y;
            r += p.r;
        }
        return new Vec(x,y,r);
    }
    public static Vec sub(Vec a, Vec b) {
        return Vec.add(a, Vec.mul(b,-1));
    }
    public static Vec mul(Vec p, double v) {
        return new Vec(
            p.x * v, p.y * v, p.r * v
        );
    }
    public static Vec mulComp(Vec a, Vec b) {
        return new Vec(a.x*b.x,a.y*b.y,a.r*b.r);
    }
    public static Vec normLoc(Vec p) {
        double len = locLen(p);
        if (len < 0.001) return Vec.ZERO;
        return mul(p,1/len);
    }
    public static Vec normMechanam(Vec p) {
        double lenT = Math.max(Math.abs(p.x),Math.abs(p.y)), lenR = Math.abs(p.r);
        if (lenT < 0.001) lenT = 0; else lenT = 1.0/lenT;
        if (lenR < 0.001) lenR = 0; else lenR = 1.0/lenR;
        return mulComp(p, new Vec(lenT,lenT,lenR));
    }
    public static Vec minCutoff(Vec p, double cutoff, double cutoffR) {
        double lenT = Math.max(Math.abs(p.x),Math.abs(p.y)), lenR = Math.abs(p.r);
        if (lenT < cutoff) lenT = 0; else lenT = 1;
        if (lenR < cutoffR) lenR = 0; else lenR = 1;
        return mulComp(p, new Vec(lenT,lenT,lenR));
    }
    public static double locLen(Vec p) {
        return Math.sqrt(dot(p,p));
    }
    public static Vec rot(Vec p, double angle, boolean dontChangeAngle) {
        return new Vec(
                Math.cos(angle) * p.x - Math.sin(angle) * p.y,
                Math.sin(angle) * p.x + Math.cos(angle) * p.y,
                p.r + (dontChangeAngle ? 0 : angle)
        );
    }
    public static double dot(Vec a, Vec b) {
        return a.x*b.x + a.y*b.y;
    }
    public static Vec rot(Vec p, double angle) {
        return rot(p,angle,false);
    }

    public static Vec project(Vec source, Vec target) {
        Vec targetNormNoRot = Vec.normLoc(new Vec(target.x,target.y,0));
        return Vec.mul(targetNormNoRot, Vec.dot(targetNormNoRot,source));
    }

    public double getRotCloseTo0() {
        return r - Math.round(r/(Math.PI*2))*(Math.PI*2);
    }

    public Vec copy() {
        return new Vec(x,y,r);
    }

}
