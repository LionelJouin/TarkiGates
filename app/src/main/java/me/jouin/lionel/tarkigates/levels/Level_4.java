package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.AndGate;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.NandGate;
import me.jouin.lionel.tarkigates.core.gates.NorGate;
import me.jouin.lionel.tarkigates.core.gates.OrGate;
import me.jouin.lionel.tarkigates.core.gates.XnorGate;
import me.jouin.lionel.tarkigates.core.gates.XorGate;

/**
 * Created by lione on 31/10/2016.
 */

public class Level_4 extends Level {

    public Level_4() {
        super(new Light());

        result[0] = 2;
        result[1] = 3;
        result[2] = 4;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        Switch s3 = new Switch();
        Switch s4 = new Switch();
        Switch s5 = new Switch();
        LogicGate g1 = new AndGate();
        LogicGate g2 = new OrGate();
        LogicGate g3 = new AndGate();
        LogicGate g4 = new XnorGate();
        LogicGate g5 = new XnorGate();
        LogicGate g6 = new XnorGate();
        LogicGate g7 = new XorGate();
        LogicGate g8 = new XnorGate();
        LogicGate g9 = new NorGate();
        LogicGate g10 = new NandGate();
        Light l = new Light();

        g7.setInA(s1);
        g7.setInB(s2);
        g8.setInA(s2);
        g8.setInB(s3);
        g9.setInA(s3);
        g9.setInB(s4);
        g10.setInA(s4);
        g10.setInB(s5);

        g4.setInA(g7);
        g4.setInB(g8);
        g5.setInA(g8);
        g5.setInB(g9);
        g6.setInA(g9);
        g6.setInB(g10);

        g2.setInA(g4);
        g2.setInB(g5);
        g3.setInA(g5);
        g3.setInB(g6);

        g1.setInA(g2);
        g1.setInB(g3);

        l.setIn(g1);

        setLight(l);

    }

}
