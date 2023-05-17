package aliensVsHumans;

import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class EnvironmentTest {
	Environment env1;
	Environment env2;
	Alien alien;
	Human human;
	
	@Before
	public void beforeTest() {
		env1 = new Environment("Desert");
		env2 = new Environment("Ocean");
		alien = new Alien("UFO",100,50,Alien.Type.FLYING);
		human = new Human("Hugh Jackman",100,50,25);	
	}
	
	//Setters And Getters
	@Test
	public void testGetters() {
		assertThat(env1.getName(),equalTo("Desert"));
		assertThat(env1.getAliens(),equalTo(0));
		assertThat(env1.getHumans(),equalTo(0));
		assertThat(env1.getEntities(),equalTo(new ArrayList<>()));
	}
	
	@Test
	public void testAddEntity() {
		env1.addEntity(alien);
		env2.addEntity(human);
		
		assertThat(env1.getAliens(),equalTo(1));
		assertThat(env1.getHumans(),equalTo(0));
		assertThat(env2.getAliens(),equalTo(0));
		assertThat(env2.getHumans(),equalTo(1));
	}
	
	@Test
	public void testRemoveEntity() {
		env1.addEntity(alien);
		env2.addEntity(human);	
		env1.removeEntity(alien);
		env2.removeEntity(human);
		
		assertThat(env1.getAliens(),equalTo(0));
		assertThat(env1.getHumans(),equalTo(0));
		assertThat(env2.getAliens(),equalTo(0));
		assertThat(env2.getHumans(),equalTo(0));
	}
	
	@Test
	public void testSwapEntity() {
		env1.addEntity(alien);
		env2.addEntity(human);
		env1.swapEnv(env2, alien);
		
		assertThat(env1.getAliens(),equalTo(0));
		assertThat(env1.getHumans(),equalTo(0));
		assertThat(env2.getAliens(),equalTo(1));
		assertThat(env2.getHumans(),equalTo(1));
		
	}
	
	@Test
	public void testRally() {
		env1.addEntity(alien);
		env1.addEntity(human);
		
		env1.rally();
		assertThat(alien.getATK(),equalTo(45));
		assertThat(human.getATK(),equalTo(60));
	}
}
