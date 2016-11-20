package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.AndGate;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.OrGate;

/**
 * Created by lione on 20/11/2016.
 */

public class Level_6 extends Level {

    public Level_6() {
        super(new Light());

        result[0] = 2;
        result[1] = 3;
        result[2] = 4;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        Switch s3 = new Switch();
        LogicGate g1 = new AndGate();
        LogicGate g2 = new OrGate();
        LogicGate g3 = new AndGate();
        Light l = new Light();

        g1.setInA(s1);
        g1.setInB(s2);

        g2.setInA(s2);
        g2.setInB(s3);

        g3.setInA(g1);
        g3.setInB(g2);

        l.setIn(g3);

        setLight(l);

    }

}
