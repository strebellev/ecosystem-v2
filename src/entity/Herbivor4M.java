package entity;

import java.util.ArrayList;

import mechanisme.DNA2;
import objects.Food2;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Herbivor4M {
	PApplet parent;
	ArrayList<PVector> herb4m;
	
	public PVector position;
	DNA2 dna2;
	PVector velocity;
	PVector acceleration;
	
	float wandertheta;
	float maxforce;
	
	///limite de terrain
	float d = 100;

	////////////variables genetiques
	float water;
	float food;
	public float health;
	float r; ///la taille
	public float maxspeed;
	float lifeTime;
	
	float maxLifeTime;
	float speed;
	
	int lastChildTime = -1;
	
	@SuppressWarnings("deprecation")
	public Herbivor4M(PVector l, DNA2 dna2_, PApplet p){
		herb4m = new ArrayList<PVector>();
		
		parent = p;
		position = l.get();
		
		dna2 = dna2_;
		health = PApplet.map(dna2.genes[0], 0, 1, 50, 250);
		r = PApplet.map(dna2.genes[0], 0, 1, 4, 6);
		//maxspeed = PApplet.map(dna2.genes[0], 0, 1, 5, 1);
		
		speed = PApplet.map(dna2.genes[0], 0, 1, 4, 1);
		
		lifeTime = PApplet.map(dna2.genes[0], 0, 1, 100, 250);
		
		maxLifeTime = PApplet.map(dna2.genes[0], 0, 1, 100, 250);
		
		water = PApplet.map(dna2.genes[1], 0, 1, 100, 200);
		food = PApplet.map(dna2.genes[1], 0, 1, 100, 200);
		
		wandertheta = 0;
		acceleration = new PVector(0,0);
		velocity = new PVector(0,0);
		maxforce = (float) 0.1;
		
	}
	
	void update(){
		position.add(velocity);
		velocity.add(acceleration);
		velocity.limit(maxspeed);
		acceleration.mult(0);
		acceleration.limit(maxspeed);
		
		water -= 0.05;
		if(water < 0) water = 0;
		if(water > 200) water = 200;
		food -= 0.1;
		if(food < 0) food = 0;
		if(food > 200) food = 200;

		
		/////attention health pas top vu que les petit gagne de la vie en plus
		//health -= 0.1;
		if(health < 0) health = 0;
		if(health > 250) health = 250;
		
		lifeTime -= 0.01;
		////plus ils veillissent plus il ralentissent
		maxspeed = speed * lifeTime/maxLifeTime;
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public void wander(PApplet p){
		
		float wanderR = 25;  // Radius for our "wander circle"
		float wanderD = 80;  // Distance for our "wander circle"
		float change = (float) 0.3;
		wandertheta += p.random(-change, change);  // Randomly change wander theta
		
		// Now we have to calculate the new position to steer towards on the wander circle
		PVector circlepos = velocity.get();
		circlepos.normalize();
		circlepos.mult(wanderD);
		circlepos.add(position);  // Make it relative to boid's position
		
		float h = velocity.heading2D();
		
		PVector circleOffSet = new PVector(wanderR*p.cos(wandertheta+h), wanderR*p.sin(wandertheta+h));
		PVector target = PVector.add(circlepos, circleOffSet);
		seek(target);
		
	}
	
	void applyForce(PVector force){
		acceleration.add(force);
	}
	
	void seek(PVector target){
		PVector desired = PVector.sub(target, position);
		desired.normalize();
		desired.mult(maxspeed);
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}
	
	public void boundaries(){
		PVector desired = null;
		
		if(position.x < d){
			desired = new PVector(maxspeed,velocity.y);
		}
		else if(position.x > 2000 - d){
			desired = new PVector(-maxspeed, velocity.y);
		}
		
		if(position.y < d){
			desired = new PVector(velocity.x, maxspeed);
		}
		else if(position.y > 2000 -d){
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
	
	public boolean isInDanger(PVector target){
		return PApplet.dist(position.x, position.y, target.x, target.y) < 150;
	}
	
	public void runaway(PVector target){
		PVector desired = new PVector(position.x - target.x, position.y - target.y);
		desired.normalize();
		desired.mult(maxspeed);
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}
	
	
	public void run(PApplet p){
		update();
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public void display(PApplet p){
		float theta = velocity.heading2D() + p.radians(90);
		p.fill(91,175,8);
		p.stroke(255,0,0, lifeTime);
		p.strokeWeight(1);
		p.pushMatrix();
		p.translate(position.x, position.y);
		p.rotate(theta);
		p.beginShape(p.TRIANGLES);
		p.vertex(0, -r*2);
		p.vertex(-r, r*2);
		p.vertex(r, r*2);
		p.endShape(PConstants.CLOSE);
		p.popMatrix();
		
		p.ellipse(position.x,position.y,r*2,r*2);
		
		//p.text("health " + health , position.x +10, position.y +20);
	}
	
	public void findFood(PVector target){
		PVector desired = PVector.sub(target, position);
		float d = desired.mag();
		if(d < 100){
			float m = PApplet.map(d, 0, 10, 0, maxspeed);
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
			float m = PApplet.map(d, 0, 10, 0, maxspeed);
			desired.setMag(m);
		}
		else{
			desired.setMag(maxspeed);
		}
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}
	
	public DNA2 getDNA2(){
		return dna2;
	}
	
	public float getR(){
		return r;
	}
	
	public ArrayList<PVector> getHerbivor4M(){
		return herb4m;
	}
	
	public boolean dead(){
		if(health < 0.0 || lifeTime < 0.0)return true;
		else 	return false;
		
	}
	
	public boolean soif(){
		if(water < 100)	return true;
		else			return false;
	}
	
	public boolean faim(){
		if(food < 100)	return true;
		else			return false;
		
	}
	
	public void drinkWater(){
		water += 100;
	}
	
	public void eat2(Food2 f){
		ArrayList<PVector> food2 = f.getFood2();
		for(int i = food2.size()-1; i >= 0; i--){
			PVector food2position = food2.get(i);
			float d = PVector.dist(position, food2position);
			if(d < r){
				//health += 100;
				food += 50;
				food2.remove(i);
			}
		}
	}
	
	
	public int lastChildTime(){
		return parent.millis() - lastChildTime;
	}
	
	public void setLastChildTime(int time){
		lastChildTime = time;
	}
	
}
