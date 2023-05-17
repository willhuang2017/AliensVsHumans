package aliensVsHumans;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class EntityTest {
	
	public Entity entity;
	public Entity attacker;

	@Before
	public void beforeTest() {
		entity = new Entity("Bob",100,50);
		attacker = new Entity("Villain", 90, 8);
	}
	
	//Test Getters And Setters
	@Test
	public void testGetters() {
		assertThat(entity.getName(),equalTo("Bob"));
		assertThat(entity.getHP(),equalTo(100));
		assertThat(entity.getATK(),equalTo(50));
	}

	@Test
	public void testSetters() {
		entity.setHP(62);
		entity.setATK(32);
		assertThat(entity.getHP(),equalTo(62));
		assertThat(entity.getATK(),equalTo(32));
	}
	
	//Test Damage Methods
	@Test
	public void testTakeDamageInteger() {
		entity.takeDamage(23);
		assertThat(entity.getHP(),equalTo(77));
	}
	
	@Test
	public void testTakeDamageEntity() {
		entity.takeDamage(attacker);
		assertThat(entity.getHP(),equalTo(92));
		attacker.takeDamage(entity);
		assertThat(attacker.getHP(),equalTo(40));
	}
	
	@Test
	public void testDealDamage() {
		entity.dealDamage(attacker);
		assertThat(attacker.getHP(),equalTo(40));
		attacker.dealDamage(entity);
		assertThat(entity.getHP(),equalTo(92));
	}

	//Test Abilities
	@Test
	public void testDrinkWater() {
		entity.drinkWater();
		assertThat(entity.getHP(),equalTo(101));
	}
	
	@Test
	public void testSplashPotion() {
		entity.useSplash(attacker);
		assertThat(attacker.getHP(),equalTo(0));
		
		attacker.useSplash(entity);
		assertThat(entity.getHP(),equalTo(0));
	}
}
