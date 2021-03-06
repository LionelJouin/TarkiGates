package me.jouin.lionel.tarkigates;

import android.content.Context;
import android.util.DisplayMetrics;

import me.jouin.lionel.tarkigates.levels.Level;

/**
 * Created by lione on 31/10/2016.
 */

public class Positions {

    private static Positions positions = null;

    private final int defaultSize = 70;

    public int screenHeight;
    public int screenWidth;
    public int windowHeight;
    public int windowWidth;

    public int marginTop;
    public int marginLeft;

    public int realLevelHeight;
    public int realLevelWidth;
    public int actualLevelHeight;
    public int actualLevelWidth;

    public int spaceBetweenComponentsX;
    public int spaceBetweenComponentsY;
    public int componentSize;
    public int switchSize;
    public int lightSize;

    public int logicGateHeight;
    public int logicGateWidth;

    public int notGateHeight;
    public int notGateWidth;

    public int lightX;
    public int lightY;

    private Positions() {
    }

    private Positions(MainActivity mainActivity) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
        windowHeight = mainActivity.findViewById(R.id.activity_main).getHeight();
        windowWidth = mainActivity.findViewById(R.id.activity_main).getWidth();

        marginTop = dpToPx((int) (defaultSize*.3), mainActivity);
        marginLeft = dpToPx((int) (defaultSize*.3), mainActivity);
        //marginTop = dpToPx(defaultSize, mainActivity);
        //marginLeft = dpToPx(defaultSize, mainActivity);

        spaceBetweenComponentsX = dpToPx(defaultSize, mainActivity);
        spaceBetweenComponentsY = dpToPx(defaultSize, mainActivity);

        componentSize = dpToPx(defaultSize, mainActivity);
        switchSize = dpToPx(defaultSize, mainActivity);
        lightSize = dpToPx(defaultSize, mainActivity);

        logicGateHeight = dpToPx(defaultSize, mainActivity);
        logicGateWidth = dpToPx(defaultSize, mainActivity);

        notGateHeight = dpToPx((int) (defaultSize*.5), mainActivity);
        notGateWidth = dpToPx((int) (defaultSize*.5), mainActivity);

    }

    public static Positions getInstance(MainActivity mainActivity) {
        if (positions == null) {
            positions = new Positions(mainActivity);
        }
        return positions;
    }

    public static Positions getInstance() {
        if (positions == null) {
            positions = new Positions();
        }
        return positions;
    }

    public void setLevelPositions(Level level) {
        realLevelWidth = 2000;
        actualLevelWidth = realLevelWidth;
        if (actualLevelWidth<windowWidth)
            actualLevelWidth = windowWidth;
        realLevelHeight = level.getNbSwitchs() * switchSize + (level.getNbSwitchs()) * spaceBetweenComponentsY - spaceBetweenComponentsY;
        actualLevelHeight = realLevelHeight;
        if (actualLevelHeight<windowHeight)
            actualLevelHeight = windowHeight;

        // Light
        lightX = 0;
        //lightY = -10;
        lightY = realLevelHeight/2-lightSize/2;

    }

    private int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}
