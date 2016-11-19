package me.jouin.lionel.tarkigates.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.R;

/**
 * Created by lione on 05/11/2016.
 */

public class SwitchUI extends ComponentUI {

    public ImageView switchButton;

    public SwitchUI(int x, int y) {
        super(x, y);
        height = Positions.getInstance().switchSize;
        width = Positions.getInstance().switchSize;
        imgId = R.drawable.switchoff;
        imgIdActivated = R.drawable.switchon;
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
    public ImageView getView() {
        return switchButton;
    }

    @Override
    public void setView(Context context) {
        switchButton = new ImageView(context);
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imgId);
        bmp = Bitmap.createScaledBitmap(bmp, width, height, true);
        switchButton.setImageBitmap(bmp);
        //switchButton.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        switchButton.setY(y);
        switchButton.setX(x);
        //switchButton.setText("k");
    }

    public void switchSwitch(Context context, boolean state) {
        Bitmap bmp;
        if (state)
            bmp = BitmapFactory.decodeResource(context.getResources(), imgIdActivated);
        else
            bmp = BitmapFactory.decodeResource(context.getResources(), imgId);
        bmp = Bitmap.createScaledBitmap(bmp, width, height, true);
        switchButton.setImageBitmap(bmp);
    }
}
