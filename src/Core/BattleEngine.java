package Core;

import java.lang.reflect.Method;
import java.util.TimerTask;

import Classes.Druid;
import Classes.Mage;
import Classes.Marksman;
import Classes.Ninja;
import Classes.Warrior;

public class BattleEngine {
	// the class to each corresponding stat. E.G DamageType[0] is a Marksman
	// attack's type.
	private String Class[] = { "Marksman", "Marksman", "Marksman", "Marksman", "Marksman", "Warrior", "Warrior",
			"Warrior", "Warrior", "Warrior", "Mage", "Mage", "Mage", "Mage", "Mage", "Druid", "Druid", "Druid", "Druid",
			"Druid", "Ninja", "Ninja", "Ninja", "Ninja", "Ninja" };

	// List of Classes
	private String classlist[] = { "Marksman", "Warrior", "Mage", "Druid", "Ninja" };
	Class[] classes = new Class[] { Marksman.class,Warrior.class, Mage.class, Druid.class, Ninja.class	};
	// Fluff. Descriptions for each class.
	private String classdescription[] = {
			"Wielding an enchanted bow, the Marksman is known for their keen prescision and talent with the Bow.",
			"With a sword in hand, none match the dueling prowess of the knight in shining armour that is the Warrior. With tough steel and strong arms, you are the master of the sword.",
			"All the worlds magic is at your finger tips. With the whisper of an incantation, or the wave of an arm, the Mage casts frighetningly powerful spells, and only they know the secrets of Arcana.",
			"Nature and you are one and the same. You are attunded to the spirits and fauna, and command them with simple spells. You can hear the whispers of the trees, the secrets of a foresst. You are a Druid, the one who wields the world as a weapon.",
			"Roaming in the shadows and lurking in the corner of one's eye. The Ninja is all but seen. Their existence known only to Biraki nobility, they are ruthless, silent killers of extradionary speed and stealth. Their sight is the Raven Queen's calling."

	};
	// Names of each Attack.
	private String AttackName[] = {
			// Marksman
			"Shoot Arrow", "Hail of Arrows", " Prime Arrow", "Wind Force", "Trueshot Barrage",
			// Warrior
			"Swing Sword", "Crash Down", "Rejuvinate", "Defender's Stance", "Eight-Path Dragon Dance",
			// Mage
			"Magic Blast", "Ether Pulse", "Soul Heal", "Chaos", "Otherworldly Gate",
			// Druid
			"Staff Strike", "Striking Vines", "Primal Revive", "Cursed Branches", "Call of the Forest",
			// Ninja
			"Dagger Stab", "Silence", "Stealth", "Assasinate", "Kage-Oni",

	};

	private String AttackNameraw[] = {
			// Marksman
			"ShootArrow", "HailofArrows", " PrimeArrow", "WindForce", "TrueshotBarrage",
			// Warrior
			"SwingSword", "CrashDown", "Rejuvinate", "DefendersStance", "EightPathDragonDance",
			// Mage
			"MagicBlast", "EtherPulse", "SoulHeal", "Chaos", "OtherworldlyGate",
			// Druid
			"StaffStrike", "StrikingVines", "PrimalRevive", "CursedBranches", "CalloftheForest",
			// Ninja
			"DaggerStab", "Silence", "Stealth", "Assasinate", "KageOni",

	};

	// The corresponding damage type. Each attack has a damage type. 'P' stands for
	// physical damage. Physical Damage is reduced by Physical Protections. 'M'
	// Stands for magical damage. Magical damage is reduced by magical protections.
	// 't' stands for True. it is not reduced by any protections.
	// 'N' Means no damage. The corresponding ability doesn't damage.

	private char DamageType[] = { 'P', 'P', 'P', 'P', 'T', 'P', 'T', 'P', 'P', 'P', 'M', 'M', 'M', 'M', 'M', 'M', 'M',
			'M', 'P', 'M', 'P', 'N', 'P', 'T', 'T'

	};

	// The raw damage to each attack. Attacks do (Damage + ((power /100) * Scale).
	// the power used is determined by Scale Type. E.G if scaletype[0]='P', it will
	// use the Entity's physical power. Same goes for Magical. True is an exception,
	// it is not a power type and cannot be a scale type.
	private double Damage[] = { 95, 9, 0, 210, 150, 90, 200, 0, 0, 80, 65, 250, 0, 300, 800, 50, 240, 55, 650, 1500,
			100, 0, 360, 320, 600 };

