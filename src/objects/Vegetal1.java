package objects;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;
import processing.core.PVector;

public class Vegetal1 {
	PApplet parent;
	
	int worldW = 2000;
	int worldH = 2000;
	
	Tree2 tree2;
	ArrayList<Food2> food2;
	
	public Vegetal1(PApplet p){
		
		int nombtree2 = 20;
		tree2 = new Tree2();
		for(int i = 0; i < nombtree2; i++){
			//tree2.add(new PVector(p.random(p.width-1000, p.width-150), p.random(p.height-700, p.height-150)));
			tree2.add(new PVector(p.random(worldW-1000, worldW-150), p.random(worldH-700, worldH-150)));
		}
		
		int nombfood2 = 1;
		food2 = new ArrayList<Food2>();
		for(int i = 0; i < nombfood2; i++){
			food2.add(new Food2(nombfood2, p));
		}
		
	}
	
	public void run(PApplet p){
		
			////arbres et fruits
			tree2.run(parent);
			
			/*
			if(parent.random(1) < 0.01){
				food2.add(new Food2(0, parent));
			}
			
			Iterator<Food2> F2 = food2.iterator();
			while(F2.hasNext()){
				Food2 f2 = F2.next();
				f2.run(parent);
				f2.grow(tree2);
				f2.growOld(f2);
				if(f2.rotten()){
					F2.remove();
				}
			}
			*/
	}
}
