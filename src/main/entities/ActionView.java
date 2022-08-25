package main.entities;

import main.Launcher;
import processing.core.PGraphics;
import processing.core.PVector;

public class ActionView {

	public Launcher launcher;
	public ActionGraphic aGraphic;
	public PVector position;
	public Boolean top;
	public String type;
	public PVector boundBox;
	
	public PVector oldPos;
	
	public ActionView (Launcher launcher, ActionGraphic aGraphic, PVector position, Boolean top) {
		
		//IMPORTANT
		//ACTIONVIEWS ARE SORTA CENTERED!!!
		// -- Centered X, Top aligned Y --
		
		//Big: 12
		//Small: 8
		
		this.launcher = launcher;
		this.aGraphic = aGraphic;
		this.position = position;
		this.top = top;
		boundBox = new PVector(0,0);
		oldPos = new PVector(0,0);
		type = "Basic";
		
	}
	public void draw(Boolean g) {
		boundBox = getBB();
		int counter = 0;
		launcher.textAlign(launcher.CENTER,launcher.TOP);
		launcher.fill(255);
		launcher.textFont(launcher.majalla);
		oldPos.set(position);
		position.x -= launcher.bigPos.x*launcher.displayWidth*launcher.res;
		position.y -= launcher.bigPos.y*launcher.displayHeight*launcher.res;
		for (Description d : aGraphic.action.descList) {
			launcher.textFont(launcher.majalla);
			if (d.base) {
				launcher.textSize(14*launcher.tScale);
			}
			else {
				launcher.textSize(10*launcher.tScale);
			}
			float gapAmt = getGap(d);
			String gapS = "";
			while (launcher.textWidth(gapS)<gapAmt-1) {
				gapS += " ";
			}
			PVector trans = new PVector(0,0);
			float scale = 0;
			launcher.imageMode(launcher.CENTER);
			if (d.base) {
				launcher.textSize(14*launcher.tScale);
				if (d.longText != "") {
					if (!g) {
						launcher.drawShadowText(d.longText,position,launcher.majalla,14*launcher.tScale);
					}
					launcher.text(d.longText,position.x,position.y-3*launcher.tScale);
				}
				else if (d.value != 0) {
					if (!g) {
						launcher.drawShadowText(d.text+gapS+d.value,position,launcher.majalla,14*launcher.tScale);
					}
					
					trans = new PVector(position.x-launcher.textWidth(d.text+gapS+d.value)/2+launcher.textWidth(d.text)+launcher.textWidth(gapS)/2,position.y+6*launcher.tScale);
					
				}
				else {
					if (!g) {
						launcher.drawShadowText(d.text+gapS+d.value,position,launcher.majalla,14*launcher.tScale);
					}
					trans = new PVector(position.x-launcher.textWidth(d.text+gapS+d.valueText)/2+launcher.textWidth(d.text)+launcher.textWidth(gapS)/2,position.y+6*launcher.tScale);
					
				}
				
				scale = 15*launcher.tScale;
				
			}
			else {
				launcher.textSize(10*launcher.tScale);
				scale = 11*launcher.tScale;
				if (d.value != 0) {
					//launcher.text(d.text+gapS+d.value,position.x,position.y+launcher.tScale*16+launcher.tScale*12*(counter-1));
					trans = new PVector(position.x-launcher.textWidth(d.text+gapS+d.value)/2+launcher.textWidth(d.text)+launcher.textWidth(gapS)/2,position.y+launcher.tScale*16+launcher.tScale*12*(counter-1)+launcher.tScale*7);

				}
				else {
					//launcher.text(d.text+gapS+d.valueText,position.x,position.y+launcher.tScale*16+launcher.tScale*12*(counter-1));
					trans = new PVector(position.x-launcher.textWidth(d.text+gapS+d.valueText)/2+launcher.textWidth(d.text)+launcher.textWidth(gapS)/2,position.y+launcher.tScale*16+launcher.tScale*12*(counter-1)+launcher.tScale*7);
				}
				
								
			}
			trans.x ++;
			
			if (d.type == "Status") {
				trans.x += 3*launcher.tScale;
			}
			
			Image image = launcher.bless;
			if (d.text == "Bless") {
				image = launcher.bless;
			}
			if (d.text == "Attack") {
				image = launcher.attack;
			}
			if (d.text == "Range") {
				image = launcher.range;
			}
			if (!g) {
				launcher.drawShadowImage(image, trans, new PVector(scale,scale));
			}
			
			
			float tSize = 1;
			if (d.base) {
				tSize = 14*launcher.tScale;
				
			}
			else {
				tSize = 10*launcher.tScale;
			}
			String text = "";
			if (d.value != 0) {
				text = d.text+gapS+d.value;
			}
			else {
				text = d.text+gapS+d.valueText;
			}
			if (g) {
				launcher.imageMode(launcher.CORNER);
				getShadow(new PVector(position.x,trans.y),text,tSize,trans,image,scale);
				launcher.imageMode(launcher.CENTER);
			}
			if (d.base) {
				launcher.text(text,position.x,position.y-3*launcher.tScale);

			}
			else {
				launcher.text(text,position.x,position.y+launcher.tScale*16+launcher.tScale*12*(counter-1));

			}
			launcher.image(image.image,trans.x,trans.y,scale,scale);
			counter++;
			launcher.imageMode(launcher.CORNER);
		}
		
		launcher.textAlign(launcher.LEFT,launcher.BASELINE);
		position.set(oldPos);
	}
	public PGraphics drawBig(Boolean g, PGraphics pg) {
		pg.beginDraw();
		boundBox = getBB();
		int counter = 0;
		pg.textAlign(launcher.CENTER,launcher.TOP);
		pg.fill(255);
		pg.textFont(launcher.majalla);
		oldPos.set(position);
		for (Description d : aGraphic.action.descList) {
			pg.textFont(launcher.majalla);
			if (d.base) {
				pg.textSize(14*launcher.tScale);
			}
			else {
				pg.textSize(10*launcher.tScale);
			}
			float gapAmt = getGap(d);
			String gapS = "";
			while (launcher.textWidth(gapS)<gapAmt-1) {
				gapS += " ";
			}
			PVector trans = new PVector(0,0);
			float scale = 0;
			pg.imageMode(launcher.CENTER);
			if (d.base) {
				pg.textSize(14*launcher.tScale);
				if (d.longText != "") {
					if (!g) {
						pg.image(launcher.getShadow(trans, x, y, text, font, size, trans, i, d)(d.longText,position,launcher.majalla,14*launcher.tScale);
					}
					launcher.text(d.longText,position.x,position.y-3*launcher.tScale);
				}
				else if (d.value != 0) {
					if (!g) {
						launcher.drawShadowText(d.text+gapS+d.value,position,launcher.majalla,14*launcher.tScale);
					}
					
					trans = new PVector(position.x-launcher.textWidth(d.text+gapS+d.value)/2+launcher.textWidth(d.text)+launcher.textWidth(gapS)/2,position.y+6*launcher.tScale);
					
				}
				else {
					if (!g) {
						launcher.drawShadowText(d.text+gapS+d.value,position,launcher.majalla,14*launcher.tScale);
					}
					trans = new PVector(position.x-launcher.textWidth(d.text+gapS+d.valueText)/2+launcher.textWidth(d.text)+launcher.textWidth(gapS)/2,position.y+6*launcher.tScale);
					
				}
				
				scale = 15*launcher.tScale;
				
			}
			else {
				launcher.textSize(10*launcher.tScale);
				scale = 11*launcher.tScale;
				if (d.value != 0) {
					//launcher.text(d.text+gapS+d.value,position.x,position.y+launcher.tScale*16+launcher.tScale*12*(counter-1));
					trans = new PVector(position.x-launcher.textWidth(d.text+gapS+d.value)/2+launcher.textWidth(d.text)+launcher.textWidth(gapS)/2,position.y+launcher.tScale*16+launcher.tScale*12*(counter-1)+launcher.tScale*7);

				}
				else {
					//launcher.text(d.text+gapS+d.valueText,position.x,position.y+launcher.tScale*16+launcher.tScale*12*(counter-1));
					trans = new PVector(position.x-launcher.textWidth(d.text+gapS+d.valueText)/2+launcher.textWidth(d.text)+launcher.textWidth(gapS)/2,position.y+launcher.tScale*16+launcher.tScale*12*(counter-1)+launcher.tScale*7);
				}
				
								
			}
			trans.x ++;
			
			if (d.type == "Status") {
				trans.x += 3*launcher.tScale;
			}
			
			Image image = launcher.bless;
			if (d.text == "Bless") {
				image = launcher.bless;
			}
			if (d.text == "Attack") {
				image = launcher.attack;
			}
			if (d.text == "Range") {
				image = launcher.range;
			}
			if (!g) {
				launcher.drawShadowImage(image, trans, new PVector(scale,scale));
			}
			
			
			float tSize = 1;
			if (d.base) {
				tSize = 14*launcher.tScale;
				
			}
			else {
				tSize = 10*launcher.tScale;
			}
			String text = "";
			if (d.value != 0) {
				text = d.text+gapS+d.value;
			}
			else {
				text = d.text+gapS+d.valueText;
			}
			if (g) {
				launcher.imageMode(launcher.CORNER);
				getShadow(new PVector(position.x,trans.y),text,tSize,trans,image,scale);
				launcher.imageMode(launcher.CENTER);
			}
			if (d.base) {
				launcher.text(text,position.x,position.y-3*launcher.tScale);

			}
			else {
				launcher.text(text,position.x,position.y+launcher.tScale*16+launcher.tScale*12*(counter-1));

			}
			launcher.image(image.image,trans.x,trans.y,scale,scale);
			counter++;
			launcher.imageMode(launcher.CORNER);
		}
		
		launcher.textAlign(launcher.LEFT,launcher.BASELINE);
		position.set(oldPos);
		pg.endDraw();
		return pg;
	}
	public void getShadow(PVector position, String text, Float size, PVector trans, Image image, Float scale) {
		launcher.textSize(size);
		launcher.getShadow(position,(int) (launcher.textWidth(text)), launcher.ceil(size), text, launcher.majalla, size, trans, image, new PVector(scale,scale));
	}
	public PVector getBB() {
		PVector BB = new PVector(0,0);
		for (Description d : aGraphic.action.descList) {
			launcher.textFont(launcher.majalla);
			if (d.base) {
				launcher.textSize(14*launcher.tScale);
			}
			else {
				launcher.textSize(10*launcher.tScale);
			}
			String gapS = "";
			while (launcher.textWidth(gapS)<getGap(d)) {
				gapS += " ";
				
			}
			
			
			if (d.value != 0 && launcher.textWidth(d.text+gapS+d.value)>BB.x) {
				BB.x=launcher.textWidth(d.text+gapS+d.value);
			}
			else if (launcher.textWidth(d.text+gapS+d.valueText)>BB.x) {
				BB.x=launcher.textWidth(d.text+gapS+d.valueText);
			}
			if (d.base) {
				BB.y+=24*launcher.tScale;
			}
			else {
				BB.y+=12*launcher.tScale;
			}
		}
		BB.y -= 4*launcher.tScale;
		
		return BB;
	}
	public void drawBB() {
		launcher.noFill();
		launcher.strokeWeight(2);
		launcher.stroke(255);
		launcher.rect(position.x-boundBox.x/2,position.y,boundBox.x,boundBox.y);
		launcher.stroke(0);
	}
	public float getGap(Description d) {
		if (d.value == 0 && d.valueText == "" && d.type != "Status") {
			return 0;
		}
		float gap = 0;
		if (d.type == "Basic") {
			gap = 15*launcher.tScale;
		}
		else if (d.type == "Status") {
			gap = 8*launcher.tScale;
		}
		else {
			return 15*launcher.tScale;
		}
		return gap;
	}
	
}
