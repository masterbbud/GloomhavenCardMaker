package main.entities;

import java.util.ArrayList;
import java.util.List;

import main.Launcher;
import processing.core.PVector;

public class ActionGraphic extends Thing{

	public Action action;
	public PVector position;
	public Boolean top;
	
	public ArrayList<Button> buttonList = new ArrayList<Button>();
	
	public ActionGraphic(Launcher launcher, Action action, PVector position, Boolean top) {
		super(launcher);
		this.action = action;
		this.position = position;
		this.top = top;
	}
	public void draw() {
		launcher.textSize(24);
		int yValue = (int) position.y;
		for (Description d : action.descList) {
			if (d.base) {
				launcher.fill(200);
				launcher.rect(position.x,yValue,245,30);
				launcher.fill(0);
				if (d.value != 0) {
					if (d.text == "XP") {
						launcher.text(d.value,position.x+20,yValue+23);
						launcher.text(reduce(d,200,d.text),position.x+23+launcher.textWidth((char) d.value),yValue+23);
					}
					else if (d.text == "Text") {
						launcher.text(reduce(d,200,d.longText),position.x+13,yValue+23);
					}
					else if (launcher.textWidth(d.text) < 201) {
						launcher.text(reduce(d,200,d.text),position.x+13,yValue+23);
						launcher.text(d.value,position.x+20+launcher.textWidth(d.text),yValue+23);
					}
				}
				else {
					if (d.text == "Summon") {
						launcher.text(reduce(d,200,d.text+d.valueText),position.x+13,yValue+23);
					}
					else if (d.text == "Text") {
						launcher.text(reduce(d,200,d.longText),position.x+13,yValue+23);
					}
					else {
						launcher.text(reduce(d,200,d.text),position.x+13,yValue+23);
						if (d.valueText != "#" && launcher.textWidth(d.text) < 201) {
							launcher.text(d.valueText,position.x+20+launcher.textWidth(d.text),yValue+23);
						}
					}
				}
			}
			else {
				launcher.fill(200);
				launcher.rect(position.x+28,yValue,217,30);
				launcher.fill(0);
				if (d.text == "HP" || d.text == "Attack" || d.text == "Move" || d.text == "Range" && action.descList.get(0).text == "Summon") {
					if (d.value != 0 && launcher.textWidth(d.text) < 177) {
						launcher.text(d.value,position.x+48,yValue+23);
						launcher.text(reduce(d,176,d.text),position.x+53+launcher.textWidth((char) d.value),yValue+23);
					}
					else if (d.valueText != "#" && launcher.textWidth(d.text) < 177) {
						if (d.valueText != "HP" && d.valueText != "ATK" && d.valueText != "MOV" && d.valueText != "RNG") {
							launcher.text(d.valueText,position.x+48,yValue+23);
							launcher.text(reduce(d,176,d.text),position.x+53+launcher.textWidth(d.valueText),yValue+23);
						}
						else {
							launcher.text(reduce(d,176,d.text),position.x+41,yValue+23);
						}
					}
					
				}
				else{
					if (d.text == "Text") {
						launcher.text(reduce(d,176,d.longText),position.x+41,yValue+23);
					}
					else {
						launcher.text(reduce(d,176,d.text),position.x+41,yValue+23);
					}
					if (d.value != 0 && launcher.textWidth(d.text) < 177) {
						launcher.text(d.value,position.x+48+launcher.textWidth(d.text),yValue+23);
					}
					else if (d.valueText != "#" && launcher.textWidth(d.text) < 177) {
						launcher.text(d.valueText,position.x+48+launcher.textWidth(d.text),yValue+23);
					}
				}
			}
			yValue += 30;
		}
	}
	public String reduce(Description d, int cutoff, String text) {
		List<Character> tempList = new ArrayList<>();
		String tempText = text;
		if (launcher.textWidth(tempText) > cutoff) {
			for (char c : tempText.toCharArray()) {
				tempList.add(c);
			}
			try {
				while (launcher.textWidth(tempText) > cutoff) {
					tempList.remove(tempList.size()-1);
					tempText = "";
					for (Character c : tempList) {
						tempText += c;
					}
				}
			}
			catch (Exception e) {
				
			}
			tempText = "";
			for (Character c : tempList) {
				tempText += c;
			}
			tempText += "...";
		}
		return tempText;
	}
	
}	
