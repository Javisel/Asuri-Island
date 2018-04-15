package Classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import Core.BattleEngine;
import Core.Entity;
import Core.Testrun;

public class Druid extends Entity{
	BattleEngine B = new BattleEngine();

	Method[]kit= this.getClass().getDeclaredMethods();
	public Druid(String name) {
		

		super("Mali", "Druid");

		this.setEntityclass("Druid");
		this.setName(name);
		this.fillstats();
		this.getClass().asSubclass(Entity.class);
   	 try {
		kit[0] = this.getClass().getDeclaredMethod("StaffStrike", Entity.class, Testrun.class);
		kit[1] = this.getClass().getDeclaredMethod("CrashDown", Entity.class, Testrun.class);
		kit[2] = this.getClass().getDeclaredMethod("Rejuvinate", Entity.class, Testrun.class);
		kit[3] = this.getClass().getDeclaredMethod("DefendersStance", Entity.class, Testrun.class);
		kit[4] = this.getClass().getDeclaredMethod("EightPathDragonDance", Entity.class, Testrun.class);

		for (int i = 0; i <5; i++) {
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

	
	public  void StaffStrike (Entity entity, Testrun t) {
		
		B.doAttack(this.attackposition("Staff Strike"), this, entity,t,1, true);
		
		return ;
		
		
	}
	
	public void StrikingVines(Entity entity, Testrun t) {
		
	}
	
	public void PrimalRevive(Entity entity, Testrun t) {
		
	}
	
public void CursedBranches(Entity entity, Testrun t)  {
		
	}

public void CalloftheForest(Entity entity, Testrun t) {
	
}

public Method[] kitcall(){
	
	return kit;
	
}
	
}
