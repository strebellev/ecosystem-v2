package objects;

import processing.core.PApplet;
import processing.core.PConstants;

public class Prairie {
	PApplet parent;
	
	public Prairie(PApplet p){
		parent = p;
	}
	
	public void run(PApplet p){
		parent.noStroke();
		parent.fill(0,153,0,25);
		parent.rect(250, 250, 1500, 1500,7);
	}
}
