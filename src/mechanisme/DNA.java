package mechanisme;

import processing.core.PApplet;

public class DNA {
	PApplet parent;
	public float[] genes;
	
	public DNA(PApplet p){
		parent = p;
		
		genes = new float[1];
		for(int i = 0; i < genes.length; i++){
			genes[i] = p.random(0,1);
		}
	}
	
	DNA(float[] newgenes){
		genes = newgenes;
	}
	
	public DNA copy(){
		float[] newgenes = new float[genes.length];
		
		for(int i = 0; i < newgenes.length; i++){
			newgenes[i] = genes[i];
		}
		return new DNA(newgenes);
	}
	
	public void mutate(float m, PApplet p){
		for(int i = 0; i < genes.length; i++){
			if (p.random(1) < m){
				genes[i] = p.random(0,1);
			}
		}
	}
	
}
