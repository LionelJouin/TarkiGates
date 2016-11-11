package me.jouin.lionel.tarkigates;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import me.jouin.lionel.tarkigates.levels.LevelList;
import me.jouin.lionel.tarkigates.pages.Game;
import me.jouin.lionel.tarkigates.pages.Home;
import me.jouin.lionel.tarkigates.pages.Page;
import me.jouin.lionel.tarkigates.pages.PageListener;
import me.jouin.lionel.tarkigates.pages.PageName;

public class MainActivity extends AppCompatActivity {

    /*
    private GameView gameView;
    private Level level;
    private WebView wv;
    */

    private FragmentTransaction tx;
    private Home home;
    private Game game;
    //private Game options;
    private Map<PageName, Page> pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //level = new Level_3();

        home = new Home();
        game = new Game();

        pages = new HashMap<>();
        pages.put(PageName.HOME, home);
        pages.put(PageName.GAME, game);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Positions.getInstance(this);

        for(Map.Entry<PageName, Page> p : pages.entrySet()) {
            p.getValue().addPageListeners(new PageListener() {
                @Override
                public void pageChange(PageName pageName) {
                    if (pages.containsKey(pageName)) {
                        tx = getSupportFragmentManager().beginTransaction();
                        tx.replace(R.id.fragment, pages.get(pageName));
                        tx.commit();
                    }
                }

                @Override
                public void selectLevel(LevelList levelList) {
                    game.setLevel(levelList);
                }
            });
        }

        tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.fragment, pages.get(PageName.HOME));
        tx.commit();




        /*
        if (level.isValid()) {

            Positions.getInstance().setLevelPositions(level);

            wv = new WebView(this);

            gameView = new GameView(this, level, wv);
            gameView.setBackgroundColor(Color.parseColor("#bdc3c7"));

            wv.setInitialScale(100);
            //wv.setLayoutParams(new RelativeLayout.LayoutParams(Positions.getInstance().actualLevelHeight, Positions.getInstance().actualLevelHeight-200));

            wv.addView(gameView);
            setContentView(wv);
        } else {
            Toast.makeText(getApplicationContext(), "Level invalide", Toast.LENGTH_LONG).show();
        }
        */

    }

}
