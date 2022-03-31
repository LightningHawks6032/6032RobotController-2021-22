package org.firstinspires.ftc.teamcode6032.debug.dbuginput;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode6032.util.GeneratorFn;

public class DBugInput {
    private final Telemetry telemetry;
    private final Gamepad gamepad;
    private final DBugInputLn[] inputLns;

    private int currentLine = 0;

    public DBugInput(Telemetry telemetry, Gamepad gamepad, DBugData ...dataIn) {
        this.telemetry = telemetry;
        this.gamepad = gamepad;
        inputLns = new DBugInputLn[dataIn.length];
        int i = 0;
        for (DBugData ln : dataIn)
            inputLns[i++] = new DBugInputLn(ln);
    }

    public void run(GeneratorFn<Boolean> isStopped) {
        int xL = 0, yL = 0, dL = 0, x, y, d;
        boolean nL = false, n, kL = false, k;
        while (!isStopped.get() && currentLine < inputLns.length) {
            x = (gamepad.dpad_left?1:0)-(gamepad.dpad_right?1:0);
            y = (gamepad.dpad_up?1:0)-(gamepad.dpad_down?1:0);
            d = (gamepad.right_bumper?1:0)-(gamepad.left_bumper?1:0);
            n = gamepad.x;
            k = gamepad.a;

            if (x == 0) xL = 0;
            if (y == 0) yL = 0;
            if (d == 0) dL = 0;

            DBugInputLn ln = inputLns[currentLine];
            ln.input(x-xL,y-yL, n && !nL, d-dL);

            telemetry.addLine(ln.renderUsageInstructions());
            telemetry.addLine("⸺⸺⸺⸺⸺⸺⸺⸺");
            for (int i = 0; i < inputLns.length; i++) {
                String z;
                if (i == currentLine)
                    z=inputLns[i].renderFocused();
                else
                    z=inputLns[i].renderSummary();
                telemetry.addLine(z);
            }
            telemetry.update();

            if (k && !kL)
                currentLine++;

            xL = x;
            yL = y;
            nL = n;
            dL = d;
            kL = k;
        }
    }
}
