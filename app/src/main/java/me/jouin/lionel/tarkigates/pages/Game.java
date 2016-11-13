package me.jouin.lionel.tarkigates.pages;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.R;
import me.jouin.lionel.tarkigates.core.LightListener;
import me.jouin.lionel.tarkigates.levels.Level;
import me.jouin.lionel.tarkigates.levels.LevelList;
import me.jouin.lionel.tarkigates.ui.GameView;

/**
 * Created by lione on 11/11/2016.
 */

public class Game extends Page {

    private GameView gameView;
    private Level level = null;
    private WebView wv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_game, null);

        RelativeLayout gameRelativeLayout = (RelativeLayout) root.findViewById(R.id.game);

        ImageView pauseButton = (ImageView) root.findViewById(R.id.pauseButton);
        final LinearLayout pauseMenu = (LinearLayout) root.findViewById(R.id.pauseMenu);

        Button resumeButton = (Button) root.findViewById(R.id.resume);
        Button settingsButton = (Button) root.findViewById(R.id.settings);
        Button homeButton = (Button) root.findViewById(R.id.gotohome);

        final LinearLayout finishGameMenu = (LinearLayout) root.findViewById(R.id.finishGame);

        ImageView continuerButton = (ImageView) root.findViewById(R.id.continuer);
        ImageView replayButton = (ImageView) root.findViewById(R.id.replay);
        ImageView homeButton2 = (ImageView) root.findViewById(R.id.gotohome2);

        if (level != null && level.isValid()) {

            Positions.getInstance().setLevelPositions(level);

            wv = new WebView(root.getContext());

            gameView = new GameView(root.getContext(), level, wv);
            gameView.setBackgroundColor(Color.parseColor("#bdc3c7"));

            wv.setInitialScale(100);

            wv.addView(gameView);

            gameRelativeLayout.addView(wv);

            level.getLight().addLightListeners(new LightListener() {
                @Override
                public void switchLight(boolean state) {
                    if (state)
                        finishGameMenu.setVisibility(gameView.VISIBLE);
                    else
                        finishGameMenu.setVisibility(gameView.INVISIBLE);
                }
            });

        } else {
            changePage(PageName.HOME);
        }


        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseMenu.setVisibility(view.VISIBLE);
            }
        });
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseMenu.setVisibility(view.INVISIBLE);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(PageName.SETTINGS);
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(PageName.HOME);
            }
        });
        continuerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(PageName.HOME);
            }
        });
        homeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(PageName.HOME);
            }
        });
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(PageName.HOME);
            }
        });

        return root;
    }

    public void setLevel(LevelList levelList) {
        level = LevelList.createLevel(levelList);
    }

}
