package main.entities;

import java.util.ArrayList;

import main.Launcher;

public class Action {

	public Launcher launcher;
	
	public ArrayList<Description> descList;
	
	
	
	public Action(Launcher launcher) {
		this.launcher = launcher;
		descList = new ArrayList<Description>();
	}
}
