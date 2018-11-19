package objects;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Meat2 {
	PApplet parent;
	ArrayList<PVector> meat2;
	
	float meat2Live;
	public PVector position;
	
	@SuppressWarnings("deprecation")
	public Meat2(PVector l,PApplet p){
		parent = p;
		position = l.get();
		meat2 = new ArrayList<PVector>();
		meat2Live = 255;
	}
	
	
	public void run(PApplet p){
		parent.ellipseMode(PConstants.CENTER);
		parent.stroke(0,meat2Live);
		parent.fill(213,0,0,meat2Live);
		parent.ellipse(position.x, position.y, 8, 8);
		//parent.text("meat2live " + meat2Live, position.x +10, position.y+10);
		update();
		
	}
	
	void update(){
		meat2Live -= 0.06;
	}
	
	
	public ArrayList<PVector> getMeat2(){
		return meat2;
	}
	
	public PVector position(){
		return position;
	}
	
	public boolean rotten(){
		if(meat2Live < 0.0){
			return true;
		}
		else{
			return false;
		}
	}
	
	
}
