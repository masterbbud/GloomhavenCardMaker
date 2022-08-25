package main.entities;

import processing.core.PImage;
import processing.core.PVector;

public class Image {

	public PImage image;
	public PVector position;
	public PVector dimensions;
	
	public Image(PImage image, PVector position, PVector dimensions) {
		this.image = image;
		this.position = position;
		this.dimensions = dimensions;
	}
}
