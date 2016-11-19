package me.jouin.lionel.tarkigates;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by lione on 17/11/2016.
 */

public class Resources {

    private static Resources resources = null;

    public int wire;
    public int wireActivated = Color.parseColor("#e74c3c");

    private MediaPlayer switchClick;
    private MediaPlayer click;

    private Resources() {
    }

    private Resources(MainActivity mainActivity) {
        switchClick = MediaPlayer.create(mainActivity, R.raw.switchclick);
        click = MediaPlayer.create(mainActivity, R.raw.click);
        wire = ResourcesCompat.getColor(mainActivity.getResources(), R.color.wire, null);
        wireActivated = ResourcesCompat.getColor(mainActivity.getResources(), R.color.wireActivated, null);
    }

    public static Resources getInstance(MainActivity mainActivity) {
        if (resources == null) {
            resources = new Resources(mainActivity);
        }
        return resources;
    }

    public static Resources getInstance() {
        if (resources == null) {
            resources = new Resources();
        }
        return resources;
    }

    public void playSwitchSound() {
        switchClick.start();
    }
    public void playClickSound() {
        click.start();
    }

}
