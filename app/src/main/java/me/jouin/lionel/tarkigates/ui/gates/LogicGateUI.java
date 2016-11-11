package me.jouin.lionel.tarkigates.ui.gates;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.ui.ComponentUI;
import me.jouin.lionel.tarkigates.ui.WireUI;

/**
 * Created by lione on 05/11/2016.
 */

public abstract class LogicGateUI extends ComponentUI {

    public int inAX;
    public int inAY;

    public int inBX;
    public int inBY;

    private ImageView componentImageView;

    public LogicGateUI(int x, int y) {
        super(x, y);
        height = Positions.getInstance().logicGateHeight;
        width = Positions.getInstance().logicGateWidth;
        inAX = (int) Math.round(width*0.31);
        inAY = (int) Math.round(height*0.40);
        inBX = (int) Math.round(width*0.31);
        inBY = (int) Math.round(height*0.60);
        outX = (int) Math.round(width*0.69);
        outY = height/2;
    }

    public int getInAX() {
        return x+inAX;
    }

    public int getInAY() {
        return y+inAY;
    }

    public int getInBX() {
        return x+inBX;
    }

    public int getInBY() {
        return y+inBY;
    }

    @Override
    public void repositioning(int repX, int repY) {
        super.repositioning(repX, repY);
        componentImageView.setX(x);
        componentImageView.setY(y);
    }

    @Override
    public void addWires(int startX, int startY) {
        int stopX;
        int stopY;
        if (wires.size() == 0) {
            stopX = getInAX();
            stopY = getInAY();
        } else {
            stopX = getInBX();
            stopY = getInBY();
        }
        wires.add(new WireUI(startX, startY, stopX, stopY));
    }

    @Override
    public View getView() {
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
