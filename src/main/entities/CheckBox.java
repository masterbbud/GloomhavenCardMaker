package main.entities;

import main.Launcher;
import processing.core.PVector;

public class CheckBox extends Thing{

	public Boolean state;
	public PVector position;
	public String text;
	
	public CheckBox (Launcher launcher, PVector position, String text) {
		super(launcher);
		state = false;
		this.position = position;
		this.text = text;
		show = false;
	}
	public void draw() {
		launcher.fill(200);
		launcher.stroke(0);
		launcher.strokeWeight(3);
		launcher.rect(position.x, position.y, 30, 30, 8);
		launcher.fill(0);
		launcher.textSize(24);
		launcher.text(text, position.x+43, position.y+23);
		if (state) {
			launcher.drawCheck(position);
		}
	}
	public Boolean clickCheck() {
		if (launcher.mouseX>position.x && launcher.mouseX<position.x+30 && launcher.mouseY>position.y && launcher.mouseY<position.y+30) {
			return true;
		}
		return false;
	}
}
