package gameplay;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.ImageIcon;
@SuppressWarnings({"unused"})
public class Oxygen_Room{

    static Rectangle rect = new Rectangle(0,0,100,100);
    static int hullpoints=100, maxhullpoints=100;
    static boolean worked;
    static boolean disabled;
    static String name = "Oxygen";
    static int powerUsed, maxPowerUsed = 3;
    static Rectangle[] powerbars = new Rectangle[3];
    static boolean[] powerbarsfilled = new boolean[3];
    static boolean powered;
    static int oxygen = 100;
    static Image[] imgs = new Image[4];
    static int loc = 0;
    
    public Oxygen_Room(){
        for(int i=0;i<powerbars.length;i++){
            powerbars[i] = new Rectangle(235,600+(50*i),50,20);
        }
        loadImages();
    }
    
    public void loadImages(){
       // imgs[0] = Toolkit.getDefaultToolkit().getImage("sprite_oxygen0.png");
    	imgs[0] = new ImageIcon(this.getClass().getResource("/images/sprite_oxygen0.png")).getImage();
        //imgs[1] = Toolkit.getDefaultToolkit().getImage("sprite_oxygen1.png");
    	imgs[1] = new ImageIcon(this.getClass().getResource("/images/sprite_oxygen1.png")).getImage();
        //imgs[2] = Toolkit.getDefaultToolkit().getImage("sprite_oxygen2.png");
    	imgs[2] = new ImageIcon(this.getClass().getResource("/images/sprite_oxygen2.png")).getImage();
       // imgs[3] = Toolkit.getDefaultToolkit().getImage("sprite_oxygen3.png");
    	imgs[3] = new ImageIcon(this.getClass().getResource("/images/sprite_oxygen3.png")).getImage();
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
        rect.x = Main.grid[3][4].x;
        rect.y = Main.grid[3][4].y;
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
                System.out.println("Oxygen Room Powered Successfully");
            }else{
                powered = false;
            }
            /*
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
            }*/
        }
        if(!disabled){
            oxygen = 100;
        }
        if(disabled){
        	oxygen = 0;
        }
    }
    
    public void repair(){
    	hullpoints = 100;
    }
    
    public int getOxygen(){
    	//System.out.println(oxygen);
        return oxygen;
        
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