package Core;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Marksman;
import Classes.Ninja;


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

	JLabel combatinfo = new JLabel("");
	JButton basicattack = new JButton("");
	JButton ability1 = new JButton("");
	JButton ability2 = new JButton("");
	JButton ability3 = new JButton("");
	JButton Ultimate = new JButton("");
	Entity player = new Ninja("Fenix");
	Entity player2 = new Marksman("A.I");
	JLabel playername = new JLabel(player.getName());
	JLabel player2name = new JLabel(player2.getName());
	
	
	JProgressBar playerhealthbar = new JProgressBar();
	JProgressBar player2healthbar = new JProgressBar();
	Timer Timer = new Timer();
	JProgressBar playermanabar = new JProgressBar();
	
	private JTable playerstats;


	



	public void init() {
		this.start();
		System.out.println("Nowhere");
	
		System.out.println("and this far");
		player.fillstats();
		player.attacks();
		player.PrintStartStats();
		System.out.println("and this far");
		
		player2.fillstats();
		player2.PrintStartStats();
	
		this.setSize(1074, 626);
		this.setVisible(true);
		ability1.setBounds(303, 542, 200, 20);
		ability1.setText(player.AttackName[1]);

		ability1.addActionListener(this);
		ability2.setBounds(723, 542, 200, 20);

		ability2.setText(player.AttackName[2]);
		ability2.addActionListener(this);
		ability3.setBounds(513, 573, 200, 20);
		ability3.setText(player.AttackName[3]);
		ability3.addActionListener(this);
		Ultimate.setBounds(513, 542, 200, 20);
		Ultimate.setText(player.AttackName[4]);
		Ultimate.addActionListener(this);
		Ultimate.setBackground(Color.black);
		Ultimate.setForeground(Color.YELLOW);
		basicattack.setBounds(513, 511, 200, 20);
		basicattack.setText(player.AttackName[0]);
		basicattack.addActionListener(this);
		basicattack.setBackground(Color.black);
		basicattack.setForeground(Color.white);
		setLayout(null);
		playerhealthbar.setBounds(303, 430, 620, 14);
	
		playerhealthbar.setForeground(new Color(0, 128, 0));
		playerhealthbar.setBackground(Color.BLACK);
		playerhealthbar.setValue((int) ((player.HP / player.maxhp) * 100));
		playerhealthbar.setMinimum(0);
		add(playerhealthbar);
		player2healthbar.setBounds(303, 104, 620, 14);

	
		player2healthbar.setForeground(new Color(0, 128, 0));
		player2healthbar.setBackground(Color.BLACK);
		player2healthbar.setValue((int) ((player2.HP / player2.maxhp) * 100));
		player2healthbar.setMinimum(0);
		add(player2healthbar);
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
		
		playerstats = new JTable();
		playerstats.setBounds(125, 339, 269, 80);
		playerstats.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		playerstats.setBackground(new Color(255, 255, 255));
		playerstats.setCellSelectionEnabled(true);
		playerstats.setRowSelectionAllowed(false);
		playerstats.setShowVerticalLines(false);
		playerstats.setModel(new DefaultTableModel(
			new Object[][] {
				{"Basic Attack Damage", player.calculateattackdamage(player.attackposition(player.AttackName[0]))},
				{"Physical Protections", player.physicalprotections},
				{"Magical Protections", player.magicalprotections},
				{"Physical Power",  player.physicalpower},
				{"Magical Power", player.magicalpower},
			},
			new String[] {
				"Stat Displayed", "Stat Number"
			}
		));
		playerstats.setFillsViewportHeight(true);
		playerstats.setColumnSelectionAllowed(true);
		add(playerstats);
	
		playermanabar.setBackground(new Color(0, 0, 0));
		add(playermanabar);
		combatinfo.setBounds(303, 458, 620, 48);
		
		
		combatinfo.setHorizontalAlignment(SwingConstants.CENTER);
		add(combatinfo);
		
		JLabel playernamelabel = new JLabel(player.getName());
		playernamelabel.setBounds(513, 405, 200, 14);
		playernamelabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(playernamelabel);
		
	
		
		
	
		healthbarupdate();
		manabarupdate();
		this.paint(getGraphics());
		
		
	}

	public void start() {
		
		Timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
			   
			  
				  manaupdate(player.maxmana *.01, player);
			   healthupdate(player.maxmana *.01, player);
			  }
			}, 2000, 2000);
	}
	
	public void healthbarupdate() {

		int playercurrenthealth = (int) ((player.HP / player.maxhp) * 100);
		playerhealthbar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		playerhealthbar.setValue(playercurrenthealth);
		int player2currenthealth = (int) ((player2.HP / player2.maxhp) * 100);
		player2healthbar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		player2healthbar.setValue(player2currenthealth);
		
	
		if (playercurrenthealth <= 0) {
			playerhealthbar.setBackground(Color.DARK_GRAY);
			
			

		} else if (playercurrenthealth <= 25) {
			playerhealthbar.setForeground(Color.red);
	
			
		} else if (playercurrenthealth <= 40) {
			playerhealthbar.setForeground(Color.yellow);

		}
		
		if (player2currenthealth <= 0) {
			player2healthbar.setBackground(Color.DARK_GRAY);
			
			

		} else if (player2currenthealth <= 25) {
			player2healthbar.setForeground(Color.red);
	
			
		} else if (player2currenthealth <= 40) {
			player2healthbar.setForeground(Color.yellow);

		}


		playerhealthbar.setString((Math.round(player.HP) + "/" + Math.round(player.maxhp)));
		playerhealthbar.updateUI();
		
	
		
		player2healthbar.setString((Math.round(player2.HP) + "/" + Math.round(player2.maxhp)));
		
		player2healthbar.updateUI();
	}
	
	
	
	public void manabarupdate() {
		int playercurrentmana = (int) ((player.mana / player.maxmana) * 100);
		playermanabar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		playermanabar.setValue(playercurrentmana);
		playermanabar.setBackground(Color.black);
		playermanabar.setForeground(Color.blue);
		
	

		playermanabar.setString((Math.round(player.mana) + "/" + Math.round(player.maxmana)));

		playermanabar.updateUI();
		
	}


	public void healthupdate(double amount, Entity entity) {
		entity.HP -= amount;
		if (entity.HP <= 0) {
			entity.HP = 0;

			combatinfo.setText(entity.getName() + " has been slain!");
			stop();

		} else {

		}
		healthbarupdate();
	}
	
	public void manaupdate(double amount, Entity entity) {
		entity.mana -= amount;
		if (entity.mana <= 0) {
			entity.mana = 0;

			combatinfo.setText(entity.getName() + " is out of mana!");

		} else {

		}
		manabarupdate();
	}

	public void player2attacks() {

		healthupdate(
				player2.battles(player2.attackposition(player2.AttackName[(int) (Math.random() * 5) + 1 - 1]), player.classnumber), player);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == basicattack) {
			combatinfo.setText("You casted: " + player.AttackName[0] + " and did "+ Math.round((player.battles(player.attackposition(player.AttackName[0]), player2.classnumber))));
			healthupdate(player.battles(player.attackposition(player.AttackName[0]), player2.classnumber), player2);
		
			player2attacks();

		}

		if (e.getSource() == ability1) {
			combatinfo.setText("You casted: " + player.AttackName[1] + " and did "
					+ Math.round((player.battles(player.attackposition(player.AttackName[1]), player2.classnumber))));
			healthupdate(player.battles(player.attackposition(player.AttackName[1]), player2.classnumber), player2);


			player2attacks();
		}
		if (e.getSource() == ability2) {
			healthupdate(player.battles(player.attackposition(player.AttackName[2]), 0), player2);
			combatinfo.setText("You casted: " + player.AttackName[2] + " and did "
					+ Math.round((player.battles(player.attackposition(player.AttackName[2]), player2.classnumber))));

			player2attacks();

		}
		if (e.getSource() == ability3) {
			combatinfo.setText("You casted: " + player.AttackName[3] + " and did "
					+ Math.round((player.battles(player.attackposition(player.AttackName[3]), 0))));
			healthupdate(player.battles(player.attackposition(player.AttackName[3]), player2.classnumber), player2);
		

			player2attacks();
		}
		if (e.getSource() == Ultimate) {
			combatinfo.setText("You casted: " + player.AttackName[4] + " and did "
					+ Math.round((player.battles(player.attackposition(player.AttackName[4]), player2.classnumber))));
			healthupdate(player.battles(player.attackposition(player.AttackName[4]), player2.classnumber), player2);


			player2attacks();
		}

	}
}
	
