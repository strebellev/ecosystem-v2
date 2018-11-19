package entity;

import java.util.ArrayList;

import mechanisme.DNA2;
import objects.Food2;
import objects.Lac;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Carnivor1M {
	PApplet parent;
	ArrayList<PVector> carn1m;
	
	public PVector position;
	DNA2 dna2;
	float xoff;
	float yoff;
	///limite de terrain
	float d = 50;
	
	////////////variables genetiques
	float water;
	float food;
	float health;
	float r; ///la taille
	float maxspeed;
	
	int lastChildTime = -1;
	
	
	float maxforce;
	PVector acceleration;
	PVector velocity;
	
	
	
	@SuppressWarnings("deprecation")
	public Carnivor1M(PVector l, DNA2 dna2_, PApplet p){
		carn1m = new ArrayList<PVector>();
		
		parent = p;
		position = l.get();
		xoff = p.random(1000);
		yoff = p.random(1000);
		
		dna2 = dna2_;
		health = PApplet.map(dna2.genes[0], 0, 1, 50, 250);
		r = PApplet.map(dna2.genes[0], 0, 1, 15, 30);
		maxspeed = PApplet.map(dna2.genes[0], 0, 1, 5, 1);
		water = PApplet.map(dna2.genes[1], 0, 1, 100, 200);
		food = PApplet.map(dna2.genes[1], 0, 1, 100, 200);
		
		acceleration = new PVector(0,0);
		velocity = new PVector(0,0);
		maxforce = (float) 0.1;
		
		
		
	}
	
	void update(){
		///a garder pour que les mouvement se fassent
		position.add(velocity);
		velocity.add(acceleration);
		velocity.limit(maxspeed);
		acceleration.mult(0);
		acceleration.limit(maxspeed);
		
		water -= 0.1;
		if(water < 0){
			water = 0;
		}
		if(water > 200){
			water = 200;
		}
		//food -= 0.1;
		//health -= 0.01;
	}
	
	public void randomMv(){
		float vx = PApplet.map(parent.noise(xoff), 0, 1, -maxspeed, maxspeed);
		float vy = PApplet.map(parent.noise(yoff), 0, 1, -maxspeed, maxspeed);
		PVector velocity = new PVector(vx,vy);
		position.add(velocity);
		xoff += 0.01;
		yoff += 0.01;
	}
	
	public void findFood(PVector target){
		PVector desired = PVector.sub(target, position);
		float d = desired.mag();
		if(d < 100){
			float m = PApplet.map(d, 0, 100, 0, maxspeed);
			desired.setMag(m);
		}
		else{
			desired.setMag(maxspeed);
		}
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}
	
	public void findWater(PVector target){
		PVector desired = PVector.sub(target, position);
		float d = desired.mag();
		if(d < 100){
			float m = PApplet.map(d, 0, 100, 0, maxspeed);
			desired.setMag(m);
		}
		else{
			desired.setMag(maxspeed);
		}
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}
	
	public void drink(Lac l){
		ArrayList<PVector> lac = l.getLac();
		for(int i = lac.size()-1; i >= 0; i--){
			PVector lacposition = lac.get(i);
			float d = PVector.dist(position, lacposition);
			if(d < r ){
				water += 100;
			}
		}
	}
	
	public void eat2(Food2 f){
		ArrayList<PVector> food2 = f.getFood2();
		for(int i = food2.size()-1; i >= 0; i--){
			PVector food2position = food2.get(i);
			float d = PVector.dist(position, food2position);
			if(d < r){
				health += 100;
				food2.remove(i);
			}
		}
	}
	
	public void slurp(){
		water += 100;
	}
	
	void borders(PApplet p){
		if (position.x < -r) position.x = p.width+r;
	    if (position.y < -r) position.y = p.height+r;
	    if (position.x > p.width+r) position.x = -r;
	    if (position.y > p.height+r) position.y = -r;
	}
	
	void boundaries(){
		PVector desired = null;
		
		if(position.x < d){
			desired = new PVector(maxspeed,velocity.y);
		}
		else if(position.x > parent.width - d){
			desired = new PVector(-maxspeed, velocity.y);
		}
		
		if(position.y < d){
			desired = new PVector(velocity.x, maxspeed);
		}
		else if(position.y > parent.height-d){
			desired = new PVector(velocity.x, -maxspeed);
		}
		
		if(desired != null){
			desired.normalize();
			desired.mult(maxspeed);
			PVector steer = PVector.sub(desired, velocity);
			steer.limit(maxforce);
			applyForce(steer);
		}
		
	}
	
	void display(){
		parent.ellipseMode(PConstants.CENTER);
		parent.strokeWeight(2);
		parent.stroke(0,0,0, health);
		parent.fill(213, 0,0, health);
		//parent.fill(213,0,0, water);
		parent.ellipse(position.x, position.y, r, r);
	}
	
	public DNA2 getDNA2(){
		return dna2;
	}
	
	public float getR(){
		return r;
	}
	
	public float getWater(){
		return water;
	}
	
	
	public ArrayList<PVector> getCarnivor1M(){
		return carn1m;
	}
	
	public void run(PApplet p){
		update();
		//borders(parent);
		boundaries();
		display();
	}
	
	void applyForce(PVector force){
		acceleration.add(force);
	}
	
	public boolean soif(){
		if(water < 50){
			return true;
		}
		else{
			return false;
		}
	}
	
}
