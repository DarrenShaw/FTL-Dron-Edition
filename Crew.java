package gameplay;
import java.awt.*;

import java.awt.geom.*;
import java.util.*;
import javax.swing.ImageIcon;
@SuppressWarnings({"unused"})

public class Crew {

    Rectangle rect;
    String name;
    int health = 100;

    String[] names = {"Cleeb","C-Los","An-thony","Youn Glu","Brandon","F-Red","Prince Josh"};
    static boolean[] namePicked = new boolean[8];
    String currentlyworking = "nothing.";
    boolean selected;
    Image[] imgs = new Image[7];
    private int origX, origY;
    private Rectangle origRect;
    
    public Crew(int x, int y, int w, int h){
        int rand = getRand(0,6);

        if(!namePicked[rand]){
            this.name = names[rand];
            namePicked[rand] = true;
        }else{
            rand = getRand(0,6);
            this.name = names[rand];
        }
        loadImages();
        setRect(x,y);
        
        origRect = new Rectangle(x,y,60,60);
        
        origX = x;
        origY = y;
    }
    
    public Rectangle getOrigRect(){
    	return origRect;
    }
    
    public int getOrigX(){
        return origX;
    }
    
    public int getOrigY(){
        return origY;
    }
    
    public void loadImages(){

    	imgs[0] = new ImageIcon(this.getClass().getResource("/images/cleeb.png")).getImage();
 
    	imgs[1] = new ImageIcon(this.getClass().getResource("/images/c-los.png")).getImage();

    	imgs[2] = new ImageIcon(this.getClass().getResource("/images/an-thony.png")).getImage();
    
    	imgs[3] = new ImageIcon(this.getClass().getResource("/images/youn glu.png")).getImage();
      
    	imgs[4] = new ImageIcon(this.getClass().getResource("/images/brandon.png")).getImage();
      
        imgs[5] = new ImageIcon(this.getClass().getResource("/images/f-red.png")).getImage();
       
        imgs[6] = new ImageIcon(this.getClass().getResource("/images/prince josh.png")).getImage();
    }
    
    public Image getImage(){
        if(name.equals("Cleeb")){
            return imgs[0];
        }
        if(name.equals("C-Los")){
            return imgs[1];
        }
        if(name.equals("An-thony")){
            return imgs[2];
        }
        if(name.equals("Youn Glu")){
            return imgs[3];
        }
        if(name.equals("Brandon")){
            return imgs[4];
        }
        if(name.equals("F-Red")){
            return imgs[5];
        }
        if(name.equals("Prince Josh")){
            return imgs[6];
        }
        return null;
    }
    
    public String getCurrWork(){
        return currentlyworking;
    }
    
    public void run(){
        
        if(rect.intersects(Ship.getBridgeRoom().getRect())){
            currentlyworking = "the bridge.";
        }
        if(rect.intersects(Ship.getWeaponRoom().getRect())){
            currentlyworking = "the weapon control room.";
        }
        if(rect.intersects(Ship.getShieldRoom().getRect())){
            currentlyworking = "the shield control room.";
        }
        if(rect.intersects(Ship.getOxygenRoom().getRect())){
            currentlyworking = "the oxygen control room.";
        }
        if(rect.intersects(Ship.getEngineRoom().getRect())){
            currentlyworking = "the engine control room.";
        }
    }

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean b){
        selected = b;
    }

    public void setRect(int x, int y){
        rect = new Rectangle(x,y,60,60);
    }

    public void setHealth(int h){
        health = h;
    }

    public Rectangle getRect(){
        return rect;
    }

    public int getHealth(){
        return health;
    }

    public String getName(){
        return name;
    }

    public static int getRand(int low,int high)
    {
        Random r = new Random();
        return r.nextInt(high-low+1)+low;
    }

}