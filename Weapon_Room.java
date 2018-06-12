package gameplay;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.geom.*;
import java.util.*;
@SuppressWarnings({"unused"})
public class Weapon_Room{

    static Rectangle rect = new Rectangle(0,0,100,100);;
    static int hullpoints=100, maxhullpoints=100;
    static boolean worked;
    static boolean disabled;
    static String name = "Weapon";
    static int powerUsed, maxPowerUsed = 3;
    static Rectangle[] powerbars = new Rectangle[3];
    static boolean[] powerbarsfilled = new boolean[3];
    static boolean powered;
    static Image[] imgs = new Image[4];
    static int loc = 0;

    public Weapon_Room(){
        for(int i=0;i<powerbars.length;i++){
            powerbars[i] = new Rectangle(235,600+(50*i),50,20);
        }
        loadImages();
    }
    
     public void loadImages(){
        //imgs[0] = Toolkit.getDefaultToolkit().getImage("sprite_weapon0.png");
    	 imgs[0] = new ImageIcon(this.getClass().getResource("/images/sprite_weapon0.png")).getImage();
        //imgs[1] = Toolkit.getDefaultToolkit().getImage("sprite_weapon1.png");
    	 imgs[1] = new ImageIcon(this.getClass().getResource("/images/sprite_weapon1.png")).getImage();
       // imgs[2] = Toolkit.getDefaultToolkit().getImage("sprite_weapon2.png");
    	 imgs[2] = new ImageIcon(this.getClass().getResource("/images/sprite_weapon2.png")).getImage();
        //imgs[3] = Toolkit.getDefaultToolkit().getImage("sprite_weapon3.png");
    	 imgs[3] = new ImageIcon(this.getClass().getResource("/images/sprite_weapon3.png")).getImage();
    }
     
     public void repair(){
     	hullpoints = 100;
     }
    
    public Image getImage(){
        if(Main.timer%10==0){
            if(loc<imgs.length)
            loc++;
            if(loc>=imgs.length){
                loc = 0;
            }
        }
        return imgs[loc];
    }

    public void run(){
        rect.x = Main.grid[2][5].x;
        rect.y = Main.grid[2][5].y;
        for(int i=0;i<Main.player.getCrew().length;i++){
            if(Main.player.getCrew()[i].getRect().intersects(rect)){
                worked = true;
                break;
            }else{
                worked = false;
            } 
        }
        if(worked){
            if(powerUsed>0){
                powered = true;
            }else{
                powered = false;
            }
            for(int i=0;i<powerbars.length;i++){
                if(powerbars[i].contains(Main.mouse)){
                    if(Main.MOUSE_MODIFIER==16 && !powerbarsfilled[i] && Engine_Room.powerbarsfilled[i]){
                        if(Engine_Room.powerAvailable>0){
                            powerbarsfilled[i] = true;
                            powerUsed += 1;
                            Engine_Room.powerbarsfilled[i] = false;
                            Engine_Room.powerAvailable -= 1;
                        }
                    }
                    if(powerbarsfilled[i]){
                        if(Main.MOUSE_MODIFIER==4){
                            powerbarsfilled[i] = false;
                            Engine_Room.powerbarsfilled[i] = true;
                            Engine_Room.powerAvailable += 1;
                            powerUsed -= 1;
                        }
                    }
                }
            }
        }
    }

    public int getUsed(){
        return powerUsed;
    }

    public int getMaxUsed(){
        return maxPowerUsed;
    }

    public Rectangle[] getPowerBars(){
        return powerbars;
    }

    public boolean[] getPowerBarsFilled(){
        return powerbarsfilled;
    }

    public Rectangle getRect(){
        return rect;
    }

    public void setMaxHP(int h){
        maxhullpoints = h;
    }

    public void setHP(int h){
        hullpoints = h;
    }

    public void setIsDisabled(boolean b){
        disabled = b;
    }

    public void setIsWorked(boolean b){
        worked = b;
    }

    public boolean isWorked(){
        return worked;
    }

    public String getName(){
        return name;
    }

    public boolean isDisabled(){
        return disabled;
    }

    public int getMaxHP(){
        return maxhullpoints;
    }

    public int getHP(){
        return hullpoints;
    }
}