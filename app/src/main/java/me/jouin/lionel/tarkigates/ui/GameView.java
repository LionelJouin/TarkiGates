package me.jouin.lionel.tarkigates.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.jouin.lionel.tarkigates.Positions;
import me.jouin.lionel.tarkigates.core.Component;
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
import me.jouin.lionel.tarkigates.levels.Level;
import me.jouin.lionel.tarkigates.ui.gates.AndGateUI;
import me.jouin.lionel.tarkigates.ui.gates.LogicGateUI;
import me.jouin.lionel.tarkigates.ui.gates.NandGateUI;
import me.jouin.lionel.tarkigates.ui.gates.NorGateUI;
import me.jouin.lionel.tarkigates.ui.gates.NotGateUI;
import me.jouin.lionel.tarkigates.ui.gates.OrGateUI;
import me.jouin.lionel.tarkigates.ui.gates.XnorGateUI;
import me.jouin.lionel.tarkigates.ui.gates.XorGateUI;

/**
 * Created by lione on 31/10/2016.
 */

public class GameView extends RelativeLayout {

    private Paint paint = new Paint();
    private Level level;
    private Context context;
    private Map<Integer, Integer> stateColumns;
    private Map<Integer, Integer> heightTopColumns;
    private Map<Integer, Integer> heightBottomColumns;
    private List<Component> alreadyChecked;

    private Map<Component, ComponentUI> components;

    private LayoutParams thisLayoutParams;

    private int nbClicks = 0;

    private boolean listenerState = true;

