package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.AndGate;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.OrGate;

/**
 * Created by lione on 31/10/2016.
 */

public class Level_2 extends Level {

    public Level_2() {
        super(new Light());

        result[0] = 1;
        result[1] = 2;
        result[2] = 3;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        Switch s3 = new Switch();
        LogicGate g1 = new OrGate();
        LogicGate g2 = new AndGate();
        LogicGate g3 = new OrGate();
        Light l = new Light();

        g3.setInA(s1);
        g3.setInB(s2);

        g2.setInA(s2);
        g2.setInB(s3);

        g1.setInA(g3);
        g1.setInB(g2);

        l.setIn(g1);

        setLight(l);

    }

}
