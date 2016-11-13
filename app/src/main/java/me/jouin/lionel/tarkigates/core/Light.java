package me.jouin.lionel.tarkigates.core;

import java.util.ArrayList;
import java.util.List;

public class Light implements Component {

	private List<LightListener> lightListener = new ArrayList<>();

	public void addLightListeners(LightListener l) {
		lightListener.add(l);
	}

	public void tiggerTheLight() {
		for (LightListener l : lightListener) {
			l.switchLight(in.out());
		}
	}

	protected Component in;
	
	public void setIn(Component c) {
		in = c;
	}
	
	@Override
	public boolean out() {
		return false;
	}

	@Override
	public String toString() {
		
		return "La lampe est "+(in.out() ? "allumé" : "eteinte");
	}

	public Component getIn() {
		return in;
	}
}
