package objects;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Lac {
	PApplet parent;
	
	ArrayList<PVector> lac;
	
	public PVector position;
	float r = 50;
	
	
	@SuppressWarnings("deprecation")
	public Lac(PApplet p,PVector l){
		parent = p;
		lac = new ArrayList<PVector>();
		position = l.get();
		
	}
	
	@SuppressWarnings("deprecation")
	public void add(PVector l){
		lac.add(l.get());
	}
	
	void update(){
		//parent.ellipseMode(PConstants.CENTER);
		parent.stroke(1);
		parent.fill(0,182,210);
		parent.ellipse(position.x, position.y, r, r);
	}
	
	public void run(PApplet p){
		update();
	}
	
	public ArrayList<PVector> getLac(){
		return lac;
	}
	
	public PVector position(){
		return position;
	}
	
	public float getR(){
		return r;
	}
}
