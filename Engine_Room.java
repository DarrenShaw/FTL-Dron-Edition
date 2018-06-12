package gameplay;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.ImageIcon;
@SuppressWarnings({"unused"})
public class Engine_Room{

    static Rectangle rect = new Rectangle(0,0,100,100);
    static int hullpoints=100, maxhullpoints=100;
    static boolean worked;
    static boolean disabled;
    static String name = "Engine";
    static int powerAvailable, maxPowerAvailable = 3;
    static Rectangle[] powerbars = new Rectangle[3];
    static boolean[] powerbarsfilled = {true,true,true};
    static boolean used;
    static Image[] imgs = new Image[9];
    static Image engineoff;
    static int loc = 0;

    public Engine_Room(){
        for(int i=0;i<powerbars.length;i++){
            powerbars[i] = new Rectangle(35,600+(50*i),50,20);
        }
        loadImages();
    }
    
    public void repair(){
    	hullpoints = 100;
    }
    
    public void loadImages(){
    	engineoff = new ImageIcon(this.getClass().getResource("/images/sprite_engineoff.png")).getImage();
      //  imgs[0] = Toolkit.getDefaultToolkit().getImage("sprite_engine0.png");
    	imgs[0] = new ImageIcon(this.getClass().getResource("/images/sprite_engine0.png")).getImage();
        //imgs[1] = Toolkit.getDefaultToolkit().getImage("sprite_engine1.png");
    	imgs[1] = new ImageIcon(this.getClass().getResource("/images/sprite_engine1.png")).getImage();
        //imgs[2] = Toolkit.getDefaultToolkit().getImage("sprite_engine2.png");
    	imgs[2] = new ImageIcon(this.getClass().getResource("/images/sprite_engine2.png")).getImage();
        //imgs[3] = Toolkit.getDefaultToolkit().getImage("sprite_engine3.png");
    	imgs[3] = new ImageIcon(this.getClass().getResource("/images/sprite_engine3.png")).getImage();
       // imgs[4] = Toolkit.getDefaultToolkit().getImage("sprite_engine4.png");
    	imgs[4] = new ImageIcon(this.getClass().getResource("/images/sprite_engine4.png")).getImage();
        //imgs[5] = Toolkit.getDefaultToolkit().getImage("sprite_engine5.png");
    	imgs[5] = new ImageIcon(this.getClass().getResource("/images/sprite_engine5.png")).getImage();
        //imgs[6] = Toolkit.getDefaultToolkit().getImage("sprite_engine6.png");
    	imgs[6] = new ImageIcon(this.getClass().getResource("/images/sprite_engine6.png")).getImage();
        //imgs[7] = Toolkit.getDefaultToolkit().getImage("sprite_engine7.png");
    	imgs[7] = new ImageIcon(this.getClass().getResource("/images/sprite_engine7.png")).getImage();
        //imgs[8] = Toolkit.getDefaultToolkit().getImage("sprite_engine8.png");
    	imgs[8] = new ImageIcon(this.getClass().getResource("/images/sprite_engine8.png")).getImage();
    }
    
    public Image getImage(){
    	if(!Main.battle){
        if(Main.timer%10==0){
            if(loc<imgs.length)
            loc++;
            if(loc>=imgs.length){
                loc = 0;
            }
        }
        return imgs[loc];
    	}else{
    		return engineoff;
    	}
    }
    
    public void run(){
        rect.x = Main.grid[2][3].x;
        rect.y = Main.grid[2][3].y;
        for(int i=0;i<Main.player.getCrew().length;i++){
            if(Main.player.getCrew()[i].getRect().intersects(rect)){
                worked = true;
                break;
            }else{
                worked = false;
            } 
        }
        
        for(int i=0;i<powerbarsfilled.length;i++){
            if(!powerbarsfilled[i]){
                used = true;
                break;
            }
        }
        
        if(worked && !used){
            powerAvailable = 3;
        }
        
    }
    
    public void SOP(String s){
        System.out.println(s);
    }
    
    public Rectangle[] getPowerBars(){
        return powerbars;
    }
    
    public boolean[] getPowerBarsFilled(){
        return powerbarsfilled;
    }
    
    public int getAvailable(){
        return powerAvailable;
    }
    
    public int getMaxAvailable(){
        return maxPowerAvailable;
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