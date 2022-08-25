package main.entities;

import main.Launcher;

import processing.core.PVector;

public class ColorSlider extends Thing{

	public PVector position;
	public int color;
	
	public ColorSlider(Launcher launcher, PVector position, int color) {
		super(launcher);
		this.position = position;
		this.color = color;
	}
	public void draw() {
		
	}
}
