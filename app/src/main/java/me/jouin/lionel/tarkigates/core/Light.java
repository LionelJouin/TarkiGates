package me.jouin.lionel.tarkigates.core;

public class Light implements Component {

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
		
		return "La lampe est "+(in.out() ? "allum�" : "eteinte");
	}

}
