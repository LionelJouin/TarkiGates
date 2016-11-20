package me.jouin.lionel.tarkigates.ui.gates;

import me.jouin.lionel.tarkigates.R;

/**
 * Created by lione on 05/11/2016.
 */

public class NandGateUI extends LogicGateUI {

    public NandGateUI(int x, int y) {
        super(x, y);
        imgId = R.drawable.nand;
        imgIdActivated = R.drawable.nand;
        outX = (int) Math.round(width*0.81);
    }

}
