package aliensVsHumans;

public class Human extends Entity {
	private int armor;
	
	Human(String name, int hp, int atk, int armor){
		super(name,hp,atk);
		this.armor = armor;
	}
	
	//Special Abilities
	public void eatBurger() {
		this.hp = this.hp - 10;
	}
	
	public void wearMoreArmor() {
		this.armor += 5;
		this.atk -= 10;
	}
	
	public void moralSupport() {
		this.atk = this.atk + 10;
	}
	
	//Overridden Methods
	public void takeDamage(Entity attacker) {
		int dmg = attacker.getATK() - this.armor;
		if(dmg < 0) {dmg = 0;}
		super.takeDamage(dmg);
	}
	
	//Getters And Setters
	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	public int getArmor() {
		return this.armor;
	}
	
	public String toString() {
		return "Human: " + super.getName() + 
				"|HP: " + super.getHP() + 
				"|ATK: " + super.getATK() + 
				"|Armor: " + this.getArmor();
	}
	
}
