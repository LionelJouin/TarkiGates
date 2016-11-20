package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.AndGate;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.NandGate;
import me.jouin.lionel.tarkigates.core.gates.NorGate;
import me.jouin.lionel.tarkigates.core.gates.NotGate;
import me.jouin.lionel.tarkigates.core.gates.XnorGate;
import me.jouin.lionel.tarkigates.core.gates.XorGate;

/**
 * Created by lione on 20/11/2016.
 */

public class Level_8 extends Level {

    public Level_8() {
        super(new Light());

        result[0] = 2;
        result[1] = 3;
        result[2] = 4;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        Switch s3 = new Switch();
        Switch s4 = new Switch();
        LogicGate g1 = new NorGate();

        LogicGate g2 = new XnorGate();
        LogicGate g3 = new XorGate();

        LogicGate g4 = new NandGate();
        LogicGate g5 = new AndGate();

        NotGate n1 = new NotGate();
        NotGate n2 = new NotGate();
        Light l = new Light();

        n1.setIn(s3);

        g4.setInA(s1);
        g4.setInB(s2);

        g5.setInA(s2);
        g5.setInB(n1);

        n2.setIn(g4);

        g2.setInA(n2);
        g2.setInB(g5);

        g3.setInA(g5);
        g3.setInB(s4);


        g1.setInA(g2);
        g1.setInB(g3);

        l.setIn(g1);

        setLight(l);

    }

}
