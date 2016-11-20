package me.jouin.lionel.tarkigates.levels;

import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.AndGate;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.NandGate;
import me.jouin.lionel.tarkigates.core.gates.NorGate;
import me.jouin.lionel.tarkigates.core.gates.NotGate;
import me.jouin.lionel.tarkigates.core.gates.OrGate;
import me.jouin.lionel.tarkigates.core.gates.XnorGate;
import me.jouin.lionel.tarkigates.core.gates.XorGate;

/**
 * Created by lione on 20/11/2016.
 */

public class Level_13 extends Level {

    public Level_13() {
        super(new Light());

        result[0] = 3;
        result[1] = 5;
        result[2] = 7;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        Switch s3 = new Switch();
        Switch s4 = new Switch();
        Switch s5 = new Switch();
        Switch s6 = new Switch();

        LogicGate g1 = new NorGate();

        LogicGate g2 = new XorGate();
        LogicGate g3 = new NandGate();

        LogicGate g4 = new OrGate();
        LogicGate g5 = new NorGate();
        LogicGate g6 = new OrGate();

        LogicGate g7 = new NorGate();
        LogicGate g8 = new NandGate();
        LogicGate g9 = new OrGate();
        LogicGate g10 = new AndGate();

        LogicGate g11 = new NandGate();
        LogicGate g12 = new AndGate();
        LogicGate g13 = new XnorGate();
        LogicGate g14 = new NorGate();
        LogicGate g15 = new OrGate();

        NotGate n1 = new NotGate();
        NotGate n2 = new NotGate();
        Light l = new Light();

        g11.setInA(s1);
        g11.setInB(s2);

        g12.setInA(s2);
        g12.setInB(s3);

        g13.setInA(s3);
        g13.setInB(s4);

        n2.setIn(s4);
        g14.setInA(n2);
        g14.setInB(s5);

        g15.setInA(s5);
        g15.setInB(s6);


        g7.setInA(g11);
        g7.setInB(g12);

        g8.setInA(g12);
        g8.setInB(g13);

        g9.setInA(g13);
        g9.setInB(g14);

        g10.setInA(g14);
        g10.setInB(g15);


        g4.setInA(g7);
        g4.setInB(g8);

        g5.setInA(g8);
        g5.setInB(g9);

        g6.setInA(g9);
        g6.setInB(g10);


        n1.setIn(g5);
        g2.setInA(g4);
        g2.setInB(g5);

        g3.setInA(n1);
        g3.setInB(g6);


        g1.setInA(g2);
        g1.setInB(g3);

        l.setIn(g1);

        setLight(l);

    }

}
