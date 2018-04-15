package Classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import Core.BattleEngine;
import Core.Entity;
import Core.Testrun;

public class Warrior extends Entity {
	BattleEngine B = new BattleEngine();
	Timer timer = new Timer();
	Method[] kit = this.getClass().getDeclaredMethods();
	int attacksdone = 0;

	public Warrior(String name) {
		

super(name, "Warrior");
	
		this.fillstats();

		this.getClass().asSubclass(Entity.class);
		
		try {
			kit[0] = this.getClass().getDeclaredMethod("SwingSword", Entity.class, Testrun.class);
			kit[1] = this.getClass().getDeclaredMethod("CrashDown", Entity.class, Testrun.class);
			kit[2] = this.getClass().getDeclaredMethod("Rejuvinate", Entity.class, Testrun.class);
			kit[3] = this.getClass().getDeclaredMethod("DefendersStance", Entity.class, Testrun.class);
			kit[4] = this.getClass().getDeclaredMethod("EightPathDragonDance", Entity.class, Testrun.class);

			for (int i = 0; i < 5; i++) {
				System.out.println(kit[i]);
			}

		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void invokeability(int num, Entity entity, Testrun t) {

		try {
			kit[num].invoke(this, entity, t);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void CrushingStrength(Entity entity, Testrun t) {
		
			B.statreduction(entity.getPhysicalprotections() * 0.0, 0, entity, this, t, false, "PD");
		
	}
	
	public void SwingSword(Entity entity, Testrun t) {

		B.doAttack(this.attackposition("Swing Sword"), this, entity, t,1, true);
		CrushingStrength(entity, t);
		return;

	}

	public void CrashDown(Entity entity, Testrun t) {

		B.doAttack(this.attackposition("Crash Down"), this, entity, t, 1, true);
		CrushingStrength(entity, t);

	}

	public void Rejuvinate(Entity entity, Testrun t) {

		double healamount = (this.getMaxhp() - this.getHP()) * .15;

		B.doheal(healamount, this.attackposition("Rejuvinate"), this, t);
		

	}

	public void DefendersStance(Entity entity, Testrun t) {

		t.getCombatinfo().setText(this.getName() + " brings their sword into a defensive position.");
		t.getCombatinfo().updateUI();
		double boostamount = this.getPhysicalprotections() * .15;

		B.statboost(boostamount, this.attackposition("Defender's Stance"), this, t, true);

	}

public void EightPathDragonDance(Entity entity, Testrun t) {
{
	   attacksdone = 0;
	timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
		
			  if (attacksdone == 8) {
				  cancel();
			  }
			  else {
		    EightPathAttacks(entity, t);
			  }
		    System.out.println("Attacks Done: " + attacksdone);
		  }
		}, 0, 250);
        	
       
        
        B.statboost(0, this.attackposition("Eight-Path Dragon Dance"), this, t, false);
	
	
}
}
	public void EightPathAttacks(Entity entity, Testrun t) {
		

		
		 if (entity.getHP() < (entity.getMaxhp() * .25)) {
	        	
	        	B.doDamage(this.attackposition("Eight-Path Dragon Dance"), entity.getMaxhp() * 100, 'T', this, entity, t, true, true);
	        	attacksdone = 8;
	        }
		 
		 else {
				if (attacksdone !=8) {
				B.doAttack(this.attackposition("Eight-Path Dragon Dance"), this, entity,t,8, false );
				CrushingStrength(entity, t);
				attacksdone++;
				}
				
				if (attacksdone ==8) {
					B.doAttack(this.attackposition("Eight-Path Dragon Dance"), this, entity,t,8, true );
					CrushingStrength(entity, t);
					}
		 }
		
	}
	

public Method kitcall(int num){
	
	return kit[num];
	
}
}
