package aliensVsHumans;

public class Alien extends Entity {
	enum Type {
		FLYING,
		TRANSPARENT,
		NORMAL
	}
	Type type;

	Alien(String name, int hp, int atk, Type type) {
		super(name,hp,atk);
		this.type = type;	
	}
	
	//Special Abilities
	public void lastStand() {
		this.setHP(2);
		this.setATK(5000);
	}
	
	public void heal() {
		this.setHP(this.hp + 10);
	}
	
	//Overridden Methods
	public void takeDamage(Entity attacker) {
		if(this.type == Type.NORMAL) {
			super.takeDamage(attacker);
		} else if (this.type == Type.FLYING) {
			super.takeDamage(1);
		} else if (this.type == Type.TRANSPARENT) {
			super.takeDamage(attacker.getATK()/2);
		}
	}
	
	//Getters And Setters
	public void mutate(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public String toString() {
		return "Alien: " + super.getName() + 
				"|HP: " + super.getHP() + 
				"|ATK: " + super.getATK() + 
				"|Type: " + this.getType();
	}
}
