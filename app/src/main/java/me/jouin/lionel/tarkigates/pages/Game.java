package me.jouin.lionel.tarkigates.pages;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.R;
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

        if (level != null && level.isValid()) {

            Positions.getInstance().setLevelPositions(level);

            wv = new WebView(root.getContext());

            gameView = new GameView(root.getContext(), level, wv);
            gameView.setBackgroundColor(Color.parseColor("#bdc3c7"));

            wv.setInitialScale(100);

            wv.addView(gameView);

            gameRelativeLayout.addView(wv);

        } else {
            Toast.makeText(root.getContext(), "Level invalide", Toast.LENGTH_LONG).show();
        }

        /*
        Button b = (Button) root.findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                triggerPageListeners(PageName.HOME);
            }
        });
        */

        return root;
    }

    public void setLevel(LevelList levelList) {
        level = LevelList.createLevel(levelList);
    }

}
