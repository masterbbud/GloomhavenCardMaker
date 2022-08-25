package main.entities;

import main.Launcher;
import processing.core.PVector;

public class Button extends Thing{
	
	public PVector position;
	public PVector dimensions;
	public boolean show;
	public String text;
	public Action action;
	
	public Button(Launcher launcher, PVector position, PVector dimensions, String text) {
		super(launcher);
		this.position = position;
		this.dimensions = dimensions;
		this.text = text;
		show = false;
	}
	public void draw() {
		launcher.strokeWeight(5);
		launcher.textFont(launcher.majalla);
		launcher.textSize(dimensions.y/3+14);
		launcher.stroke(0);
		if (show == true) {
			launcher.fill(200);
			launcher.rect(position.x,position.y,dimensions.x,dimensions.y);
			launcher.fill(0);
			if (dimensions.y == 50) {
				launcher.text(text,position.x+23,position.y+35);
			}
			else if (dimensions.y == 30) {
				launcher.text(text,position.x+13,position.y+23);
			}
			else if (dimensions.y == 20) {
				launcher.text(text,position.x+3,position.y+17);
			}
			else if (dimensions.y == 40) {
				launcher.textSize(50);
				launcher.text(text,position.x+10,position.y+33);
			}
			else {
				launcher.text(text,position.x+23,position.y+35);
			}
		}
	}
	public boolean clickCheck() {
		if (launcher.mouseX>position.x && launcher.mouseX<position.x+dimensions.x && launcher.mouseY>position.y && launcher.mouseY<position.y+dimensions.y) {
			return true;
		}
		else {
			return false;
		}
	}
}
