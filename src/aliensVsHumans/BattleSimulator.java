package aliensVsHumans;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class BattleSimulator {
	
	private Scanner input;
	private ArrayList<Environment> arenas;
	private int humanPotions;
	private int alienPotions;
	
	BattleSimulator(Scanner input, ArrayList<Environment> arenas){
		this.input = input;
		this.arenas = arenas;
		this.humanPotions = 1;
		this.alienPotions = 1;
	}
	
	private <T> T getOutputOnInput(String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever){
		System.out.println(userInputPrompt);
		while(true){
			if(this.input.hasNextInt()){
				int order = this.input.nextInt();
				try{
					return intUserInputRetriever.produceOutputOnIntUserInput(order);
				} catch(IllegalArgumentException e) {
					System.out.println(order + " is not a valid input. Try Again!");
				}
			} else {
				System.out.println("Input needs to be an 'int' type.");
				this.input.next();
			}
		}
	}
	
	//Picks Arena To Start Turn
	public Environment pickArena(int turn){
		int arenaID = 1;
		for(Environment env : arenas) {
			System.out.println(arenaID+" "+env.getName());
			arenaID++;
		}
		String userPrompt = "Choose An Arena ";
		IntUserInputRetriever<Environment> intUserInputRetriever = selection -> {
			if(selection > 0 && selection < arenas.size() + 1){
				if(turn == 0 && arenas.get(selection -1).getHumans() == 0) {
					System.out.println("There are no humans here");
					throw new IllegalArgumentException();
				} else if (turn == 1 && arenas.get(selection - 1).getAliens() == 0) {
					System.out.println("There are no aliens here");
					throw new IllegalArgumentException();
				}
		        return arenas.get(selection - 1);
			} else {
				throw new IllegalArgumentException();
			}
		};
		return (Environment) getOutputOnInput(userPrompt, intUserInputRetriever);
	}
	
	//Picks Arena To Swap To
	public Environment pickArenaSwap(){
		int arenaID = 1;
		for(Environment env : arenas) {
			System.out.println(arenaID+" "+env.getName());
			arenaID++;
		}
		String userPrompt = "Choose An Arena To Swap To ";
		IntUserInputRetriever<Environment> intUserInputRetriever = selection -> {
			if(selection > 0 && selection < arenas.size() + 1){
		        return arenas.get(selection - 1);
			} else {
				throw new IllegalArgumentException();
			}
		};
		return (Environment) getOutputOnInput(userPrompt, intUserInputRetriever);
	}
	
	//Picks Human
	public Human pickHuman(Environment env) {
		env.printEntities();
		String userPrompt = "Choose A Human ";
		IntUserInputRetriever<Human> intUserInputRetriever = selection -> {
			if(selection > 0 && selection < env.getEntities().size() + 1){
				Entity entity = env.getEntities().get(selection - 1);
				if(entity instanceof Alien) {
					throw new IllegalArgumentException();
				}
				return (Human) env.getEntities().get(selection - 1);
			} else {
				throw new IllegalArgumentException();
			}
		};
		return (Human) getOutputOnInput(userPrompt, intUserInputRetriever);
	}
	
	//Picks Alien
	public Alien pickAlien(Environment env) {
		env.printEntities();
		String userPrompt = "Choose An Alien ";
		IntUserInputRetriever<Alien> intUserInputRetriever = selection -> {
			if(selection > 0 && selection < env.getEntities().size() + 1){
				Entity entity = env.getEntities().get(selection - 1);
				if(entity instanceof Human) {
					throw new IllegalArgumentException();
				}
				return (Alien) env.getEntities().get(selection - 1);
			} else {
				throw new IllegalArgumentException();
			}
		};
		return (Alien) getOutputOnInput(userPrompt, intUserInputRetriever);
	}
	
	//Creates Action Based On Whether You Are An Alien Or Human
	public Integer takeAction(Entity entity, Environment env) {
		if(entity instanceof Human) {
			System.out.println("1. Fight Opponent"
				+ "\n2. Use Potion"
				+ "\n3. Swap Arena"
				+ "\n4. Drink Water"
				+ "\n5. Eat Burger"
				+ "\n6. Wear More Armor"
				+ "\n7. Boost Morale");
			String userPrompt = "Pick An Action";
			IntUserInputRetriever<Integer> intUserInputRetriever = selection -> {
				if(selection > 0 && selection < 8){
					
					if((selection == 1 || selection == 2) && env.getAliens() == 0) {
						System.out.println("There are no aliens here to fight");
						throw new IllegalArgumentException();
					}
					if(selection == 2 && humanPotions == 0) {
						System.out.println("There are no potions left");
						throw new IllegalArgumentException();
					}
				
					return selection;
				} else {
					throw new IllegalArgumentException();
				}
			};
			return (Integer) getOutputOnInput(userPrompt, intUserInputRetriever);
		} else  {
			System.out.println("1. Fight Opponent"
				+ "\n2. Use Potion"
				+ "\n3. Swap Arena"
				+ "\n4. Drink Water"
				+ "\n5. Heal"
				+ "\n6. Mutate"
				+ "\n7. Last Stand");
			String userPrompt = "Pick An Action";
			IntUserInputRetriever<Integer> intUserInputRetriever = selection -> {
				if(selection > 0 && selection < 8){			
					if((selection == 1 || selection == 2) && env.getHumans() == 0) {
						System.out.println("There are no humans here to fight");
						throw new IllegalArgumentException();
					}
					if(selection == 2 && alienPotions == 0) {
						System.out.println("There are no potions left");
						throw new IllegalArgumentException();
					}
				
					return selection;
				} else {
					throw new IllegalArgumentException();
				}
			};
			return (Integer) getOutputOnInput(userPrompt, intUserInputRetriever);
		}
	}
	
	public void startGame() {
		int turn = 1;
		while(true) {
			int totalHumans = 0;
			int totalAliens = 0;
			
			if(turn == 0) {turn = 1;}
			else if(turn == 1) {turn = 0;}
			for(Environment env : arenas) {
				env.printEntities();
				totalHumans += env.getHumans();
				totalAliens += env.getAliens();
			}
			
			if(totalHumans == 0) {
				System.out.println("Aliens WON!!!");
				break;
			}
			if(totalAliens == 0) {
				System.out.println("Humnans WON!!!");
				break;
			}
			
			if(turn == 0) {System.out.println("It is the humans turn. You have "+humanPotions+" potion(s)");}
			if(turn == 1) {System.out.println("It is the aliens turn. You have "+alienPotions+" potion(s)");}
			
			Environment currEnv = this.pickArena(turn);
			
			if(turn == 0) {
				Human currHuman = this.pickHuman(currEnv);
				Integer currAction = this.takeAction(currHuman, currEnv);
				if(currAction == 1) {
					Alien opponent = this.pickAlien(currEnv);
					currHuman.dealDamage(opponent);
					if(opponent.getHP() <= 0) {
						currEnv.removeEntity(opponent);
					}
				} else if(currAction == 2) {
					Alien opponent = this.pickAlien(currEnv);
					currHuman.useSplash(opponent);
					if(opponent.getHP() <= 0) {
						currEnv.removeEntity(opponent);
					}
					humanPotions--;
				} else if(currAction == 3) {
					Environment newEnv = this.pickArenaSwap();
					currEnv.swapEnv(newEnv, currHuman);
					
				} else if(currAction == 4) {
					System.out.println("You feel refreshed and gained 1 HP");
					currHuman.drinkWater();
				} else if(currAction == 5) {
					System.out.println("Burgers are unhealthy, you've lost 10 HP");
					currHuman.eatBurger();
					if(currHuman.getHP() <= 0) {
						System.out.println(currHuman.getName() +" has died");
						currEnv.removeEntity(currHuman);
					}
				} else if(currAction == 6) {
					System.out.println("You wear more armor at the cost of defense");
					currHuman.wearMoreArmor();
				} else if(currAction == 7) {
					System.out.println("You boosted the morale of all humans and the made all the aliens sad. MEAN :(");
					currEnv.rally();
				}
			}
				
			if(turn == 1) {
				Alien currAlien = this.pickAlien(currEnv);
				Integer currAction = this.takeAction(currAlien, currEnv);
				if(currAction == 1) {
					Human opponent = this.pickHuman(currEnv);
					currAlien.dealDamage(opponent);
					if(opponent.getHP() <= 0) {
						currEnv.removeEntity(opponent);
					}
				} else if(currAction == 2) {
					Human opponent = this.pickHuman(currEnv);
					currAlien.useSplash(opponent);
					if(opponent.getHP() <= 0) {
						currEnv.removeEntity(opponent);
					}
					alienPotions--;
				} else if(currAction == 3) {
					Environment newEnv = this.pickArenaSwap();
					currEnv.swapEnv(newEnv, currAlien);
				} else if(currAction == 4) {
					System.out.println("You feel refreshed and gained 1 HP");
					currAlien.drinkWater();
				} else if(currAction == 5) {
					System.out.println("You channel the power of the illumnati to gain 10 HP");
					currAlien.heal();
				} else if(currAction == 6) {
					Random rand = new Random();
					Integer type = rand.nextInt(3) + 1;
					if(type == 1) {
						currAlien.mutate(Alien.Type.NORMAL);
					} else if (type == 2) {
						currAlien.mutate(Alien.Type.FLYING);
					} else if (type == 3) {
						currAlien.mutate(Alien.Type.TRANSPARENT);
					}
					System.out.println("You randomly mutated into a "+currAlien.getType()+" alien");
				} else if(currAction == 7) {
					System.out.println("Your stats went CRAZYYY");
					currAlien.lastStand();
				}
			}
		}
	}
}
