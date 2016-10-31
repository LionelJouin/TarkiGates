package me.jouin.lionel.tarkigates.core.gates;

import me.jouin.lionel.tarkigates.core.Component;

public class NotGate implements Component {

	protected Component in;
	
	@Override
	public boolean out() {
		return !in.out();
	}

	public Component getIn() {
		return in;
	}
}
