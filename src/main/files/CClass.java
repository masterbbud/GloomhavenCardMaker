package main.files;



import main.entities.Image;
import processing.core.PImage;

public class CClass {
		
	public String name;
		// Class colors (rgb)
	public int cR;
	public int cG;
	public int cB;
	
	public PImage icon;
	
	public CClass(String name, int cR, int cG, int cB, PImage icon) {
		this.cR = cR;
		this.cG = cG;
		this.cB = cB;
		this.name = name;
		this.icon = icon;
	}
}
