package gameplay;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.ImageIcon;

//@SuppressWarnings({"unused"})
public class Ship {

	public static Bridge_Room bridge_room = new Bridge_Room();
	public static Engine_Room engine_room = new Engine_Room();
	public static Shield_Room shield_room = new Shield_Room();
	public static Weapon_Room weapon_room = new Weapon_Room();
	public static Oxygen_Room oxygen_room = new Oxygen_Room();

	public static ArrayList<String> alerts = new ArrayList<String>();

	static Crew[] crew = new Crew[3];

	static int hullpoints, maxhullpoints, oxygenpoints;
	static int shieldpoints;
	static String name = "The D-Ron";
	static int fuel = 3;
	static boolean canJump;
	static Rectangle jumpBtn = new Rectangle(670, 35, 100, 50);
	static ArrayList<Systems> sys = new ArrayList<Systems>();
	static String currentSystem = "";
	public static int credits = 100;
	public static int level = 1;
	public static int accuracy = 3;
	static ArrayList<EnemyShip> eship = new ArrayList<EnemyShip>();
	public static int firerate = 300;
	public static Image b_left, b_right, t_left, t_right, allway, nose;
	public static Rectangle2D.Double[] stars = new Rectangle2D.Double[50];
	public static int shielddmg;
	public static int reloadTime = 2, enemies, stores;
	public static boolean reloading;
	public static boolean showAlerts;
	public static boolean hyperspace, onStore;
	public static int sysToJump, hyperspaceCounter = 10, currentsysid = 0;
	Store store = new Store();

	public Ship() {
		loadImages();
		crew[0] = new Crew(20, 250, 50, 50);
		crew[1] = new Crew(20, 350, 50, 50);
		crew[2] = new Crew(20, 450, 50, 50);

		/**/
		maxhullpoints = bridge_room.getMaxHP() + engine_room.getMaxHP() + shield_room.getMaxHP()
				+ weapon_room.getMaxHP() + oxygen_room.getMaxHP();
		createSystems();
		for (int i = 0; i < stars.length; i++) {
			stars[i] = new Rectangle2D.Double(getRand(0, 2500), getRand(0, 775), 1, 1);
		}

		addAlert("[Command]: NEUTRALIZE ALL PIRATE ENEMIES IN THIS SECTOR", 1);
		addAlert("Press P to open battle tutorial(P will also close the tutorial.)", 1);
		addAlert("Press I to open tutorial.(I will also close the tutorial.)", 1);
		addAlert("O will open/close the alert view.",1);
		addAlert("To get started, place a crew member on the engine and bridge in order to jump.",1);
		addAlert("Alerts show up here. Press O to view tutorial.", 1);
	}

	public void createSystems() {
		sys.add(new Systems(10, 30, getRand(1, 2), true));

		for (int i = 0; i < 10; i++) {

			sys.add(new Systems(getRand(10, 1000), getRand(100, 700), getRand(1, 2), false));

			if (sys.get(i).getHasEnemy()) {
				enemies += 1;
				// addAlert("There are "+enemies+" enemies.",1);
			}

			if (sys.get(i).getHasStore()) {
				stores += 1;
				// addAlert("There are "+stores+" stores.",1);
			}

		}
	}

	public int getLevel() {
		return level;
	}

	public static void addAlert(String s, int print) {

		for (int i = 0; i < print; i++)
			alerts.add(s);

	}

