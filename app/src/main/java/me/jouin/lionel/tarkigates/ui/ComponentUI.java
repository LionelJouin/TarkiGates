package me.jouin.lionel.tarkigates.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lione on 05/11/2016.
 */

public abstract class ComponentUI {

    public int height;
    public int width;

    public List<WireUI> wires = new ArrayList<>();

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

    public int getOutX() {
        return x+outX;
    }

    public int getOutY() {
        return y+outY;
    }

    public abstract void addWires(int startX, int startY);

    public void repositioning(int repX, int repY) {
        x += repX;
        y += repY;
    }
    /*
    public void addWires(int startX, int startY) {
        int stopX = getInX();
        int stopY = getInY();
        wires.add(new WireUI(startX, startY, stopX, stopY));
    }
    */
}
