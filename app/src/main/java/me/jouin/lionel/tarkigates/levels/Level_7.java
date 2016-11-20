package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.AndGate;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.OrGate;

/**
 * Created by lione on 20/11/2016.
 */

public class Level_7 extends Level {

    public Level_7() {
        super(new Light());

        result[0] = 2;
        result[1] = 3;
        result[2] = 4;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        Switch s3 = new Switch();
        Switch s4 = new Switch();
        LogicGate g1 = new AndGate();

        LogicGate g2 = new OrGate();
        LogicGate g3 = new OrGate();

        LogicGate g4 = new OrGate();
        LogicGate g5 = new AndGate();
        LogicGate g6 = new AndGate();
        Light l = new Light();

        g4.setInA(s1);
        g4.setInB(s2);

        g5.setInA(s2);
        g5.setInB(s3);

        g6.setInA(s3);
        g6.setInB(s4);


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
