package aliensVsHumans;

public class Entity {
	protected int atk;
	protected int hp;
	protected String name;
	
	Entity(String name, int hp, int atk) {
		this.name = name;
		this.hp = hp;
		this.atk = atk;	
	}
	
	//Special Abilities
	public void useSplash(Entity opponent) {
		opponent.takeDamage(opponent.getHP());
	}
	
	public void drinkWater() {
		this.setHP(this.hp + 1);
	}
	
	//Damage Methods
	public void takeDamage(Entity attacker) {
		this.setHP(this.hp - attacker.getATK());
	}
	public void takeDamage(int dmg) {
		this.setHP(this.hp - dmg);
	}
	
	
	public void dealDamage(Entity opponent) {
		opponent.takeDamage(this);
	}
	
	//Getters And Setters
	public void setHP(int hp) {
		this.hp = hp;
	}
	
	public void setATK(int atk) {
		this.atk = atk;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getHP() {
		return this.hp;
	}
	
	public int getATK() {
		return this.atk;
	}
}
