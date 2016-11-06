package me.jouin.lionel.tarkigates.ui;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.R;

/**
 * Created by lione on 05/11/2016.
 */

public class SwitchUI extends ComponentUI {

    public SwitchUI(int x, int y) {
        super(x, y);
        height = Positions.getInstance().switchSize;
        width = Positions.getInstance().switchSize;
        imgId = R.drawable.switch_button;
        imgIdActivated = R.drawable.switch_button;
        outX = x;
        outY = y;
    }

}
