package gameplay;

import java.awt.*;

import java.awt.geom.*;
import java.util.*;
import javax.swing.ImageIcon;
@SuppressWarnings({"unused"})

public class Store {
	
	public static Rectangle repairBtn = new Rectangle(900,200,260,100);
	public static Rectangle fuelBtn = new Rectangle(900,365,260,100);
	public static Rectangle shieldBtn = new Rectangle(900,505,260,130);
	static Image img;
	
	public Store(){
		loadImages();
	}
	
	public void loadImages(){
		img = new ImageIcon(this.getClass().getResource("/images/store.png")).getImage();
	}
	
	public static Image getImg(){
		return img;
	}
	
	public static Rectangle getRepairBtn(){
		return repairBtn;
	}
	
	public static Rectangle getFuelBtn(){
		return fuelBtn;
	}
	
	public static Rectangle getShieldBtn(){
		return shieldBtn;
	}
	
	public static void buyRepair(){
		
		Ship.addAlert("You have purchased repairs from the shop.",1);
		Ship.engine_room.repair();
		Ship.bridge_room.repair();
		Ship.oxygen_room.repair();
		Ship.weapon_room.repair();
		Ship.shield_room.repair();
		
	}
	
	public static void buyFuel(){
		
		Ship.fuel += 1;
		
	}
	
	public static void buyShieldCore(){
		
		Ship.shielddmg = 0;
		
	}
	
	
}
