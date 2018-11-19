package entity;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Charognard1M {
	PApplet parent;
	ArrayList<PVector> cha1m;
	
	public PVector position;
	
	float[] x = new float[20];
	float[] y = new float[20];
	float segLength = 18;
	
	
	@SuppressWarnings("deprecation")
	public Charognard1M(PVector l, PApplet p){
		
		cha1m = new ArrayList<PVector>();
		
		parent = p;
		position = l.get();
		
	}
	
	public void run(){
		display();
	}
	
	public void dragSegment(int i , float xin, float yin){
		float dx = xin - x[i];
		float dy = yin - y[i];
		float angle = PApplet.atan2(dy, dx);
		x[i] = xin - PApplet.cos(angle) * segLength;
		y[i] = yin - PApplet.cos(angle) * segLength;
		segment(x[i], y[i], angle);
	}
	
	public void segment(float x, float y, float a){
		parent.pushMatrix();
		parent.translate(x, y);
		parent.rotate(a);
		parent.line(0, 0, segLength, 0);
		
		parent.popMatrix();
	}
	
	void display(){
		parent.strokeWeight(9);
		parent.stroke(0);
	}
}
