
/*package gameplay;

import java.awt.*;
import java.util.*;
import java.awt.Toolkit.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import audio.Sound;
import java.awt.geom.*;
 
@SuppressWarnings({ "unused", "serial"})

public class OldMain extends JPanel implements MouseListener, MouseMotionListener{		
	
	public volatile boolean stop = false;//Controls the loop
    public int timer = 0;
    public static KeyMove key = new KeyMove();
    Image test;
    Sound snd = new Sound();
    
    public OldMain(){   
    	
        super();
        System.setProperty("sun.java2d.opengl","True");
        JFrame frame = new JFrame("Sample Mouse Input Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700); //Frame Size
        frame.add(this);
        frame.setVisible(true);
        //adds the mouseListeners
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(key);
        this.setFocusable(true);
        //Thread Code
        Thread thr1 = new Thread(r1);
        thr1.start();
        loadImages();
        //snd.play();
        
    }
 
    public void loadImages(){
    	
    	test = new ImageIcon(this.getClass().getResource("/images/alien.png")).getImage();
    	
    }
 
    public void mouseMoved(MouseEvent e){}
 
    public void mouseDragged(MouseEvent e){}
 
    public void mouseEntered(MouseEvent e){}
 
    public void mouseExited(MouseEvent e){}
 
    public void mouseClicked(MouseEvent e){}
 
    public void mouseReleased(MouseEvent e){}
 
    public void mousePressed(MouseEvent e){
    	
        Point mouse = new Point(e.getX(),e.getY());
        repaint();
        
    }
 
    public void paintComponent(Graphics g){
    	
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(test, 0,0, this);
        
    }
 
    Runnable r1 = new Runnable() {
    	
            public void run() {
            	
                try {
                	
                    while (!stop) {
                    	
                        timer++;//adds to the timer
 
                        Thread.sleep(10);//pauses the thread for 10ms
                        repaint();//redraws the paintComponent method
 
                    }
 
                } catch (InterruptedException iex) {}
 
            }
 
        };
 
    public void stopThread(){
    	
        stop = true;//ends thread when called
        
    }
 
    public int getRand(int low,int high){
    	
        Random r = new Random();
        return r.nextInt(high-low+1)+low;
        ]]
    }\']
 
   // public static void main(String[] args){
    	
   //     new OldMain();
        
    //}
}
*/