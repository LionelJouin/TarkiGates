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

import me.jouin.lionel.tarkigates.R;
import me.jouin.lionel.tarkigates.Resources;
import me.jouin.lionel.tarkigates.levels.LevelList;

/**
 * Created by lione on 12/11/2016.
 */

public class Settings extends Page {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_settings, container, false);

        LinearLayout settingsLayout = (LinearLayout) root.findViewById(R.id.settings);
        ImageView backImageView = (ImageView) root.findViewById(R.id.back);
        Button resetButton = (Button) root.findViewById(R.id.reset);

        int[] colors = {ResourcesCompat.getColor(getResources(), R.color.skyStart, null), ResourcesCompat.getColor(getResources(), R.color.skyEnd, null)};
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);
        settingsLayout.setBackgroundDrawable(gd);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources.getInstance().playClickSound();
                changePage(PageName.HOME);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources.getInstance().playClickSound();
                SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.pref_levels), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                for (final LevelList levelList : LevelList.values()) {
                    editor.putInt(levelList.toString(), -1);
                    editor.commit();
                }
            }
        });

        return root;
    }

}