	public void loadImages() {
		// b_left =
		// Toolkit.getDefaultToolkit().getImage("corridor_corner_bottom_left.png");
		b_left = new ImageIcon(this.getClass().getResource("/images/corridor_corner_bottom_left.png")).getImage();
		// b_right =
		// Toolkit.getDefaultToolkit().getImage("corridor_corner_bottom_right.png");
		b_right = new ImageIcon(this.getClass().getResource("/images/corridor_corner_bottom_right.png")).getImage();
		// t_left =
		// Toolkit.getDefaultToolkit().getImage("corridor_corner_top_left.png");
		t_left = new ImageIcon(this.getClass().getResource("/images/corridor_corner_top_left.png")).getImage();
		// t_right =
		// Toolkit.getDefaultToolkit().getImage("corridor_corner_top_right.png");
		t_right = new ImageIcon(this.getClass().getResource("/images/corridor_corner_top_right.png")).getImage();
		// allway = Toolkit.getDefaultToolkit().getImage("4wayint.png");
		allway = new ImageIcon(this.getClass().getResource("/images/4wayint.png")).getImage();
		// nose = Toolkit.getDefaultToolkit().getImage("nose.png");
		nose = new ImageIcon(this.getClass().getResource("/images/nose.png")).getImage();
	}

	public void shoot(Point p) {
		for (int i = 0; i < eship.size(); i++) {
			eship.get(i).playerShoot(p);
		}
	}

	public ArrayList<EnemyShip> getEShip() {
		return eship;
	}

	public String getCurrentSystem() {
		return currentSystem;
	}

	public ArrayList<Systems> getSystems() {
		return sys;
	}

	public boolean getCanJump() {
		return canJump;
	}

	public int getFuel() {
		return fuel;
	}

	public Crew[] getCrew() {
		return crew;
	}

	public void useFuel() {
		if (fuel > 0)
			fuel -= 1;
	}

	public Rectangle getJumpBtn() {
		return jumpBtn;
	}

	public void doHyperspace(int i) {

		sysToJump = i;
		canJump = false;

	}

