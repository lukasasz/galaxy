package Train;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Horn {
	
	private final static String horn_file = "";
	
	public static void soundHorn(int duration) throws FileNotFoundException {
		if (duration != 0) {/*
			try {
	        	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(horn_file));
	        	Clip clip = AudioSystem.getClip();
	        	clip.open(audioInputStream);
	        	clip.start();
	        	clip.loop(Clip.LOOP_CONTINUOUSLY);
	        	for (int i = 0; i < duration; i++) {
	        		TimeUnit.MINUTES.sleep(1);
	        	}
	        	clip.stop();
			} catch (Exception ex) {
	        	ex.printStackTrace();
	    	}*/
		}
	}
}
