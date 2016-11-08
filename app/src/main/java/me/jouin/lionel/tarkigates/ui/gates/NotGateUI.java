package me.jouin.lionel.tarkigates.ui.gates;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.R;
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
        height = Positions.getInstance().notGateHeight;
        width = Positions.getInstance().notGateWidth;
        inX = (int) Math.round(width*0.30);
        inY = height/2;
        outX = (int) Math.round(width*0.84);
        outY = height/2;
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
