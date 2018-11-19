package objects;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Food {
	PApplet parent;
	ArrayList<PVector> food;
	
	
	public Food(int nombFood, PApplet p){
		parent = p;
		food = new ArrayList<PVector>();
	}
	
	@SuppressWarnings("deprecation")
	public void add(PVector l){
		food.add(l.get());
	}
	
	public void run(PApplet p){
		for(PVector f : food){
			parent.rectMode(PConstants.CENTER);
			parent.stroke(0);
			parent.fill(255,0,0);
			parent.rect(f.x, f.y, 8, 8);
		}
		
		if(parent.random(1) < 0.001){
			//food.add(new PVector(parent.random(parent.width), parent.random(parent.height)));
		}
		
	}
	
	public ArrayList<PVector> getFood(){
		return food;
	}
}
