package main.entities;

import java.util.ArrayList;
import java.util.List;

import main.Launcher;
import processing.core.PVector;

public class TextBox extends Thing{

	public PVector position;
	public PVector dimensions;
	public String basicText;
	
	public boolean typing;
	public String text;
	public boolean suggestions;
	public int suggestList;
	public boolean scroll;
	
	public TextBox(Launcher launcher, PVector position, PVector dimensions, String basicText, Boolean suggestions) {
		super(launcher);
		this.position = position;
		this.dimensions = dimensions;
		this.basicText = basicText;
		show = false;
		typing = false;
		this.suggestions = suggestions;
		text = basicText;
		suggestList = 0;
		scroll = false;
	}
	public void draw() {
		launcher.fill(200);
		launcher.strokeWeight(5);
		launcher.rect(position.x,position.y,dimensions.x,dimensions.y,5);
		launcher.fill(0);
		launcher.textFont(launcher.majalla);
		launcher.textSize(20);
		String newText = rightScroll(text);
		if (! scroll) {
			if (launcher.textWidth(text)>dimensions.x-20) {
				launcher.text(newText,position.x+dimensions.x-launcher.textWidth(newText)-10,position.y+dimensions.y/2+5);
			}
			else {
				launcher.text(newText,position.x+10,position.y+dimensions.y/2+5);
			}
		}
		else {
			ArrayList<String> vert = new ArrayList<String>();
			if (text.length() > 0) {
				vert = vertScroll(text);
			}
			int counter = 0;
			for (String s : vert) {
				launcher.text(vert.get(counter),position.x+10,position.y+dimensions.y/2+15-vert.size()*10+counter*20);
				counter++;
			}
			
		}
	}
	public void type() {
		if (text == basicText) {
			text = "";
		}
		typing = true;
	}
	public void stopType() {
		if (text == "") {
			text = basicText;
		}
		typing = false;
	}
	public ArrayList<String> getSuggFromList() {
		ArrayList<String> tempList = new ArrayList<String>();
		
		for(String s: launcher.suggestListList.get(suggestList)) {
			try {
			    if(s.toUpperCase().trim().contains(text.toUpperCase()) && Character.toUpperCase(s.charAt(0)) == Character.toUpperCase(text.charAt(0))) {
			       tempList.add(s);
			    }
			}
			catch (Exception e) {
				
			}
		}
		for(String s: launcher.suggestListList.get(suggestList)) {
			try {
			    if(s.toUpperCase().trim().contains(text.toUpperCase()) && Character.toUpperCase(s.charAt(0)) != Character.toUpperCase(text.charAt(0))) {
			       tempList.add(s);
				}
			}
			catch (Exception e) {
				
			}
		}
		return (tempList);
	}
	public String rightScroll(String t) {
		String result1 = "";
		int counter = 1;
		while (launcher.textWidth(result1)<dimensions.x-30 && counter<=text.length()) {
			result1 += text.charAt(text.length()-counter);
			counter++;
		}
		counter = 1;
		String result = "";
		while (counter<=result1.length()) {
			result += result1.charAt(result1.length()-counter);
			counter++;
		}
		return result;
	}
	public ArrayList<String> vertScroll(String t) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<Character> tempList = new ArrayList<Character>();
		int counter = 0;
		int counter2 = 0;
		int maxLength = 0;
		String tempString = "";
		while (counter2<text.length()) {
			counter = 0;
			maxLength = 0;
			for (String s : result) {
				maxLength += s.length();
			}
			while (launcher.textWidth(tempString)<dimensions.x-20 && counter<text.length()-maxLength && counter2<text.length()) {
				tempString += text.charAt(counter2);
				counter++;
				counter2++;
			}
			if (counter2<text.length()) {
				tempList = new ArrayList<Character>();
				for (Character i : tempString.toCharArray()) {
					tempList.add(i);
				}
				try {
					while (! Character.isWhitespace(tempList.get(tempList.size()-1))) {
						tempList.remove(tempList.size()-1);
						counter2--;
					}
				}
				catch (Exception e) {
					
				}
				tempString = "";
				for (Character i : tempList) {
					tempString += i;
				}
			}
			result.add(tempString);
			tempString = "";
		}
		
		
		
//		ArrayList<String> result = new ArrayList<String>();
//		ArrayList<String> wordList = new ArrayList<String>();
//		int counter = 0;
//		int counter2 = 0;
//		int maxLength = 0;
//		int wordCount = 0;
//		String tempString = "";
//		while (counter2<text.length()) {
//			if (Character.isWhitespace(text.charAt(counter2))) {
//				wordList.add(tempString);
//				tempString = "";
//			}
//			else {
//				tempString += text.charAt(counter2);
//			}
//			
//			counter2++;
//		}
//		wordList.add(tempString);
//		counter2 = 0;
//		tempString = "";
//		launcher.println(wordList);
//		while (counter2<text.length()) {
//			counter = 0;
//			maxLength = 0;
//			for (String s : result) {
//				maxLength += s.length();
//			}
//			while (launcher.textWidth(tempString)<dimensions.x-5 && counter<text.length()-maxLength) {
//				tempString += text.charAt(counter2);
//				if (Character.isWhitespace(text.charAt(counter2))) {
//					wordCount++;
//				}
//				counter++;
//				counter2++;
//				
//			}
//			ArrayList<Character> temp = new ArrayList<Character>();
//			if (wordCount != wordList.size()-1) {
//				for (char c : tempString.toCharArray()) {
//					temp.add(c);
//				}
//				for (int i = 0; i<wordList.get(wordCount).length();i++) {
//					temp.remove(temp.size()-1);
//				}
//				tempString = "";
//				for (char c : temp) {
//					tempString += c;
//				}
//				counter2 -= wordList.get(wordCount).length();
//			}
//			result.add(tempString);
//			tempString = "";
//		}
		return result;
	}
}
