package me.jouin.lionel.tarkigates.core;

public class Switch implements Component {

	public Boolean state = false;

	public void changeState() {
		state = !state;
	}
	
	@Override
	public boolean out() {
		return state;
	}

	@Override
	public String toString() {
		return "Switch [state=" + state + "]";
	}
	
}
