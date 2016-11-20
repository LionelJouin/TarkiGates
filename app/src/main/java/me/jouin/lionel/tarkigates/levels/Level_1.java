package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.*;

/**
 * Created by lione on 31/10/2016.
 */

public class Level_1 extends Level {

    public Level_1() {
        super(new Light());

        result[0] = 2;
        result[1] = 4;
        result[2] = 6;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        LogicGate g1 = new AndGate();
        Light l = new Light();

        g1.setInA(s1);
        g1.setInB(s2);

        l.setIn(g1);

        setLight(l);

    }

}
