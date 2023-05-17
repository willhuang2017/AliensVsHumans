package aliensVsHumans;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class AlienTest {
	
	public Alien alien;
	public Entity attacker;

	@Before
	public void beforeTest() {
		alien = new Alien("UFO",100,50,Alien.Type.FLYING);
		attacker = new Entity("Villain", 90, 10);
	}
	
	//Test Getters And Setters
	@Test
	public void testGetters() {
		assertThat(alien.getName(),equalTo("UFO"));
		assertThat(alien.getHP(),equalTo(100));
		assertThat(alien.getATK(),equalTo(50));
		assertThat(alien.getType(),equalTo(Alien.Type.FLYING));
	}

	@Test
	public void testSetters() {
		alien.setHP(632);
		alien.setATK(32);
		assertThat(alien.getHP(),equalTo(632));
		assertThat(alien.getATK(),equalTo(32));
	}
	
	//Test Damage Methods
	@Test
	public void testTakeDamageInteger() {
		alien.takeDamage(32);
		assertThat(alien.getHP(),equalTo(68));
	}
	
	@Test
	public void testTakeDamageNormal() {
		alien.mutate(Alien.Type.NORMAL);
		alien.takeDamage(attacker);
		assertThat(alien.getHP(),equalTo(90));
	}
	
	@Test
	public void testTakeDamageFlying() {
		alien.mutate(Alien.Type.FLYING);
		alien.takeDamage(attacker);
		assertThat(alien.getHP(),equalTo(99));
	}
	
	@Test
	public void testTakeDamageTransparent() {
		alien.mutate(Alien.Type.TRANSPARENT);
		alien.takeDamage(attacker);
		assertThat(alien.getHP(),equalTo(95));
	}
	
	@Test
	public void testDealDamage() {
		alien.dealDamage(attacker);
		assertThat(attacker.getHP(),equalTo(40));
		attacker.dealDamage(alien);
		assertThat(alien.getHP(),equalTo(99));
	}
	
	//Test Superclass Methods
	public void testDrinkWater() {
		alien.drinkWater();
		assertThat(alien.getHP(),equalTo(101));
	}
	
	@Test
	public void testSplashPotion() {
		alien.useSplash(attacker);
		assertThat(attacker.getHP(),equalTo(0));
		
		attacker.useSplash(alien);
		assertThat(alien.getHP(),equalTo(0));
	}
	
	//Test Special Abilities
	@Test
	public void testMutate() {
		alien.mutate(Alien.Type.TRANSPARENT);
		assertThat(alien.getType(),equalTo(Alien.Type.TRANSPARENT));
		alien.mutate(Alien.Type.NORMAL);
		assertThat(alien.getType(),equalTo(Alien.Type.NORMAL));
	}
	
	@Test
	public void testHeal() {
		alien.heal();
		assertThat(alien.getHP(),equalTo(110));
	}
	
	@Test
	public void testLastStand() {
		alien.lastStand();
		assertThat(alien.getHP(),equalTo(2));
		assertThat(alien.getATK(),equalTo(5000));
	}
	
	//To String Test
	@Test
	public void testToString() {
		assertThat(alien.toString(),equalTo("Alien: UFO|"
				+ "HP: 100|"
				+ "ATK: 50|"
				+ "Type: FLYING"));
	}
}
