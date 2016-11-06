package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.AndGate;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.OrGate;
import me.jouin.lionel.tarkigates.core.gates.XnorGate;

/**
 * Created by lione on 31/10/2016.
 */

public class Level_3 extends Level {

    public Level_3() {
        super(new Light());

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        Switch s3 = new Switch();
        Switch s4 = new Switch();
        LogicGate g1 = new AndGate();
        LogicGate g2 = new OrGate();
        LogicGate g3 = new AndGate();
        LogicGate g5 = new XnorGate();
        LogicGate g6 = new XnorGate();
        Light l = new Light();

        g5.setInA(s2);
        g5.setInB(s3);
        g6.setInA(s3);
        g6.setInB(s4);

        g2.setInA(s1);
        g2.setInB(g5);
        g3.setInA(g5);
        g3.setInB(g6);

        g1.setInA(g2);
        g1.setInB(g3);

        l.setIn(g1);

        setLight(l);

    }

}
