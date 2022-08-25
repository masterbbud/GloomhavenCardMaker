package main.entities;

import main.Launcher;


public abstract class Thing {

	public Launcher launcher;
	public boolean show;
	
	public Thing(Launcher launcher) {
		this.launcher = launcher;
		show = false;
	}
	public abstract void draw();
}
