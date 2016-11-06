package me.jouin.lionel.tarkigates.ui.gates;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.ui.ComponentUI;

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
        inAX = x;
        inAY = y;
        inBX = x;
        inBY = y;
    }

}
