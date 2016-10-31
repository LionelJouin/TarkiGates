package me.jouin.lionel.tarkigates.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import me.jouin.lionel.tarkigates.levels.Level;

/**
 * Created by lione on 31/10/2016.
 */

public class GameView extends View {

    private Paint paint = new Paint();
    private Level level;

    public GameView(Context context, Level level) {
        super(context);
        this.level = level;
        for (int i = 0;i<level.getNbSwitchs();i++) {

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 3050;
        int height = 3000 + 50;
        setMeasuredDimension(width, height);
    }

    @Override
    public void onDraw(Canvas canvas) {

        paint.setColor(Color.GREEN);
        canvas.drawRect(30, 30, 90, 200, paint);
        paint.setColor(Color.BLUE);

        canvas.drawLine(100, 20, 100, 1900, paint);

        paint.setColor(Color.GREEN);
        canvas.drawRect(200, 2000, 400, 3000, paint);

        paint.setColor(Color.CYAN);
        canvas.drawRect(300, 2000, 1500, 3000, paint);

    }
}
