package Core;

import java.lang.reflect.Method;

public abstract class Entity {

	private String name = "";
	private String entityclass = "Marksman";
	String classdescription = "null";
	int classnumber;
	double HP = 0;
	double maxhp = 0;
	double mana = 0;
	double maxmana = 0;
	double magicalprotections = 0;
	double physicalprotections = 0;
	double magicalpower = 0;
	double physicalpower = 0;
	double speed = 0;
	int currentattack = -1;
	int Attacks[] = new int[5];
	String AttackName[] = new String[5];
	char type[] = new char[5];
	char scaletype[] = new char[5];
	double damage[] = new double[5];
	double scale[] = new double[5];
	char statboost[] = new char[5];
	double statboostnumber[] = new double[5];
	BattleEngine B = new BattleEngine();
	CoreFunctions C = new CoreFunctions();

	

	public void fillstats() {
		classdescription = B.classdescription(B.classnumber(getEntityclass()));
		for (int i = 0; i < damage.length; i++) {
			damage[i] = B.damage(B.attackposition(i + 1, getEntityclass()));
		}
		for (int i = 0; i < 5; i++) {
			AttackName[i] = B.nameofattack(i+1, getEntityclass());
		}

		for (int i = 0; i < Attacks.length; i++) {
			Attacks[i] = B.attackposition(i + 1, getEntityclass());

		}
		classnumber = B.classnumber(getEntityclass());

		HP = B.hp(B.classnumber(getEntityclass()));

		maxhp = B.hp(B.classnumber(getEntityclass()));
		
		mana =B.mana(B.classnumber(getEntityclass()));
		
		maxmana =B.MaxMana(B.classnumber(getEntityclass()));

		magicalprotections = B.MagicProt(B.classnumber(getEntityclass()));

		physicalprotections = B.PhysProt(B.classnumber(getEntityclass()));

		magicalpower = B.MagicPower(B.classnumber(getEntityclass()));

		physicalpower = B.PhysicalPower(B.classnumber(getEntityclass()));
		
		speed = B.speed(B.classnumber(getEntityclass()));

	}
	
		Method[] print = B.getClass().getMethods();

	public void PrintStartStats() {
		System.out.println("Class: " + getEntityclass());
		System.out.println(classdescription);
		System.out.println("Health Points: " + (int) HP);
		System.out.println("Maximum Health Points: " + maxhp);
		System.out.println("Magical Protections: " + magicalprotections);
		System.out.println("Physical Protections: " + physicalprotections);
		System.out.println("Magical Power: " + magicalpower);
		System.out.println("Physical Power: " + physicalpower);
		System.out.println();
		System.out.println(Method[].class);
		
	}

	public void printstats() {
		System.out.println("Class: " + getEntityclass());
		System.out.println("Health Points: " + (int) HP);
		System.out.println("Maximum Health Points: " + maxhp);
		System.out.println("Magical Protections: " + magicalprotections);
		System.out.println("Physical Protections: " + physicalprotections);
		System.out.println("Magical Power: " + magicalpower);
		System.out.println("Physical Power: " + physicalpower);
		System.out.println();

	}

	public void attacks() {

		System.out.println(B.attacklist(getEntityclass()));

	}

	public int attacknumber(int pos) {

		return Attacks[pos - 1];

	}
	
	public int attackposition(String name) {
		
		return B.attacknumber(name);
		
	}

	public double battles(int attack1, int attack2) {

		return B.Combat(attack1, attack2);
	}
	
	public double calculateattackdamage(int attack1) {

		return B.CalculatedDamage(attack1);
	}
	
	public double damage(int number) {
		
		return damage[number];
	}
	
	public double scale(int number) {
		
		return scale[number];
	}
	
	
	public int classnumber() {
		return classnumber;
	}

	public String getEntityclass() {
		return entityclass;
	}

	public void setEntityclass(String entityclass) {
		this.entityclass = entityclass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
