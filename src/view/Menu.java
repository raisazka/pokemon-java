package view;

import java.util.List;
import java.util.Scanner;

import helper.Helper;
import model.*;

public class Menu {
	
    private Scanner sc = new Scanner(System.in);
    public User user;
    private int choose;
    private static List<Pokemon> pokemons = Pokemon.generatePokemons();
    private Pokemon myPokemon;
    private Pokemon randPokemon;
	int chooseSkill;
	
	public Menu() {
		menuInit();
		mainMenu();
	}
	
	private void menuInit() {
		String name;
		String gender;
		int age;
		
		do {
			System.out.print("Input Name: ");
			name = sc.nextLine();
		} while (name.length() < 5);
		
		do {
			System.out.print("Input Gender: ");
			gender = sc.nextLine();
		} while(!(gender.equalsIgnoreCase("pria") || gender.equalsIgnoreCase("wanita")));
		
		do {
			System.out.print("Input Age: ");
			age = sc.nextInt();
			sc.nextLine();
		} while (age < 13);
		user = new User(name, gender, age);
		System.out.println();
		System.out.println();
	}
	
	
	void mainMenu() {
		do {
			System.out.println("1. Profile");
			System.out.println("2. Battle");
			System.out.println("3. Exit");
			System.out.print("Choose Menu: ");
			choose =  sc.nextInt();
			sc.nextLine();
			menuBridge();
		} while(choose != 3);
	}
	
	void menuBridge() {
		switch(choose) {
		case 1:
			profileMenu();
			break;
		case 2:
			choosePokeMenu();
			break;
		default:
			break;
		}
	}
	
	void profileMenu() {
		System.out.println();
		System.out.println();
		System.out.println("Name: " + user.getName());
		System.out.println("Gender: " + user.getGender());
		System.out.println("Age: " + user.getAge());
		System.out.println();
		System.out.println();
	}
	
	void choosePokeMenu() {
		int choosePoke;
		System.out.println();
		System.out.println();
		System.out.println("==================");
		System.out.println("Choose Your Pokemon!");
		System.out.println("==================");
		for(int i = 0; i < pokemons.size(); i++) {
			System.out.println(i + 1 + ". " + pokemons.get(i).getName());
		}
		do {
			System.out.print("Choose Pokemon: ");
			choosePoke = sc.nextInt();
			sc.nextLine();
		} while(choosePoke < 1 || choosePoke > pokemons.size());
		
		myPokemon = pokemons.get(choosePoke - 1);
		randPokemon = pokemons.get(Helper.getRandomNumber(1, pokemons.size() - 1));
		pokemonsMenu();
	}
	
	void pokemonsMenu() {
		System.out.println();
		System.out.println();
		System.out.println("==================");
		System.out.println("This is Your Pokemon!");
		System.out.println("==================");
		System.out.println("Poke Name: " + myPokemon.getName());
		System.out.println("Poke Skill: ");
		for (int i = 0; i < myPokemon.getSkills().size(); i++) {
			System.out.println("\t Skill" + (i + 1) + ": " + myPokemon.getSkills().get(i).getName() +
					", SP: " + myPokemon.getSkills().get(i).getSp());
		}
		System.out.println("Poke Health: " + myPokemon.getHealth());
		System.out.println();
		System.out.println();
		System.out.println("==================");
		System.out.println("This is Enemy Pokemon!");
		System.out.println("==================");
		System.out.println("Poke Name: " + randPokemon.getName());
		System.out.println("Poke Skill: ");
		for (int i = 0; i < myPokemon.getSkills().size(); i++) {
			System.out.println("\t Skill " + (i + 1) + ": " + randPokemon.getSkills().get(i).getName() +
					", SP: " + randPokemon.getSkills().get(i).getSp());
		}
		System.out.println("Poke Health: " + randPokemon.getHealth());
		System.out.println();
		System.out.println();
		battleMenu();
	}
	
	void battleMenu() {
		int chooseBattMenu;
		do {
			System.out.println("1. Battle!");
			System.out.println("2. Cancel Battle");
			System.out.print("Choose: ");
			chooseBattMenu = sc.nextInt();
			sc.nextLine();
		} while(chooseBattMenu < 1 || chooseBattMenu > 2);
		
		switch (chooseBattMenu) {
		case 1: {
			battleProcess();
			break;
		}
		default: break;
		}
	}
	
