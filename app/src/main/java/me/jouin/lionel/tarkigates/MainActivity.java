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

    public static final Positions positions = new Positions();

    public GameView gameView;
    public Level level_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        level_1 = new Level_1();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (level_1.isValid()) {

            gameView = new GameView(this, level_1);
            gameView.setBackgroundColor(Color.WHITE);

            WebView wv = new WebView(this);
            int height = level_1.getNbSwitchs() * positions.switchSize + (level_1.getNbSwitchs() + 2) * positions.spaceBetweenComponents;
            //wv.loadData("<html style='height:" + height + "px;width:3050px;'>htryy</html>", "text/html; charset=utf-8", "UTF-8");
            wv.loadData("<html style='height:3000px;width:3050px;'>htryy</html>", "text/html; charset=utf-8", "UTF-8");
            wv.addView(gameView);
            setContentView(wv);
        } else {
            Toast.makeText(getApplicationContext(), "Level invalide", Toast.LENGTH_LONG).show();
        }
    }
}
