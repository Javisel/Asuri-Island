package Core;

import java.lang.reflect.Method;

public class BattleEngine {
	// the class to each corresponding stat. E.G DamageType[0] is a Marksman
	// attack's type.
	private String Class[] = { "Marksman", "Marksman", "Marksman", "Marksman", "Marksman", "Warrior", "Warrior",
			"Warrior", "Warrior", "Warrior", "Mage", "Mage", "Mage", "Mage", "Mage", "Druid", "Druid", "Druid", "Druid",
			"Druid", "Ninja", "Ninja", "Ninja", "Ninja","Ninja" };
	
	//List of Classes
	private String classlist[] = { "Marksman", "Warrior", "Mage", "Druid", "Ninja" };
	// Fluff. Descriptions for each class.
	private String classdescription[] = {
			"Wielding an enchanted bow, the Marksman is known for their keen prescision and talent with the Bow.",
			"With a sword in hand, none match the dueling prowess of the knight in shining armour that is the Warrior. With tough steel and strong arms, you are the master of the sword.",
			"All the worlds magic is at your finger tips. With the whisper of an incantation, or the wave of an arm, the Mage casts frighetningly powerful spells, and only they know the secrets of Arcana.",
			"Nature and you are one and the same. You are attunded to the spirits and fauna, and command them with simple spells. You can hear the whispers of the trees, the secrets of a foresst. You are a Druid, the one who wields the world as a weapon."
, "Roaming in the shadows and lurking in the corner of one's eye. The Ninja is all but seen. Their existence known only to Biraki nobility, they are ruthless, silent killers of extradionary speed and stealth. Their sight is the Raven Queen's calling."
			
	};
	// Names of each Attack.
	private String AttackName[] = {
			// Marksman
			"Shoot Arrow", "Arrow Storm", " Prime Arrow", "Wind Force", "Trueshot Barrage",
			// Warrior
			"Swing Sword", "Crash Down", "Rejuvinate", "Defender's Stance", "Eight-Path Dragon Dance",
			// Mage
			"Magic Blast", "Ether Pulse", "Soul Heal", "Chaos", "Otherworldly Gate",
			// Druid
			"Staff Strike", "Striking Vines", "Primal Revive", "Cursed Branches", "Call of the Forest",
			//Ninja
			"Dagger Stab", "Silence", "Stealth", "Assasinate","Kage-Oni",

	};

	// The corresponding damage type. Each attack has a damage type. 'P' stands for
	// physical damage. Physical Damage is reduced by Physical Protections. 'M'
	// Stands for magical damage. Magical damage is reduced by magical protections.
	// 't' stands for True. it is not reduced by any protections.

	private char DamageType[] = { 'P', 'P', 'P', 'T', 'T', 'P', 'T', 'P', 'P', 'P', 'M', 'M', 'M', 'M', 'M', 'M', 'M',
			'M', 'P', 'M','P','N','P','T','T'

	};

	// The raw damage to each attack. Attacks do (Damage + ((power /100) * Scale).
	// the power used is determined by Scale Type. E.G if scaletype[0]='P', it will
	// use the Entity's physical power. Same goes for Magical. True is an exception,
	// it is not a power type and cannot be a scale type.
	private double Damage[] = { 95, 150, 0, 300, 600, 90, 200, 0, 0, 700, 65, 250, 0, 300, 800, 50, 240, 55, 650,
			1500, 100,0,360,320,600 };

	private double Scale[] = { 100, 100, 0, 0, 150,
			120, 80, 0, 0, 120,
			20, 75, 0, 60, 150, 
			20, 70, 75, 50, 100,
			100, 0, 100,100,150,
			};
	private char ScaleType[] = { 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'M', 'M', 'M', 'M', 'M', 'M', 'P',
			'M', 'P', 'M','P','P','P','P','P' };

	// What stat this attack boosts for the user, if any.
	private String Statboost[] = { "N", "P", "N", "N", "N", "N", "N", "HP", "PD", "P", "N", "N", "HP", "MP", "MD", "N",
			"N", "HP", "PD", "PD","N","N","S","P" };

	// How much it will boost by.
	private double Statboostnumber[] = { 0, 0, 40, 0, 0, 0, 400, 60, 20, 0, 0, 0, 1500, 250, 100, 0, 0, 400, 40, 40, 0,0,120,100};

	// Basic Stats for each class.
	private double HP[] = { 1750, 2250, 1500, 1850, 1200 };
	private double MaxHP[] = { 1750, 2250, 1500, 1850, 1200 };
	private double Mana[]= {880, 900,1300, 1000, 600};
	private double MaxMana[]= {880, 900,1300, 1000, 600};
	private double MagicalProtections[] = { 48, 210, 48, 110,48 };
	private double PhysicalProtections[] = { 72, 230, 72, 120, 60 };
	private double MagicalPower[] = { 0, 0, 750, 300,0 };
	private double PhysicalPower[] = { 220, 50, 0, 150,300 };
	private double speed[] = {365,240,350,320,400};
	
	public String attacklist(String nameofclass) {
		String answer = "";
		int j = 1;
		for (int i = 0; i < Class.length; i++) {
			if (Class[i].equals(nameofclass)) {
				answer += j + ". " + AttackName[i] + "\n";

				j++;
			}
		}
		return answer;
	}
	
	public int classnumber(String nameofclass) {

		int classnum = -1;
		for (int i = 0; i < classlist.length; i++) {
			if (classlist[i].equals(nameofclass)) {
				classnum = i;
			}

		}

		return classnum;
	}

	public String classname(int number) {

		return classlist[number];
	}

	public String classdescription(int classnumber) {

		return classdescription[classnumber];

	}
	