	private double Scale[] = { 100, 2.5, 60, 0, 65, 100, 80, 0, 0, 12.5, 20, 75, 0, 60, 150, 20, 70, 75, 50, 100, 100,
			0, 100, 100, 150, };
	private char ScaleType[] = { 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'M', 'M', 'M', 'M', 'M', 'M', 'P',
			'M', 'P', 'M', 'P', 'P', 'P', 'P', 'P' };

	// What stat this attack boosts for the user, if any.
	private String Statboost[] = { "N", "N", "N", "S", "N", "N", "N", "N", "PD", "PP", "N", "N", "N", "MP", "MD", "N",
			"N", "N", "PD", "PD", "N", "N", "N", "S", "PP" };

	// How much it will boost by.
	private double Statboostnumber[] = { 0, 0, 0, 45, 0, 0, 0, 60, 20, 100, 0, 0, 1500, 250, 100, 0, 0, 400, 40, 40, 0,
			0, 0, 120, 100 };

	// Basic Stats for each class.
	private double HP[] = { 1750, 2250, 1500, 1850, 1200 };
	private double MaxHP[] = { 1750, 2250, 1500, 1850, 1200 };
	private double Mana[] = { 880, 900, 1300, 1000, 600 };
	private double MaxMana[] = { 880, 900, 1300, 1000, 600 };
	private double MagicalProtections[] = { 48, 210, 48, 110, 30 };
	private double PhysicalProtections[] = { 72, 230, 72, 120, 50 };
	private double MagicalPower[] = { 0, 0, 750, 300, 0 };
	private double PhysicalPower[] = { 220, 50, 0, 150, 250 };
	private double speed[] = { 365, 240, 350, 320, 400 };
	private String combattext = "";

	

	
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

		int classnum = 0;
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
	 * public double Combat(String attack1, String attack2) {
	 * 
	 * int pos = 0; for (int i = 0; i < AttackName.length; i++) { if
	 * (AttackName[i].equals(attack1)) { pos = i;
	 * 
	 * } } double protections = 0; if (DamageType[pos] == 'T') { protections = 0; }
	 * else if (DamageType[pos] == 'M') { int pos2 = 0; for (int i = 0; i <
	 * AttackName.length; i++) { if (AttackName[i].equals(attack2)) { pos2 = i;
	 * 
	 * } } protections = MagicalProtections[classnumber(Class[pos2])]; }
	 * 
	 * else if (DamageType[pos] == 'P') { int pos2 = 0; for (int i = 0; i <
	 * AttackName.length; i++) { if (AttackName[i].equals(attack2)) { pos2 = i;
	 * 
	 * } } protections = PhysicalProtections[classnumber(Class[pos2])]; }
	 * 
	 * return (damage(attack1) * 100) / (100 + protections);
	 * 
	 * }
	 * 
	 */
	// How an attack's damage is calculated.
	public double Combat(int attack, Entity Attacker, Entity target) {

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
			protections = target.getMagicalprotections();
			damagetype = "Magical";

		}

		else if (DamageType[attack] == 'P') {
			protections = target.getPhysicalprotections();
			damagetype = "Physical";

		}

		if (ScaleType[attack] == 'P') {
			power = Attacker.getPhysicalpower();

		}

		else if (ScaleType[attack] == 'M') {
			power = Attacker.getMagicalpower();

		}

		combineddamage = damage(attack) + (power * (scale(attack) / 100));

		calculateddamage = (combineddamage * 100) / (100 + protections);

		if (calculateddamage <= 0) {
			calculateddamage = 0;
		}
		System.out.println("You casted " + AttackName[attack] + " and did " + (int) calculateddamage + " " + damagetype
				+ " damage! " + "With " + damage(attack) + " base damage and " + scale(attack) + "% scaling!");

