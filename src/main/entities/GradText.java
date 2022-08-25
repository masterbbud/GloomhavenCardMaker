package main.entities;

import java.util.ArrayList;

import main.Launcher;
import processing.core.PGraphics;
import processing.core.PVector;


public class GradText{
	
	public PGraphics p;
	public Launcher launcher;
	public String text;
	public PVector position = new PVector(0,0);
	public int c1;
	public int c2;
	public float x;
	public float y;
	public float x2;
	public float y2;
	public int[] pixList;
	
	public GradText(Launcher launcher,String text,float x,float y,float x2,float y2,int c1,int c2,int[] pixList) {
		this.launcher = launcher;
		this.x=x;
		this.y=y;
		this.x2=x2;
		this.y2=y2;
		this.c1=c1;
		this.c2=c2;
		this.text=text;
		this.pixList = pixList;
	}
	public void draw() {
		launcher.imageMode(Launcher.CENTER);
		launcher.image(p, x, y);
	}
	public void getInit() {
		p = launcher.getGText(this);
	}
}
