

import mechanisme.World2;
import processing.core.PApplet;

public class Game extends PApplet{
	
	public static void main(String[] args){
		PApplet.main("Game");
	}
	
	public void settings(){
		size(1200,900);
	}
	
	//World world;
	World2 world2;
	
	public void setup(){
		//world = new World(this);
		world2 = new World2(this);
	}
	
	public void draw(){
		//background(255);
		//world.run();
		world2.run();
	}
}
