package org.firstinspires.ftc.teamcode6032.debug.dbuginput;

public class DBugData {
    protected int     valueI;
    protected boolean valueB;
    protected double  valueD;

    protected int decimals = 0;

    public final DBugDataType type;

    public final String name;

    public DBugData(String name, int initial) {
        valueI = initial;
        type = DBugDataType.INT;
        this.name = name;
    }
    public DBugData(String name, double initial,int decimals) {
        valueD = initial;
        type = DBugDataType.DOUBLE;
        this.name = name;
        this.decimals = decimals;
    }
    public DBugData(String name, boolean initial) {
        valueB = initial;
        type = DBugDataType.BOOL;
        this.name = name;
    }

    public double  getValueD() { return valueD; }
    public int     getValueI() { return valueI; }
    public boolean getValueB() { return valueB; }

    public String valueString() {
        switch (type) {
            case INT: return Integer.toString(valueI);
            case DOUBLE: return Double.toString(valueD);
            case BOOL: return valueB ? "true" : "false";
            default: return "<NO DATA>";
        }
    }

    public enum DBugDataType {
        INT,
        DOUBLE,
        BOOL
    }
}
