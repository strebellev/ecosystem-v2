package mechanisme;

import processing.core.PApplet;

public class DNA3 {
	PApplet parent;
	public float[] genes;
	int diffgenes = 2;
	DNA3 dna3;
	
	public DNA3(PApplet p){
		parent = p;
		genes = new float[diffgenes];
		for(int i = 0; i < genes.length; i++){
			genes[i] = p.random(0, 1);
		}
	}
	
	DNA3(float[] newgenes){
		genes = newgenes;
	}
	
	public DNA3 copy(){
		float[] newgenes = new float[genes.length];
		for(int i =0; i < newgenes.length; i++){
			newgenes[i] = genes[i];
		}
		return new DNA3(newgenes);
	}
	
	DNA3 crossover(DNA3 partner, PApplet p){
		float[] child = new float[genes.length];
		//float crossover =  (parent.random(genes.length));
		int crossover = (int) p.random(5);
		for(int i = 0; i < genes.length; i++){
			if(i > crossover){
				child[i] = genes[i];
			}
			else{
				child[i] = partner.genes[i];
			}
		}
		DNA3 newgenes = new DNA3(child);
		return newgenes;
	}
	
	public DNA3 getDNA3(){
		return dna3;
	}
	
	public void mutate(float m){
		for(int i = 0; i < genes.length; i++){
			if(parent.random(1) < m){
				genes[i] = parent.random(0, 1);
			}
		}
	}



}
