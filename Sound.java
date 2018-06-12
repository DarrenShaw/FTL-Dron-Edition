package gameplay;

import java.util.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
 import javax.sound.sampled.Clip;

@SuppressWarnings("unused")
public class Sound{

    public Sound(){

    }

    public void play(){
    	
    	     try{
    	     AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/audio/hola.wav"));
    	     Clip clip = AudioSystem.getClip();
    	     clip.open(audioInputStream);
    	     clip.start( );
    	    }
    	   catch(Exception ex)
    	   {  }
  }
    
    public void playExplosion(){
    	
	     try{
	     AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/audio/explosion.wav"));
	     Clip clip = AudioSystem.getClip();
	     clip.open(audioInputStream);
	     clip.start();
	     
	    
	     
	    }
	   catch(Exception ex)
	   {  }
} 
    
    public void playMain(boolean b){
    	
	     try{
	     AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/audio/maintheme.wav"));
	     Clip clip = AudioSystem.getClip();
	     clip.open(audioInputStream);
	     clip.loop(100);
	     if(b){
	    	 clip.stop();
	     }
	    }
	   catch(Exception ex)
	   {  }
} 
    
    
    
}

