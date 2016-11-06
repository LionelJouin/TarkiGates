package me.jouin.lionel.tarkigates.ui;

/**
 * Created by lione on 05/11/2016.
 */

public abstract class ComponentUI {

    public int height;
    public int width;

    public int x;
    public int y;

    public int outX;
    public int outY;

    public int imgId;
    public int imgIdActivated;

    public ComponentUI(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
