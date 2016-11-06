package me.jouin.lionel.tarkigates.ui.gates;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.R;
import me.jouin.lionel.tarkigates.ui.ComponentUI;

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
        inX = x;
        inY = y;
        outX = x;
        outY = y;
    }

}
