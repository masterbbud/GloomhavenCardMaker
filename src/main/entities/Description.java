package main.entities;

import main.Launcher;

public class Description {

	public Launcher launcher;
	public boolean base;
	public String text;
	public String valueText;
	public String longText;
	public int pips;
	public String type;
	
	public Description(Launcher launcher, boolean base, String text, String valueText, int pips) {
		this.launcher = launcher;
		this.base = base;
		this.text = text;
		this.valueText = valueText;
		this.pips = pips;
		longText = "";
		type = "";
	}
	
	public String getType() {
		String newType = "";
		if (text == "Bless" || text == "Curse" || text == "Disarm" || text == "Immobilize" || text == "Wound" || text == "Muddle" || text == "Stun" || text == "Poison" || text == "Invisible") {
			newType = "Status";
		
		}
		else {
			newType = "Basic";
		}
		return newType;
	}
	public void setType() {
		type = getType();
	}
}
