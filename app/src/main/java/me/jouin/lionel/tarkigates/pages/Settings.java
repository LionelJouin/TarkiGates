package me.jouin.lionel.tarkigates.pages;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import me.jouin.lionel.tarkigates.R;
import me.jouin.lionel.tarkigates.Resources;
import me.jouin.lionel.tarkigates.levels.LevelList;

/**
 * Created by lione on 12/11/2016.
 */

public class Settings extends Page {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_settings, container, false);

        RelativeLayout settingsLayout = (RelativeLayout) root.findViewById(R.id.settings);
        ImageView backImageView = (ImageView) root.findViewById(R.id.back);

        final TextView infos = (TextView) root.findViewById(R.id.infos);
        final ImageView orientation = (ImageView) root.findViewById(R.id.orientation);
        Button resetButton = (Button) root.findViewById(R.id.reset);
        Switch music = (Switch) root.findViewById(R.id.music);
        Switch soundeffect = (Switch) root.findViewById(R.id.soundeffect);

        infos.setText(getString(R.string.upToDate));

        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.pref_settings), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        boolean prefMusic = sharedPref.getBoolean(getString(R.string.pref_music), true);
        boolean prefSoundEffects = sharedPref.getBoolean(getString(R.string.pref_soundEffects), true);
        final int[] prefOrientation = {sharedPref.getInt(getString(R.string.pref_orientation), ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)};
        if (prefOrientation[0] == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            orientation.setImageResource(R.drawable.landscape);
        } else {
            orientation.setImageResource(R.drawable.portrait);
        }
        music.setChecked(prefMusic);
        soundeffect.setChecked(prefSoundEffects);

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

        orientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources.getInstance().playClickSound();
                infos.setText(getString(R.string.saved));
                if (prefOrientation[0] == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    prefOrientation[0] = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    orientation.setImageResource(R.drawable.portrait);
                } else {
                    prefOrientation[0] = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    orientation.setImageResource(R.drawable.landscape);
                }
                editor.putInt(getString(R.string.pref_orientation), prefOrientation[0]);
                editor.commit();
            }
        });

        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                infos.setText(getString(R.string.saved));
                Resources.getInstance().playBackgroundMusic(b);
                Resources.getInstance().playClickSound();
                editor.putBoolean(getString(R.string.pref_music), b);
                editor.commit();
            }
        });

        soundeffect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                infos.setText(getString(R.string.saved));
                Resources.getInstance().soundEffect = b;
                Resources.getInstance().playClickSound();
                editor.putBoolean(getString(R.string.pref_soundEffects), b);
                editor.commit();
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
                infos.setText(getString(R.string.hasBeenReset));
            }
        });

        return root;
    }

}
