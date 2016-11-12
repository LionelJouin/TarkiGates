package me.jouin.lionel.tarkigates.pages;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
        LinearLayout buildingLayout = (LinearLayout) root.findViewById(R.id.building);

        int[] colors = {Color.parseColor("#2c3e50"), Color.parseColor("#34495e")};
        GradientDrawable gd = new GradientDrawable( GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);
        homeLayout.setBackgroundDrawable(gd);

        buttonsPlay = new HashMap<>();

        int i = 0;
        LinearLayout floorWindow = new LinearLayout(root.getContext());
        LinearLayout floorWindowSill = new LinearLayout(root.getContext());

        Random rand = new Random();

        for (final LevelList levelList : LevelList.values()) {
            if (i%3 == 0) {
                floorWindow = new LinearLayout(root.getContext());
                floorWindowSill = new LinearLayout(root.getContext());
                floorWindow.setOrientation(LinearLayout.HORIZONTAL);
                floorWindowSill.setOrientation(LinearLayout.HORIZONTAL);
                int r = rand.nextInt(255);
                int g = rand.nextInt(255);
                int b = rand.nextInt(255);
                //floorWindow.setBackgroundColor(Color.rgb(r, g, b));
                //floorWindowSill.setBackgroundColor(Color.rgb(r, g, b));
                int buildingColor = Color.parseColor("#bdc3c7");
                floorWindow.setBackgroundColor(buildingColor);
                floorWindowSill.setBackgroundColor(buildingColor);
                buildingLayout.addView(floorWindow);
                buildingLayout.addView(floorWindowSill);
            }

            Button window = new Button(root.getContext());
            window.setText(""+i);
            window.setTextSize(60);
            //window.setTextColor(Color.parseColor("#34495e"));
            //window.setBackgroundColor(Color.parseColor("#2c3e50"));
            window.setTextColor(Color.parseColor("#f1c40f"));
            window.setBackgroundColor(Color.parseColor("#f39c12"));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            params.setMargins(30, 40, 30, 0);
            window.setLayoutParams(params);
            buttonsPlay.put(window, levelList);
            floorWindow.addView(window);
            window.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeLevel(levelList);
                    changePage(PageName.GAME);
                }
            });

            LinearLayout windowSill = new LinearLayout(root.getContext());
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 35, 1f);
            windowSill.setBackgroundColor(Color.parseColor("#f1c40f"));
            params.setMargins(20, 0, 20, 0);
            windowSill.setLayoutParams(params);
            floorWindowSill.addView(windowSill);

            i++;
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
