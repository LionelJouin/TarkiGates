package me.jouin.lionel.tarkigates.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Button;
import android.widget.ImageView;
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



    public GameView(Context context, Level level) {
        super(context);
        this.level = level;
        this.context = context;

        setLight(level.getLight());
        setWires();

        for (Map.Entry<Component, ComponentUI> c : components.entrySet()) {
            if (c.getKey() instanceof Switch) {

            }
        }

        LayoutParams a = new LayoutParams(Positions.getInstance().actualLevelWidth, Positions.getInstance().actualLevelHeight);
        this.setLayoutParams(a);
    }

    private void setLight(Light light) {
        ImageView lightImageView = new ImageView(context);
        LightUI lightUI = new LightUI(Positions.getInstance().lightX, Positions.getInstance().lightY);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), lightUI.imgId);
        bmp = Bitmap.createScaledBitmap(bmp, lightUI.width, lightUI.height, true);
        lightImageView.setImageBitmap(bmp);
        lightImageView.setY(lightUI.y);
        lightImageView.setX(lightUI.x);
        addView(lightImageView);
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
                    ImageView componentImageView = new ImageView(context);
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), componentUI.imgId);
                    bmp = Bitmap.createScaledBitmap(bmp, componentUI.width, componentUI.height, true);
                    componentImageView.setImageBitmap(bmp);
                    componentImageView.setImageBitmap(bmp);
                    componentImageView.setY(componentUI.y);
                    componentImageView.setX(componentUI.x);
                    addView(componentImageView);
                    LogicGate component = (LogicGate) c;

                    components.put(component, componentUI);

                    setComponents(component.getInA(), componentUI, state + 1);
                    setComponents(component.getInB(), componentUI, state + 1);
                }
            } else if (c instanceof NotGate) {
                componentUI = new NotGateUI(x, y);
            } else if (c instanceof Switch) {
                componentUI = new SwitchUI(x, y);
                setComponentPosition(componentUI, state);
                setStateColumns(state + 1); // fix les holes provoqués par le fait que les switchs ne soient pas obligatoirement au meme niveau
                Button b = new Button(context);
                b.setLayoutParams(new LayoutParams(componentUI.width, componentUI.height));
                b.setY(componentUI.y);
                b.setX(componentUI.x);
                b.setText("k");
                addView(b);

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
        } else { // haut
            setHeightTopColumns(state);
            componentUI.y = heightTopColumns.get(state);
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
            heightTopColumns.put(state, heightTopColumns.get(state)-Positions.getInstance().componentSize-Positions.getInstance().spaceBetweenComponentsY);
        } else {
            if (state%2 == 1)
                heightTopColumns.put(state, Positions.getInstance().lightY-Positions.getInstance().lightSize-Positions.getInstance().spaceBetweenComponentsY);
            else
                heightTopColumns.put(state, Positions.getInstance().lightY-Positions.getInstance().lightSize);
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
            } else if (c.getKey() instanceof Light) {
                Component component = ((Light) c.getKey()).getIn();
                ComponentUI cUI = components.get(component);
                if (cUI != null)
                    c.getValue().addWires(cUI.getOutX(), cUI.getOutY());
            }
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

                System.out.println("koukou "+component+" : "+component.out());

                component = ((LogicGate) c.getKey()).getInB();
                if (component.out())
                    c.getValue().wires.get(1).draw(canvas, paint, true);
                else
                    c.getValue().wires.get(1).draw(canvas, paint);

                System.out.println("koukou "+component);
            } else if (c.getKey() instanceof NotGate) {
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

}