package objects;

import java.util.ArrayList;

import entity.Herbivor4F;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Meat {
	PApplet parent;
	ArrayList<PVector> meat;
	
	float meatLive;
	public PVector position;
	
	public Meat(int nombMeat,PApplet p){
		parent = p;
		meat = new ArrayList<PVector>();
		meatLive = 255;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void add(PVector l){
		meat.add(l.get());
	}
	
	
	
	public void dropMeat(PVector target){
		//int nombmeat = 10;
		//for(int i = 0; i < nombmeat; i++){
			
		//}
		meat.add(new PVector(parent.random(target.x -10, target.x +10), parent.random(target.y-10, target.y+10)));
		
	}
	
	
	public void run(PApplet p){
		for(PVector m : meat){
			parent.ellipseMode(PConstants.CENTER);
			parent.stroke(0,meatLive);
			parent.fill(213,0,0,meatLive);
			parent.ellipse(m.x, m.y, 8, 8);
		}
	}
	
	public void growOld(Meat m){
		ArrayList<PVector> meat = m.getMeat();
		for(int i = meat.size()-1; i >= 0; i--){
			meatLive -= 0.06;
		}
	}
	
	public ArrayList<PVector> getMeat(){
		return meat;
	}
	
	public PVector position(){
		return position;
	}
	
	public boolean rotten(){
		if(meatLive < 0.0){
			return true;
		}
		else{
			return false;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
