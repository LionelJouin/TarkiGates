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
        inX = x;
        inY = y;
    }
}
