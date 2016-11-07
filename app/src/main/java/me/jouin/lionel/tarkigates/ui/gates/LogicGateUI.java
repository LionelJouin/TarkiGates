package me.jouin.lionel.tarkigates.ui.gates;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.ui.ComponentUI;
import me.jouin.lionel.tarkigates.ui.WireUI;

/**
 * Created by lione on 05/11/2016.
 */

public abstract class LogicGateUI extends ComponentUI {

    public int inAX;
    public int inAY;

    public int inBX;
    public int inBY;

    public LogicGateUI(int x, int y) {
        super(x, y);
        height = Positions.getInstance().logicGateHeight;
        width = Positions.getInstance().logicGateWidth;
        inAX = 0;
        inAY = (int) Math.round(height*0.40);
        inBX = 0;
        inBY = (int) Math.round(height*0.60);
        outX = width;
        outY = height/2;
    }

    public int getInAX() {
        return x+inAX;
    }

    public int getInAY() {
        return y+inAY;
    }

    public int getInBX() {
        return x+inBX;
    }

    public int getInBY() {
        return y+inBY;
    }

    public void addWires(int startX, int startY) {
        int stopX;
        int stopY;
        if (wires.size() == 0) {
            stopX = getInAX();
            stopY = getInAY();
        } else {
            stopX = getInBX();
            stopY = getInBY();
        }
        wires.add(new WireUI(startX, startY, stopX, stopY));
    }
}
