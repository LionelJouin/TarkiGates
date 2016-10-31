package me.jouin.lionel.tarkigates.core.gates;

import me.jouin.lionel.tarkigates.core.Component;

public abstract class LogicGate implements Component {

	protected Component inA;
	protected Component inB;
	
	public void setInA(Component c) {
		inA = c;
	}
	
	public void setInB(Component c) {
		inB = c;
	}
	
}
