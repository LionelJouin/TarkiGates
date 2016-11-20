package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.NandGate;
import me.jouin.lionel.tarkigates.core.gates.NotGate;

/**
 * Created by lione on 31/10/2016.
 */

public class Level_3 extends Level {

    public Level_3() {
        super(new Light());

        result[0] = 2;
        result[1] = 3;
        result[2] = 4;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        LogicGate g1 = new NandGate();
        NotGate n1 = new NotGate();
        Light l = new Light();

        g1.setInA(s1);
        g1.setInB(s2);

        n1.setIn(g1);

        l.setIn(n1);

        setLight(l);


    }

}
