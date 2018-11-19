package mechanisme;

import java.util.ArrayList;
import java.util.Iterator;

import entity.Carnivor2F;
import entity.Carnivor2M;
import entity.Herbivor4F;
import entity.Herbivor4M;
import entity.Player;
import objects.Food2;
import objects.Lac;
import objects.Meat;
import objects.Meat2;
import objects.Prairie;
import objects.Tree2;
import processing.core.PApplet;
import processing.core.PVector;

public class World3 {
	PApplet parent;
	
	int worldW = 2000;
	int worldH = 2000;
	
	Prairie prairie;
	Player player;
	
	Tree2 tree2;
	ArrayList<Food2> food2;
	ArrayList<Lac> lac;
	
	ArrayList<Meat> meat;
	
	ArrayList<Meat2> meat2;
	
	
	ArrayList<Herbivor4M> herb4m;
	ArrayList<Herbivor4F> herb4f;
	
	ArrayList<Carnivor2M> carn2m;
	ArrayList<Carnivor2F> carn2f;
	
	
	public World3(PApplet p){
		parent = p;
		
		prairie = new Prairie(parent);
		
		float px = parent.width/2 - worldW/2;
		float py = parent.height/2 - worldH/2;
		PVector playerVector = new PVector(px,py); 
		player = new Player(parent,playerVector);
		
		//tree2//
		int nombtree2 = 20;
		tree2 = new Tree2();
		for(int i = 0; i < nombtree2; i++){
			tree2.add(new PVector(p.random(worldW-1750 , worldW-250 ), p.random(worldH-1750 , worldH-250 )));
		}
		
		//food2//
		int nombfood2 = 1;
		food2 = new ArrayList<Food2>();
		for(int i = 0; i < nombfood2; i++){
			food2.add(new Food2(nombfood2, p));
		}
		
		//meat2//
		int nombMeat2 = 1;
		meat2 = new ArrayList<Meat2>();
		for(int i = 0; i < nombMeat2; i++){
			PVector l = new PVector(worldW/2,worldH/2);
			//meat2.add(new Meat2(l,p));
		}
		
		//lac//
		int nomlac = 3;
		lac = new ArrayList<Lac>();
		for(int i = 0; i < nomlac; i++){
			PVector l = new PVector(p.random(worldW-1750 , worldW-250), p.random(worldH-1750 , worldH-250));
			lac.add(new Lac(p, l));
		}
		
		
		//herbivor4///
		int nombHerb4m = 10;
		herb4m = new ArrayList<Herbivor4M>();
		for(int i = 0; i < nombHerb4m; i++){
			PVector l = new PVector(p.random(worldW-1750 , worldW-250),p.random(worldH-1750 , worldH-250));
			DNA2 dna2 = new DNA2(p);
			herb4m.add(new Herbivor4M(l, dna2, p));
		}
		
		int nombHerb4f = 10;
		herb4f = new ArrayList<Herbivor4F>();
		for(int i = 0; i < nombHerb4f; i++){
			PVector l = new PVector(p.random(worldW-1750 , worldW-250),p.random(worldH-1750 , worldH-250));
			DNA2 dna2 = new DNA2(p);
			herb4f.add(new Herbivor4F(l,dna2,p));
		}

		
		//carnivor2///
		int nomCarni2m = 5;
		carn2m = new ArrayList<Carnivor2M>();
		for(int i = 0; i < nomCarni2m; i++){
			PVector l = new PVector(p.random(worldW-1750 , worldW-250),p.random(worldH-1750 , worldH-250));
			DNA3 dna3 = new DNA3(p);
			carn2m.add(new Carnivor2M(l,dna3,p));
		}
		
		int nomCarni2f = 5;
		carn2f = new ArrayList<Carnivor2F>();
		for(int i = 0; i < nomCarni2f; i++){
			PVector l = new PVector(p.random(worldW-1750 , worldW-250),p.random(worldH-1750 , worldH-250));
			DNA3 dna3 = new DNA3(p);
			carn2f.add(new Carnivor2F(l,dna3,p));
		}
		
		
	}
	
