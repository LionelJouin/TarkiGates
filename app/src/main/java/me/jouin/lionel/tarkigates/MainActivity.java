package me.jouin.lionel.tarkigates;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import me.jouin.lionel.tarkigates.levels.LevelList;
import me.jouin.lionel.tarkigates.pages.Game;
import me.jouin.lionel.tarkigates.pages.Home;
import me.jouin.lionel.tarkigates.pages.Informations;
import me.jouin.lionel.tarkigates.pages.Page;
import me.jouin.lionel.tarkigates.pages.PageListener;
import me.jouin.lionel.tarkigates.pages.PageName;
import me.jouin.lionel.tarkigates.pages.Settings;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction tx;

    private Home home;
    private Game game;
    private Settings settings;
    private Informations informations;
    private Map<PageName, Page> pages;
    private PageName pageActuel;
    private PageName previousPage;

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        home = new Home();
        game = new Game();
        settings = new Settings();
        informations = new Informations();

        pages = new HashMap<>();
        pages.put(PageName.HOME, home);
        pages.put(PageName.GAME, game);
        pages.put(PageName.SETTINGS, settings);
        pages.put(PageName.INFORMATIONS, informations);

        for(Map.Entry<PageName, Page> p : pages.entrySet()) {
            p.getValue().addPageListeners(new PageListener() {
                @Override
                public void pageChange(PageName pageName) {
                    changePage(pageName);
                }

                @Override
                public void selectLevel(LevelList levelList) {
                    game.setLevel(levelList);
                }
            });
        }

        pageActuel = PageName.HOME;
        changePage(pageActuel);

        Resources.getInstance(this);

        Resources.getInstance().playBackgroundMusic(true);

        super.onStart();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.pref_settings), Context.MODE_PRIVATE);
        boolean prefSoundEffects = sharedPref.getBoolean(getString(R.string.pref_soundEffects), true);
        int prefOrientation = sharedPref.getInt(getString(R.string.pref_orientation), ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Resources.getInstance().soundEffect = prefSoundEffects;
        if (prefOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.pref_settings), Context.MODE_PRIVATE);
        boolean prefMusic = sharedPref.getBoolean(getString(R.string.pref_music), true);
        Resources.getInstance().playBackgroundMusic(prefMusic);
        Resources.getInstance().setVolume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Resources.getInstance().playBackgroundMusic(false);
    }

    public void changePage(PageName pageName) {
        if (pages.containsKey(pageName)) {
            if (pageActuel != PageName.SETTINGS) {
                previousPage = pageActuel;
                pageActuel = pageName;
                tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.fragment, pages.get(pageName));
                tx.commit();
            } else {
                pageActuel = previousPage;
                previousPage = PageName.SETTINGS;
                tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.fragment, pages.get(pageActuel));
                tx.commit();
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Positions.getInstance(this);
    }

    @Override
    public void onBackPressed() {
        if (pageActuel == PageName.HOME) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getString(R.string.doubleBackToExit), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            changePage(PageName.HOME);
        }
    }

}
