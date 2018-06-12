package gameplay;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.ImageIcon;

@SuppressWarnings({ "unused" })
public class EnemyShip {

	private int hullpoints, maxhullpoints, oxygenpoints;
	private int shieldpoints;
	private String name = "The D-Ron";
	private int bridgehealth, weaponhealth, oxygenhealth, shieldhealth, enginehealth;
	private boolean bridgealive = true, weaponalive = true, oxygenalive = true, shieldalive = true, enginealive = true;
	private boolean canRetreat;
	private int firerate = 500;
	private Rectangle rect = new Rectangle(900, 100, 366, 562);
	private Rectangle shot = new Rectangle(-500, 0, 10, 10);
	private Image eship;
	private int reloadTime = 3;
	private boolean reloading;
	
	
	private Rectangle bridge = new Rectangle(1028, 197, 100, 100);
	private Rectangle weapon = new Rectangle(1024, 300, 100, 100);
	private Rectangle oxygen = new Rectangle(1130, 404, 100, 100);
	private Rectangle shield = new Rectangle(927, 402, 100, 100);
	private Rectangle engine = new Rectangle(1025, 505, 100, 100);

	public EnemyShip() {
		hullpoints = 100;
		maxhullpoints = 100 * Ship.level;
		shieldpoints = 100 * Ship.level;
		oxygenpoints = 100 * Ship.level;
		firerate = 200 - (50 * Ship.level);

		bridgehealth = 100;
		weaponhealth = 250;
		oxygenhealth = 100;
		shieldhealth = 120;
		enginehealth = 100;

		loadImages();
	}

	public void loadImages() {

		eship = new ImageIcon(this.getClass().getResource("/images/eship.png")).getImage();

	}

	public Rectangle getBridge() {
		return bridge;
	}

	public Rectangle getWeapon() {
		return weapon;
	}

	public Rectangle getOxygen() {
		return oxygen;
	}

	public Rectangle getShield() {
		return shield;
	}

	public Rectangle getEngine() {
		return engine;
	}

	public void playerShoot(Point mouse) {
		
		if (!Ship.reloading) {
			Main.snd.playExplosion();
			if (shieldpoints <= 0) {
				if (bridge.contains(mouse)) {
					bridgehealth -= (10 * Main.player.getLevel()) + ((10 * Ship.getWeaponRoom().getUsed())/2);
					System.out.println("Enemy Bridge Hit");
					Ship.addAlert("The enemy bridge has been hit.",1);
					if (bridgehealth <= 0) {
						bridgealive = false;
						System.out.println("Enemy Bridge Disabled");
						Ship.addAlert("The enemy bridge has been disabled.",1);
					}
				}
				if (weapon.contains(mouse)) {
					int dmg = (10 * Main.player.getLevel()) + ((10 * Ship.getWeaponRoom().getUsed())/2);
					weaponhealth -= (10 * Main.player.getLevel()) + ((10 * Ship.getWeaponRoom().getUsed())/2);
					System.out.println("Enemy Weapons Hit "  + dmg);
					Ship.addAlert("The enemy weapon control station has been hit.",1);
					if (weaponhealth <= 0) {
						weaponalive = false;
						shot.x = -500;
						System.out.println("Enemy Weapons Disabled ");
						Ship.addAlert("The enemy weapon control station has been disabled.",1);
					}
				}
				if (oxygen.contains(mouse)) {
					oxygenhealth -= (10 * Main.player.getLevel()) + ((10 * Ship.getWeaponRoom().getUsed())/2);
					System.out.println("Enemy Oxygen Hit");
					Ship.addAlert("The enemy oxygen generator has been hit.",1);
					if (oxygenhealth <= 0) {
						oxygenalive = false;
						System.out.println("Enemy Oxygen Disabled");
						Ship.addAlert("The enemy oxygen generator has been disabled.",1);
					}
				}
				if (shield.contains(mouse)) {
					shieldhealth -= (10 * Main.player.getLevel()) + ((10 * Ship.getWeaponRoom().getUsed())/2);
					System.out.println("Enemy Shield Generator Hit");
					Ship.addAlert("The enemy shield generator has been hit.",1);
					if (shieldhealth <= 0) {
						shieldalive = false;
						System.out.println("Enemy Shield Generator Disabled");
						Ship.addAlert("The enemy shield generator has been disabled.",1);
					}
				}
				if (engine.contains(mouse)) {
					enginehealth -= (10 * Main.player.getLevel()) + ((10 * Ship.getWeaponRoom().getUsed())/2);
					System.out.println("Enemy Engine Hit");
					Ship.addAlert("The enemy engine has been hit.",1);
					if (enginehealth <= 0) {
						enginealive = false;
						System.out.println("Enemy Engine Disabled");
						Ship.addAlert("The enemy engine has been disabled.",1);
					}
				}
			} else if (shieldpoints > 0) {
				shieldpoints -= (10 * Main.player.getLevel()) + ((10 * Ship.getWeaponRoom().getUsed())/2);
				System.out.println("Enemy Shield Hit");
				Ship.addAlert("The enemy shield has been hit.",1);
			}

		}
	}
	
	public void setReloading(boolean b){
		reloading = b;
	}

