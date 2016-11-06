package me.jouin.lionel.tarkigates;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import me.jouin.lionel.tarkigates.levels.Level;
import me.jouin.lionel.tarkigates.levels.Level_1;
import me.jouin.lionel.tarkigates.ui.GameView;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private Level level_1;
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        level_1 = new Level_1();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Positions.getInstance(this);

        super.onWindowFocusChanged(hasFocus);
        if (level_1.isValid()) {

            Positions.getInstance().setLevelPositions(level_1);

            gameView = new GameView(this, level_1);
            gameView.setBackgroundColor(Color.WHITE);

            WebView wv = new WebView(this);
            wv.setInitialScale(100);
            //wv.setLayoutParams(new RelativeLayout.LayoutParams(Positions.getInstance().actualLevelHeight, Positions.getInstance().actualLevelHeight-200));
            wv.loadData("<html>" +
                    "<header><meta name='viewport' content='width=device-width, initial-scale=1' /></header>" +
                    "<body style='margin: 0; padding: 0;height:"+Positions.getInstance().actualLevelHeight+";width:"+Positions.getInstance().actualLevelWidth+"px;'>" +
                    //"<div style='height:"+Positions.getInstance().actualLevelHeight+"px;width:"+Positions.getInstance().actualLevelWidth+"px;background:red;'></div>" +
                    "</body>" +
                    "</html>", "text/html; charset=utf-8", "UTF-8");
            wv.addView(gameView);
            setContentView(wv);
        } else {
            Toast.makeText(getApplicationContext(), "Level invalide", Toast.LENGTH_LONG).show();
        }
    }

}
