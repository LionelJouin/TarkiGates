package me.jouin.lionel.tarkigates.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.R;

/**
 * Created by lione on 05/11/2016.
 */

public class LightUI extends ComponentUI {

    public int inX;
    public int inY;

    private ImageView componentImageView;

    private List<LightUIListener> lightUIListener = new ArrayList<>();

    public void addSwitchUIListeners(LightUIListener l) {
        lightUIListener.add(l);
    }

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

    @Override
    public void repositioning(int repX, int repY) {
        super.repositioning(repX, repY);
        componentImageView.setX(x);
        componentImageView.setY(y);
    }

    @Override
    public void addWires(int startX, int startY) {
        int stopX = getInX();
        int stopY = getInY();
        WireUI w = new WireUI(startX, startY, stopX, stopY);
        wires.add(w);
        w.addSwitchUIListeners(new WireUIListener() {
            @Override
            public void switchWire() {
                for (LightUIListener l : lightUIListener) {
                    l.switchLightUI();
                }
            }
        });
    }

    @Override
    public ImageView getView() {
        return componentImageView;
    }

    @Override
    public void setView(Context context) {
        componentImageView = new ImageView(context);
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imgId);
        bmp = Bitmap.createScaledBitmap(bmp, width, height, true);
        componentImageView.setImageBitmap(bmp);
        componentImageView.setX(x);
        componentImageView.setY(y);
    }

}
