package Core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public  abstract class Entity {

	private String name = "";
	private String entityclass = "Marksman";
	String classdescription = "null";
	private int classnumber;
	private double HP = 0;
	private double maxhp = 0;
	private double mana = 0;
	private double maxmana = 0;
	private double magicalprotections = 0;
	private double physicalprotections = 0;
	private double magicalpower = 0;
	private double physicalpower = 0;
	double speed = 0;
	int currentattack = -1;
	int Attacks[] = new int[5];
	private String AttackName[] = new String[5];
	private String rawAttackName[] = new String[5];
	char type[] = new char[5];
	char scaletype[] = new char[5];
	double damage[] = new double[5];
	double scale[] = new double[5];
	char statboost[] = new char[5];
	double statboostnumber[] = new double[5];
	Class gameclass;
	
	BattleEngine B = new BattleEngine();
	CoreFunctions C = new CoreFunctions();
	Method kit[];
	
	public Entity(String Name, String Class) {
		name=Name;
		entityclass = Class;
		
		
	}
	

	public void fillstats() {
		
		setClassnumber(B.classnumber(getEntityclass()));
		Class gameclass	= B.classes[classnumber];

		classdescription = B.classdescription(B.classnumber(getEntityclass()));
		for (int i = 0; i < damage.length; i++) {
			damage[i] = B.damage(B.attackposition(i + 1, getEntityclass()));
		}
		for (int i = 0; i < 5; i++) {
			getAttackName()[i] = B.nameofattack(i+1, getEntityclass());
		}
		
		for (int i = 0; i < 5; i++) {
			rawAttackName[i] = B.nameofrawattack(i+1, getEntityclass());
		}

		for (int i = 0; i < Attacks.length; i++) {
			Attacks[i] = B.attackposition(i + 1, getEntityclass());

		}	
		try {
			
		
				
				kit[0] = gameclass.getClass().getDeclaredMethod(rawAttackName[1], Entity.class,Testrun.class);
				kit[1] = gameclass.getClass().getDeclaredMethod(rawAttackName[2],  Entity.class,Testrun.class);
				kit[2] = gameclass.getClass().getDeclaredMethod(rawAttackName[3], Entity.class,Testrun.class);
				kit[3] = gameclass.getClass().getDeclaredMethod(rawAttackName[4],  Entity.class,Testrun.class);
				kit[4] = gameclass.getClass().getDeclaredMethod(rawAttackName[5],  Entity.class,Testrun.class);

				

		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		setHP(B.hp(B.classnumber(getEntityclass())));

		setMaxhp(B.hp(B.classnumber(getEntityclass())));
		
		setMana(B.mana(B.classnumber(getEntityclass())));
		
		setMaxmana(B.MaxMana(B.classnumber(getEntityclass())));

		setMagicalprotections(B.MagicProt(B.classnumber(getEntityclass())));

		setPhysicalprotections(B.PhysProt(B.classnumber(getEntityclass())));

		setMagicalpower(B.MagicPower(B.classnumber(getEntityclass())));

		setPhysicalpower(B.PhysicalPower(B.classnumber(getEntityclass())));
		
		speed = B.speed(B.classnumber(getEntityclass()));

	
	
	}
	

	public void PrintStartStats() {
		System.out.println("Class: " + getEntityclass());
		System.out.println(classdescription);
		System.out.println("Health Points: " + (int) getHP());
		System.out.println("Maximum Health Points: " + getMaxhp());
		System.out.println("Magical Protections: " + getMagicalprotections());
		System.out.println("Physical Protections: " + getPhysicalprotections());
		System.out.println("Magical Power: " + getMagicalpower());
		System.out.println("Physical Power: " + getPhysicalpower());
		System.out.println();
		System.out.println(Method[].class);
		
	}

	public void printstats() {
		System.out.println("Class: " + getEntityclass());
		System.out.println("Health Points: " + (int) getHP());
		System.out.println("Maximum Health Points: " + getMaxhp());
		System.out.println("Magical Protections: " + getMagicalprotections());
		System.out.println("Physical Protections: " + getPhysicalprotections());
		System.out.println("Magical Power: " + getMagicalpower());
		System.out.println("Physical Power: " + getPhysicalpower());
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

	public double battles(int attack1, Entity attacker, Entity target) {
		
		return B.Combat(attack1,attacker, target);
	}
	
	public double calculateattackdamage(int attack1, Entity caller) {

		return B.CalculatedDamage(attack1, caller);
	}
	
	public double damage(int number) {
		
		return damage[number];
	}
	
	public double scale(int number) {
		
		return scale[number];
	}
	
	
	public int classnumber() {
		return getClassnumber();
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

	public String[] getAttackName() {
		return AttackName;
	}
	
	public String[] getAttackNameraw() {
		return rawAttackName;
	}

	public double getMaxhp() {
		return maxhp;
	}

	public void setMaxhp(double maxhp) {
		this.maxhp = maxhp;
	}

	public double getHP() {
		return HP;
	}

	public void setHP(double hP) {
		HP = hP;
	}

	public double getPhysicalprotections() {
		return physicalprotections;
	}

	public void setPhysicalprotections(double physicalprotections) {
		this.physicalprotections = physicalprotections;
	}

	public double getMagicalprotections() {
		return magicalprotections;
	}

	public void setMagicalprotections(double magicalprotections) {
		this.magicalprotections = magicalprotections;
	}

	public double getPhysicalpower() {
		return physicalpower;
	}

	public void setPhysicalpower(double physicalpower) {
		this.physicalpower = physicalpower;
	}

	public double getMagicalpower() {
		return magicalpower;
	}

	public void setMagicalpower(double magicalpower) {
		this.magicalpower = magicalpower;
	}

	public double getMana() {
		return mana;
	}

	public void setMana(double mana) {
		this.mana = mana;
	}

	public double getMaxmana() {
		return maxmana;
	}

	public void setMaxmana(double maxmana) {
		this.maxmana = maxmana;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double amount) {
		this.speed = amount;
	}


	public int getClassnumber() {
		return classnumber;
	}

	public void setClassnumber(int classnumber) {
		this.classnumber = classnumber;
	}
	
	public String getrawname(int num) {
		
		return rawAttackName[num];
	}

	public Class getgameclass() {
		
		return gameclass;
		
	}
	
	public void invokeability(int num, Entity entity, Testrun t) {

		try {
			kit[num].invoke(this, entity, t);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
