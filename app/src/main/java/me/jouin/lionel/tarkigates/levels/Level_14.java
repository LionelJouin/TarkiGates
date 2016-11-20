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

public class Level_14 extends Level {

    public Level_14() {
        super(new Light());

        result[0] = 2;
        result[1] = 4;
        result[2] = 6;

        Switch s1 = new Switch();
        Switch s2 = new Switch();
        Switch s3 = new Switch();
        Switch s4 = new Switch();
        Switch s5 = new Switch();
        Switch s6 = new Switch();

        LogicGate g1 = new AndGate();

        LogicGate g2 = new XorGate();
        LogicGate g3 = new XnorGate();

        LogicGate g4 = new OrGate();
        LogicGate g5 = new NorGate();
        LogicGate g6 = new AndGate();

        LogicGate g7 = new NorGate();
        LogicGate g8 = new NandGate();
        LogicGate g9 = new NorGate();
        LogicGate g10 = new XorGate();

        LogicGate g11 = new AndGate();
        LogicGate g12 = new AndGate();
        LogicGate g13 = new XnorGate();

        NotGate n1 = new NotGate();
        NotGate n2 = new NotGate();
        Light l = new Light();


        g11.setInA(s2);
        g11.setInB(s3);

        g12.setInA(s3);
        g12.setInB(s4);

        g13.setInA(s4);
        g13.setInB(s5);


        g7.setInA(s1);
        g7.setInB(g11);

        g8.setInA(g11);
        g8.setInB(g12);

        g9.setInA(g12);
        g9.setInB(g13);

        g10.setInA(g13);
        g10.setInB(s6);


        n2.setIn(g9);
        g4.setInA(g7);
        g4.setInB(g8);

        g5.setInA(g8);
        g5.setInB(n2);

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
