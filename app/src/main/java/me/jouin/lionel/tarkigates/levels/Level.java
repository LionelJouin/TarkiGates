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

public abstract class Level {

    protected Light light;
    protected boolean isValid;
    protected int nbSwitchs;
    protected int[] result = new int[3];

    public Level(Light light) {
        setLight(light);
    }

    public boolean isValid() {
        return isValid;
    }

    public int getNbSwitchs() {
        return nbSwitchs;
    }

    public void setLight(Light light) {
        this.light = light;
        checkLevel();
    }

    public Light getLight() {
        return light;
    }

    protected boolean checkLevel() {
        List<Component> alreadyChecked = new ArrayList<>();
        isValid = countSwitchAndIsAValidLevel(light, alreadyChecked);
        return isValid;
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

    public int getResult(int nbClicks) {
        if(nbClicks<=result[0]) {
            return 3;
        } else if (nbClicks<=result[1]) {
            return 2;
        } else if (nbClicks<=result[2]) {
            return 1;
        } else {
            return 0;
        }
    }
}
