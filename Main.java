package gameplay;

import java.awt.*;
import java.util.*;
import java.awt.Toolkit.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*; // For Ellipse2D, etc.
import java.awt.image.*;

@SuppressWarnings({ "serial", "unused", "static-access" })

public class Main extends JPanel implements MouseListener, MouseMotionListener {
	JFrame frame = new JFrame("FTL D-Ron Edition");
	private final int WIDTH = 1260, HEIGHT = 800;
	public static KeyMove key = new KeyMove();
	public static double clock = 0;
	public static int timer = 0;
	public static boolean win;
	public static Rectangle[][] grid = new Rectangle[6][8];
	public static Ship player = new Ship();
	public static volatile boolean GAME, LOSE, WIN, MENU = true;
	public static boolean crewSelected, tutorial1, battletutorial;
	public static Point mouse = new Point(0, 0), target = new Point(0, 0), shotloc = new Point(0, 0);
	public static int MOUSE_MODIFIER;
	public static boolean travel;
	public static boolean startBattle, battle;
	Image bg1, hud, explosion, gameover, gamewin, menu, tut1, batTut;
	public static Rectangle travelBack = new Rectangle(1100, 25, 100, 50);
	public static Sound snd = new Sound();

	static boolean shootgif = false;

	// File Stuff
	Files highScores;
	String[] values;// Array used to hold "high scores"
	double scores[];// Array used to hold converted "high scores"
	int currentScore = 0;

	public Main() {
		super();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, WIDTH, HEIGHT);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setUndecorated(true);
		frame.add(this);
		frame.setVisible(true);
		this.setLayout(null);
		setFocusable(true);// Needed for Key inputs
		addMouseListener(this);
		addMouseMotionListener(this);
		this.addKeyListener(key);
		System.setProperty("sun.java2d.opengl", "True");
		requestFocusInWindow();
		createGrid();
		loadImages();

