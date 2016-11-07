package me.jouin.lionel.tarkigates.ui.gates;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.R;
import me.jouin.lionel.tarkigates.core.Component;
import me.jouin.lionel.tarkigates.ui.ComponentUI;
import me.jouin.lionel.tarkigates.ui.WireUI;

/**
 * Created by lione on 05/11/2016.
 */

public class NotGateUI extends ComponentUI {

    public int inX;
    public int inY;

    public NotGateUI(int x, int y) {
        super(x, y);
        imgId = R.drawable.not;
        imgIdActivated = R.drawable.not;
        height = Positions.getInstance().logicGateHeight;
        width = Positions.getInstance().logicGateWidth;
        inX = 0;
        inY = 0;
        outX = 0;
        outY = 0;
    }

    @Override
    public void addWires(int startX, int startY) {
        int stopX = getInX();
        int stopY = getInY();
        wires.add(new WireUI(startX, startY, stopX, stopY));
    }

    public int getInX() {
        return x+inX;
    }

    public int getInY() {
        return y+inY;
    }
}