	public void run(){
		
		parent.background(0); ///fond noir = le néant
		
		////// attention il faut pop les créature sur base du monde et non de l'écran !!!! 
		////// mtn il faut mettre dans le push les objets, ou il vont suivrent la camera
		
		
		
		parent.pushMatrix();
		parent.translate(player.position.x, player.position.y);
		
				
		parent.fill(255); ///le monde en lui meme
		parent.rect(0, 0, worldW, worldH);
		
		
		/////////////////////////////////
		/////////////////////////////////		
		prairie.run(parent);
		/////////////////////////////////
		/////////////////////////////////		
		
		
		
		/////////////////////////////////
		/////////meat2///////////////////
		Iterator<Meat2> M2 = meat2.iterator();
		while(M2.hasNext()){
			Meat2 m2 = M2.next();
			m2.run(parent);
			if(m2.rotten()) M2.remove();
		}
		
		for(int a = herb4f.size()-1; a >=0; a--){
			Herbivor4F h4f = herb4f.get(a);
			if(h4f.dead()){
				int nombmeat2 = 5;
				for(int b = 0; b < nombmeat2; b++){
					//PVector l = new PVector(h4f.position.x, h4f.position.y);
					PVector l = new PVector(parent.random(h4f.position.x-10, h4f.position.x+10), parent.random(h4f.position.y-10, h4f.position.y+10));
					meat2.add(new Meat2(l,parent));
				}
				
			}
		}
		
		for(int b = herb4m.size()-1; b >=0; b--){
			Herbivor4M h4m = herb4m.get(b);
			if(h4m.dead()){
				int nombmeat2 = 5;
				for(int c = 0; c < nombmeat2; c++){
					PVector l = new PVector(parent.random(h4m.position.x-10, h4m.position.x+10), parent.random(h4m.position.y-10, h4m.position.y+10));
					meat2.add(new Meat2(l,parent));
				}
			}
		}
		/////////////////////////////////
		/////////////////////////////////
		
		
		/////////////////////////////////		
		///////tree2/////////////////////
		tree2.run(parent);
		/////////////////////////////////
		/////////////////////////////////
		
		/////////////////////////////////
		///////fruit-tree2///////////////
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
		/////////////////////////////////
		/////////////////////////////////
		
	
		
		/////////////////////////////////
		/////////Lac/////////////////////
		for(int i = lac.size()-1; i >= 0; i--){
			Lac l = lac.get(i);
			l.run(parent);
		}
		/////////////////////////////////
		/////////////////////////////////
		
		
		/////////herbivor4M START////////
		/////////////////////////////////
		
		Iterator<Herbivor4M> H4M = herb4m.iterator();
		while(H4M.hasNext()){
			Herbivor4M h4m = H4M.next();
			h4m.boundaries();
			h4m.wander(parent);
			h4m.display(parent);
			if(h4m.dead()){
				H4M.remove();
			}
			////////flee carni////////
			Iterator<Carnivor2M> C2M = carn2m.iterator();
			while(C2M.hasNext()){
				Carnivor2M c2m = C2M.next();
				PVector target = new PVector(c2m.position.x,c2m.position.y);
				if(c2m.faim() == true){
					if(h4m.isInDanger(target)){
						h4m.runaway(target);
					}
				}
			}
			Iterator<Carnivor2F> C2F = carn2f.iterator();
			while(C2F.hasNext()){
				Carnivor2F c2f = C2F.next();
				PVector target = new PVector(c2f.position.x, c2f.position.y);
				if(c2f.faim() == true){
					if(h4m.isInDanger(target)){
						h4m.runaway(target);
					}
				}
			}
			///////////////////////////
			h4m.run(parent);
		}

		/////drink water////////////////
		for(int a = herb4m.size()-1; a >= 0; a--){
			for(int b = lac.size()-1; b >= 0; b--){
				Herbivor4M h4m = herb4m.get(a);
				Lac l = lac.get(b);
				PVector lpos = l.position;
				PVector h3fpos = h4m.position;
				float dist = PVector.dist(lpos, h3fpos);
				if(h4m.getR() > dist){
					h4m.drinkWater();
				}
			}
		}
		/////////////////////////////////
		
		
		///////find water////////////////
		if(herb4m.size() > 0){
			float currentSmallest;
			float dist;
			for(int a = herb4m.size()-1; a >= 0; a--){
				Herbivor4M h4m = herb4m.get(a);
				/*
				if(h4m.soif() == false){
					h4m.wander(parent);
				}
				*/
				if(h4m.soif() == true){
					PVector target = new PVector(0,0);
					currentSmallest = (worldH * worldH) + (worldW * worldW);
					for(int b = lac.size()-1; b >= 0; b--){
						Lac l = lac.get(b);
						dist = ((h4m.position.x - l.position.x) * (h4m.position.x - l.position.x)) + ((h4m.position.y - l.position.y) * (h4m.position.y - l.position.y));
						if( dist < currentSmallest){
							currentSmallest = dist;
							target.x = l.position.x;
							target.y = l.position.y;
						}
					}
					h4m.findWater(target);
				}
			}
		}
		/////////////////////////////////		
		
		////eat food2///////////////////
		for(int b = herb4m.size()-1; b >= 0; b--){
			Herbivor4M h4m = herb4m.get(b);
			for(int i = food2.size()-1; i >= 0; i--){
				Food2 f2 = food2.get(i);
				if(h4m.faim() == true){
					h4m.eat2(f2);
				}
				
			}
		}
		////////////////////////////////
		
		//////////find food//////////////
		if(herb4m.size() > 0){
			float currentSmallest;
			float dist;
			for(int a = herb4m.size()-1; a >= 0; a--){
				Herbivor4M h4m = herb4m.get(a);
				/*
				if(h4m.faim() == false){
					h4m.wander(parent);
				}
				*/
				if(h4m.faim() == true){
					PVector target = new PVector(0,0);
					currentSmallest = (worldH * worldH) + (worldW * worldW);
					
					for(Iterator<Food2> i = food2.iterator(); i.hasNext();){
						Food2 f = i.next();
						ArrayList<PVector> foodList = f.getFood2();
							
						for(int b = 0; b < foodList.size(); b++){
							PVector food = foodList.get(b);
							dist = ((h4m.position.x - food.x) * (h4m.position.x - food.x)) + ((h4m.position.y - food.y) * (h4m.position.y - food.y));
							if(dist < currentSmallest){
								currentSmallest = dist;
								target.x = food.x;
								target.y = food.y;
							}
						}
					}
					h4m.findFood(target);
				}
			}
		}
		/////////////////////////////////
		
		/////////herbivor4M END////////
		/////////////////////////////////
		
		/////////herbivor4F START////////
		/////////////////////////////////
		
		Iterator<Herbivor4F> H4F = herb4f.iterator();
		while(H4F.hasNext()){
			Herbivor4F h4f = H4F.next();
			h4f.boundaries();
			h4f.wander(parent);
			h4f.display(parent);
			if(h4f.dead()){
				H4F.remove();
			}
			
			Iterator<Carnivor2M> C2M = carn2m.iterator();
			while(C2M.hasNext()){
				Carnivor2M c2m = C2M.next();
				PVector target = new PVector(c2m.position.x,c2m.position.y);
				if(c2m.faim() == true){
					if(h4f.isInDanger(target)){
						h4f.runaway(target);
					}
				}
			}
			Iterator<Carnivor2F> C2F = carn2f.iterator();
			while(C2F.hasNext()){
				Carnivor2F c2f = C2F.next();
				PVector target = new PVector(c2f.position.x, c2f.position.y);
				if(c2f.faim() == true){
					if(h4f.isInDanger(target)){
						h4f.runaway(target);
					}
				}
			}

			h4f.run(parent);
		}
		
		/////drink water////////////////
		for(int a = herb4f.size()-1; a >= 0; a--){
			for(int b = lac.size()-1; b >= 0; b--){
				Herbivor4F h4f = herb4f.get(a);
				Lac l = lac.get(b);
				PVector lpos = l.position;
				PVector h3fpos = h4f.position;
				float dist = PVector.dist(lpos, h3fpos);
				if(h4f.getR() > dist){
					h4f.drinkWater();
				}
			}
		}
		/////////////////////////////////
		
		///////find water////////////////
		if(herb4f.size() > 0){
			float currentSmallest;
			float dist;
			for(int a = herb4f.size()-1; a >= 0; a--){
				Herbivor4F h4f = herb4f.get(a);
				/*
				if(h4f.soif() == false){
					h4f.wander(parent);
				}
				*/
				if(h4f.soif() == true){
					PVector target = new PVector(0,0);
					currentSmallest = (worldH * worldH) + (worldW * worldW);
					for(int b = lac.size()-1; b >= 0; b--){
						Lac l = lac.get(b);
						dist = ((h4f.position.x - l.position.x) * (h4f.position.x - l.position.x)) + ((h4f.position.y - l.position.y) * (h4f.position.y - l.position.y));
						if( dist < currentSmallest){
							currentSmallest = dist;
							target.x = l.position.x;
							target.y = l.position.y;
						}
					}
					h4f.findWater(target);
				}
			}
		}
		/////////////////////////////////		
		
		////eat food2///////////////////
		for(int b = herb4f.size()-1; b >= 0; b--){
			Herbivor4F h4f = herb4f.get(b);
			for(int i = food2.size()-1; i >= 0; i--){
				Food2 f2 = food2.get(i);
				if(h4f.faim() == true){
					h4f.eat2(f2);
				}
				
			}
		}
		////////////////////////////////
		
		//////////find food//////////////
		if(herb4f.size() > 0){
			float currentSmallest;
			float dist;
			for(int a = herb4f.size()-1; a >= 0; a--){
				Herbivor4F h4f = herb4f.get(a);
				/*
				if(h4f.faim() == false){
					h4f.wander(parent);
				}
				*/
				if(h4f.faim() == true){
					PVector target = new PVector(0,0);
					currentSmallest = (worldH * worldH) + (worldW * worldW);
					
					for(Iterator<Food2> i = food2.iterator(); i.hasNext();){
						Food2 f = i.next();
						ArrayList<PVector> foodList = f.getFood2();
							
						for(int b = 0; b < foodList.size(); b++){
							PVector food = foodList.get(b);
							dist = ((h4f.position.x - food.x) * (h4f.position.x - food.x)) + ((h4f.position.y - food.y) * (h4f.position.y - food.y));
							if(dist < currentSmallest){
								currentSmallest = dist;
								target.x = food.x;
								target.y = food.y;
							}
						}
					}
					h4f.findFood(target);
				}
			}
		}
		/////////////////////////////////
		
		///////fuite test mouse//////////
		
		
		
		
		/////////////////////////////////
		
		
		/////////herbivor4F END////////
		/////////////////////////////////
		
		/////reproduction herbivor 3/////
		/////////////////////////////////		
		for(int x = herb4m.size()-1; x >= 0; x--){
			for(int y = herb4f.size()-1; y >= 0; y--){
				Herbivor4M h4m = herb4m.get(x);
				Herbivor4F h4f = herb4f.get(y);
				PVector h4mpos = h4m.position;
				PVector h4fpos = h4f.position;
				float d = PVector.dist(h4mpos, h4fpos);
				if(d < h4f.getR()){
					DNA2 momgenes = h4f.getDNA2();
					DNA2 dadgenes = h4m.getDNA2();
					DNA2 childDna2 = momgenes.crossover(dadgenes, parent);
					if(parent.random(1) < 0.05 && h4m.lastChildTime() > 10000 && h4f.lastChildTime() > 10000){
						if(parent.random(10) > 5 && herb4f.size() < 20){
							herb4f.add(new Herbivor4F(h4fpos, childDna2, parent));
							h4f.setLastChildTime(parent.millis());
						}
						if(parent.random(10) < 5 && herb4m.size() < 20){
							herb4m.add(new Herbivor4M(h4fpos, childDna2, parent));
							h4m.setLastChildTime(parent.millis());
						}
					}
				}
			}
		}
		/////////////////////////////////
		/////////////////////////////////		
		
		
		
		/////////carnivor2m START////////
		/////////////////////////////////
		
		Iterator<Carnivor2M> C2M = carn2m.iterator();
		while(C2M.hasNext()){
			Carnivor2M c2m = C2M.next();
			c2m.run(parent);
			c2m.boundaries(parent);
			c2m.wander(parent);
			c2m.display(parent);
			if(c2m.dead()){
				C2M.remove();
			}
			
		}
		
		/////drink water////////////////
		for(int a = carn2m.size()-1; a >= 0; a--){
			for(int b = lac.size()-1; b >= 0; b--){
				Carnivor2M c2m = carn2m.get(a);
				Lac l = lac.get(b);
				PVector lpos = l.position;
				PVector c2mpos = c2m.position;
				float dist = PVector.dist(lpos, c2mpos);
				if(c2m.getR() > dist){
					c2m.drinkWater();
				}
			}
		}
		/////////////////////////////////
		
		///////find water////////////////
		if(carn2m.size() > 0){
			float currentSmallest;
			float dist;
			for(int a = carn2m.size()-1; a >= 0; a--){
				Carnivor2M c2m = carn2m.get(a);
				if(c2m.soif() == true){
					PVector target = new PVector(0,0);
					currentSmallest = (worldH * worldH) + (worldW * worldW);
					for(int b = lac.size()-1; b >= 0; b--){
						Lac l = lac.get(b);
						dist = ((c2m.position.x - l.position.x) * (c2m.position.x - l.position.x)) + ((c2m.position.y - l.position.y) * (c2m.position.y - l.position.y));
						if( dist < currentSmallest){
							currentSmallest = dist;
							target.x = l.position.x;
							target.y = l.position.y;
						}
					}
					c2m.findWater(target);
				}
			}
		}
		//////////////////////////////////
		
		/////////eat meat2////////////////
		for(int a = carn2m.size()-1; a >= 0; a--){
			Carnivor2M c2m = carn2m.get(a);
			for(int b = meat2.size()-1; b >= 0; b--){
				Meat2 m2 = meat2.get(b);
				PVector cpos = c2m.position;
				PVector mpos = m2.position;
				
				float d = PVector.dist(cpos, mpos);
				if(c2m.fullbelly() == false){
					if(d < c2m.getR()){
						c2m.eat2();
						meat2.remove(b);
					}
				}
			}
		}
		//////////////////////////////////
		
		
		/////////find meat////////////////
		if(carn2m.size() > 0){
			float currentSmallest;
			float dist;
			for(int a = carn2m.size()-1; a >=0; a--){
				Carnivor2M c2m = carn2m.get(a);
				if(meat2.size() > 0 && c2m.fullbelly() == false){
					PVector target = new PVector(0,0);
					currentSmallest = (worldH * worldH) + (worldW * worldW);
					for(int b = meat2.size()-1; b >=0; b--){
						Meat2 m = meat2.get(b);
						dist = ((c2m.position.x - m.position.x) * (c2m.position.x - m.position.x) + (c2m.position.y - m.position.y) * (c2m.position.y - m.position.y));
						if(dist < currentSmallest){
							currentSmallest = dist;
							target.x = m.position.x;
							target.y = m.position.y;
						}
					}
				c2m.findMeat(target);
				}
			}
		}
		//////////////////////////////////
		
		/////////kill prey////////////////
		for(int a = carn2m.size()-1; a >=0; a--){
			Carnivor2M c2m = carn2m.get(a);
			for(int b = herb4f.size()-1; b >=0; b--){
				Herbivor4F h4f = herb4f.get(b);
				PVector c2mpos = c2m.position;
				PVector h4fpos = h4f.position;
				float d = PVector.dist(c2mpos, h4fpos);
				if(c2m.faim() == true){
					if(d < c2m.getR()){
						h4f.health -= 100;
					}
				}
			}
			for(int c = herb4m.size()-1; c >=0; c--){
				Herbivor4M h4m = herb4m.get(c);
				PVector c2mpos = c2m.position;
				PVector h4mpos = h4m.position;
				float d = PVector.dist(c2mpos, h4mpos);
				if(c2m.faim()==true){
					if(d < c2m.getR()){
						h4m.health -= 100;
					}
				}
			}
		}
		//////////////////////////////////

		
		/////////find prey////////////////
		if(carn2m.size() > 0){
			float currentSmallest;
			float dist;
			float dist2;
			for(int a = carn2m.size()-1; a >=0; a--){
				Carnivor2M c2m = carn2m.get(a);
				if(c2m.faim() == true){
					PVector target = new PVector(0,0);
					currentSmallest = (worldH * worldH) + (worldW * worldW);
					for(int b = herb4f.size()-1; b >=0; b--){
						Herbivor4F h4f = herb4f.get(b);
						dist = ((c2m.position.x - h4f.position.x) * (c2m.position.x - h4f.position.x) + (c2m.position.y - h4f.position.y) * (c2m.position.y - h4f.position.y));
						if(dist < currentSmallest){
							currentSmallest = dist;
							target.x = h4f.position.x;
							target.y = h4f.position.y;
						}
					}
					for(int c = herb4m.size()-1; c >=0; c--){
						Herbivor4M h4m = herb4m.get(c);
						dist2 = ((c2m.position.x - h4m.position.x) * (c2m.position.x - h4m.position.x) + (c2m.position.y - h4m.position.y) * (c2m.position.y - h4m.position.y));
						if(dist2 < currentSmallest){
							currentSmallest = dist2;
							target.x = h4m.position.x;
							target.y = h4m.position.y;
						}
					}
					c2m.findPrey(target);
				}
			}
		}
		//////////////////////////////////
		
		/////////carnivor2m END//////////
		/////////////////////////////////
		
		/////////////////////////////////
		/////////////////////////////////
		parent.popMatrix();
		
		player.update(parent);
		player.PlayerMvt(parent);
		
		//////je pense que l'UI sera mis ici
		parent.fill(0);
		parent.text("herbivor male " + herb4m.size(),10,20);
		parent.text("herbivor female " + herb4f.size(),10,30);
		parent.text("carnivor male " + carn2m.size(), 10, 40);
		//parent.text("mouse x" + parent.mouseX, 10, 40);
		parent.text("Meat " + meat2.size(), 10, 50);
		
		
		
		
		player.run(parent);
		
		
		
		
	}
}