	public void run() {
		sys.get(0).setHasEnemy(false);
		// System.out.println(sys.get(0).getIsOn());
		hullpoints = bridge_room.getHP() + engine_room.getHP() + shield_room.getHP() + weapon_room.getHP()
				+ oxygen_room.getHP();

		bridge_room.run();
		engine_room.run();
		shield_room.run();
		weapon_room.run();
		oxygen_room.run();

		if (alerts.size() > 25) {
			for (int i = 0; i < 5; i++) {
				alerts.remove(i);
			}
		}

		if (Main.key.o && Main.timer % 10 == 0) {

			showAlerts = !showAlerts;

		}

		for (int i = 0; i < crew.length; i++) {
			crew[i].run();
		}

		if (!Main.battle) {
			shieldpoints = shield_room.getGeneratedSP() - shielddmg;
			oxygenpoints = oxygen_room.getOxygen();
		} else if (Main.battle) {
			shieldpoints = shield_room.getGeneratedSP() - shielddmg;
		}

		if (shieldpoints < 0) {
			shieldpoints = 0;
			// addAlert("Your shields are damaged.",1);
		}

		if (getEngineRoom().isWorked() && getFuel() > 0 && getBridgeRoom().isWorked() && !Main.startBattle
				&& !Main.battle && !hyperspace) {
			canJump = true;
		} else {
			canJump = false;
		}

		for (int i = 0; i < sys.size(); i++) {
			if (sys.get(i).getIsOn()) {
				currentSystem = sys.get(i).getName();
				currentsysid = i;
			}
		}

		if (reloading) {

			if (Main.timer % 100 == 0) {
				reloadTime -= 1;
			}

			if (reloadTime <= 0) {
				reloadTime = 2;
				reloading = false;
			}
		}

		if (hyperspace) {

			if (Main.timer % 100 == 0) {
				hyperspaceCounter--;
				addAlert("Arriving in... T- " + hyperspaceCounter, 1);
			}

			if (hyperspaceCounter <= 0) {
				System.out.println("Jumped" + hyperspace);
				sys.get(sysToJump).setIsOn(true);
				currentsysid = sysToJump;
				if (sys.get(sysToJump).getHasEnemy()) {
					Main.startBattle = true;
					System.out.println("Attemped to start battle");
				}
				hyperspaceCounter = 10;
				hyperspace = false;
				System.out.println("Jumped" + hyperspace);
				addAlert("You have arrived.", 1);

				if (sys.get(currentsysid).getHasStore()) {
					addAlert("This system has a shop.", 1);
				}

				if (fuel <= 0) {
					addAlert("You are out of fuel. Hopefully a ship will gift you some..", 1);
				}
			}
		}

		if (sys.get(currentsysid).getHasStore()) {
			onStore = true;

		} else {
			onStore = false;
		}

		if (Main.startBattle) {
			eship.add(new EnemyShip());
			Main.battle = true;
			Main.startBattle = false;
			addAlert("To battle stations! A battle has begun.", 1);
			//Main.snd.playMain(true);
			//Main.snd.playBattle(false);
		}

		if (Main.battle) {
			for (int i = 0; i < eship.size(); i++) {
				eship.get(i).run();

				if (hullpoints <= 0) {
					Main.lose();
				}

				if (eship.get(i).getHP() <= 0) {
					Main.battle = false;
					enemies -= 1;
					if (Math.random() < 0.3) {
						addAlert("You gained fuel from the destroyed ship.", 1);
						fuel += 1;
					}

					if (Math.random() < 0.7) {
						int coin = getRand(10, 100);
						addAlert("You have looted " + coin + " credits from the enemy.", 1);
						credits += coin;
					}

					if (Math.random() < 0.1) {
						addAlert("You have made repairs from the salvaged enemy ship", 1);
						engine_room.repair();
						bridge_room.repair();
						oxygen_room.repair();
						weapon_room.repair();
						shield_room.repair();
					}
					addAlert("The battle is over. You are victorious.", 1);
					addAlert("Press O to check for loot.", 1);
					for (int j = 0; j < sys.size(); j++) {
						if (sys.get(j).getHasEnemy() && sys.get(j).getIsOn()) {
							sys.get(j).setHasEnemy(false);
						}
					}
					if (fuel <= 0) {
						addAlert("You are out of fuel. Hopefully a ship will gift you some..", 1);
					}
					//Main.snd.playBattle(true);
					//Main.snd.playMain(false);
					eship.clear();
					break;
				}

			}
		}

		if (fuel <= 0 && !Main.battle && !hyperspace) {
			if (Main.timer % 500 == 0) {
				if (Math.random() < 0.5) {
					fuel += 1;
					addAlert("A passing merchant ship has gifted you fuel.", 1);
				}
			}
		}

		if (enemies <= 0) {
			Main.win();
		}

		for (int i = 0; i < stars.length; i++) {

			if (!hyperspace && !Main.travel) {
				if (!Main.battle)
					stars[i].x -= 1;
				else {
					stars[i].x -= 0.3;
				}
			}

			if (hyperspace) {
				stars[i].x -= 0.5 * i;
			} else {
				if (stars[i].width > 1)
					stars[i].width -= 0.5;
			}

			if (stars[i].x < 0) {
				stars[i].x = getRand(1200, 2500);
				if (hyperspace) {
					stars[i].width += 0.5 * i;
				}
			}
		}

	}

	public static int getAccuracy() {
		return accuracy;
	}

	public int getOP() {
		return oxygenpoints;
	}

	public String getName() {
		return name;
	}

	public int getMaxHP() {
		return maxhullpoints;
	}

	public static Oxygen_Room getOxygenRoom() {
		return oxygen_room;
	}

	public static Weapon_Room getWeaponRoom() {
		return weapon_room;
	}

	public static Shield_Room getShieldRoom() {
		return shield_room;
	}

	public static Engine_Room getEngineRoom() {
		return engine_room;
	}

	public static Bridge_Room getBridgeRoom() {
		return bridge_room;
	}

	public void setHP(int h) {
		hullpoints = h;
	}

	public void setSP(int s) {
		shieldpoints = s;
	}

	public int getHP() {
		return hullpoints;
	}

	public int getSP() {
		return shieldpoints;
	}

	public static int getRand(int low, int high) {
		Random r = new Random();
		return r.nextInt(high - low + 1) + low;
	}
}