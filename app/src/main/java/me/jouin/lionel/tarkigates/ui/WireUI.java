package me.jouin.lionel.tarkigates.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by lione on 06/11/2016.
 */

public class WireUI {

    public int color = Color.BLACK;

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

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

}
