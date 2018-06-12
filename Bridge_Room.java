package gameplay;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.ImageIcon;

import java.awt.Image;
@SuppressWarnings({"unused"})
public class Bridge_Room{

    static Rectangle rect = new Rectangle(0,0,100,100);
    static int hullpoints=100, maxhullpoints=100;
    static boolean worked;
    static boolean disabled;
    static String name = "Bridge";
    static Image[] imgs = new Image[4];
    static int loc = 0;

    public Bridge_Room(){
       loadImages();
    }
    
    public void loadImages(){
      
    	imgs[0] = new ImageIcon(this.getClass().getResource("/images/sprite_bridge0.png")).getImage();
       
    	imgs[1] = new ImageIcon(this.getClass().getResource("/images/sprite_bridge1.png")).getImage();
       
    	imgs[2] = new ImageIcon(this.getClass().getResource("/images/sprite_bridge2.png")).getImage();
        
    	imgs[3] = new ImageIcon(this.getClass().getResource("/images/sprite_bridge3.png")).getImage();
    }
    
    public void repair(){
    	hullpoints = 100;
    }
    
    public Image getImage(){
        if(Main.timer%100==0){
            if(loc<imgs.length)
            loc++;
            if(loc>=imgs.length){
                loc = 0;
            }
        }
        return imgs[loc];
    }
    
        public Rectangle getRect(){
        return rect;
    }
    
    public void run(){
        rect.x = Main.grid[2][6].x;
        rect.y = Main.grid[2][6].y;
        
        for(int i=0;i<Main.player.getCrew().length;i++){
            if(Main.player.getCrew()[i].getRect().intersects(rect)){
                worked = true;
                break;
            }else{
                worked = false;
            }            
        }               
        
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