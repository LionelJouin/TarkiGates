package me.jouin.lionel.tarkigates.pages;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.jouin.lionel.tarkigates.R;
import me.jouin.lionel.tarkigates.Resources;
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

        int[] colors = {ResourcesCompat.getColor(getResources(), R.color.skyStart, null), ResourcesCompat.getColor(getResources(), R.color.skyEnd, null)};
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);
        homeLayout.setBackgroundDrawable(gd);

        buttonsPlay = new HashMap<>();

        int i = 0;
        LinearLayout floorWindow = new LinearLayout(root.getContext());
        LinearLayout floorWindowSill = new LinearLayout(root.getContext());

        Random rand = new Random();

        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.pref_levels), Context.MODE_PRIVATE);

        for (final LevelList levelList : LevelList.values()) {
            int levelSaved = sharedPref.getInt(levelList.toString(), 0);
            if (i%3 == 0) {
                floorWindow = new LinearLayout(root.getContext());
                floorWindowSill = new LinearLayout(root.getContext());
                floorWindow.setOrientation(LinearLayout.HORIZONTAL);
                floorWindowSill.setOrientation(LinearLayout.HORIZONTAL);
                floorWindow.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.building, null));
                floorWindowSill.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.building, null));
                buildingLayout.addView(floorWindow);
                buildingLayout.addView(floorWindowSill);
            }

            Button window = new Button(root.getContext());
            window.setText(""+i);
            window.setTextSize(60);
            if (levelSaved>0) {
                window.setTextColor(ResourcesCompat.getColor(getResources(), R.color.windowTextOn, null));
                window.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.windowOn, null));
            } else {
                window.setTextColor(ResourcesCompat.getColor(getResources(), R.color.windowTextOff, null));
                window.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.windowOff, null));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            params.setMargins(30, 40, 30, 0);
            window.setLayoutParams(params);
            buttonsPlay.put(window, levelList);
            floorWindow.addView(window);
            window.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Resources.getInstance().playClickSound();
                    changeLevel(levelList);
                    changePage(PageName.GAME);
                }
            });

            LinearLayout windowSill = new LinearLayout(root.getContext());
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 35, 1f);
            switch(levelSaved) {
                case 1:
                    windowSill.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sillBronze, null));
                    break;
                case 2:
                    windowSill.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sillSilver, null));
                    break;
                case 3:
                    windowSill.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sillGold, null));
                    break;
                default:
                    windowSill.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sillClear, null));
            }

            params.setMargins(20, 0, 20, 0);
            windowSill.setLayoutParams(params);
            floorWindowSill.addView(windowSill);

            i++;
        }

        ImageView settings = (ImageView) root.findViewById(R.id.imageViewSettings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources.getInstance().playClickSound();
                changePage(PageName.SETTINGS);
            }
        });

        ImageView info = (ImageView) root.findViewById(R.id.imageViewInformations);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources.getInstance().playClickSound();
                changePage(PageName.INFORMATIONS);
            }
        });

        return root;
    }

}