	public Image getImg() {
		return eship;
	}

	public void shoot() {

		int randI = getRand(1, 3), randJ = getRand(2, 6);
		shot.x = (int) Main.grid[randI][randJ].getCenterX();
		shot.y = (int) Main.grid[randI][randJ].getCenterY();
		Main.snd.playExplosion();
		if (shot.intersects(Ship.getBridgeRoom().getRect()) || shot.intersects(Ship.getWeaponRoom().getRect())
				|| shot.intersects(Ship.getOxygenRoom().getRect()) || shot.intersects(Ship.getShieldRoom().getRect())
				|| shot.intersects(Ship.getEngineRoom().getRect())) {
		//	System.out.println("hit");
			
			if (Ship.shieldpoints > 0 && Ship.getShieldRoom().getUsed()>0) {
				Ship.shielddmg += 20 * Ship.level;
				//System.out.println("Player shield hit ");
				Ship.addAlert("You have been hit but the shields hold.",1);
			}
		}

		if (shot.intersects(Main.grid[1][3]) || shot.intersects(Main.grid[1][5]) || shot.intersects(Main.grid[3][3])
				|| shot.intersects(Main.grid[3][5])) {
			//System.out.println("Player Hull Hit. ");
			if (Ship.shieldpoints > 0) {
				Ship.shielddmg += 20 * Ship.level;
			}
		}

		if (Ship.shieldpoints <= 0) {
			if (shot.intersects(Ship.getBridgeRoom().getRect())) {
				Ship.getBridgeRoom().setHP(Ship.getBridgeRoom().getHP() - 30 * Ship.level);
				//System.out.println("Player Bridge Hit. " + Ship.getBridgeRoom().getHP());
				Ship.addAlert("Your bridge has been hit.",1);
			}
			if (shot.intersects(Ship.getWeaponRoom().getRect())) {
				Ship.getWeaponRoom().setHP(Ship.getWeaponRoom().getHP() - 30 * Ship.level);
				//System.out.println("Player Weapon Control Hit. " + Ship.getWeaponRoom().getHP());
				Ship.addAlert("Your weapon control station has been hit.",1);
			}
			if (shot.intersects(Ship.getOxygenRoom().getRect())) {
				Ship.getOxygenRoom().setHP(Ship.getOxygenRoom().getHP() - 30 * Ship.level);
				//System.out.println("Player Oxygen Generator Hit. " + Ship.getOxygenRoom().getHP());
				Ship.addAlert("Your oxygen generator has been hit.",1);
			}
			if (shot.intersects(Ship.getShieldRoom().getRect())) {
				Ship.getShieldRoom().setHP(Ship.getShieldRoom().getHP() - 30 * Ship.level);
				//System.out.println("Player Shield Generator Hit. " + Ship.getShieldRoom().getHP());
				Ship.addAlert("Your shield generator has been hit.",1);
			}
			if (shot.intersects(Ship.getEngineRoom().getRect())) {
				Ship.getEngineRoom().setHP(Ship.getEngineRoom().getHP() - 30 * Ship.level);
				//System.out.println("Player Engine Hit. " + Ship.getEngineRoom().getHP());
				Ship.addAlert("Your engine has been hit.",1);
			}

		}

	}
	
	public boolean isReloading(){
		return reloading;
	}

	public Rectangle getShot() {
		return shot;
	}

	public int getBridgeHealth() {
		return bridgehealth;
	}

	public int getWeaponHealth() {
		return weaponhealth;
	}

	public int getOxygenHealth() {
		return oxygenhealth;
	}

	public int getShieldHealth() {
		return shieldhealth;
	}

	public int getEngineHealth() {
		return enginehealth;
	}

	public void run() {
		if (bridgehealth > 0) {
			bridgealive = true;
		} else {
			bridgealive = false;
		}
		if (weaponhealth > 0) {
			weaponalive = true;
		} else {
			weaponalive = false;
		}
		if (oxygenhealth > 0) {
			oxygenalive = true;
		} else {
			oxygenalive = false;
		}
		if (shieldhealth > 0) {
			shieldalive = true;
		} else {
			shieldalive = false;
		}
		if (enginehealth > 0) {
			enginealive = true;
		} else {
			enginealive = false;
		}

		if (Main.timer % 500 == 0) {
			System.out.println("Bridge Health: " + bridgehealth);
			System.out.println("Weapon Health: " + weaponhealth);
			System.out.println("Oxygen Health: " + oxygenhealth);
			System.out.println("Shield Health: " + shieldhealth);
			System.out.println("Engine Health: " + enginehealth);
			System.out.println("Enemy HP: " + hullpoints);
			System.out.println("");
		}

		if (Main.timer % firerate == 0 && !reloading && weaponalive) {
			shoot();
			reloading = true;
		}
		
		if(reloading){
			
			if(Main.timer%100==0){
				reloadTime-=1;
			}
			
			if(reloadTime<=0){
				reloadTime = 3;
				reloading = false;
			}
			
		}
		hullpoints = bridgehealth + weaponhealth + oxygenhealth + shieldhealth + enginehealth;
	}

	public Rectangle getRect() {
		return rect;
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