package entity;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Player {
	PApplet parent;
	
	public PVector position;
	
	boolean up,down,left,right = false;
	
	@SuppressWarnings("deprecation")
	public Player(PApplet p, PVector l){
		parent = p;
		position = l.get();
		
	}
	
	public void run(PApplet p){
		//parent.rectMode(PConstants.CENTER);
		parent.fill(204,0,204);
		//parent.rect(position.x, position.y, 10, 10);
		//parent.rect(parent.width/2, parent.height/2, 10, 10);
		parent.ellipse(parent.width/2, parent.height/2, 10, 10);
	}
	
	public void update(PApplet p){
		
	}
	
	
	
	public void PlayerMvt(PApplet p){
		if(p.keyPressed){
			if(p.key == PConstants.CODED){
				if(p.keyCode == PConstants.UP){
					position.y += 5;
				}
				if(p.keyCode == PConstants.DOWN){
					position.y -= 5;
				}
				if(p.keyCode == PConstants.LEFT){
					position.x += 5;
				}
				if(p.keyCode == PConstants.RIGHT){
					position.x -= 5;
				}
			}
		}
	}
	
	
	
	
	
	
	
}
