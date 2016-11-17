package me.jouin.lionel.tarkigates;

import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by lione on 17/11/2016.
 */

public class Colors {

    private static Colors colors = null;

    public int wire;
    public int wireActivated = Color.parseColor("#e74c3c");

    private Colors() {
    }

    private Colors(MainActivity mainActivity) {
        wire = ResourcesCompat.getColor(mainActivity.getResources(), R.color.wire, null);
        wireActivated = ResourcesCompat.getColor(mainActivity.getResources(), R.color.wireActivated, null);
    }

    public static Colors getInstance(MainActivity mainActivity) {
        if (colors == null) {
            colors = new Colors(mainActivity);
        }
        return colors;
    }

    public static Colors getInstance() {
        if (colors == null) {
            colors = new Colors();
        }
        return colors;
    }

}