    public GameView(Context context, Level level, WebView wv) {
        super(context);
        this.level = level;
        this.context = context;

        nbClicks = 0;

        setLight(level.getLight());
        repositioning();
        positioning();
        setWires();

        for (Map.Entry<Component, ComponentUI> c : components.entrySet()) {
            if (c.getKey() instanceof Switch) {
                SwitchUI sUI = (SwitchUI) c.getValue();
                final Switch key = (Switch) c.getKey();
                sUI.switchButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listenerState) {
                            nbClicks++;
                            key.changeState();
                            invalidate();
                        }
                    }
                });
            }
        }

        this.setLayoutParams(thisLayoutParams);

        wv.loadData("<html>" +
                "<header><meta name='viewport' content='width=device-width, initial-scale=1' /></header>" +
                "<body style='margin: 0; padding: 0;height:"+thisLayoutParams.height+";width:"+thisLayoutParams.width+"px;'>" +
                //"<div style='height:"+Positions.getInstance().actualLevelHeight+"px;width:"+Positions.getInstance().actualLevelWidth+"px;background:red;'></div>" +
                "</body>" +
                "</html>", "text/html; charset=utf-8", "UTF-8");

        //LayoutParams a = new LayoutParams(Positions.getInstance().actualLevelWidth, Positions.getInstance().actualLevelHeight);
        //this.setLayoutParams(a);
    }

    private void setLight(final Light light) {
        LightUI lightUI = new LightUI(Positions.getInstance().lightX, Positions.getInstance().lightY);

        lightUI.setView(context);

        lightUI.addSwitchUIListeners(new LightUIListener() {
            @Override
            public void switchLightUI() {
                light.tiggerTheLight();
            }
        });

        stateColumns = new HashMap<>();
        alreadyChecked = new ArrayList<>();
        heightTopColumns = new HashMap<>();
        heightBottomColumns = new HashMap<>();
        components = new HashMap<>();
        components.put(light, lightUI);
        setComponents(light.getIn(), lightUI, 1);
    }

    /*
    state = colonne & nombre de composants par colonne
    stateColumns.get(state) = ligne - 1
     */
    private void setComponents(Component c, ComponentUI cParentUI, int state) {
        if (!alreadyChecked.contains(c)) {
            alreadyChecked.add(c);
            ComponentUI componentUI = null;
            int x = cParentUI.x - Positions.getInstance().spaceBetweenComponentsX - cParentUI.width;
            if (!(c instanceof NotGate))
                setStateColumns(state);
            int y = 0;

            if (c instanceof LogicGate) {
                if (c instanceof AndGate) {
                    componentUI = new AndGateUI(x, y);
                } else if (c instanceof NandGate) {
                    componentUI = new NandGateUI(x, y);
                } else if (c instanceof NorGate) {
                    componentUI = new NorGateUI(x, y);
                } else if (c instanceof OrGate) {
                    componentUI = new OrGateUI(x, y);
                } else if (c instanceof XnorGate) {
                    componentUI = new XnorGateUI(x, y);
                } else if (c instanceof XorGate) {
                    componentUI = new XorGateUI(x, y);
                }
                if (componentUI != null) {
                    setComponentPosition(componentUI, state);
                    componentUI.setView(context);

                    LogicGate component = (LogicGate) c;

                    components.put(component, componentUI);

                    setComponents(component.getInA(), componentUI, state + 1);
                    setComponents(component.getInB(), componentUI, state + 1);
                }
            } else if (c instanceof NotGate) {

                Component componentBefore = getComponentBefore(c);
                if (componentBefore instanceof LogicGate) {
                    LogicGate componentBeforeLogicGate = (LogicGate) componentBefore;
                    LogicGateUI componentBeforeLogicGateUI = (LogicGateUI) components.get(componentBeforeLogicGate);
                    if (c.equals(componentBeforeLogicGate.getInA()))
                        y = componentBeforeLogicGateUI.getInAX();
                    else if (c.equals(componentBeforeLogicGate.getInB()))
                        y = componentBeforeLogicGateUI.getInBY();
                } else if (componentBefore instanceof NotGate) {
                    NotGate componentBeforeNotGate = (NotGate) componentBefore;
                    NotGateUI componentBeforeNotGateUI = (NotGateUI) components.get(componentBeforeNotGate);
                    y = componentBeforeNotGateUI.getInY();
                } else if (componentBefore instanceof Light) {
                    Light componentBeforeLight = (Light) componentBefore;
                    LightUI componentBeforeLightUI = (LightUI) components.get(componentBeforeLight);
                    y = componentBeforeLightUI.getInY();
                }

                componentUI = new NotGateUI(cParentUI.x, y);
                componentUI.x = componentUI.x-componentUI.width;
                componentUI.y = componentUI.y-componentUI.width/2;

                componentUI.setView(context);

                NotGate component = (NotGate) c;

                components.put(component, componentUI);

                setComponents(component.getIn(), componentUI, state);
            } else if (c instanceof Switch) {
                componentUI = new SwitchUI(x, y);
                setComponentPosition(componentUI, state);
                setStateColumns(state + 1); // fix les holes provoqués par le fait que les switchs ne soient pas obligatoirement au meme niveau

                componentUI.setView(context);

                Switch component = (Switch) c;
                components.put(component, componentUI);
            }
        }
    }

    private void setComponentPosition(ComponentUI componentUI, int state) {
        if (state%2 == 1 && (stateColumns.get(state)+1) == (int) Math.ceil((double) state/2)) { // milieu
            componentUI.y = Positions.getInstance().realLevelHeight / 2 - componentUI.height / 2;
        } else if ((stateColumns.get(state)+1) > (int) Math.ceil((double) state/2)) { // base
            setHeightBottomColumns(state);
            componentUI.y = heightBottomColumns.get(state);
            if (componentUI instanceof SwitchUI) // fix les holes provoqués par le fait que les switchs ne soient pas obligatoirement au meme niveau
                setHeightBottomColumns(state+1);
        } else { // haut
            setHeightTopColumns(state);
            componentUI.y = heightTopColumns.get(state);
            if (componentUI instanceof SwitchUI) // fix les holes provoqués par le fait que les switchs ne soient pas obligatoirement au meme niveau
                setHeightTopColumns(state + 1);
        }
    }

    private void setStateColumns(int state) {
        if(stateColumns.get(state) != null) {
            stateColumns.put(state, stateColumns.get(state)+1);
        } else {
            stateColumns.put(state, 0);
        }
    }

    private void setHeightBottomColumns(int state) {
        if(heightBottomColumns.get(state) != null) {
            heightBottomColumns.put(state, heightBottomColumns.get(state)+Positions.getInstance().componentSize+Positions.getInstance().spaceBetweenComponentsY);
        } else {
            if (state%2 == 1)
                heightBottomColumns.put(state, Positions.getInstance().lightY+Positions.getInstance().lightSize+Positions.getInstance().spaceBetweenComponentsY);
            else
                heightBottomColumns.put(state, Positions.getInstance().lightY+Positions.getInstance().lightSize);
        }
    }

    private void setHeightTopColumns(int state) {
        if(heightTopColumns.get(state) != null) {
            heightTopColumns.put(state, heightTopColumns.get(state)+Positions.getInstance().componentSize+Positions.getInstance().spaceBetweenComponentsY);
        } else {
            if (state%2 == 1)
                heightTopColumns.put(state, Positions.getInstance().lightY-(Positions.getInstance().lightSize+Positions.getInstance().spaceBetweenComponentsY)*(int) Math.floor(state/2));
            else
                heightTopColumns.put(state, Positions.getInstance().lightY-(Positions.getInstance().lightSize+Positions.getInstance().spaceBetweenComponentsY)*(state/2)+Positions.getInstance().spaceBetweenComponentsY);

        }
    }

    public void setWires() {
        for(Map.Entry<Component, ComponentUI> c : components.entrySet()) {
            if (c.getKey() instanceof LogicGate) {

                Component component = ((LogicGate) c.getKey()).getInA();
                ComponentUI cUI = components.get(component);
                if (cUI != null)
                    c.getValue().addWires(cUI.getOutX(), cUI.getOutY());

                component = ((LogicGate) c.getKey()).getInB();
                cUI = components.get(component);
                if (cUI != null)
                    c.getValue().addWires(cUI.getOutX(), cUI.getOutY());

            } else if (c.getKey() instanceof NotGate) {
                Component component = ((NotGate) c.getKey()).getIn();
                ComponentUI cUI = components.get(component);
                if (cUI != null)
                    c.getValue().addWires(cUI.getOutX(), cUI.getOutY());
            } else if (c.getKey() instanceof Light) {
                Component component = ((Light) c.getKey()).getIn();
                ComponentUI cUI = components.get(component);
                if (cUI != null)
                    c.getValue().addWires(cUI.getOutX(), cUI.getOutY());
            }
        }
    }

    public Component getComponentBefore(Component component) {
        for (Map.Entry<Component, ComponentUI> c : components.entrySet()) {
            if (c.getKey() instanceof LogicGate) {
                if(((LogicGate) c.getKey()).getInA().equals(component) || ((LogicGate) c.getKey()).getInB().equals(component))
                    return c.getKey();
            } else if (c.getKey() instanceof NotGate) {
                if(((NotGate) c.getKey()).getIn().equals(component))
                    return c.getKey();
            } else if (c.getKey() instanceof Light) {
                if (((Light) c.getKey()).getIn().equals(component))
                    return c.getKey();
            }
        }
        return null;
    }

    public void repositioning() {
        int height = 0;
        int width = 0;

        Integer xMax = null;
        Integer xMin = null;
        Integer yMax = null;
        Integer yMin = null;

        for (Map.Entry<Component, ComponentUI> c : components.entrySet()) {
            int currentXMin = c.getValue().x;
            int currentXMax = currentXMin+c.getValue().width;
            int currentYMin = c.getValue().y;
            int currentYMax = currentYMin+c.getValue().height;
            if (xMax == null && xMin == null && yMax == null && yMin == null) {
                xMax = currentXMax;
                xMin = currentXMin;
                yMax = currentYMax;
                yMin = currentYMin;
            } else {
                if (currentXMax > xMax)
                    xMax = currentXMax;
                if (currentXMin < xMin)
                    xMin = currentXMin;
                if (currentYMin < yMin)
                    yMin = currentYMin;
                if (currentYMax > yMax)
                    yMax = currentYMax;
            }
        }

        if (xMax != null && xMin != null && yMax != null && yMin != null) {
            width = xMax - xMin + Positions.getInstance().marginLeft*2;
            height = yMax - yMin + Positions.getInstance().marginTop*2;
            int repX;
            int repY;
            if (xMin >= Positions.getInstance().marginLeft)
                repX = Positions.getInstance().marginLeft - xMin;
            else
                repX = Math.abs(xMin) + Positions.getInstance().marginLeft;
            if (yMin >= Positions.getInstance().marginTop)
                repY = Positions.getInstance().marginTop - yMin;
            else
                repY = Math.abs(yMin) + Positions.getInstance().marginTop;
            for (Map.Entry<Component, ComponentUI> c : components.entrySet()) {
                c.getValue().repositioning(repX, repY);
            }

            if (width<Positions.getInstance().windowWidth)
                width = Positions.getInstance().windowWidth;
            if (height<Positions.getInstance().windowHeight)
                height = Positions.getInstance().windowHeight;

            thisLayoutParams = new LayoutParams(width, height);
        }
    }

    public void positioning() {
        for (Map.Entry<Component, ComponentUI> c : components.entrySet()) {
            addView(c.getValue().getView());
        }
    }

    @Override
    public void onDraw(Canvas canvas) {

        for (Map.Entry<Component, ComponentUI> c : components.entrySet()) {
            if (c.getKey() instanceof LogicGate) {

                Component component = ((LogicGate) c.getKey()).getInA();
                if (component.out())
                    c.getValue().wires.get(0).draw(canvas, paint, true);
                else
                    c.getValue().wires.get(0).draw(canvas, paint);

                component = ((LogicGate) c.getKey()).getInB();
                if (component.out())
                    c.getValue().wires.get(1).draw(canvas, paint, true);
                else
                    c.getValue().wires.get(1).draw(canvas, paint);

            } else if (c.getKey() instanceof NotGate) {
                Component component = ((NotGate) c.getKey()).getIn();
                if (component.out())
                    c.getValue().wires.get(0).draw(canvas, paint, true);
                else
                    c.getValue().wires.get(0).draw(canvas, paint);
            } else if (c.getKey() instanceof Light) {
                Component component = ((Light) c.getKey()).getIn();
                if (component.out())
                    c.getValue().wires.get(0).draw(canvas, paint, true);
                else
                    c.getValue().wires.get(0).draw(canvas, paint);
            }
        }

    }

    public void setListenerState(boolean listenerState) {
        this.listenerState = listenerState;
    }
    public boolean getListenerState() {
        return listenerState;
    }

    public int getNbClicks() {
        return nbClicks;
    }
}