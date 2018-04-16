package Core;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Classes.Marksman;
import Classes.Warrior;


public class Testrun extends Applet implements ActionListener {
	public Testrun() {
		setBackground(new Color(255, 255, 255));
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BattleEngine B = new BattleEngine();

	JLabel title = new JLabel("ASURI ISLAND!");
	
	private JLabel combatinfo = new JLabel("");
	JButton basicattack = new JButton("");
	JButton ability1 = new JButton("");
	JButton ability2 = new JButton("");
	JButton ability3 = new JButton("");
	JButton Ultimate = new JButton("");
	public boolean canattack = true;
	JTextPane playerstatstext = new JTextPane();
	
	Marksman player = new Marksman("Fenix");
	

	Timer Timer = new Timer();
	Marksman player2 = new Marksman("A.I");
	JLabel playername = new JLabel(player.getName()+ " - " + B.classname(player.classnumber()));
	JLabel player2name = new JLabel(player2.getName()+ " - " + B.classname(player2.getClassnumber()));
	
	
	JProgressBar playerhealthbar = new JProgressBar();
	JProgressBar player2healthbar = new JProgressBar();

	JProgressBar playermanabar = new JProgressBar();


	



	public void init() {
		
		
	
		player.fillstats();
		player.attacks();
	
		System.out.println("and this far");
		
		player2.fillstats();
		
	
		this.setSize(1074, 626);
		this.setVisible(true);
		ability1.setBounds(303, 542, 200, 20);
		ability1.setText(player.getAttackName()[1]);

		ability1.addActionListener(this);
		ability2.setBounds(723, 542, 200, 20);

		ability2.setText(player.getAttackName()[2]);
		ability2.addActionListener(this);
		ability3.setBounds(513, 573, 200, 20);
		ability3.setText(player.getAttackName()[3]);
		ability3.addActionListener(this);
		Ultimate.setBounds(513, 542, 200, 20);
		Ultimate.setText(player.getAttackName()[4]);
		Ultimate.addActionListener(this);
		Ultimate.setBackground(Color.black);
		Ultimate.setForeground(Color.YELLOW);
		basicattack.setBounds(513, 511, 200, 20);
		basicattack.setText(player.getAttackName()[0]);
		basicattack.addActionListener(this);
		basicattack.setBackground(Color.black);
		basicattack.setForeground(Color.white);
		setLayout(null);
		playerhealthbar.setBounds(303, 430, 620, 14);
	
		playerhealthbar.setForeground(new Color(0, 128, 0));
		playerhealthbar.setBackground(Color.BLACK);
		playerhealthbar.setValue((int) ((player.getHP() / player.getMaxhp()) * 100));
		playerhealthbar.setMinimum(0);
	
		player2healthbar.setBounds(303, 104, 620, 14);

	
		player2healthbar.setForeground(new Color(0, 128, 0));
		player2healthbar.setBackground(Color.BLACK);
		player2healthbar.setValue((int) ((player2.getHP() / player2.getMaxhp()) * 100));
		player2healthbar.setMinimum(0);
		
		title.setBounds(579, 21, 149, 14);
		
		add(title);
		playermanabar.setBounds(303, 442, 620, 14);

		playermanabar.setStringPainted(true);
		playerhealthbar.setStringPainted(true);
		player2healthbar.setStringPainted(true);
		player2healthbar.setBorderPainted(true);
		playerhealthbar.setBorderPainted(true);
		add(basicattack);
		add(ability1);
		add(ability2);
		add(ability3);
		add(Ultimate);
		add(playerhealthbar);
		add(player2healthbar);
		add(playermanabar);
		
		;
	
		playermanabar.setBackground(new Color(0, 0, 0));
	
		getCombatinfo().setBounds(303, 458, 620, 48);
		
		
		getCombatinfo().setHorizontalAlignment(SwingConstants.CENTER);
		add(getCombatinfo());
		
		JLabel playernamelabel = new JLabel(player.getName());
		playernamelabel.setBounds(513, 405, 200, 14);
		playernamelabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(playernamelabel);
		
		JLabel player2namelabel = new JLabel(player2.getName());
		player2namelabel.setHorizontalAlignment(SwingConstants.CENTER);
		player2namelabel.setBounds(513, 79, 200, 14);
		add(player2namelabel);
		
;
playerstatstext.setText(" Player Stats \n" + " Basic Attack Damage: " + player.calculateattackdamage(player.attackposition(player.getAttackName()[0]), player) + "\n Physical Power: " + player.getPhysicalpower()  + "\n Magical Power: " + player.getMagicalpower()  + "\n Physical Protections: " + player.getPhysicalprotections()  + "\n Magical Protections: " + player.getMagicalprotections() + "\n Speed: " + player.getSpeed());

		playerstatstext.setBounds(77, 302, 229, 118);
		add(playerstatstext);
		
	
		
		
	
		healthbarupdate();
		manabarupdate();

		
		
	}


	
	
	public void healthbarupdate() {

		int playercurrenthealth = (int) ((player.getHP() / player.getMaxhp()) * 100);
		playerhealthbar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		playerhealthbar.setValue(playercurrenthealth);
		int player2currenthealth = (int) ((player2.getHP() / player2.getMaxhp()) * 100);
		player2healthbar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		player2healthbar.setValue(player2currenthealth);
		
	
		if (playercurrenthealth <= 0) {
			playerhealthbar.setBackground(Color.DARK_GRAY);
			
			

		} else if (playercurrenthealth <= 25) {
			playerhealthbar.setForeground(Color.red);
	
			
		} else if (playercurrenthealth <= 40) {
			playerhealthbar.setForeground(Color.yellow);

		}
		else {
			playerhealthbar.setForeground(new Color(0, 128, 0));

		}
		
		if (player2currenthealth <= 0) {
			player2healthbar.setBackground(Color.DARK_GRAY);
			
			

		} else if (player2currenthealth <= 25) {
			player2healthbar.setForeground(Color.red);
	
			
		} else if (player2currenthealth <= 40) {
			player2healthbar.setForeground(Color.yellow);

		}
		else {
			player2healthbar.setForeground(new Color(0, 128, 0));

		}

		playerhealthbar.setString((Math.round(player.getHP()) + "/" + Math.round(player.getMaxhp())));
		playerhealthbar.updateUI();
		
	
		
		player2healthbar.setString((Math.round(player2.getHP()) + "/" + Math.round(player2.getMaxhp())));
		
		player2healthbar.updateUI();
	}
	
	
	
	public void manabarupdate() {
		int playercurrentmana = (int) ((player.getMana() / player.getMaxmana()) * 100);
		playermanabar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		playermanabar.setValue(playercurrentmana);
		playermanabar.setBackground(Color.black);
		playermanabar.setForeground(Color.blue);
		
	

		playermanabar.setString((Math.round(player.getMana()) + "/" + Math.round(player.getMaxmana())));

		playermanabar.updateUI();
		
	}
	
	public void resetplayerstatstext() {
		playerstatstext.setText(" Player Stats \n" + " Basic Attack Damage: " + (int)player.calculateattackdamage(player.attackposition(player.getAttackName()[0]), player) + "\n Physical Power: " +  (int)player.getPhysicalpower()  + "\n Magical Power: " +  (int)player.getMagicalpower()  + "\n Physical Protections: " +  (int)player.getPhysicalprotections()  + "\n Magical Protections: " +  (int)player.getMagicalprotections() + "\n Speed: " +  (int)player.getSpeed());

	}


	

	public void player2attacks() {

		B.healthupdate(
				player2.battles(player2.attackposition(player2.getAttackName()[(int) (Math.random() * 5) + 1 - 1]), player2, player), player, this);

	}
	
	public void healthupdate(Double amount, Entity entity) {
		B.healthupdate(amount, entity, this);
		
		if (entity.getHP() <= 0) {
			
			entity.setHP(0);
			
			getCombatinfo().setText(entity.getName() + "Has been Slain!");
			stop();
		}
		
		else if (entity.getHP() > entity.getMaxhp()) {
			entity.setHP(entity.getMaxhp());
		}
		healthbarupdate();
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if (canattack) {
		if (e.getSource() == basicattack) {
		
			B.Fight(0, (int) (Math.random() * 5) + 1 - 1, player, player2, this);
			
		}

		if (e.getSource() == ability1) {
		
			B.Fight(1,(int) (Math.random() * 5) + 1 - 1, player, player2, this);

		
		}
		if (e.getSource() == ability2) {
			
			B.Fight(2, (int) (Math.random() * 5) + 1 - 1, player, player2, this);

		
		}
		if (e.getSource() == ability3) {
			
			B.Fight(3, (int) (Math.random() * 5) + 1 - 1, player, player2, this);

		
			
		}
		if (e.getSource() == Ultimate) {
			
			B.Fight(4, (int) (Math.random() * 5) + 1 - 1, player, player2, this);

		
		}

		}
		
		else {
			
		}
	}




	public JLabel getCombatinfo() {
		return combatinfo;
	}




	public void setCombatinfo(JLabel combatinfo) {
		this.combatinfo = combatinfo;
	}
}
	
