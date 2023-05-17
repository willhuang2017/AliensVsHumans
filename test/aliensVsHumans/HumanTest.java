package aliensVsHumans;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class HumanTest {
	
	public Entity attacker;
	public Human human;
	
	@Before
	public void beforeTest() {
		human = new Human("Hugh Jackman",100,50,25);
		attacker = new Entity("Villain", 90, 8);
	}
	
	//Test Getters And Setters
	@Test
	public void testGetters() {
		assertThat(human.getName(),equalTo("Hugh Jackman"));
		assertThat(human.getHP(),equalTo(100));
		assertThat(human.getATK(),equalTo(50));
		assertThat(human.getArmor(),equalTo(25));
	}

	@Test
	public void testSetters() {
		System.out.println(human);
		human.setHP(62);
		human.setATK(32);
		human.setArmor(327);
		assertThat(human.getHP(),equalTo(62));
		assertThat(human.getATK(),equalTo(32));
		assertThat(human.getArmor(),equalTo(327));
	}	

	//Test Damage Methods
	@Test
	public void testTakeDamageInteger() {
		human.takeDamage(20);
		assertThat(human.getHP(),equalTo(80));
	}
	
	@Test
	public void testTakeDamageEntity() {
		human.takeDamage(attacker);
		assertThat(human.getHP(),equalTo(100));
		
		attacker.setATK(40);
		human.takeDamage(attacker);
		assertThat(human.getHP(),equalTo(85));
	}
	
	@Test
	public void testDealDamageEntity() {
		human.dealDamage(attacker);
		assertThat(attacker.getHP(),equalTo(40));
		attacker.dealDamage(human);
		assertThat(human.getHP(),equalTo(100));
		
		attacker.setATK(45);
		attacker.dealDamage(human);
		assertThat(human.getHP(),equalTo(80));
	}
	
	//Test Superclass Methods
	@Test
	public void testSplash() {
		attacker.useSplash(human);
		assertThat(human.getHP(),equalTo(0));
		
		human.useSplash(attacker);
		assertThat(attacker.getHP(),equalTo(0));
	}
	
	@Test
	public void testDrinkWater() {
		human.drinkWater();
		assertThat(human.getHP(),equalTo(101));
	}
	
	//Test Special Abilities
	@Test
	public void testBurger() {
		human.eatBurger();
		assertThat(human.getHP(),equalTo(90));
	}
	
	@Test
	public void testMoral() {
		human.moralSupport();
		assertThat(human.getATK(),equalTo(60));
	}
	
	@Test
	public void testArmor() {
		human.wearMoreArmor();
		assertThat(human.getArmor(),equalTo(30));
		assertThat(human.getATK(),equalTo(40));
	}
	
	@Test
	public void testToString() {
		assertThat(human.toString(),equalTo("Human: Hugh Jackman|"
				+ "HP: 100|"
				+ "ATK: 50|"
				+ "Armor: 25"));
	}
}
