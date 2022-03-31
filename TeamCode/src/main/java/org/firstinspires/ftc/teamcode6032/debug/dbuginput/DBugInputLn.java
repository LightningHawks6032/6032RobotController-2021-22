package org.firstinspires.ftc.teamcode6032.debug.dbuginput;

public class DBugInputLn {
    private DBugData data;
    private int digitFocused = 0;
    protected DBugInputLn(DBugData data) {
        this.data = data;
    }

    public void input(int x, int y, boolean negate, int d) {
        int placeValue = (int)Math.pow(10,digitFocused);
        switch (data.type) {
            case INT:
                digitFocused = Math.max(0,digitFocused+x);
                int signI = data.valueI < 0 ? -1 : 1;
                data.valueI *= signI;
                int digitI = (data.valueI/placeValue) % 10;
                data.valueI -= digitI*placeValue;
                digitI = (digitI+y+10)%10;
                data.valueI += digitI*placeValue;
                data.valueI *= signI;
                if (negate) data.valueI *= -1;
                break;
            case DOUBLE:
                digitFocused += x;
                data.decimals = Math.max(0,Math.max(-digitFocused,data.decimals) + d);
                int signD = data.valueD < 0 ? -1 : 1;
                data.valueD *= signD;
                int digitD = (int)Math.floor((data.valueD/placeValue) % 10);
                data.valueD -= digitD*placeValue;
                digitD = (digitD+y+10)%10;
                data.valueD += digitD*placeValue;
                data.valueD *= signD;
                if (negate) data.valueD *= -1;
                break;
            case BOOL:
                if (x != 0 || y != 0 || negate)
                    data.valueB = !data.valueB;
                break;
        }
    }

    private String renderFocusedFinalize(String contents) {
        String ln = "⸺⸺⸺⸺⸺⸺⸺⸺";
        return ln+"\n>> "+renderSummary()+"\n"+contents+"\n"+ln;
    }
    public String renderFocused() {
        switch (data.type) {
            case INT:
                int vi = Math.abs(data.valueI);
                StringBuilder d = new StringBuilder(data.valueI < 0 ? "-" : "");
                for (int i = Math.max(digitFocused,(int)Math.floor(Math.log10(vi))); i >= 0; i--) {
                    if (i == digitFocused) d.append("[");
                    d.append((int)(vi / Math.pow(10, i)) % 10);
                    if (i == digitFocused) d.append("]");
                }
                return renderFocusedFinalize(d.toString());
            case DOUBLE:
                double vd = data.valueD;
                StringBuilder g = new StringBuilder();
                g.append("decimals: ");
                g.append(data.decimals);
                g.append("\n");
                g.append(data.valueI < 0 ? "-" : "");
                for (int i = Math.max(digitFocused,(int)Math.floor(Math.log10(vd))); i >= -data.decimals; i--) {
                    if (i == -1) g.append(".");
                    if (i == digitFocused) g.append("[");
                    g.append((int)(vd / Math.pow(10, i)) % 10);
                    if (i == digitFocused) g.append("]");
                }
                return renderFocusedFinalize("");
            case BOOL:
                boolean vb = data.valueB;
                return renderFocusedFinalize(
                        (vb?"> ":"")+"true\n"+
                                (vb?"":"> ")+"false"
                );
            default: return "<NO TYPE, CAN'T EDIT>";
        }
    }
    public String renderSummary() {
        String tString = "<nullType>";
        switch (data.type) {
            case INT:    tString="int";    break;
            case BOOL:   tString="bool";   break;
            case DOUBLE: tString="double"; break;
        }
        return "["+tString+"] \""+data.name+"\": "+data.valueString();
    }
    public String renderUsageInstructions() {
        switch (data.type) {
            case INT:
                return (""+
                        "⇅: change digit; ⇄: next/prev digit;\n"+
                        "x: negative; a: next line"
                );
            case DOUBLE:
                return (""+
                        "⇅: change digit; ⇄: next/prev digit;\n"+
                        "br/lr: [in/de]crease num decimals\n"+
                        "x: negative; a: next line"
                );
            case BOOL:
                return (""+
                        "⇅/⇄/x: switch value; a: next line"
                );
        }
        return "XXX";
    }
}
