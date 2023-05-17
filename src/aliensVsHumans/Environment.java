package aliensVsHumans;

import java.util.ArrayList;

public class Environment {

	public ArrayList<Entity> entities;
	public String name;
	public int numAliens;
	public int numHumans;
	
	public Environment(String name) {
		this.name = name;
		this.numAliens = 0;
		this.numHumans = 0;
		entities = new ArrayList<>();
	}
	
	public void rally() {
		for(Entity e : entities) {
			if(e instanceof Alien) {
				e.setATK(e.getATK() - 5);
			}
			if(e instanceof Human) {
				((Human) e).moralSupport();
			}
		}
	}
	
	public void removeEntity(Entity entity) {
		if(entity instanceof Alien) {
			this.numAliens--;
		} else if(entity instanceof Human) {
			this.numHumans--;
		}
		this.entities.remove(entity);
	}
	
	
	public void addEntity(Entity entity) {
		if(entity instanceof Alien) {
			this.numAliens++;
		} else if(entity instanceof Human) {
			this.numHumans++;
		}
		this.entities.add(entity);
	}
	
	public void swapEnv(Environment env, Entity entity) {
		if(entity instanceof Alien) {
			this.numAliens--;
		} else if(entity instanceof Human) {
			this.numHumans--;
		}
		entities.remove(entity);
		env.addEntity(entity);
	}
	
	public void printEntities() {
		System.out.println("|||Arena: "+this.getName()+"" +"|Humans: "+this.getHumans() + "|Aliens: "+ this.getAliens()+"||");
		if(this.getAliens() > 0 || this.getHumans() > 0)
			System.out.println("-----");
		int index = 1;
		for(Entity e: entities) {
			System.out.print("Spot: " + index+"|");
			System.out.println(e);
			System.out.println("-----");
			index++;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAliens() {
		return this.numAliens;
	}
	
	public int getHumans() {
		return this.numHumans;
	}
	
	public ArrayList<Entity> getEntities() {
		return this.entities;
	}
}