		return calculateddamage;

	}

	public double CalculatedDamage(int pos, Entity caller) {

		double power = 0;
		double combineddamage = 0;

		if (ScaleType[pos] == 'P') {
			power = caller.getPhysicalpower();
		}

		else if (ScaleType[pos] == 'M') {
			power = caller.getMagicalpower();
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

	//This method takes input from each player. It then compares their speed and then executes a method from in order of who has the highest speed
	// E.G if player1 is a Warrior. It would do player1.invokeability(1,player2, t).
	//Warrior should be a subclass of Entity. I'm trying to make it so that i can input player into the entity field and have it grab Warrior.player, not entity.player
	public void Fight(int attack1, int attack2, Entity player , Entity player2, Testrun t) {
	

		

		if (player.getSpeed() < player2.getSpeed()) {

		
		}
		
	}
	
	public void invoke(Class invoker,Entity entity, Testrun t) {

		for (int i = 0; i < classes.length; i++) {
			
		}
		
		
	}

	public void healthupdate(double amount, Entity entity, Testrun t) {
		entity.setHP(entity.getHP() - amount);
		if (entity.getHP() <= 0) {
			entity.setHP(0);

		} else {

		}

		t.healthbarupdate();
	}

	public void manaupdate(double amount, Entity entity) {
		entity.setMana(entity.getMana() - amount);
		if (entity.getMana() <= 0) {
			entity.setMana(0);

		} else {

		}

	}

	public String getCombattext() {
		return combattext;
	}

	public void setCombattext(String combattext) {
		this.combattext = combattext;
	}

	public void doAttack(int attackpos, Entity attacker, Entity target, Testrun t, int hits, boolean announce) {
		String damagetype = "";

		if (DamageType[attackpos] == 'T') {
			damagetype = "true";
		}

		else if (DamageType[attackpos] == 'P') {
			damagetype = "physical";
		}

		else if (DamageType[attackpos] == 'M') {
			damagetype = "magical";
		}

		target.setHP(target.getHP() - Combat(attackpos, attacker, target));

		if (target.getHP() <= 0) {
			target.setHP(0);
		}

		if (announce) {
			if (hits == 0) {
				t.getCombatinfo().setText(attacker.getName() + " tried to attack " + target.getName() + " but missed!");
			}
			if (hits == 1) {
				t.getCombatinfo()
						.setText(attacker.getName() + " used " + AttackName[attackpos] + " and did "
								+ (int) Combat(attackpos, attacker, target) + " " + damagetype + " damage to "
								+ target.getName());
			}

			else {
				System.out.println("We got this far!");
				t.getCombatinfo()
						.setText(attacker.getName() + " used " + AttackName[attackpos] + " and did "
								+ (int) (Combat(attackpos, attacker, target) * hits) + " " + damagetype + " damage to "
								+ target.getName() + " in " + hits + " hits");
			}

		}

		else {
			t.getCombatinfo().setText("");
		}

		t.healthbarupdate();
	}

	public void doDamage(int attackpos, Double amount, char Type, Entity attacker, Entity target, Testrun t,
			boolean wasexecute, boolean announce) {
		String damagetype = "";
		double protections = 0;
		if (Type == 'T') {
			damagetype = "true";

		}

		else if (Type == 'P') {
			damagetype = "physical";
			protections = target.getPhysicalprotections();
		}

		else if (Type == 'M') {
			damagetype = "magical";
			protections = target.getMagicalprotections();
		}

		double calculateddamage = (amount * 100) / (100 + protections);

		target.setHP(target.getHP() - calculateddamage);

		if (target.getHP() <= 0) {
			target.setHP(0);
		}

		if (announce) {
			if (!wasexecute) {
				t.getCombatinfo().setText(attacker.getName() + " used " + AttackName[attackpos] + " and did "
						+ (int) calculateddamage + " " + damagetype + " damage to " + target.getName());
			} 
			else {
				t.getCombatinfo().setText(attacker.getName() + " used " + AttackName[attackpos] + " and" + " executed "
						+ target.getName());
			}
		} else {

		}
		t.healthbarupdate();
	}

	public String getAttackNameraw(int number) {
		return AttackNameraw[number];
	}

	public String nameofrawattack(int attacknum, String nameofclass) {

		String answer = "";
		for (int i = 0; i < classlist.length; i++) {
			if (classlist[i].equals(nameofclass)) {
				answer = AttackNameraw[attackposition(attacknum, nameofclass)];

			}

		}

		return answer;

	}

	public void doheal(Double amount, int attackpos, Entity user, Testrun t) {
		double healamount = (statboostnumber(attackpos) + amount);
		if (user.getHP() == user.getMaxhp()) {
			healamount = 0;

		}

		user.setHP(user.getHP() + healamount);

		if (user.getHP() > user.getMaxhp()) {
			user.setHP(user.getMaxhp());

		}

		t.getCombatinfo()
				.setText(user.getName() + " used " + AttackName[attackpos] + " and healed " + (int) (healamount));
		t.healthbarupdate();
	}

	public void statboost(double amount, int attackpos, Entity user, Testrun t, boolean announce) {

		double boostamount = (statboostnumber(attackpos) + amount);

		String boostedstat = "";
		if (Statboost[attackpos].equals("PP")) {

			if (user.getPhysicalpower() >= 355) {
				user.setPhysicalpower(355);
				boostamount = 0;
			}
			if (boostamount > 400) {
				boostamount = 400;
			}

			user.setPhysicalpower(user.getPhysicalpower() + boostamount);
			boostedstat = "Physical Power";

			if (user.getPhysicalpower() >= 400) {
				user.setPhysicalpower(400);

			}
		} else if (Statboost[attackpos].equals("PD")) {

			if (user.getPhysicalprotections() >= 355) {
				user.setPhysicalprotections(355);
				boostamount = 0;
			}
			if (boostamount > 355) {
				boostamount = 355;
			}

			user.setPhysicalprotections(user.getPhysicalprotections() + boostamount);
			if (user.getPhysicalprotections() >= 355) {
				user.setPhysicalprotections(355);

			}

			boostedstat = "Physical Protections";

		}

		else if (Statboost[attackpos].equals("MP")) {

			if (user.getMagicalpower() >= 900) {
				user.setMagicalpower(900);
				boostamount = 0;
			}
			if (boostamount >= 900) {
				boostamount = 900;
			}

			user.setMagicalpower(user.getMagicalpower() + boostamount);
			boostedstat = "Magical Power";

			if (user.getMagicalpower() >= 900) {
				user.setMagicalpower(900);

			}
		}

		else if (Statboost[attackpos].equals("MD")) {

			if (user.getMagicalprotections() >= 355) {
				user.setMagicalprotections(355);
				boostamount = 0;
			}
			if (boostamount >= 355) {
				boostamount = 355;
			}

			user.setMagicalprotections(user.getMagicalprotections() + boostamount);
			;
			boostedstat = "Magical Protections";
			if (user.getMagicalprotections() >= 355) {
				user.setMagicalprotections(355);

			}
		} else if (Statboost[attackpos].equals("S")) {
			user.setSpeed(user.getSpeed() + boostamount);
			boostedstat = "Speed";

		}

		if (announce) {
			t.getCombatinfo().setText(user.getName() + " used " + AttackName[attackpos] + " and gained "
					+ (int) boostamount + " " + boostedstat);
		}

		else {

		}
		t.resetplayerstatstext();
	}

	public void statreduction(double amount, int attackpos, Entity target, Entity user, Testrun t, boolean announce,
			String statreduced) {

		if (amount < 0) {

			amount = 0;
		}
		String statreducedname = "";
		if (statreduced.equals("PP")) {

			if (target.getPhysicalpower() <= 0) {
				target.setPhysicalpower(0);
				amount = 0;
			}
			if (amount > target.getPhysicalpower()) {
				amount = target.getPhysicalpower();
			}

			target.setPhysicalpower(target.getPhysicalpower() - amount);
			statreducedname = "Physical Power";

			if (target.getPhysicalpower() <= 0) {
				target.setPhysicalpower(0);
			}

		}

		else if (statreduced.equals("PD")) {

			if (target.getPhysicalprotections() <= 0) {
				target.setPhysicalprotections(0);
				amount = 0;
			}
			if (amount > target.getPhysicalprotections()) {
				amount = target.getPhysicalprotections();
			}

			target.setPhysicalprotections(target.getPhysicalprotections() - amount);
			if (target.getPhysicalprotections() <= 0) {
				target.setPhysicalprotections(0);

			}

			statreducedname = "Physical Protections";

		}

		else if (statreduced.equals("MP")) {

			if (target.getMagicalpower() <= 0) {
				target.setMagicalpower(0);
				amount = 0;
			}

			target.setMagicalpower(target.getMagicalpower() - amount);
			statreducedname = "Magical Power";

			if (target.getMagicalpower() <= 0) {
				target.setMagicalpower(0);

			}
		}

		else if (statreduced.equals("MD")) {

			if (target.getMagicalprotections() <= 0) {
				target.setMagicalprotections(0);
				amount = 0;
			}

			target.setMagicalprotections(target.getMagicalprotections() + amount);
			statreducedname = "Magical Protections";
			if (target.getMagicalprotections() <= 0) {
				target.setMagicalprotections(0);

			} else if (statreduced.equals("S")) {
				target.setSpeed(target.getSpeed() - amount);
				statreducedname = "Speed";

				if (target.getSpeed() <= 0) {
					target.setSpeed(0);
				}

			}

			if (announce) {
				t.getCombatinfo().setText(user.getName() + "used " + AttackName[attackpos] + " and reduced "
						+ target.getName() + "'s " + statreducedname);
			}

			else {

			}
			t.resetplayerstatstext();
		}
	}
	
	
	
	
}
