package me.jouin.lionel.tarkigates.ui;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.R;

/**
 * Created by lione on 05/11/2016.
 */

public class SwitchUI extends ComponentUI {

    public Button switchButton;

    public SwitchUI(int x, int y) {
        super(x, y);
        height = Positions.getInstance().switchSize;
        width = Positions.getInstance().switchSize;
        imgId = R.drawable.switch_button;
        imgIdActivated = R.drawable.switch_button;
        outX = width;
        outY = height/2;
    }

    @Override
    public void repositioning(int repX, int repY) {
        super.repositioning(repX, repY);
        switchButton.setX(x);
        switchButton.setY(y);
    }

    @Override
    public void addWires(int startX, int startY) {}

    @Override
    public Button getView() {
        return switchButton;
    }

    @Override
    public void setView(Context context) {
        switchButton = new Button(context);
        switchButton.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        switchButton.setY(y);
        switchButton.setX(x);
        switchButton.setText("k");
    }
}
