package me.jouin.lionel.tarkigates.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lione on 06/11/2016.
 */

public class WireUI {

    private boolean stateListener = false;
    private List<WireUIListener> wireUIListener = new ArrayList<>();

    public void addSwitchUIListeners(WireUIListener l) {
        wireUIListener.add(l);
    }

    public int wireThickness = 5;

    public int color = Color.parseColor("#2c3e50");
    public int colorActivated = Color.parseColor("#e74c3c");

    public int startX;
    public int startY;
    public int stopX;
    public int stopY;

    public WireUI(int startX, int startY, int stopX, int stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }

    public void draw(Canvas canvas, Paint paint, boolean state) {
        if (!state) {
            if (stateListener != state) {
                for (WireUIListener l : wireUIListener) {
                    l.switchWire();
                }
                stateListener = state;
            }
            paint.setColor(color);
        } else {
            if (stateListener != state) {
                for (WireUIListener l : wireUIListener) {
                    l.switchWire();
                }
                stateListener = state;
            }
            paint.setColor(colorActivated);
        }

        paint.setStrokeWidth(wireThickness);
        if (startY != stopY) {
            canvas.drawLine(startX, startY, (stopX+startX)/2+(wireThickness/2), startY, paint);
            canvas.drawLine((stopX+startX)/2, startY, (stopX+startX)/2, stopY, paint);
            canvas.drawLine((stopX+startX)/2-(wireThickness/2), stopY, stopX, stopY, paint);
        } else {
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        draw(canvas, paint, false);
    }

}
