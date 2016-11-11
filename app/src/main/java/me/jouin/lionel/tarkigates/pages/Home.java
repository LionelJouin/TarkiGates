package me.jouin.lionel.tarkigates.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import me.jouin.lionel.tarkigates.R;
import me.jouin.lionel.tarkigates.levels.LevelList;

/**
 * Created by lione on 11/11/2016.
 */

public class Home extends Page {

    private Map<Button, LevelList> buttonsPlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        //ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, null);

        LinearLayout homeLayout = (LinearLayout) root.findViewById(R.id.home);

        buttonsPlay = new HashMap<>();

        for (final LevelList levelList : LevelList.values()) {
            Button b = new Button(root.getContext());
            buttonsPlay.put(b, levelList);
            homeLayout.addView(b);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeLevel(levelList);
                    changePage(PageName.GAME);
                }
            });
        }

        /*
        Button b = (Button) root.findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                triggerPageListeners(PageName.GAME);
            }
        });
        */
        return root;
    }

}
