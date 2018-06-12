package gameplay;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.ImageIcon;

@SuppressWarnings({ "unused" })
public class Systems {

	private Image img;
	private Rectangle rect;
	private boolean isOn;
	private boolean hasEnemy, hasStore;
	private String name;
	private Image star1, star2;
	private int imgNum;
	
	private String[] prefix = {"Kan ", "Baan ", "Icron ", "Prikel ", "Vruks ", "Bosde ", "Etreek ", "Gra ", "Kasrul ", "Xars ", "Theta ", "Ursa "};
	private String[] suffix = {"Major ","Minor ","Crae ","BK-XY ","ABY ", "Beta ","Orion ","Zealae ", "Delta ", "Capulus ","System ","Cantor "};
	
	public Systems(int x, int y, int imgNum, boolean b) {
		rect = new Rectangle(x, y, 30, 30);
		name = prefix[getRand(0,11)] + suffix[getRand(0,11)];

		// test = new
		// ImageIcon(this.getClass().getResource("/images/alien.png")).getImage();

		if (Math.random() < 0.7) {
			hasEnemy = true;
		}

		this.imgNum = imgNum;
		isOn = b;
		
		if(!hasEnemy){
			if(Math.random()<0.9){
				hasStore = true;
			}
		}
		
		loadImage();
	}
	
	

	public void loadImage() {
		star1 = new ImageIcon(this.getClass().getResource("/images/star1.png")).getImage();
		star2 = new ImageIcon(this.getClass().getResource("/images/star2.png")).getImage();
	}
	
	public boolean getHasStore(){
		return hasStore;
	}
	
	public void setHasEnemy(boolean b){
		hasEnemy = b;
	}
	
	public Image getImg() {
		switch (imgNum) {

		case 1:
			return star1;
		case 2:
			return star2;
			

		default:
			return star1;
			

		}

	}

	public boolean isStore() {
		return hasStore;
	}

	public String getName() {
		return name;
	}

	public Image getImage() {
		return img;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setIsOn(boolean b) {
		isOn = b;
	}

	public boolean getIsOn() {
		return isOn;
	}

	public boolean getHasEnemy() {
		return hasEnemy;
	}

	public static int getRand(int low, int high) {
		Random r = new Random();
		return r.nextInt(high - low + 1) + low;
	}

}