package me.jouin.lionel.tarkigates.ui;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.R;

/**
 * Created by lione on 05/11/2016.
 */

public class LightUI extends ComponentUI {

    public int inX;
    public int inY;

    public LightUI(int x, int y) {
        super(x, y);
        height = Positions.getInstance().lightSize;
        width = Positions.getInstance().lightSize;
        imgId = R.drawable.light;
        imgIdActivated = R.drawable.light;
        inX = width/2;
        inY = height;
    }

    public int getInX() {
        return x+inX;
    }

    public int getInY() {
        return y+inY;
    }

    public void addWires(int startX, int startY) {
        int stopX = getInX();
        int stopY = getInY();
        wires.add(new WireUI(startX, startY, stopX, stopY));
    }
}