		Thread thr1 = new Thread(r1);
		thr1.start();
		Thread thr2 = new Thread(r2);
		thr2.start();
		snd.playMain(false);
	}

	public void loadImages() {
		bg1 = new ImageIcon(this.getClass().getResource("/images/bg1.png")).getImage();
		hud = new ImageIcon(this.getClass().getResource("/images/hud.png")).getImage();
		explosion = new ImageIcon(this.getClass().getResource("/images/explosion.gif")).getImage();
		gameover = new ImageIcon(this.getClass().getResource("/images/gameover.png")).getImage();
		gamewin = new ImageIcon(this.getClass().getResource("/images/gamewin.png")).getImage();
		menu = new ImageIcon(this.getClass().getResource("/images/menu.png")).getImage();
		tut1 = new ImageIcon(this.getClass().getResource("/images/tutorial1.png")).getImage();
		batTut = new ImageIcon(this.getClass().getResource("/images/battletutorial.png")).getImage();
	}

	public void mouseMoved(MouseEvent e) {
		target.x = e.getX();
		target.y = e.getY();
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
		MOUSE_MODIFIER = e.getModifiers();
		for (int i = 0; i < player.getCrew().length; i++) {
			if (player.getCrew()[i].getRect().contains(mouse) || player.getCrew()[i].getOrigRect().contains(mouse)) {
				if (MOUSE_MODIFIER == 16 && !crewSelected) {
					player.getCrew()[i].setSelected(true);
					crewSelected = true;
				} else if (MOUSE_MODIFIER == 4 && player.getCrew()[i].isSelected()) {
					player.getCrew()[i].setSelected(false);
					crewSelected = false;
				}
			}
			if (player.getCrew()[i].isSelected()) {
				if (player.getBridgeRoom().getRect().contains(mouse) && !player.getBridgeRoom().isWorked()) {
					player.getCrew()[i].setRect(player.getBridgeRoom().getRect().x, player.getBridgeRoom().getRect().y);
					player.getCrew()[i].setSelected(false);
					crewSelected = false;
				}
				if (player.getEngineRoom().getRect().contains(mouse) && !player.getEngineRoom().isWorked()) {
					player.getCrew()[i].setRect(player.getEngineRoom().getRect().x, player.getEngineRoom().getRect().y);
					player.getCrew()[i].setSelected(false);
					crewSelected = false;
				}
				if (player.getShieldRoom().getRect().contains(mouse) && !player.getShieldRoom().isWorked()) {
					player.getCrew()[i].setRect(player.getShieldRoom().getRect().x, player.getShieldRoom().getRect().y);
					player.getCrew()[i].setSelected(false);
					crewSelected = false;
				}
				if (player.getWeaponRoom().getRect().contains(mouse) && !player.getWeaponRoom().isWorked()) {
					player.getCrew()[i].setRect(player.getWeaponRoom().getRect().x, player.getWeaponRoom().getRect().y);
					player.getCrew()[i].setSelected(false);
					crewSelected = false;
				}
				if (player.getOxygenRoom().getRect().contains(mouse) && !player.getOxygenRoom().isWorked()) {
					player.getCrew()[i].setRect(player.getOxygenRoom().getRect().x, player.getOxygenRoom().getRect().y);
					player.getCrew()[i].setSelected(false);
					crewSelected = false;
				}
			}
		}
		if (player.getJumpBtn().contains(mouse)) {
			if (player.getCanJump()) {
				travel = true;
			}
		}
		if (travel) {
			for (int i = 0; i < player.getSystems().size(); i++) {
				// player.getSystems().get(i).setIsOn(false);
			}
			for (int i = 0; i < player.getSystems().size(); i++) {

				if (player.getSystems().get(i).getRect().contains(mouse)) {
					player.hyperspace = true;
					player.doHyperspace(i);
					player.useFuel();
					player.getSystems().get(Ship.currentsysid).setIsOn(false);
					travel = false;
				}
			}
			if (travelBack.contains(mouse)) {
				travel = false;
			}
		}
		if (battle) {
			for (int i = 0; i < player.getEShip().size(); i++) {
				if (player.getEShip().get(i).getRect().contains(mouse) && !Ship.reloading) {

					player.shoot(mouse);
					shootgif = true;
					shotloc.x = target.x;
					shotloc.y = target.y;
					Ship.reloading = true;
				}
			}
		}

		if (Ship.onStore && !travel && !Ship.hyperspace) {

			if (Store.getRepairBtn().contains(Main.mouse)) {
				if (Ship.credits >= 200 && Ship.hullpoints != 500) {
					Store.buyRepair();
					Ship.credits -= 200;
					Ship.addAlert("You have purchased a repair for 200 credits.", 1);
				}
			}

			if (Store.getFuelBtn().contains(Main.mouse)) {
				if (Ship.credits >= 50 && Ship.fuel < 500) {
					Store.buyFuel();
					Ship.credits -= 50;
					Ship.addAlert("You have purchased fuel for 50 credits.", 1);
				}
			}

			if (Store.getShieldBtn().contains(Main.mouse)) {
				if (Ship.credits >= 100 && Ship.shielddmg > 0) {
					Store.buyShieldCore();
					Ship.credits -= 100;
					Ship.addAlert("You have purchased a shield core for 100 credits.", 1);
				}
			}

		}
		repaint();
	}

	public static void lose() {
		GAME = false;
		LOSE = true;
	}

	public static void win() {
		GAME = false;
		WIN = true;
	}

	public void createGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new Rectangle(35 + (100 * j), 100 + (100 * i), 100, 100);
			}
		}
	}

	BufferedImage particles; // declare the image

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, 1270, 775);

		// g2.drawImage(bg1,0,0,this);
		for (int i = 0; i < Ship.stars.length; i++) {
			g2.setColor(Color.white);
			g2.fill(Ship.stars[i]);
		}

		if (LOSE) {

			g2.drawImage(gameover, 0, 0, this);

		}

		if (WIN) {

			g2.drawImage(gamewin, 0, 0, this);

		}
		
		if(MENU){
			
			g2.drawImage(menu,0,0,this);
			
		}

		if (GAME) {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == null) {
						break;
					}
					// g2.draw(grid[i][j]);
					// g2.drawString("[i "+i+"]"+"[j
					// "+j+"]",(int)grid[i][j].getCenterX()-10,(int)grid[i][j].getCenterY());
				}
			}

			if (battle) {
				g2.setColor(Color.red);
				g2.drawLine(grid[2][6].x, grid[2][6].y, target.x, target.y);
				g2.drawLine(grid[3][6].x, grid[3][6].y, target.x, target.y);
			}

			// g2.fill(player.getBridgeRoom().getRect());
			g2.drawImage(player.getBridgeRoom().getImage(), player.getBridgeRoom().getRect().x,
					player.getBridgeRoom().getRect().y, this);
			// g2.fill(player.getEngineRoom().getRect());
			g2.drawImage(player.getEngineRoom().getImage(), player.getEngineRoom().getRect().x - 100,
					player.getEngineRoom().getRect().y, this);
			// g2.fill(player.getShieldRoom().getRect());
			g2.drawImage(player.getShieldRoom().getImage(), player.getShieldRoom().getRect().x,
					player.getShieldRoom().getRect().y, this);
			// g2.fill(player.getWeaponRoom().getRect());
			g2.drawImage(player.getWeaponRoom().getImage(), player.getWeaponRoom().getRect().x,
					player.getWeaponRoom().getRect().y, this);
			// g2.fill(player.getOxygenRoom().getRect());
			g2.drawImage(player.getOxygenRoom().getImage(), player.getOxygenRoom().getRect().x,
					player.getOxygenRoom().getRect().y, this);

			g2.drawImage(Ship.b_left, grid[3][3].x, grid[3][3].y, this);
			g2.drawImage(Ship.b_right, grid[3][5].x, grid[3][5].y, this);
			g2.drawImage(Ship.t_left, grid[1][3].x, grid[1][3].y, this);
			g2.drawImage(Ship.t_right, grid[1][5].x, grid[1][5].y, this);
			g2.drawImage(Ship.allway, grid[2][4].x, grid[2][4].y, this);
			g2.drawImage(Ship.nose, grid[2][7].x, grid[2][7].y, this);

			// g2.setColor(Color.green);
			// g2.drawString(player.getBridgeRoom().getName(),player.getBridgeRoom().getRect().x,player.getBridgeRoom().getRect().y+85);
			// g2.drawString(player.getEngineRoom().getName(),player.getEngineRoom().getRect().x,player.getEngineRoom().getRect().y+85);
			// g2.drawString(player.getShieldRoom().getName(),player.getShieldRoom().getRect().x,player.getShieldRoom().getRect().y+85);
			// g2.drawString(player.getWeaponRoom().getName(),player.getWeaponRoom().getRect().x,player.getWeaponRoom().getRect().y+85);
			// g2.drawString(player.getOxygenRoom().getName(),player.getOxygenRoom().getRect().x,player.getOxygenRoom().getRect().y+85);

			for (int i = 0; i < player.getCrew().length; i++) {
				g2.setColor(Color.blue);
				g2.drawImage(player.getCrew()[i].getImage(), player.getCrew()[i].getOrigX(),
						player.getCrew()[i].getOrigY(), this);
				g2.drawString(player.getCrew()[i].getName(), player.getCrew()[i].getOrigX(),
						player.getCrew()[i].getOrigY() + 70);
				g2.drawImage(player.getCrew()[i].getImage(), player.getCrew()[i].getRect().x,
						player.getCrew()[i].getRect().y, this);
				g2.drawString(player.getCrew()[i].getName(), player.getCrew()[i].getRect().x,
						player.getCrew()[i].getRect().y + 70);
				if (player.getCrew()[i].isSelected()) {
					g2.setColor(Color.white);
					g2.drawRect(player.getCrew()[i].getRect().x, player.getCrew()[i].getRect().y,
							player.getCrew()[i].getRect().width, player.getCrew()[i].getRect().height);
					g2.drawRect(player.getCrew()[i].getOrigRect().x, player.getCrew()[i].getOrigRect().y,
							player.getCrew()[i].getOrigRect().width, player.getCrew()[i].getOrigRect().height);
					g2.drawString("Name: " + player.getCrew()[i].getName(), 10, grid[1][0].y);
					g2.drawString("Health: " + player.getCrew()[i].getHealth(), 10, grid[1][0].y + 20);
					g2.drawString("Currently working " + player.getCrew()[i].getCurrWork(), 10, grid[1][0].y + 40);
				}
			}

			for (int i = 0; i < player.getEngineRoom().getMaxAvailable(); i++) {
				if (player.getEngineRoom().getPowerBarsFilled()[i]) {
					g2.setColor(Color.green);
					g2.fill(player.getEngineRoom().getPowerBars()[i]);
				} else {
					g2.setColor(Color.red);
					g2.fill(player.getEngineRoom().getPowerBars()[i]);
				}
				g2.setColor(Color.white);
				g2.drawRect(grid[5][0].x, grid[5][0].y + (50 * i), 50, 20);
				g2.drawString("Available Power", grid[5][0].x, grid[5][0].y + (50 * 3));
			}

			for (int i = 0; i < player.getShieldRoom().getMaxUsed(); i++) {
				if (player.getShieldRoom().getPowerBarsFilled()[i]) {
					g2.setColor(Color.green);
					g2.fill(player.getShieldRoom().getPowerBars()[i]);
				} else {
					g2.setColor(Color.red);
					g2.fill(player.getShieldRoom().getPowerBars()[i]);
				}
				g2.setColor(Color.white);
				g2.drawRect(grid[5][1].x, grid[5][1].y + (50 * i), 50, 20);
				g2.drawString("Shield Power", grid[5][1].x, grid[5][1].y + (50 * 3));
			}

			for (int i = 0; i < player.getOxygenRoom().getMaxUsed(); i++) {
				if (player.getOxygenRoom().getPowerBarsFilled()[i]) {
					// g2.setColor(Color.green);
					// g2.fill(player.getOxygenRoom().getPowerBars()[i]);
				} else {
					// g2.setColor(Color.red);
					// g2.fill(player.getOxygenRoom().getPowerBars()[i]);
				}
				// g2.setColor(Color.white);
				// g2.drawRect(grid[5][2].x, grid[5][2].y + (50 * i), 50, 20);
				// g2.drawString("Oxygen Power", grid[5][2].x, grid[5][2].y +
				// (50 * 3));
			}

			for (int i = 0; i < player.getWeaponRoom().getMaxUsed(); i++) {
				if (player.getWeaponRoom().getPowerBarsFilled()[i]) {
					g2.setColor(Color.green);
					g2.fill(player.getWeaponRoom().getPowerBars()[i]);
				} else {
					g2.setColor(Color.red);
					g2.fill(player.getWeaponRoom().getPowerBars()[i]);
				}
				g2.setColor(Color.white);
				g2.drawRect(grid[5][2].x, grid[5][2].y + (50 * i), 50, 20);
				g2.drawString("Weapon Power", grid[5][2].x, grid[5][2].y + (50 * 3));
			}

			g2.setColor(Color.white);
			// g2.drawString("Hull: "+player.getHP() +
			// "HP/"+player.getMaxHP()+"HP" + " Shields: "+player.getSP()+"
			// Oxygen: "+player.getOP()+"%" + " Fuel: " +player.getFuel() + "
			// System: "+player.getCurrentSystem() , 10, 20);
			g2.drawImage(hud, 10, 20, this);
			g2.setFont(new Font("Fixedsys", Font.PLAIN, 16));
			g2.drawString(player.getHP() + "/500", 28, 75);
			g2.drawString(player.getSP() + " SP", 135, 75);
			g2.drawString(player.getOP() + "%", 250, 75);
			g2.drawString(player.getFuel() + " JUMPS", 330, 75);
			g2.drawString(player.getCurrentSystem(), 445, 75);
			g2.drawString(player.credits + "", 575, 75);

			if (player.getCanJump()) {
				g2.setColor(Color.green);
				g2.setFont(new Font("Fixedsys", Font.PLAIN, 40));
				g2.drawString("JUMP", 670, 73);
			} else {
				g2.setColor(Color.red);
				g2.setFont(new Font("Fixedsys", Font.PLAIN, 40));
				g2.drawString("JUMP", 670, 73);
			}

			if (travel) {
				g2.setColor(Color.black);
				g2.fillRect(0, 0, 10000, 6000);

				for (int i = 0; i < player.getSystems().size(); i++) {
					if (!player.getSystems().get(i).getIsOn()) {
						g2.setColor(Color.white);
					} else {
						g2.setColor(Color.white);
						g2.setFont(new Font("def", Font.PLAIN, 12));
						g2.drawString("YOU ARE HERE", (int) player.getSystems().get(Ship.currentsysid).getRect().getX(),
								(int) player.getSystems().get(Ship.currentsysid).getRect().getY() - 15);
					}

					if (!player.getSystems().get(i).getHasEnemy()) {
						//g2.setColor(Color.green);
					} else if (player.getSystems().get(i).getHasEnemy()) {
						//g2.setColor(Color.yellow);
					}
					if (player.getSystems().get(i).getHasStore()) {
						//g2.setColor(Color.blue);
					}
					g2.setColor(Color.green);
					g2.setFont(new Font("def", Font.PLAIN, 12));
					g2.drawImage(player.getSystems().get(i).getImg(), (int) player.getSystems().get(i).getRect().getX(),
							(int) player.getSystems().get(i).getRect().getY(), this);
					// g2.draw(player.getSystems().get(i).getRect());
					g2.drawString(player.getSystems().get(i).getName(), player.getSystems().get(i).getRect().x,
							player.getSystems().get(i).getRect().y + 40);
				}
				for (int i = 0; i < Ship.stars.length; i++) {
					g2.setColor(Color.white);
					g2.fill(Ship.stars[i]);
				}

				g2.setColor(Color.red);
				g2.setFont(new Font("def", Font.PLAIN, 18));
				g2.drawString("Select a system to jump to.", 450, 30);

				g2.setColor(Color.WHITE);
				g2.draw(travelBack);
				g2.setFont(new Font("def", Font.PLAIN, 36));
				g2.drawString("Back", travelBack.x + 10, travelBack.y + 40);

			}

			if (battle) {
				g2.setColor(Color.red);
				// g2.drawString("BATTLE ENGAGED", 1000, 30);
				for (int i = 0; i < player.getEShip().size(); i++) {
					// g2.fill(player.getEShip().get(i).getRect());
					g2.drawImage(player.getEShip().get(i).getImg(), (int) player.getEShip().get(i).getRect().getX(),
							(int) player.getEShip().get(i).getRect().getY(), this);
					// g2.fill(player.getEShip().get(i).getShot());

					/*
					 * g2.setColor(Color.green);
					 * g2.draw(player.getEShip().get(i).getBridge());
					 * g2.draw(player.getEShip().get(i).getWeapon());
					 * g2.draw(player.getEShip().get(i).getOxygen());
					 * g2.draw(player.getEShip().get(i).getShield());
					 * g2.draw(player.getEShip().get(i).getEngine());
					 */

					if (!player.getEShip().get(i).isReloading())
						g2.drawImage(explosion, (int) player.getEShip().get(i).getShot().getX() - 50,
								(int) player.getEShip().get(i).getShot().getY() - 50, this);

					if (shootgif) {
						g2.drawImage(explosion, shotloc.x - 50, shotloc.y - 50, this);
						if (timer % 150 == 0) {
							shootgif = false;
						}
					}

					if (Ship.reloading) {
						g2.setColor(Color.yellow);
						g2.setFont(new Font("def", Font.PLAIN, 15));
						g2.drawString("RELOADING", target.x, target.y);
					}

				}

			}

			if (Ship.onStore && !travel && !Ship.hyperspace) {

				g2.drawImage(Store.getImg(), 850, 100, this);
				g2.setColor(Color.gray);
				g2.draw(Store.getRepairBtn());
				g2.draw(Store.getFuelBtn());
				g2.draw(Store.getShieldBtn());

			}

			if (!travel) {

				g2.setFont(new Font("def", Font.PLAIN, 15));
				g2.setColor(Color.red);
				if (!Ship.showAlerts) {
					g2.drawString(Ship.alerts.get(Ship.alerts.size() - 1), 845, 40);
				} else {

					g2.setColor(Color.black);
					g2.fillRect(0, 0, 10000, 10000);
					g2.setColor(Color.red);
					for (int i = 0; i < Ship.alerts.size(); i++) {
						g2.drawString(Ship.alerts.get(i), 20, 50 + (20 * i));
					}

				}

			}
			
			if(tutorial1){
				g2.drawImage(tut1,0,0,this);
			}
			if(battletutorial){
				g2.drawImage(batTut,0,0,this);
			}

		}
	}

	Runnable r1 = new Runnable() {
		public void run() {
			try {
				Random rand = new Random();
				
				while (!stop) {
					timer++;
					// Moving Ball
					if(MENU){
						if(key.space){
							MENU=false;
							GAME=true;
						}
					}
					player.run();
					
					if(key.i && Main.timer%10==0){
						tutorial1 = !tutorial1;
					}
					if(key.p && Main.timer%10==0){
						battletutorial = !battletutorial;
					}
					
					Thread.sleep(10);// pauses the thread for 10ms
					repaint();// redraws the paintComponent method
				}
			} catch (InterruptedException iex) {
			}
		}
	};
	Runnable r2 = new Runnable() {
		public void run() {
			try {
				boolean temp = true;
				while (!stop) {

					if (!win) {
						clock += .1;// adds .1 sec to the clock
						clock = Math.round(clock * 100.0) / 100.0;
						temp = true;
					} else if (temp) {
						temp = false;// ensures this runs ONLY 1 time
						// check the lowest score and compares
						// to the new score replaces if better
						if (currentScore > scores[scores.length - 1])
							scores[scores.length - 1] = currentScore;
						Arrays.sort(scores);
						for (int i = 0; i < scores.length; i++) {// sets the
																	// string
																	// values to
																	// the
																	// scores
							values[i] = "" + scores[i];
						}
						// re-writes the file with the new array values
						highScores.writeFile(values);

					}
					Thread.sleep(100);// pauses the thread for 100ms
				}
			} catch (InterruptedException iex) {
			}
		}
	};

	public static int getRand(int low, int high) {
		Random r = new Random();
		return r.nextInt(high - low + 1) + low;
	}

	public volatile boolean stop = false;

	public void stopThread() {
		stop = true;// ends thread when called
	}

	public static void main(String[] args) {
		new Main();
		new ThreadFixer();
	}

}