	void battleProcess() {
		System.out.println();
		System.out.println();
		System.out.println("==================");
		System.out.println("BATTLE START!");
		System.out.println("==================");
		while(myPokemon.getHealth() > 0 && randPokemon.getHealth() > 0) {
			chooseSkillSet();
		}
	}
	
	void chooseSkillSet() {
		System.out.println();
		System.out.println("==================");
		System.out.println("My Poke Health: " + myPokemon.getHealth());
		System.out.println("==================");
		System.out.println();
		System.out.println("==================");
		System.out.println("Enemy Poke Health: " + randPokemon.getHealth());
		System.out.println("==================");
		System.out.println();
		
		do {
			for (int i = 0; i < myPokemon.getSkills().size(); i++) {
				System.out.println((i + 1) + ": " + myPokemon.getSkills().get(i).getName() +
						", SP: " + randPokemon.getSkills().get(i).getSp());
			}
			System.out.print("Choose Skill: ");
			chooseSkill = sc.nextInt();
			sc.nextLine();
		} while(chooseSkill < 1 || chooseSkill > myPokemon.getSkills().size());
		
		if(myPokemon.getHealth() > 0) {
			attackSequence();
		} 
		
		if (randPokemon.getHealth() > 0) {
			computerAttackSequence();
		}
	}
	
	void attackSequence() {
		Skill chosenSkill = myPokemon.getSkills().get(chooseSkill-1);
		if(randPokemon.getElementWeakness() == myPokemon.getElement()) {
			randPokemon.setHealth(randPokemon.getHealth() - (chosenSkill.getSp() * 2));
		} else {
			randPokemon.setHealth(randPokemon.getHealth() - chosenSkill.getSp());
		}
		
		System.out.println();
        System.out.println("=============================");
		System.out.println(myPokemon.getName().toUpperCase() + " ATTACK WITH " + chosenSkill.getName().toUpperCase() + "!!!!");
        System.out.println("=============================");
        sleepThread();
        System.out.println();
        System.out.println("=============================");
        if(randPokemon.getHealth() <= 0) {
        	System.out.println(randPokemon.getName().toUpperCase() + " IS DEAD!!!!");
        } else {
        	System.out.println(randPokemon.getName().toUpperCase() + " HEALTH IS " + randPokemon.getHealth()  + "!!!!");
        }
        System.out.println("=============================");
        
        System.out.println();
        
        if(randPokemon.getHealth() <= 0) {
            System.out.println("--------------------");
        	System.out.println("YOU WIN!!");
            System.out.println("=-------------------");
        }
        
        sleepThread();
	}
	
	void computerAttackSequence() {
		Skill chosenSkill = randPokemon.getSkills().get(Helper.getRandomNumber(1, 3));
		if(myPokemon.getElementWeakness() == randPokemon.getElement()) {
			myPokemon.setHealth(myPokemon.getHealth() - (chosenSkill.getSp() * 2));
		} else {
			myPokemon.setHealth(myPokemon.getHealth() - chosenSkill.getSp());
		}
		System.out.println();
        System.out.println("=============================");
		System.out.println(randPokemon.getName().toUpperCase() + " ATTACK WITH " + chosenSkill.getName().toUpperCase() + "!!!!");
        System.out.println("=============================");
        sleepThread();
        System.out.println();
        System.out.println("=============================");
        if(myPokemon.getHealth() <= 0) {
        	System.out.println(myPokemon.getName().toUpperCase() + " IS DEAD!!!!");
        } else {
        	System.out.println(myPokemon.getName().toUpperCase() + " HEALTH IS " + myPokemon.getHealth()  + "!!!!");
        }
        System.out.println("=============================");
        
        if(myPokemon.getHealth() <= 0) {
            System.out.println("--------------------");
        	System.out.println("YOU LOSE!!");
            System.out.println("=-------------------");
        }
        
        sleepThread();
	}
	
	void sleepThread() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
