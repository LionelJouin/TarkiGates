package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.AndGate;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.NotGate;
import me.jouin.lionel.tarkigates.core.gates.XnorGate;

/**
 * Created by lione on 31/10/2016.
 */

public class Level_4 extends Level {

    public Level_4() {
        super(new Light());

        result[0] = 1;
        result[1] = 2;
        result[2] = 3;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        Switch s3 = new Switch();
        LogicGate g1 = new XnorGate();
        LogicGate g2 = new AndGate();
        NotGate n1 = new NotGate();
        Light l = new Light();

        g2.setInA(s2);
        g2.setInB(s3);

        n1.setIn(g2);

        g1.setInA(s1);
        g1.setInB(n1);

        l.setIn(g1);

        setLight(l);

    }

}