	public int numberofclasses() {

		return classlist.length;

	}

	public int attackposition(int number, String nameofclass) {
		int pos = 0;
		for (int i = 0; i < Class.length; i++) {
			if (Class[i].equals(nameofclass)) {
				pos = i;

			}
		}
		return pos - (5 - number);
	}
	
	public String nameofattack(int attacknum, String nameofclass) {
		
		String answer = "";
		for (int i = 0; i < classlist.length; i++) {
			if (classlist[i].equals(nameofclass)) {
				answer = AttackName[attackposition(attacknum, nameofclass)];
				System.out.println(answer);
			}

		}
		
		return answer;
		
	}


	public double damage(int number) {

		return Damage[number];
	}

	public double damage(String name) {

		int pos = 0;
		for (int i = 0; i < AttackName.length; i++) {
			if (AttackName[i].equals(name)) {
				pos = i;

			}
		}
		return Damage[pos];
	}

	public int attacknumber(String name) {

		int pos = 0;
		for (int i = 0; i < AttackName.length; i++) {
			if (AttackName[i].equals(name)) {
				pos = i;

			}
		}
		return pos;
	}

	public double scale(int number) {

		return Scale[number];
	}

	public double statboostnumber(int number) {

		return Statboostnumber[number];
	}

	public double hp(int number) {

		return HP[number];
	}

	public double maxhp(int number) {

		return MaxHP[number];
	}
	
	public double mana(int number) {

		return Mana[number];
	}

	public double MaxMana(int number) {

		return MaxMana[number];
	}


	public double MagicProt(int number) {

		return MagicalProtections[number];
	}

	public double PhysProt(int number) {

		return PhysicalProtections[number];
	}

	public double MagicPower(int number) {

		return MagicalPower[number];
	}

	public double PhysicalPower(int number) {

		return PhysicalPower[number];
	}
/*
	public double Combat(String attack1, String attack2) {

		int pos = 0;
		for (int i = 0; i < AttackName.length; i++) {
			if (AttackName[i].equals(attack1)) {
				pos = i;

			}
		}
		double protections = 0;
		if (DamageType[pos] == 'T') {
			protections = 0;
		} else if (DamageType[pos] == 'M') {
			int pos2 = 0;
			for (int i = 0; i < AttackName.length; i++) {
				if (AttackName[i].equals(attack2)) {
					pos2 = i;

				}
			}
			protections = MagicalProtections[classnumber(Class[pos2])];
		}

		else if (DamageType[pos] == 'P') {
			int pos2 = 0;
			for (int i = 0; i < AttackName.length; i++) {
				if (AttackName[i].equals(attack2)) {
					pos2 = i;

				}
			}
			protections = PhysicalProtections[classnumber(Class[pos2])];
		}

		return (damage(attack1) * 100) / (100 + protections);

	}

	*/
	// How an attack's damage is calculated.
	public double Combat(int attack, int target) {
		
		System.out.println("Pos: " + attack + "Pos2: " + target);

		double protections = 0;
		double power = 0;
		double combineddamage = 0;
		double calculateddamage = 0;
		String damagetype = "null";

		if (DamageType[attack] == 'T') {
			protections = 0;
			damagetype = "True";
			System.out.println("Protections: " + protections);
		} else if (DamageType[attack] == 'M') {
			protections = MagicalProtections[target];
			damagetype = "Magical";
	
		}

		else if (DamageType[attack] == 'P') {
			protections = PhysicalProtections[target];
			damagetype = "Physical";
		
		}

		if (ScaleType[attack] == 'P') {
			power = PhysicalPower[classnumber(Class[attack])];
	
		}

		else if (ScaleType[attack] == 'M') {
			power = MagicalPower[classnumber(Class[attack])];
	
		}

		combineddamage = damage(attack) + (power * (scale(attack) / 100));
		calculateddamage = (combineddamage * 100) / (100 + protections);

		if (calculateddamage <=0) {
			calculateddamage = 0;
		}
		System.out.println("You casted " + AttackName[attack] + " and did " + (int) calculateddamage + " " + damagetype
				+ " damage! " + "With " + damage(attack) + " base damage and " + scale(attack) + "% scaling!");
		return calculateddamage;

	}
	
	public double CalculatedDamage(int pos) {

		double power = 0;
		double combineddamage = 0;
		String damagetype = "null";

		if (DamageType[pos] == 'T') {
	
			damagetype = "True";
		} else if (DamageType[pos] == 'M') {
	
			damagetype = "Magical";
		}

		else if (DamageType[pos] == 'P') {
			
			damagetype = "Physical";
		}

		if (ScaleType[pos] == 'P') {
			power = PhysicalPower[classnumber(Class[pos])];
		}

		else if (ScaleType[pos] == 'M') {
			power = MagicalPower[classnumber(Class[pos])];
		}

		combineddamage = damage(pos) + (power * (scale(pos) / 100));
	


		return combineddamage;

	}

	public void PrintClassStats(int classnumber) {
		System.out.println(classlist[classnumber]);
		System.out.println(classdescription[classnumber]);
		System.out.println("HP: " + HP[classnumber]);
		System.out.println("Magical Protections: " + MagicalProtections[classnumber]);
		System.out.println("Physical Protections: " + PhysicalProtections[classnumber]);
		System.out.println("Magical Power: " + MagicalPower[classnumber]);
		System.out.println("Physical Power: " + PhysicalPower[classnumber]);

		return;
	}

	public double speed(int number) {

		return speed[number];
	}
	
	public void Fight(Method attack1, Method attack2, Entity Player, Entity Player2) {
		
		
		
		
		
	}
	

}