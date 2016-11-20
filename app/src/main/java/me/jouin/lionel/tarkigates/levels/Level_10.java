package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.AndGate;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.NorGate;
import me.jouin.lionel.tarkigates.core.gates.OrGate;
import me.jouin.lionel.tarkigates.core.gates.XnorGate;
import me.jouin.lionel.tarkigates.core.gates.XorGate;

/**
 * Created by lione on 20/11/2016.
 */

public class Level_10 extends Level {

    public Level_10() {
        super(new Light());

        result[0] = 3;
        result[1] = 4;
        result[2] = 6;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        Switch s3 = new Switch();
        Switch s4 = new Switch();
        Switch s5 = new Switch();

        LogicGate g1 = new NorGate();

        LogicGate g2 = new OrGate();
        LogicGate g3 = new XnorGate();

        LogicGate g4 = new NorGate();
        LogicGate g5 = new XorGate();
        LogicGate g6 = new AndGate();

        LogicGate g7 = new NorGate();
        LogicGate g8 = new XorGate();
        LogicGate g9 = new AndGate();
        LogicGate g10 = new AndGate();

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
