package Classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import Core.BattleEngine;
import Core.Entity;
import Core.Testrun;

public class Marksman extends Entity {
	BattleEngine B = new BattleEngine();
	Timer timer = new Timer();
	Method[] kit = this.getClass().getDeclaredMethods();
	int hailofarrowsticks = 0;
	int trueshotbarrageshots = 0;
	int attacksdone = 0;
	boolean isarrowprimed = false;
	boolean ultflufftext = false;
	public Marksman(String name) {

		super("Haykor", "Marksman");

	
		this.setName(name);
		this.fillstats();
		this.getClass().asSubclass(Entity.class);
		try {
			kit[0] = this.getClass().getDeclaredMethod("ShootArrow", Entity.class, Testrun.class);
			kit[1] = this.getClass().getDeclaredMethod("HailofArrows", Entity.class, Testrun.class);
			kit[2] = this.getClass().getDeclaredMethod("PrimeArrow", Entity.class, Testrun.class);
			kit[3] = this.getClass().getDeclaredMethod("WindForce", Entity.class, Testrun.class);
			kit[4] = this.getClass().getDeclaredMethod("TrueShotBarrage", Entity.class, Testrun.class);

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

	public void TrueShooter(Entity entity, Testrun t) {

		B.doDamage(this.attackposition("Shoot Arrow"), 20.0, 'T', this, entity, t, false, false);

	}

	public void ShootArrow(Entity entity, Testrun t) {

		if (isarrowprimed) {
			B.doDamage(this.attackposition("Shoot Arrow"),
					B.CalculatedDamage(this.attackposition("Shoot Arrow"), this) + 50, 'T', this, entity, t, false,
					true);
		} else {
			B.doAttack(this.attackposition("Shoot Arrow"), this, entity, t, 1, true);
		}
		TrueShooter(entity, t);

	}

	public void HailofArrows(Entity entity, Testrun t) {

		hailofarrowsticks = 0;
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {

				if (hailofarrowsticks == 40) {
					cancel();
				} else {
					HailofArrowsattack(entity, t);
				}
				System.out.println("Attacks Done: " + hailofarrowsticks);
			}
		}, 0, 100);

		B.statboost(0, this.attackposition("Eight-Path Dragon Dance"), this, t, false);

	}

	public void HailofArrowsattack(Entity entity, Testrun t) {

		if (hailofarrowsticks != 40) {
			B.doAttack(this.attackposition("Hail of Arrows"), this, entity, t, 40, false);

			hailofarrowsticks++;
		}

		if (hailofarrowsticks == 40) {
			B.doAttack(this.attackposition("Hail of Arrows"), this, entity, t, 40, true);

		}

	}

	public void PrimeArrow(Entity entity, Testrun t) {

		isarrowprimed = true;
		t.getCombatinfo().setText(this.getName() + " used" + this.getAttackName()[2]
				+ " His next basic attack gains + 50 Damage and deals true damage!");

	}

	public void WindForce(Entity entity, Testrun t) {

		B.doAttack(this.attackposition("Wind Force"), this, entity, t, 1, true);
		B.statboost(0, this.attackposition("Wind Force"), this, t, false);

	}

	public void TrueShotBarrage(Entity entity, Testrun t) {


		{
			if (attacksdone == 0 && ultflufftext == false) {
				t.getCombatinfo().setText(
						this.getName() + " gain great power and ascends high into the sky, knocking 5 arrows...");
				ultflufftext = true;
			} else if (attacksdone == 5) {
				attacksdone = 0;
			}

			else {
				
				attacksdone++;
				int Chance = (int) (Math.random() * attacksdone + 1);

				if (Chance == 1) {
					t.getCombatinfo().setText("Arrow " + attacksdone + " Fired! It Hit!");

					timer.schedule(new TimerTask() {
						@Override
						public void run() {

							TrueShotBarrageattacks(entity, t);

						}
					}, 1000);
					
					

				}

				else {
					t.getCombatinfo().setText("Arrow " + attacksdone + " Fired! It Missed!");
				}
			}

		}
	}

	public void TrueShotBarrageattacks(Entity entity, Testrun t) {

		B.doDamage(this.attackposition("Trueshot Barrage"),
				(B.CalculatedDamage(this.attackposition("Trueshot Barrage"), this) * 1) + (B.CalculatedDamage(this.attackposition("Trueshot Barrage"), this) *.25 * attacksdone), 'P',
				this, entity, t, false, true);

	}

}
