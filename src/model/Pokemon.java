package model;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
	
	private String name;
	private int health;
	private List<Skill> skills;
	private Element element;
	private Element elementWeakness;
	

	public Pokemon(String name, int health, List<Skill> skill, Element element, Element elementWeakness) {
		this.name = name;
		this.health = health;
		this.skills = skill;
		this.element = element;
		this.elementWeakness = elementWeakness;
	}


	public String getName() {
		return name;
	}
	

	public List<Skill> getSkills() {
		return skills;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public int getHealth() {
		return health;
	}


	public Element getElement() {
		return element;
	}


	public Element getElementWeakness() {
		return elementWeakness;
	}
	
	
	public static List<Pokemon> generatePokemons() {
		List<Pokemon> pokemons = new ArrayList<Pokemon>();
		List<Skill> charSkillSet = new ArrayList();
		List<Skill> squiSkillSet = new ArrayList();
		List<Skill> bulbaSkillSet = new ArrayList();
		List<Skill> pikaSkillSet = new ArrayList();

		
		Skill tackle = new Skill("Tackle", 20);
		Skill burst = new Skill("Burst", 30);
		Skill flameT = new Skill("Flamethroweer", 40);
		
		charSkillSet.add(tackle);
		charSkillSet.add(burst);
		charSkillSet.add(flameT);
		
		Skill waterball = new Skill("Waterball", 30);
		Skill waterCan = new Skill("Water Cannon", 40);
		
		squiSkillSet.add(tackle);
		squiSkillSet.add(waterball);
		squiSkillSet.add(waterCan);

		
		Skill vineWhip = new Skill("Vine Whip", 30);
		Skill razorLeaf = new Skill("Razor Leaf", 40);
		
		bulbaSkillSet.add(tackle);
		bulbaSkillSet.add(vineWhip);
		bulbaSkillSet.add(razorLeaf);
		
		Skill electric = new Skill("Electric", 30);
		Skill thunder = new Skill("Thunder", 40);
		
		pikaSkillSet.add(tackle);
		pikaSkillSet.add(electric);
		pikaSkillSet.add(thunder);
		
		
		pokemons.add(new Pokemon("Charmander", 100, charSkillSet, Element.FIRE, Element.WATER));
		pokemons.add(new Pokemon("Squirtle", 100, squiSkillSet, Element.WATER, Element.ELECTRIC));
		pokemons.add(new Pokemon("Bulbasaur", 100, bulbaSkillSet, Element.GRASS, Element.FIRE));
		pokemons.add(new Pokemon("Pikachu", 100, pikaSkillSet, Element.ELECTRIC, Element.GRASS));

		return pokemons;
	}

}
