package me.jouin.lionel.tarkigates.core.gates;

public class OrGate extends LogicGate {

	@Override
	public boolean out() {
		return (inA.out() || inB.out());
	}
	
}
