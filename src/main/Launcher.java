package main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.entities.Action;
import main.entities.ActionGraphic;
import main.entities.ActionView;
import main.entities.Button;
import main.entities.CheckBox;
import main.entities.ColorSlider;
import main.entities.Description;
import main.entities.GradText;
import main.entities.Image;
import main.entities.TextBox;
import main.entities.Thing;
import main.files.CClass;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Launcher extends PApplet {
	
	//Things to work on:::
		//Making it so when you click on a button it doesn't register any other button clicks
			//Especially seen with the suggestion list pseudo-buttons
		//Making it so stuff like XP, loss, persistent, etc is always at the end of its side -- Sorta done
		//
		
		//Add a dashing typing bar like the one you see right here ->

		//Hex attacks, Pips, Enhancement Pips
		//Large Text
		//Adding every image and checking its gap length
		
		//Apparently Curse is shorter than Bless? Check word length -> Gap size transformation
		
		//Removing "Add desc" for round bonus/loss/stuff that you can't add stuff too
		//permaloss
		
		//Still some problems with adding images (pretty much in subdescs.)
		
		//Checkboxes don't stop (hitboxes always active)
		
		//Make all booleans lowercase

		// Don't show the NEW ACTION thing and the bless symbol if that's what it is
	
		//Summon descriptions + drawing !!! DO THIS THIS IS FUN
	
		// Somehow say that all "renders" mean only for big card
	
	// Card Base
		public Image cardBase; // Card Base
		public Image cardMask; // Card Screen
		public Image cardNull; // Basic Attack/Move
		public Image cardNull2; // Initiative Arrow
		public Image cardNull3; // Card Border (Uncolored)
	
	// Icons
		public Image largeCheck;
		public Image bless;
		public Image attack;
		public Image range;
		
	// Other Images
		public Image sample;
	
	// Color Picker Sliders
		public ColorSlider RSlider; // Red
		public ColorSlider GSlider; // Green
		public ColorSlider BSlider; // Blue
		
	// Card Setting Textboxes
		public TextBox titleBox; // Card Title Textbox
		public TextBox levelBox; // Card Level Textbox
		public TextBox initBox; // Card Initiative g
	// Description Textboxes
		public TextBox descChooseBox; // Specific line text
		public TextBox numberChoose; // The value for the description
		public TextBox elementChoose; // The element for the description
		public TextBox textText; // The "long text" for the description
	
	// Summon Textboxes
		public TextBox summonTitle; // Summon title
		public TextBox hpBox; // Summon health
		public TextBox attackBox; // Summon attack
		public TextBox moveBox; // Summon move
		public TextBox rangeBox; // Summon range
	
	// Other Textboxes
		public TextBox pips; // Input a list of "pips" for a limited persistent bonus: ex. <1,0,1,0,1>
	
	// Menu Buttons
		public Button cardSettings; // Card Settings
		public Button colorSettings; // Color Settings
	
	// Add new Actions/Descriptions
		public Button add1; // New Action
		public Button add2; // New Description
	
	// Closing Buttons
		public Button remove; // Delete a description/action
		public Button xOut; // Close the program
	
	// Specific Checkboxes
		public CheckBox selfCheckBox; // Add "Self" description
		public CheckBox roundCheckBox; // Add "Round Bonus" action
		public CheckBox loseCheckBox; // Add "Lose" action
	
	// Fonts
		public PFont pirataOne;
		public PFont majalla;
	
	// Variables
		public PVector bigPos = new PVector(0,0); // The sector for large image scanning (LIS)
		
		public int res = 1; // Resolution for LIS; number of horizontal screens
		public int cR; // Card's red value
		public int cG; // Card's green value
		public int cB; // Card's blue value
		public int actionView; // Current action being viewed
		public int actionDescView; // Current description being viewed
		public int settingMode; // Type of window open
		public int menuMode; // Type of menu open:
		//	0 - None (Card)
		//	1 - Main Menu
		//	2 - Card Selection
		//	3 - Class Selection
		
		public float tScale; // Text scale
		
		public String title; // Card title
		public String level; // Card level
		public String init; // Card initiative
		
		public boolean drawBBs = false; // Draw bounding boxes for actions?
		public boolean settingColor; // Dragging the color sliders?
		public boolean saveMenu = false;
		
	// Other
		public PImage dI = null; // Temporary developmental "draw image"
		public PGraphics cardRunes;
		
	// Lists
		public ArrayList<Image> imgList = new ArrayList<Image>(); // Loaded images
		public ArrayList<TextBox> textBoxList = new ArrayList<TextBox>(); // Text boxes
		public ArrayList<Action> actionList = new ArrayList<Action>(); // Actions
		public ArrayList<Button> buttonList = new ArrayList<Button>(); // Buttons
		public ArrayList<ActionGraphic>	aGraphicList = new ArrayList<ActionGraphic>(); // Action Graphics
		public ArrayList<ArrayList<String>> suggestListList = new ArrayList<ArrayList<String>>(); // List of Suggestion Lists
		public ArrayList<ActionView> viewList = new ArrayList<ActionView>(); // Displayed actions
		public ArrayList<CheckBox> checkList = new ArrayList<CheckBox>(); // Checkboxes
		public ArrayList<GradText> gTextList = new ArrayList<GradText>(); // Gradient Texts
		public ArrayList<PImage> classSampleList = new ArrayList<PImage>(); // All class previews
		
		// Suggestion Lists
			public ArrayList<String> suggestList0 = new ArrayList<String>(); // Basic Actions 
			public ArrayList<String> suggestList1 = new ArrayList<String>(); // Secondary Actions
			public ArrayList<String> suggestList2 = new ArrayList<String>(); //Elements
	
	
			
	// Static
		public static double s_aR = 14/43; // ratio from space width to attack width
			
			
	public static void main(String[] args) {	// Main PApplet Launcher
		System.out.println("-------------\nGloomhaven Card Creator\n-------------\n");
		PApplet.main("main.Launcher");
	}

	public void settings() {	// PApplet Settings
		fullScreen(P2D);
	}

	public void setup() {
		// Set up variables
		
			// Initialize variables and fonts
		cardBase = new Image(loadImage("assets/card/Ability Cards - Front.jpg"),new PVector(200,100), new PVector (413,563));
		cardMask = new Image(loadImage("assets/card/Glowing Runes - Front - Set to Screen in Photoshop (EDITED).jpg"),new PVector(200,100), new PVector(413,563));
		cardNull = new Image(loadImage("assets/card/Ability Cards - Front (NULL).gif"),new PVector(200,100), new PVector (413,563));
		cardNull2 = new Image(loadImage("assets/card/Ability Cards - Front (ARROW).gif"),new PVector(200,100), new PVector (413,563));
		cardNull3 = new Image(loadImage("assets/card/Ability Cards - Front (BORDER).gif"),new PVector(200,100), new PVector (413,563));
		largeCheck = new Image(loadImage("assets/icons/General Icons-23.gif"),new PVector(200,100),new PVector(834,834));
		bless = new Image(loadImage("assets/icons/Status Effect Icons-01.gif"),new PVector(200,100),new PVector(1667,1667));
		attack = new Image(loadImage("assets/icons/General Icons-03.gif"),new PVector(200,100),new PVector(834,834));
		range = new Image(loadImage("assets/icons/General Icons-06.gif"),new PVector(200,100),new PVector(834,834));
		sample = new Image(loadImage("assets/Sample.png"),new PVector(0,0),new PVector(100,100));
		pirataOne = createFont("assets/fonts/PirataOne-Gloomhaven.ttf",32);
		majalla = createFont("assets/fonts/majallab.ttf",32);
		
			// Initialize card graphics
		cardRunes = createGraphics(413,563);
		cardRunes.beginDraw();
		cardRunes.image(cardMask.image,0,0);
		cardRunes.endDraw();
		
			// Initialize color sliders
		RSlider = new ColorSlider(this, new PVector(750,100), color(255,0,0));
		GSlider = new ColorSlider(this, new PVector(830,100), color(0,255,0));
		BSlider = new ColorSlider(this, new PVector(910,100), color(0,0,255));
		
			// Initialize text boxes
		titleBox = new TextBox(this, new PVector(750,120), new PVector(185,30),"Title",false);
		levelBox = new TextBox(this, new PVector(750,200), new PVector(70,50),"LVL",false);
		initBox = new TextBox(this, new PVector(865,200), new PVector(70,50),"INIT",false);
		descChooseBox = new TextBox(this, new PVector(730,100), new PVector(225,50),"Action",true);
		numberChoose = new TextBox(this, new PVector(813,180), new PVector(50,50),"#",false);
		elementChoose = new TextBox(this, new PVector(730,180), new PVector(225,50),"Element",true);
			elementChoose.suggestList = 2;
		textText = new TextBox(this, new PVector(730,180), new PVector(225,200),"Write Text",false);
			textText.scroll = true;
		hpBox = new TextBox(this, new PVector(730,310), new PVector(100,50),"HP",false);
		attackBox = new TextBox(this, new PVector(855,310), new PVector(100,50),"ATK",false);
		moveBox = new TextBox(this, new PVector(730,370), new PVector(100,50),"MOV",false);
		rangeBox = new TextBox(this, new PVector(855,370), new PVector(100,50),"RNG",false);
		summonTitle = new TextBox(this, new PVector(730,245), new PVector(225,50),"Title",false);
		pips = new TextBox(this, new PVector(730,180), new PVector(225,50),"Configuration",false);
			textBoxList.add(titleBox);
			textBoxList.add(levelBox);
			textBoxList.add(initBox);
			textBoxList.add(descChooseBox);
			textBoxList.add(numberChoose);
			textBoxList.add(elementChoose);
			textBoxList.add(textText);
			textBoxList.add(hpBox);
			textBoxList.add(attackBox);
			textBoxList.add(moveBox);
			textBoxList.add(rangeBox);
			textBoxList.add(summonTitle);
			textBoxList.add(pips);
			
			// Initialize buttons
		cardSettings = new Button(this, new PVector(1052,80), new PVector(245,50), "Card Settings");
		colorSettings = new Button(this, new PVector(1052,130), new PVector(245,50), "Color Settings");
		add1 = new Button(this, new PVector(1052,230), new PVector(245,30), "Add new Action");
		add2 = new Button(this, new PVector(1052,280), new PVector(245,30), "Add new Action");
		remove = new Button(this, new PVector(950,74), new PVector(20,20), "X");
		xOut = new Button(this, new PVector(displayWidth-60,20),new PVector(40,40),"X");
			buttonList.add(cardSettings);
			buttonList.add(colorSettings);
			buttonList.add(add1);
			buttonList.add(add2);
			buttonList.add(remove);
			buttonList.add(xOut);

			// Intitialize check boxes
		selfCheckBox = new CheckBox(this, new PVector(730,260),"Target Self");
		roundCheckBox = new CheckBox(this, new PVector(730,300),"Round Bonus");
		loseCheckBox = new CheckBox(this, new PVector(730,180),"Lose");
			checkList.add(selfCheckBox);
			checkList.add(roundCheckBox);
			checkList.add(loseCheckBox);
		
			// Default Variables
		title = "";
		level = "";
		init = "";
		cR = 0;
		cG = 0;
		cB = 0;
		tScale = (float) 1.6667; // Default Text Scale = 5/3
		actionView = 0;
		actionDescView = 0;
		settingMode = 0;
			// 0 - Nothing
			// 1 - Color
			// 2 - Action
			// 3 - Other Settings (Initiative, Level, Title)
			// 4 - Class Settings (Board Design, etc.)
		menuMode = 0;
		settingColor = false;
		
			// Initialize Suggestion Lists
				// Base list
	// With Values
		suggestList0.add("Attack");
		suggestList0.add("Move");
		suggestList0.add("Heal");
			// @ Self
		suggestList0.add("Shield");
			// @ Self
			// @ Round Bonus
		suggestList0.add("Loot");
		suggestList0.add("Push");
		suggestList0.add("Pull");
		suggestList0.add("Retaliate");
			// @ Self
			// @ Round Bonus
		
	// Without Values
		suggestList0.add("Curse");
		suggestList0.add("Disarm");
		suggestList0.add("Immobilize");
		suggestList0.add("Wound");
		suggestList0.add("Muddle");
		suggestList0.add("Stun");
		suggestList0.add("Poison");
		suggestList0.add("Bless");
			// @ Self
		suggestList0.add("Invisible");
			// @ Self
		suggestList0.add("Strengthen");
			// @ Self
		
	// Odd Cases
		suggestList0.add("XP");
		suggestList0.add("Infuse");
			// ! Element
		suggestList0.add("Consume");
			// ! Element
		suggestList0.add("Limited Persistent Bonus");
			// @ Lose
			// ! Spot List (Explain)
		suggestList0.add("Lose");
		suggestList0.add("Super Lose");
		suggestList0.add("Persistent Bonus");
			// @ Lose
		suggestList0.add("Round Bonus");
		suggestList0.add("Summon");
			// @ Lose
			// ! HP
			// ! Attack
			// ! Move
			// ! Range
		suggestList0.add("Text");
			// ! Text
		
		
				// Secondary List
	// With Values
		suggestList1.add("Range");
		suggestList1.add("Push");
		suggestList1.add("Pull");
		suggestList1.add("Pierce");
		suggestList1.add("Target");
		
	// Without Values
		suggestList1.add("Bless");
			// @ Self
		suggestList1.add("Curse");
		suggestList1.add("Disarm");
		suggestList1.add("Immobilize");
		suggestList1.add("Wound");
		suggestList1.add("Muddle");
		suggestList1.add("Stun");
		suggestList1.add("Poison");
		suggestList1.add("Invisible");
			// @ Self
		suggestList1.add("Strengthen");
			// @ Self
		suggestList1.add("Flying");
		suggestList1.add("Jump");
		
		
	// Odd Cases
		suggestList1.add("Target Hexes");
			// ! Configuration
		suggestList1.add("Text");
			// ! Text
		
		
				// Element List
		suggestList2.add("Fire");
		suggestList2.add("Frost");
		suggestList2.add("Wind");
		suggestList2.add("Light");
		suggestList2.add("Darkness");
		suggestList2.add("Earth");
		suggestList2.add("Any");
		
		
			// Add Lists to a big list
		suggestListList.add(suggestList0);
		suggestListList.add(suggestList1);
		suggestListList.add(suggestList2);
		
//		textFont(majalla);
//		textSize(tScale*14);
//		println(textWidth("Attack"));
//		println(textWidth("3"));
		println(tScale);
		
	}

	public void draw() {
		// Draw the screen. Updates every frame
		
		if (menuMode == 1) {
			drawMainMenu();
		}
		if (menuMode == 0) {
			backgroundGraphics();
			drawCard();
			drawMenu();
			drawActionViews();
		}
		
		for (Button b : buttonList) {
			b.draw();
		}
		
		drawSuggLists();
		drawRightMenu();
		
		if (menuMode == 1) {
			drawClassMenu();
		}
		if (dI != null) {
			image(dI.get(),0,0);
		}
		
	}
	public void drawMainMenu() {
		
	}
	public void drawColors(ColorSlider slider) {
		// Draw a color slider
		
			// Draw an outlining rectangle
			strokeWeight(10);	stroke(255);	noFill();
		rect(slider.position.x,slider.position.y,25,255);
			strokeWeight(1);
		
			// For every pxiel from the top to bottom of the rectangle, draw
		for (int i = (int) slider.position.y; i <= slider.position.y + 255; i++) {
		      float inter = map(i, slider.position.y, slider.position.y+255, 0, 1);
		      int c = lerpColor(color(0), slider.color, inter);
		      stroke(c);
		      line(slider.position.x, i, slider.position.x+25, i);
		}
	}
	public void drawRightMenu() {
		// Draw the right menu
		
		cardSettings.show = true;
		textFont(majalla);
			strokeWeight(5);	stroke(0);	fill(200);
		rect(1052,130,245,50);
			fill(0);
		text("Color Settings",1075,165);
		
		int yValue = 230;
		yValue = drawGraphics(yValue,true);
		
		
		yValue += 50;
		
			strokeWeight(5);	stroke(0);
		line(1052,yValue-10,1297,yValue-10);
		buttonList.get(2).position.y = yValue-50;
		buttonList.get(2).show = true;
		
		yValue = drawGraphics(yValue,false);
		
			strokeWeight(5);	stroke(0);
		buttonList.get(3).position.y = yValue;
		buttonList.get(3).show = true;
	}
	public int getColor(PVector position) {
		// Get the color you are selecting with a slider
		
		return round(mouseY - position.y);
	}
	public void loadImg(String img, PVector position, PVector dimensions) {
		// Load an asset into an Image
		
		imgList.add(new Image(loadImage(img),position, dimensions));
	}
	public void keyPressed() {
		// Register keypresses
			// Used for textboxes and moving ActionGraphics
		
		boolean any = false;
		for (TextBox t : textBoxList) {
			any = type(t, any);
		}
		
		if (! any) {
			moveGraphic(any);
		}
	}
	public void moveAction(Boolean up, Action a) {
		// Move an action in the right menu
		
		if (up) {
			if ((a != actionList.get(0) && aGraphicList.get(actionView).top) || (a != actionList.get(numTops()) && ! aGraphicList.get(actionView).top)) {
				moveActionUp();
			}
		}
		else {
//			error with only one thing (ONLY DOWN FOR TOP)
			if ((a != actionList.get(actionList.size()-1) && ! aGraphicList.get(actionView).top) || (a != actionList.get(numTops()-1) && aGraphicList.get(actionView).top)) {
				moveActionDown();
			}
		}
	}
	public void moveActionUp() {
		// Move an action up
		
		actionList.add(actionView-1,actionList.get(actionView));
		aGraphicList.add(actionView-1,new ActionGraphic(this,actionList.get(actionView-1),new PVector(1052,700),aGraphicList.get(actionView-1).top));
		viewList.add(actionView-1,new ActionView(this,aGraphicList.get(actionView-1),new PVector(1052,700),aGraphicList.get(actionView-1).top));
		buttonList.add(new Button(this,new PVector(1108,700),new PVector(189,30),"Add new Asset"));
		buttonList.get(buttonList.size()-1).action = actionList.get(actionView-1);
		
		removeAction(actionList.get(actionView+1),actionView+1);
		actionView -= 1;
		actionDescView = 0;
	}
	public void moveActionDown() {
		// Move an action down
		
		actionList.add(actionView+2,actionList.get(actionView));
		aGraphicList.add(actionView+2,new ActionGraphic(this,actionList.get(actionView),new PVector(1052,700),aGraphicList.get(actionView).top));
		viewList.add(actionView+2,new ActionView(this,aGraphicList.get(actionView),new PVector(1052,700),aGraphicList.get(actionView).top));
		buttonList.add(new Button(this,new PVector(1108,700),new PVector(189,30),"Add new Asset"));
		buttonList.get(buttonList.size()-1).action = actionList.get(actionView+2);
		
		removeAction(actionList.get(actionView),actionView);
		actionView += 1;
		actionDescView = 0;
	}
	public void moveDesc(Boolean up, Action a, Description d) {
		// Move a description inside of an action
		
		if (up) {
			if (d != a.descList.get(1)) {
				a.descList.add(actionDescView-1,d);
				removeDescription(a,actionDescView+1);
				actionDescView -= 1;
			}
		}
		else {
			if (d != a.descList.get(a.descList.size()-1)) {
				a.descList.add(actionDescView+2,d);
				removeDescription(a,actionDescView);
				actionDescView++;
			}
		}
	}
	public void keyReleased() {
		// TEMPORARY
		// Do things without worrying about the keyPressed() function
			// Currently, saves the image
		
		if (keyCode == SHIFT) {
			saveImage(1);
			menuMode = 1;
			//getClassSamples(getClasses());
		}
	}
	public void mousePressed() {
		// Check the clicking of buttons
		
		if (menuMode != 0 || saveMenu) {
			if (saveMenu) {
				checkFileNavClick();
			}
			return;
		}
		
		checkBasicClick();
		
		if (remove.clickCheck()) {
			removeDescButton();
		}
		
		
		checkAGraphicClick();
		checkSuggestionClick();
		checkTextBoxClick();
		checkAddDescClick();
		checkCheckboxClick();
		checkSlidersClick();
	}
	public void checkFileNavClick() {
		//Check if you have clicked any of the rectangles displayed during file navigation
		
		//rect((float) (Math.floorMod(z,5)*(displayWidth*0.8)/5+(displayWidth*0.6)/5),(float) (floor((z)/5)*(displayHeight*0.8)/3+(displayHeight*0.6)/5),(float) ((displayWidth*0.6)/5),(float) ((displayWidth*0.6)/5),10);
		
	}
	public void checkBasicClick() {
		// Check if any of the permanent buttons are clicked
		
		if (cardSettings.clickCheck()) {
			settingMode = 3;
		}
		if (colorSettings.clickCheck()) {
			settingMode = 1;
		}
		if (add1.clickCheck()) {
			settingMode = 2;
			addAction(true);
		}
		if (add2.clickCheck()) {
			settingMode = 2;
			addAction(false);
		}
		if (xOut.clickCheck()) {
			exit();
		}
	}
	public void removeDescButton() {
		// Trigger a click for the "remove description" button, remove the current description and possibly the whole action
		
		removeDescription(actionList.get(actionView),actionDescView);
		try {
			if (! actionList.get(actionView).descList.get(0).base) {
				removeAction(actionList.get(actionView),actionView);
			}
		}
		catch (Exception e) {
			if (actionList.get(actionView).descList.size()<=0) {
				removeAction(actionList.get(actionView),actionView);
			}
		}
		if (actionView>=actionList.size()-1) {
			settingMode = 0;
		}
		try {
			if (actionDescView>=actionList.get(actionView).descList.size()-1) {
				settingMode = 0;
			}
		}
		catch (Exception e) {}
	}
	public void checkAGraphicClick() {
		// Check if an ActionGraphic was clicked and select it
		
		for (ActionGraphic a : aGraphicList) {
			if (mouseX>1052 && mouseX<1297 && mouseY>a.position.y && mouseY<a.position.y+30) {
				setDescription(a.action, a.action.descList.get(0));
			}
			if (mouseX>1080 && mouseX<1297 && mouseY>a.position.y+30 && mouseY<a.position.y+30*a.action.descList.size()) {
				setDescription(a.action, a.action.descList.get(round(mouseY-a.position.y)/30));
			}
		}
	}
	public void checkTextBoxClick() {
		// Check if a TextBox was clicked and select it
		
		for (TextBox t : textBoxList) {
			if (t.show) {
				if (mouseX>t.position.x && mouseX<t.position.x+t.dimensions.x && mouseY>t.position.y && mouseY<t.position.y+t.dimensions.y) {
					t.type();
				}
			}
		}
	}
	public void checkSuggestionClick() {
		// Check if a Suggestion was clicked and select it
		
		for (TextBox z : textBoxList) {
			if (z.typing) {
				if (z.getSuggFromList().size() > 0 && z.suggestions) {
					if (mouseX>z.position.x && mouseX<z.position.x+z.dimensions.x) {
						if (mouseY<z.position.y+z.dimensions.y*4 && mouseY>z.position.y+z.dimensions.y) {
							z.text = z.getSuggFromList().get(floor((mouseY-z.position.y-z.dimensions.y)/z.dimensions.y));
						}
					}
				}
				z.stopType();

				setVars(z);
			}
		}
	}
	public void checkAddDescClick() {
		// For all buttons, if it has an action, add a description to that action
		
		for (Button b : buttonList) {
			try {
				if (b.show && b.clickCheck()) {
					addDescription(b.action);
				}
			}
			catch (Exception e) {}
		}
	}
	public void checkSlidersClick() {
		// Check if a Color Slider was clicked and note that you are dragging a slider
		
		if ((settingMode == 1) && ((mouseX>750 && mouseX<775 && mouseY>100 && mouseY<355) || (mouseX>830 && mouseX<855 && mouseY>100 && mouseY<355) || (mouseX>910 && mouseX<935 && mouseY>100 && mouseY<355))) {
			settingColor = true;
		}
	}
	public void checkCheckboxClick() {
		// Check if a Checkbox was clicked and check it, then do its effects
		
		for (CheckBox c : checkList) {
			if (c.show && c.clickCheck()) {
				c.state = ! c.state;
				if (c == selfCheckBox) {
					toggleSelfDesc(c);
				}
				if (c == roundCheckBox) {
					toggleRBonusAction(c);
				}
				if (c == loseCheckBox) {
					toggleLostAction(c);
				}
			}
		}
	}
	public void toggleSelfDesc(CheckBox c) {
		// Add the "Self" description to the current action or remove it
		
		if (c.state) {
			actionList.get(actionView).descList.add(new Description(this,false,"Self","",0,0));
		}
		else {
			int counter = 0;
			for (Description d : actionList.get(actionView).descList) {
				if (d.text == "Self") {
					removeDescription(actionList.get(actionView), counter);
					break;
				}
				counter++;
			}
		}
	}
	public void toggleRBonusAction(CheckBox c) {
		// Add the "Round Bonus" action to the current side or remove it
		
		if (c.state) {
			actionList.add(new Action(this));
			actionList.get(actionList.size()-1).descList.add(new Description(this, true, "Round Bonus","",0,0));
			aGraphicList.add(new ActionGraphic(this,actionList.get(actionList.size()-1),new PVector(1052,700),aGraphicList.get(actionView).top));
			buttonList.add(new Button(this,new PVector(1108,700),new PVector(189,30),"Add new Asset"));
			buttonList.get(buttonList.size()-1).action = actionList.get(actionList.size()-1);
			viewList.add(new ActionView(this,aGraphicList.get(aGraphicList.size()-1),new PVector(100,100),aGraphicList.get(actionView).top));
			
		}
		else {
			int counter = 0;
			for (Action a : actionList) {
				if (a.descList.get(0).text == "Round Bonus") {
					removeAction(a, counter);
					break;
				}
				counter++;
			}
		}
	}
	public void toggleLostAction(CheckBox c) {
		// Add the "Lose" action to the current side or remove it
		
		if (c.state) {
			actionList.add(new Action(this));
			actionList.get(actionList.size()-1).descList.add(new Description(this, true, "Lose","",0,0));
			aGraphicList.add(new ActionGraphic(this,actionList.get(actionList.size()-1),new PVector(1052,700),aGraphicList.get(actionView).top));
			buttonList.add(new Button(this,new PVector(1108,700),new PVector(189,30),"Add new Asset"));
			buttonList.get(buttonList.size()-1).action = actionList.get(actionList.size()-1);
			viewList.add(new ActionView(this,aGraphicList.get(aGraphicList.size()-1),new PVector(100,100),aGraphicList.get(actionView).top));
		}
		else {
			int counter = 0;
			for (Action a : actionList) {
				if (a.descList.get(0).text == "Lose") {
					removeAction(a, counter);
					break;
				}
				counter++;
			}
		}
	}
	public void mouseReleased() {
		// Do something when the mouse is released
			// Stop dragging a Color Slider
		
		if (settingColor) {
			settingColor = false;
		}
	}
	public void addAction(Boolean top) {
		// Create a new blank Action
		
		if (! top) {
			actionList.add(new Action(this));
			actionList.get(actionList.size()-1).descList.add(new Description(this, true, "New Action","",0,0));
			aGraphicList.add(new ActionGraphic(this,actionList.get(actionList.size()-1),new PVector(1052,700),top));
			buttonList.add(new Button(this,new PVector(1108,700),new PVector(189,30),"Add new Asset"));
			buttonList.get(buttonList.size()-1).action = actionList.get(actionList.size()-1);
			actionView = actionList.size()-1;
		}
		else {
			int numTops = numTops();
			actionList.add(numTops,new Action(this));
			actionList.get(numTops).descList.add(new Description(this, true, "New Action","",0,0));
			aGraphicList.add(new ActionGraphic(this,actionList.get(numTops),new PVector(1052,700),top));
			buttonList.add(new Button(this,new PVector(1108,700),new PVector(189,30),"Add new Asset"));
			buttonList.get(buttonList.size()-1).action = actionList.get(numTops);
			actionView = numTops;
		}
		viewList.add(new ActionView(this,aGraphicList.get(aGraphicList.size()-1),new PVector(100,100),top));
		
		settingMode = 2;
		actionDescView = 0;
		resetBoxes("New Action","#");
	}
	public void removeAction(Action a, int c) {
		// Removes an Action from the list
		
		actionList.remove(c);
		aGraphicList.remove(c);
		
		for (Button b : buttonList) {
			try {
				if (b.action == a) {
					buttonList.remove(b);
					break;
				}
			}
			catch (Exception e) {}
		}
		if (actionView>c) {
			actionView--;
		}
		viewList.remove(c);
	}
	public void addDescription(Action a) {
		// Appends a new blank Description to the current action
		
		a.descList.add(new Description(this,false,"New Asset","",0,0));
		int tempCount = 0;
		for (Action s : actionList) {
			if (s == a) {
				actionView = tempCount;
			}
			tempCount ++;
		}
		actionDescView = a.descList.size()-1;
		resetBoxes("New Asset","#");
	}
	public void removeDescription(Action a, int c) {
		// Removes the current Description from the current Action
		
		a.descList.remove(c);
		if (actionDescView>c) {
			actionDescView--;
		}
	}
	public void setDescription(Action a, Description d) {
		// Set the current description to a new one and reset all boxes and variables
		
		int tempCount = 0;
		for (Action s : actionList) {
			if (s == a) {
				actionView = tempCount;
			}
			tempCount++;
		}
		tempCount = 0;
		for (Description k : a.descList) {
			if (k == d) {
				actionDescView = tempCount;
			}
			tempCount++;
		}
		if (d.value != 0) {
			resetBoxes(d.text,Integer.toString(d.value));
		}
		else {
			resetBoxes(d.text,d.valueText);
		}
		settingMode = 2;
	}
	public void setVars(TextBox z) {
		// Sets the current variables based on info in TextBoxes
		
		setBasicVars(z);
		try {
			if (z == descChooseBox) {
				actionList.get(actionView).descList.get(actionDescView).text = descChooseBox.text;
			}
			if (z == numberChoose) {
				setLatestBox(z,actionDescView);
			}
			setOtherVars(z);
			actionList.get(actionView).descList.get(actionDescView).setType();
			if (actionList.get(actionView).descList.get(actionDescView).type == "Status") {
				actionList.get(actionView).descList.get(actionDescView).text = actionList.get(actionView).descList.get(actionDescView).text.toUpperCase();
			}
		}
		catch (Exception e) {}
		
	}
	public void setBasicVars(TextBox z) {
		// Set the basic variables (ones in Card Settings)
		
		if (z == titleBox) {
			title = z.text;
		}
		if (z == levelBox) {
			level = z.text;
		}
		if (z == initBox) {
			init = z.text;
		}
	}
	public void setSummonVars(TextBox z) {
		// Sets the variables for a Summon, including making new boxes if necessary
		
		int exists = -1; // There currently does not exist one of the right kind of box. When you find one, make this the location of that variable in this action's desclist.
		int counter = 0;
		for (Description d : actionList.get(actionView).descList) {
			if ((z == hpBox && d.text == "HP") || (z == attackBox && d.text == "Attack") || (z == moveBox && d.text == "Move") || (z == rangeBox && d.text == "Range")) {
				exists = counter;
			}
			counter++;
		}
		if (exists == -1) {
			makeSummonBoxes(z);
			exists = actionList.get(actionView).descList.size()-1;
		}
		setLatestBox(z,exists);
	}
	public void setOtherVars(TextBox z) {
		// Set other TextBox values to variables
		
		if (z == elementChoose) {
			actionList.get(actionView).descList.get(actionDescView).valueText = elementChoose.text;
		}
		if (z == summonTitle) {
			actionList.get(actionView).descList.get(actionDescView).valueText = summonTitle.text;
		}
		if (z == textText) {
			actionList.get(actionView).descList.get(actionDescView).longText = z.text;
		}
		if (z == pips) {
			actionList.get(actionView).descList.get(actionDescView).longText = z.text;
		}
		if (z == hpBox || z == attackBox || z == moveBox || z == rangeBox) {
			setSummonVars(z);
		}
	}
	public void makeSummonBoxes(TextBox z) {
		// Make the necessary boxes for Summons
		
		if (z == hpBox) {
			actionList.get(actionView).descList.add(new Description(this,false,"HP","",0,0));
		}
		if (z == attackBox) {
			actionList.get(actionView).descList.add(new Description(this,false,"Attack","",0,0));
		}
		if (z == moveBox) {
			actionList.get(actionView).descList.add(new Description(this,false,"Move","",0,0));
		}
		if (z == rangeBox) {
			actionList.get(actionView).descList.add(new Description(this,false,"Range","",0,0));
		}
	}
	public void setLatestBox(TextBox z, int exists) {
		// Sets a Description to have a value equal to the text in the Number Choose TextBox
			// exists = the value of the Description in the Action's descList
		
		try {
			if (Integer.parseInt(numberChoose.text) > -99) {
				actionList.get(actionView).descList.get(exists).value = Integer.parseInt(z.text);
				actionList.get(actionView).descList.get(exists).valueText = "";
			}
		}
		catch (Exception e) {
			actionList.get(actionView).descList.get(exists).valueText = z.text;
			actionList.get(actionView).descList.get(exists).value = 0;
		}
	}
	public void resetBoxes(String dcbtext, String nctext) {
		// Reset all the TextBoxes to their default value when changing DescView
		
		getBasicBoxes(dcbtext,nctext);
		if (actionList.get(actionView).descList.get(0).text == "Summon") {
			getSummonBoxes();
		}
		if (elementChoose.show) {
			elementChoose.basicText = actionList.get(actionView).descList.get(actionDescView).valueText;
			elementChoose.text = elementChoose.basicText;
		}
		getCBox_Desc(selfCheckBox,"Self");
		getCBox_Action(roundCheckBox,"Round Bonus");
		getCBox_Action(loseCheckBox,"Lose");
		
	}
	public void getCBox_Action(CheckBox c, String text) {
		// Gets + Sets the state of a check box used to add an Action
			// ex. the Lose CheckBox
		
		c.state = false;
		int counter = 0;
		for (Action a : actionList) {
			if (a.descList.get(0).text == text && aGraphicList.get(counter).top == aGraphicList.get(actionView).top) {
				loseCheckBox.state = true;
			}
			counter++;
		}
	}
	public void getCBox_Desc(CheckBox c, String text) {
		// Gets + Sets the state of a check box used to add a Description
			// ex. the Self CheckBox
		
		c.state = false;
		for (Description d : actionList.get(actionView).descList) {
			if (d.text == text) {
				c.state = true;
			}
		}
	}
	public void getSummonBoxes() {
		// Gets + Sets the base value for each parameter of the summmon
			// Try statement ?? Why?
		
		try {
			for (Description d : actionList.get(actionView).descList) {
				if (d.text == "HP") {
					hpBox.basicText = d.valueText;
					hpBox.text = hpBox.basicText;
				}
				if (d.text == "Attack") {
					attackBox.basicText = d.valueText;
					attackBox.text = attackBox.basicText;
				}
				if (d.text == "Move") {
					moveBox.basicText = d.valueText;
					moveBox.text = moveBox.basicText;
				}
				if (d.text == "Range") {
					rangeBox.basicText = d.valueText;
					rangeBox.text = rangeBox.basicText;
				}
			}
		}
		catch (Exception e) {}
	}
	public void getBasicBoxes(String dcbtext, String nctext) {
		// Set the basic text boxes for action/desc parameters
		
		descChooseBox.basicText = dcbtext;
		descChooseBox.text = descChooseBox.basicText;
		numberChoose.basicText = nctext;
		numberChoose.text = numberChoose.basicText;
		textText.text = actionList.get(actionView).descList.get(actionDescView).longText;
		pips.text = actionList.get(actionView).descList.get(actionDescView).longText;
		elementChoose.basicText = actionList.get(actionView).descList.get(actionDescView).valueText;
		elementChoose.text = elementChoose.basicText;
		summonTitle.basicText = actionList.get(actionView).descList.get(actionDescView).valueText;
		summonTitle.text = summonTitle.basicText;
	}
	public void drawCheck(PVector position) {
		// Draw a checkmark at a position

		image(largeCheck.image,position.x,position.y,30,30);
	}
	public void drawCheck(PVector position, int size) {
		// Draw a checkmark at a position of a specific size

		image(largeCheck.image,position.x,position.y,size,size);
	}
	public int numTops() {
		// Get the number of Top actions
		
		int numOfTops = 0;
		for (ActionGraphic a : aGraphicList) {
			if (a.top) {
				numOfTops++;
			}
		}
		return numOfTops;
	}
	public void clickOff() {
		// Click out of a TextBox
		
		for (TextBox z : textBoxList) {
			if (z.typing) {
				if (z.getSuggFromList().size() > 0 && z.suggestions) {
					if (mouseX>z.position.x && mouseX<z.position.x+z.dimensions.x) {
						if (mouseY<z.position.y+z.dimensions.y*4 && mouseY>z.position.y+z.dimensions.y) {
							z.text = z.getSuggFromList().get(floor((mouseY-z.position.y-z.dimensions.y)/z.dimensions.y));
						}
					}
				}
				z.stopType();
				
				setVars(z);
			}
		}
	}
	public void arrangeViews() {
		// Arrange the ActionViews, first the top then the bottom
		
		tScale = (float) 1.667;
		arrangeAViews(true);
		arrangeAViews(false);
	}
	public void getViewArrange(int r) {
		
		tScale = (float) 1.667 * r * (displayWidth / cardBase.dimensions.x);
		bigArrangeAViews(true,r);
		bigArrangeAViews(false,r);
	}
	public void arrangeAViews(boolean top) {
		// Arrange that ActionViews for either the top or bottom
			// If big, do this based on a resolution r
		
		float totalY = 0;
		for (ActionView v : viewList) {
			if (v.top == top) {
				if (v.type == "Basic") {
					v.boundBox = v.getBB();
					totalY += v.boundBox.y;
				}
			}
		}
		float newY;
		totalY -= 8*tScale;
		newY = 180;
		if (! top) {
			newY+=230;
		}
		for (ActionView v : viewList) {
			if (v.top == top) {
				if (v.type == "Basic") {
					v.position.x = 406;
					v.position.y = (180-totalY)/2 + newY;
					newY += v.boundBox.y;
				}
			}
		}
	}
	public void bigArrangeAViews(boolean top, int r) {
		// Arrange that ActionViews for either the top or bottom
			// If big, do this based on a resolution r
		
		float totalY = 0;
		for (ActionView v : viewList) {
			if (v.top == top) {
				if (v.type == "Basic") {
					v.boundBox = v.getBB();
					totalY += v.boundBox.y;
				}
			}
		}
		float newY;
		totalY -= 8*tScale;
		if (top) {
			newY = (displayWidth/cardBase.dimensions.x)*cardBase.dimensions.y*r*((float)0.142);
		}
		else {
			newY = (displayWidth/cardBase.dimensions.x)*cardBase.dimensions.y*r*((float)0.551);
		}
		for (ActionView v : viewList) {
			if (v.top == top) {
				if (v.type == "Basic") {
					v.position.x = (displayWidth*r)/2;
					v.position.y = ((displayWidth/cardBase.dimensions.x)*cardBase.dimensions.y*r*((float)0.32)-totalY)/2 + newY;
					newY += v.boundBox.y;
				}
			}
		}
	}
	public void drawShadowText(String s, PVector position, PFont font, Float size) {
		// Draw the shadow for a line of text
		
			textFont(font);	textSize(size);	fill(0,0,0,100);	textAlign(CENTER,BASELINE);
		text(s,position.x+tScale/4,Math.round(position.y+size*.7)+tScale/4);
			textAlign(CENTER,TOP);	fill(255,255);
	}
	public void drawShadowImage(Image i, PVector p, PVector d) {
		// Draw the shadow for an image
			tint(0,155);
		image(i.image,p.x+tScale/4,p.y+tScale/4,d.x,d.y);
			noTint();
	}
	public PGraphics getShadow(PVector position, int x, int y, String text, PFont font, Float size, PVector trans, Image i, PVector d) {
		// Get the shadow for a full description (text and image)
		
		PGraphics g = createGraphics(2*x,2*y);
		g.beginDraw();
				g.textFont(font);	g.textSize(size);	g.fill(0,0,0,255);	g.textAlign(CENTER,CENTER);
			g.text(text,x/2+tScale/4+x/2,(float) (y-tScale*2));
				g.textAlign(CENTER,TOP);	g.fill(255,255);	g.imageMode(CENTER);	g.tint(0,255);
			g.image(i.image,trans.x-position.x+x/2+tScale/2+x/2,y/2+tScale/2+y/2,d.x,d.y);
				g.noTint();
				g.filter(BLUR,3);
		g.endDraw();
		return g;
	}
	public PGraphics getFullTextGraphics(Description d) {
		if (d.base) {
			textSize(14*tScale);
		}
		else {
			textSize(10*tScale);
		}
		float width = textWidth(d.text+d.valueText)+s_tR;
		PGraphics pg = createGraphics();
	}
	public PGraphics getHighRes(int r) {
		// Draw the big card for final rendering
		
		PGraphics pg = createGraphics(displayWidth*r,(int) Math.ceil(displayWidth*r*(cardBase.dimensions.x/cardBase.dimensions.y)));
		pg.beginDraw();
		tScale = (float) 1.667*(displayWidth/cardBase.dimensions.x)*r;
			pg.background(0);
			pg.tint(cR,cG,cB);
		pg.image(cardBase.image,
				0,
				0,
				displayWidth*r,
				(displayWidth/cardBase.dimensions.x)*cardBase.dimensions.y*r);
			pg.noTint();
		pg.image(cardNull3.image,-1*bigPos.x*displayWidth,-1*bigPos.y*displayHeight,displayWidth*r,(int)((displayWidth/cardNull.dimensions.x)*cardNull.dimensions.y*r));
		pg.blend(cardRunes,0,0,413,563,(int)(-1*bigPos.x*displayWidth),(int)(-1*bigPos.y*displayHeight),displayWidth*r,(int)((displayWidth/cardNull.dimensions.x)*cardNull.dimensions.y*r),SCREEN);
		pg.image(cardNull.image,-1*bigPos.x*displayWidth,-1*bigPos.y*displayHeight,displayWidth*r,(displayWidth/cardNull.dimensions.x)*cardNull.dimensions.y*r);
		pg.image(cardNull2.image,-1*bigPos.x*displayWidth,-1*bigPos.y*displayHeight,displayWidth*r,(displayWidth/cardNull2.dimensions.x)*cardNull2.dimensions.y*r);	
		GradText title = makeGradGraphics(this.title,displayWidth*r/2,displayWidth*r/10,displayWidth*r/3,displayWidth*r/20,color(cR,cG,cB),color(255));
		pg.image(title.p,title.x,title.y);
			pg.textSize(round((float) (24*tScale)));
		pg.text(init,((displayWidth-3)*r)/2-1*bigPos.x*displayWidth,(displayWidth/cardBase.dimensions.x)*cardBase.dimensions.y*r*((float)315/563)-1*bigPos.y*displayHeight);
			pg.textSize(round((float) (12*tScale)));
		pg.text(level,((displayWidth-3)*r)/2-1*bigPos.x*displayWidth,(displayWidth/cardBase.dimensions.x)*cardBase.dimensions.y*r*((float)86/563)-1*bigPos.y*displayHeight);
			pg.textAlign(LEFT);
		pg.imageMode(CORNER);
		getViewArrange(r);
		for (ActionView a : viewList) {
			a.draw(true);
		}
		res = r;
		pg.endDraw();
		//pg.drawBB();
		return pg;
	}
	public void renderMask(int r) {
		// Render the card mask on top of the card
		
		blend(cardMask.image,
				(int)(-1*bigPos.x*displayWidth),
				(int)(-1*(2*bigPos.y-1)*displayHeight),
				displayWidth*r,
				ceil((displayWidth/cardMask.dimensions.x)*cardMask.dimensions.y)*r,
				(int)(-1*bigPos.x*displayWidth),
				(int)(-1*bigPos.y*displayHeight),
				displayWidth*r,
				ceil((displayWidth/cardMask.dimensions.x)*cardMask.dimensions.y)*r,
				SCREEN);
	}
	public void drawBB(boolean top) {
		// Draw Bounding Boxes for ActionViews, specifically for top or bottom
			// DEVELOPING TOOL
		
		for (ActionView a : viewList) {
			if (a.aGraphic.top == top) {
				a.draw(true);
				if (drawBBs) {
					a.drawBB();
				}
			}
		}
	}
	public void drawBB() {
		// Draw Bounding Boxes for all ActionViews
			// DEVELOPING TOOL
		
		drawBB(true);
		drawBB(false);
	}
	public void saveImage(int r) {
		// Save the card as an image to your computer
		println("DONE");
		
		//fullPixelArray = getPixelArray(r, fullPixelArray);
		
		//int z = getCutoff(fullPixelArray);
		
		//PImage image = createImage(displayWidth*r,z,RGB);
		/* int[] newList = new int[image.pixels.length];
		
		for (int a=0; a<z;a++) {
			for (int b=0; b<displayWidth*r;b++) {
				newList[a*displayWidth*r+b]=fullPixelArray.get(a).get(b);
			}
		}
		
		image.pixels = newList;*/
		
		dI = getHighRes(r);
		dI.save("ImageTest.jpg");
		
		
		
		
	}
	public int getCutoff(ArrayList<ArrayList<Integer>> fullPixelArray) {
		// Get the y location of the cutoff line on the card
		
		int z = 10;
		for (int i=0;i<fullPixelArray.size();i++) {
			if (fullPixelArray.get(i).get(0)==color(0)) {
				z=i;
				break;
			}
		}
		if (z<=10) {
			z=ceil(displayWidth/cardBase.dimensions.x*cardBase.dimensions.y);
		}
		return z;
	}
	/* public ArrayList<ArrayList<Integer>> getPixelArray(int r, ArrayList<ArrayList<Integer>> fullPixelArray) {
		// Get the Pixel Array for the big-drawn card and return it for parsing
		
		int y = ceil((displayWidth/cardBase.dimensions.x)*(cardBase.dimensions.y/displayHeight)*r);
		for (int a=0;a<y;a++) { // a = horizontal row # (chunk)
			bigPos.y = a;
			for (int b=0;b<r;b++) { // b = vertical column # (chunk)
				bigPos.x = b;
				bigDraw(r);
				delay(100);
				loadPixels();
				for (int c=0;c<displayHeight;c++) { // c = y pixel # on screen
					if (bigPos.x == 0) { // initialize all new rows (if you are all the way on the left)
						fullPixelArray.add(new ArrayList<Integer>());
					}
					for (int d=0;d<displayWidth;d++) { // d = x pixel # on screen
						fullPixelArray.get((int) (bigPos.y*displayHeight)+c).add(pixels[d+c*displayWidth]);
					}g
				}
			}
		}
		return fullPixelArray;
	} */
	public GradText makeGradGraphics(String text,float x, float y,float x2,float y2,int c1,int c2) {
		// Make a Gradient Text with the given parameters
		
		GradText g = new GradText(this,text,x,y,x2,y2,c1,c2,null);
		g.pixList = getTextPixels(g);
		g.getInit();
		gTextList.add(g);
		return g;
	}
	public PGraphics getGText(GradText g) {
		// Draw a Gradient Text
			// Parses every line individually and makes all nonblack pixels a color on the gradient
		PGraphics p = createGraphics((int) Math.ceil(g.x2),(int) Math.ceil(g.y2));
		p.beginDraw();
		for (int a=0;a<g.y2;a++) {
			float inter = map(a,0,g.y2,0,1);
			int c = lerpColor(g.c1, g.c2, inter);
			for (int b=0;b<g.x2;b++) {
				if (g.pixList[b+(a*ceil(g.x2))] != color(0)) {
					p.set(b,a,c);
				}
			}
		}
		p.endDraw();
		return p;
	}
	public int[] getTextPixels(GradText g) {
		// Get the nonblack pixels in a string of text and return a hyper-contrast list of pixels (black or white)
		
			textFont(majalla);	textSize(ceil(g.y2)/2);
		int[] pixList = new int[(ceil(g.x2)*ceil(g.y2))];
		PGraphics p = createGraphics(ceil(g.x2)*2,ceil(g.y2)*2);
		p.beginDraw();
				p.background(0);	p.fill(255);	p.textAlign(TOP,TOP);	p.textFont(pirataOne);	p.textSize(g.y2);
			p.text(g.text,g.x2/2,g.y2/2);
			p.loadPixels();
			pixList = p.pixels;
		p.endDraw();
		return pixList;
	}
	public void drawMouseCursor(float size) {
		// Draw the mouse cursor

			noFill();	stroke(240, 240, 240);
		ellipse(mouseX, mouseY, size, size);
			noStroke();
	}
	public void backgroundGraphics() {
		// Draws the background
		
		background(190,150,110);
		for (Image i: imgList) {
			image(i.image,i.position.x,i.position.y,i.dimensions.x,i.dimensions.y);
		}
			strokeWeight(10);	fill(150);
		rect(150,displayHeight/20,513,displayWidth*4/9,displayWidth/50);
		rect(713,displayHeight/20,270,displayWidth*4/9,displayWidth/50);
		rect(1033,displayHeight/20,280,displayWidth*4/9,displayWidth/50);
			fill(0);
	}
	public void drawCard() {
		// Draws the basic card
		
			tint(cR,cG,cB);
		image(cardBase.image,cardBase.position.x,cardBase.position.y,cardBase.dimensions.x,cardBase.dimensions.y);
			noTint();
		image(cardNull3.image,cardNull3.position.x,cardNull3.position.y,cardNull3.dimensions.x,cardNull3.dimensions.y);
			tint(cR,cG,cB);
		blend(cardRunes,0,0,413,563,200,100,413,563,SCREEN);
			noTint();
		image(cardNull.image,cardNull.position.x,cardNull.position.y,cardNull.dimensions.x,cardNull.dimensions.y);
		image(cardNull2.image,cardNull2.position.x,cardNull2.position.y,cardNull2.dimensions.x,cardNull2.dimensions.y);
			textFont(pirataOne);	fill(255);	textAlign(CENTER);	textSize(32);
		text(title,406,157);
			textSize(40);
		text(init,406,415);
			textSize(20);
		text(level,406,186);
	}
	public void drawXOut() {
		// Draws the X in the corner to exit the program
		
		textAlign(LEFT);
		xOut.show = true;
		xOut.draw();
	}
	public void drawActionViews() {
		// Draws the ActionViews on the basic card
		
		arrangeViews();
		drawAViews(true);
		drawAViews(false);
	}
	public void drawAViews(boolean top) {
		// Draws one side of the card's ActionViews
		
		for (ActionView a : viewList) {
			if (a.aGraphic.top == top) {
				a.draw(false);
				if (drawBBs) {
					a.drawBB();
				}
			}	
		}
	}
	public void drawColorMenu() {
		// Draws the menu for color selection
		
		
		
		drawColors(RSlider);
		drawColors(GSlider);
		drawColors(BSlider);
		
			strokeWeight(5);	stroke(255);	fill(cR,cG,cB);
		rect(793,400,100,100);
		
		if (settingColor) {
			if (mouseX>750 && mouseX<775 && mouseY>100 && mouseY<355) {
				cR = getColor(new PVector(0,100));
			}
			if (mouseX>830 && mouseX<855 && mouseY>100 && mouseY<355) {
				cG = getColor(new PVector(0,100));
			}
			if (mouseX>910 && mouseX<935 && mouseY>100 && mouseY<355) {
				cB = getColor(new PVector(0,100));
			}
			colorRunes();
		}
		line(750,cR+100,775,cR+100);
		line(830,cG+100,855,cG+100);
		line(910,cB+100,935,cB+100);
		
		strokeWeight(1);
	}
	public void drawDescMenu() {
		// Draws the menu for the current description
		
			//Dropdowns
		descChooseBox.show = true;
		descChooseBox.draw();
		remove.show = true;
		remove.draw();
		if (actionList.get(actionView).descList.get(actionDescView).base) {
			descChooseBox.suggestList = 0;
		}
		else {
			descChooseBox.suggestList = 1;
		}
		checkActionText();
	}
	public void checkActionText() {
		// Check and draw all necessary boxes for the current Description's text
		
		String vText = actionList.get(actionView).descList.get(actionDescView).text;
		checkText(vText,new ArrayList<String>(Arrays.asList("Attack","Move","Heal","Shield","Loot","Retaliate","XP","Push","Pull","Pierce","Range","Target")),numberChoose);
		checkText_Base(vText,new ArrayList<String>(Arrays.asList("Heal","Shield","Bless","Invisible","Strengthen","Retaliate")),selfCheckBox);
		checkText(vText,new ArrayList<String>(Arrays.asList("Shield","Retaliate")),roundCheckBox);
		checkText(vText,new ArrayList<String>(Arrays.asList("Persistent Bonus","Summon","Limited Persistent Bonus")),loseCheckBox);
		checkText(vText,new ArrayList<String>(Arrays.asList("Consume","Infuse")),elementChoose);
		checkText(vText,"Text",textText);
		checkText(vText,"Summon",new ArrayList<Thing>(Arrays.asList(summonTitle,hpBox,attackBox,moveBox,rangeBox)));
		checkText(vText,"Limited Persistent Bonus",pips);
	}
	public void checkText(String vText, ArrayList<String> textList, Thing thing) {
		// Checks and parses the current Description's text
			// List of possible texts
			// Single box to set up
		
		if (textList.contains(vText)) {
			thing.show = true;
			thing.draw();
		}
		else {
			thing.show = false;
		}
	}
	public void checkText(String vText, String text, Thing thing) {
		// Checks and parses the current Description's text
			// Single possible text
			// Single box to set up
		
		if (vText == text) {
			thing.show = true;
			thing.draw();
		}
		else {
			thing.show = false;
		}
	}
	public void checkText_Base(String vText, ArrayList<String> textList, Thing thing) {
		// Checks and parses the current Description's text
			// List of possible texts
			// Single box to set up
			// Only triggers if the action is a base
		
		if (textList.contains(vText)) {
			if (actionList.get(actionView).descList.get(actionDescView).base) {
				thing.show = true;
				thing.draw();
			}
		}
		else {
			thing.show = false;
		}
	}
	public void checkText(String vText, ArrayList<String> textList, ArrayList<Thing> thingList) {
		// Checks and parses the current Description's text
			// List of possible texts
			// List of boxes to set up
		
		if (textList.contains(vText)) {
			for (Thing t : thingList) {
				t.show = true;
				t.draw();
			}
		}
		else {
			for (Thing t : thingList) {
				t.show = false;
			}
		}
	}
	public void checkText(String vText, String text, ArrayList<Thing> thingList) {
		// Checks and parses the current Description's text
			// Single possible text
			// List of boxes to set up
		
		if (vText == text) {
			for (Thing t : thingList) {
				t.show = true;
				t.draw();
			}
		}
		else {
			for (Thing t : thingList) {
				t.show = false;
			}
		}
	}
	public void drawCardMenu() {
		// Draws the card settings menu
		
		titleBox.show = true;
		levelBox.show = true;
		initBox.show = true;
		
		titleBox.draw();
		levelBox.draw();
		initBox.draw();
	}
	public void drawMenu() {
		// Draws the menu (second panel)
		
		drawXOut();
		if (settingMode == 1) {
			drawColorMenu();
		}
		else {
			descChooseBox.show = false;
			titleBox.show = false;
			levelBox.show = false;
			initBox.show = false;
		}
		if (settingMode == 2) {
			drawDescMenu();
		}
		else {
			descChooseBox.show = false;
			remove.show = false;
		}
		if (settingMode == 3) {
			drawCardMenu();
		}
		else {
			titleBox.show = false;
			levelBox.show = false;
			initBox.show = false;
		}
	}
	public void drawSuggLists() {
		// Draw the suggestion dropdowns for every textbox
		
		for (TextBox t : textBoxList) {
			if (t.typing && t.suggestions) {
				ArrayList<String> suggList = t.getSuggFromList();
				while (suggList.size() > 3) {
					suggList.remove(suggList.size()-1);
				}
				float yScale = t.position.y + t.dimensions.y;
				for (String s : suggList) {
						fill(200);	strokeWeight(5);	stroke(0);
					rect(t.position.x,yScale,t.dimensions.x,t.dimensions.y);
						fill(0);	textFont(majalla);	textSize(20);
					text(s,t.position.x+10,yScale+35);
					yScale += t.dimensions.y;
				}
			}
		}
	}
	public void colorRunes() {
		// Get the current look for the colorRunes
		
		cardRunes.beginDraw();
			cardRunes.clear();
			cardRunes.background(cR/2,cG/2,cB/2);
			cardRunes.blend(cardMask.image,0,0,413,563,0,0,413,563,DARKEST);
		cardRunes.endDraw();
	}
	public int drawGraphics(int yValue, boolean top) {
		// Draw all of the ActionGraphics on the right side of the screen, for one side of the card (top or bottom). Returns how far down the list reaches
		
		for (ActionGraphic a : aGraphicList) {
			if (a.top == top) {
				a.position.y = yValue;
				a.draw();
				yValue += a.action.descList.size()*30+50;
			}
			for (Button b : buttonList) {
				try {
					if (b.action == a.action) {
						b.position.y = a.position.y + a.action.descList.size()*30;
						b.show = true;
					}
				}
				catch (Exception e) {}
			}
		}
		return yValue;
	}
	public boolean type(TextBox t, boolean any) {
		// Type in the selected TextBox. Returns true if any textbox was typed into, false if not.
		
		if (t.typing) {
			any = true;
			if (key != '\b' && keyCode != SHIFT && keyCode != ENTER) {
				t.text += key;
			}
			else if (key == '\b') {
				try {
					List<Character> charList = new ArrayList<>();
					for (char c : t.text.toCharArray()) {
						charList.add(c);
					}
					charList.remove(charList.size()-1);
					t.text = "";
					for (Character c : charList) {
						t.text += c;
					}
				}
				catch (Exception e) {}
			}
			else if (keyCode == ENTER) {
				for (TextBox z : textBoxList) {
					if (z.typing) {
						z.stopType();
						setVars(z);
						clickOff();
					}
				}
			}
		}
		return any;
	}
	public void moveGraphic(boolean any) {
		// Moves an action down or up a list.
		
		if (keyCode == DOWN) {
			if (actionList.get(actionView).descList.get(actionDescView).base) {
				moveAction(false,actionList.get(actionView));
			}
		}
		else if (keyCode == UP) {
			if (actionList.get(actionView).descList.get(actionDescView).base) {
				moveAction(true,actionList.get(actionView));
			}
		}
		if (keyCode == DOWN) {
			if (! actionList.get(actionView).descList.get(actionDescView).base) {
				moveDesc(false,actionList.get(actionView),actionList.get(actionView).descList.get(actionDescView));
			}
		}
		else if (keyCode == UP) {
			if (! actionList.get(actionView).descList.get(actionDescView).base) {
				moveDesc(true,actionList.get(actionView),actionList.get(actionView).descList.get(actionDescView));
			}
		}
	}
	public void drawClassMenu() {
		// Initializes the menu to get saved classes, cards, etc.
		
		background(100);
			strokeWeight(10);	stroke(0);	fill(150);
		rect((float) (displayWidth*0.05),(float) (displayHeight*0.05),(float) (displayWidth*0.9),(float) (displayHeight*0.9),20);
			strokeWeight(5);
		int z = 0;
		for (CClass c : getClasses()) {
			drawClassPreview(c,z);
			z++;
		}
		
	}
	public void drawClassPreview(CClass c, int z) {
		// Draws a preview of a class at a specified location and size
		
		imageMode(CORNER);
		tint(c.cR,c.cG,c.cB);
		image(classSampleList.get(z).get(),(float) (Math.floorMod(z,5)*(displayWidth*0.8)/5+(displayWidth*0.6)/5),(float) (floor((z)/5)*(displayHeight*0.8)/3+(displayHeight*0.6)/5));
		fill(255,0);
		rect((float) (Math.floorMod(z,5)*(displayWidth*0.8)/5+(displayWidth*0.6)/5),(float) (floor((z)/5)*(displayHeight*0.8)/3+(displayHeight*0.6)/5),(float) ((displayWidth*0.6)/5),(float) ((displayWidth*0.6)/5),10);
		fill(200);
		textAlign(CENTER,BASELINE);
		text(c.name,(float) ((Math.floorMod(z,5)*(displayWidth*0.8)/5+(displayWidth*0.6)/5) + ((displayWidth*0.3)/5)),(float) ((floor((z)/5)*(displayHeight*0.8)/3+(displayHeight*0.6)/5) + ((displayWidth*0.55)/5)));
		imageMode(CENTER);
		tint(c.cR,c.cG,c.cB);
		image(c.icon,(float) ((Math.floorMod(z,5)*(displayWidth*0.8)/5+(displayWidth*0.6)/5) + ((displayWidth*0.3)/5)),(float) ((floor((z)/5)*(displayHeight*0.8)/3+(displayHeight*0.6)/5) + ((displayWidth*0.25)/5)), (float) ((displayWidth*0.4)/5),(float) ((displayWidth*0.4)/5));
	}
	public ArrayList<CClass> getClasses() {
		// Gets a list of the Classes in the project folder
		
		ArrayList<CClass> classList = new ArrayList<CClass>();
		File savedClasses = new File("saved_files/classes");
		
		for (final File f : savedClasses.listFiles()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(f.getPath()+"\\colors"));
				classList.add(new CClass(
					f.getName(),
					Integer.parseInt(br.readLine()),
					Integer.parseInt(br.readLine()),
					Integer.parseInt(br.readLine()),
					loadImage(f.getPath()+"\\icon.png")
				));
			}
			catch(Exception e) {
				println("Error: color data not found or corrupted for class "+f.getName());
				println(e);
			}
		}
		
		return classList;
	}
	public void getClassSamples(ArrayList<CClass> cList) {
		// get a list of sample images for every class in a list
		
		for (CClass c : cList) {
			classSampleList.add(initSample(c.cR,c.cG,c.cB));
		}
	}
	public PImage initSample(int r, int g, int b) {
		// create a sample view of a class
		//(float) ((displayWidth*0.6)/5)
		PGraphics p = createGraphics(displayWidth/5,displayWidth/5);
		p.beginDraw();
			p.background(0);
			p.fill(255);
			p.stroke(0);
			p.strokeWeight(5);
			p.rect(-2,-2,(float) ((displayWidth*0.6)/5)+2,(float) ((displayWidth*0.6)/5)+2,10);
			p.blend(sample.image,0,0,100,100,0,0,(int) ((displayWidth*0.6)/5),(int) ((displayWidth*0.6)/5),DARKEST);
			
		p.endDraw();
		PImage pi = createImage(displayWidth/5,displayWidth/5,ARGB);
		pi.pixels = p.pixels;
		imageMode(CORNER);
		for (int i = 0; i < pi.pixels.length; i++) {
			if (pi.pixels[i] == color(0)) {
				pi.pixels[i] = color(0,0);
			}
		}
		return pi;
	}
}