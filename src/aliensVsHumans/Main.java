package aliensVsHumans;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	//Generates Humans In The Game
	public static int generateHumanEnv(Environment env) {
		Random rand = new Random();
		int randHumans = rand.nextInt(1) + 1;
		System.out.println("You have "+randHumans+ " human(s) in the "+env.getName());
		for(int i = 0; i < randHumans; i++) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Name The Human");
			String name = scanner.next();
			int randHP = rand.nextInt(100) + 1;
			int randATK = rand.nextInt(50) + 1;
			int randArmor = rand.nextInt(25) + 1;
			env.addEntity(new Human(name,randHP,randATK,randArmor));
		}
		return randHumans;
	}
	
	//Generates Aliens In The Game
	public static int generateAlienEnv(Environment env) {
		Random rand = new Random();
		int randAliens = rand.nextInt(1) + 1;
		
		System.out.println("You have "+randAliens+ " alien(s) in the "+env.getName());
		for(int i = 0; i < randAliens; i++) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Name The Alien");
			String name = scanner.next();
			int randHP = rand.nextInt(100) + 1;
			int randATK = rand.nextInt(50) + 1;
			int randType = rand.nextInt(3) + 1;
			if(randType == 1) {
				env.addEntity(new Alien(name,randHP,randATK,Alien.Type.NORMAL));
			} else if (randType == 2) {
				env.addEntity(new Alien(name,randHP,randATK,Alien.Type.FLYING));
			} else if (randType == 3) {
				env.addEntity(new Alien(name,randHP,randATK,Alien.Type.TRANSPARENT));
			}
		}
		return randAliens;
	}
	  
	//Main Method
	public static void main(String[] args) {
		//Set Up Environments 
		Environment desert = new Environment("Desert");
		Environment plains = new Environment("Plains");
		Environment ocean = new Environment("Ocean");
		generateHumanEnv(desert);
		generateHumanEnv(plains);
		generateHumanEnv(ocean);
		generateAlienEnv(desert);
		generateAlienEnv(plains);
		generateAlienEnv(ocean);
		
		//Create Battle Arena
		Scanner scanner = new Scanner(System.in);
		ArrayList<Environment> arenas = new ArrayList<>();
		arenas.add(desert);
		arenas.add(plains);
		arenas.add(ocean);
		
		//Start Game
		BattleSimulator sim = new BattleSimulator(scanner,arenas);
		sim.startGame();
	}
}
