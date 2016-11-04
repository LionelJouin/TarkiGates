package me.jouin.lionel.tarkigates.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import me.jouin.lionel.tarkigates.MainActivity;
import me.jouin.lionel.tarkigates.R;
import me.jouin.lionel.tarkigates.levels.Level;

/**
 * Created by lione on 31/10/2016.
 */

public class GameView extends RelativeLayout {

    private Paint paint = new Paint();
    private Level level;
    private Context context;
    public List<Button> buttons = new ArrayList<>();

    public GameView(Context context, Level level) {
        super(context);
        this.level = level;
        this.context = context;

        ImageView light = new ImageView(context);
        light.setImageResource(R.drawable.and);
        addView(light);

        for (int i = 0;i<level.getNbSwitchs();i++) {
            Button b = new Button(context);
            buttons.add(b);
            b.setY((MainActivity.positions.spaceBetweenComponents+MainActivity.positions.switchSize)*i);
            b.setText(""+i);
            addView(b);
        }

        RelativeLayout.LayoutParams a = new RelativeLayout.LayoutParams(3000, 3000);
        this.setLayoutParams(a);
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