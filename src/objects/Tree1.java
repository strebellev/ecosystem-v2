package objects;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

/////////////////V3/////////////////////////


public class Tree1 {
	PApplet parent;
	
	ArrayList<PVector> tree1;
	
	
	public Tree1(int nombTree1, PApplet p){
		parent = p;
		tree1 = new ArrayList<PVector>();
	}
	
	@SuppressWarnings("deprecation")
	public void add(PVector l){
		tree1.add(l.get());
	}
	
	public void run(PApplet p){
		for(PVector t : tree1){
			parent.stroke(1);
			parent.fill(76,153,0);
			parent.ellipse(t.x,t.y, 30, 30);
		}
	}
	
	
	
	ArrayList<PVector> getTree1(){
		return tree1;
	}
	
}
