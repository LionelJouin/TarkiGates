package me.jouin.lionel.tarkigates.levels;

import java.util.ArrayList;
import java.util.List;

import me.jouin.lionel.tarkigates.core.Component;
import me.jouin.lionel.tarkigates.core.Light;
import me.jouin.lionel.tarkigates.core.Switch;
import me.jouin.lionel.tarkigates.core.gates.LogicGate;
import me.jouin.lionel.tarkigates.core.gates.NotGate;

/**
 * Created by lione on 31/10/2016.
 */

public class Level {

    protected Light light;
    protected boolean isValid;
    protected int nbSwitchs;

    public Level(Light light) {
        this.light = light;
        checkLevel();
    }

    public boolean isValid() {
        return isValid;
    }

    public int getNbSwitchs() {
        return nbSwitchs;
    }

    protected void checkLevel() {
        List<Component> alreadyChecked = new ArrayList<>();
        isValid = countSwitchAndIsAValidLevel(light, alreadyChecked);
    }

    private boolean countSwitchAndIsAValidLevel(Component c, List<Component> alreadyChecked) {
        boolean r = false;
        if (!alreadyChecked.contains(c)) {
            alreadyChecked.add(c);
            if (c instanceof LogicGate) {
                if (((LogicGate) c).getInA() == null)
                    return false;
                r = countSwitchAndIsAValidLevel(((LogicGate) c).getInA(), alreadyChecked);
                if (((LogicGate) c).getInB() == null)
                    return false;
                r = countSwitchAndIsAValidLevel(((LogicGate) c).getInB(), alreadyChecked);
            } else if (c instanceof NotGate) {
                if (((NotGate) c).getIn() == null)
                    return false;
                r = countSwitchAndIsAValidLevel(((NotGate) c).getIn(), alreadyChecked);
            } else if (c instanceof Switch) {
                nbSwitchs++;
                r = true;
            } else if (c instanceof Light) {
                if (((Light) c).getIn() == null)
                    return false;
                r = countSwitchAndIsAValidLevel(((Light) c).getIn(), alreadyChecked);
            }
        }
        return r;
    }
}
