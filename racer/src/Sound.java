import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Sound {
	
	Clip audio;
	File[] trackList = new File[4];
	
	public Sound() {
		try {
			File horn = new File("shortHorn.wav");
			File crash = new File("crashSound.wav");
			File alarm = new File ("alarm.wav");
			File engine = new File ("engine.wav");
			trackList[0] = horn;
			trackList[1] = crash; 
			trackList[2] = alarm;
			trackList[3] = engine;			
		}  catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void setAudioClip(int i) {
		try {
			AudioInputStream in = AudioSystem.getAudioInputStream(trackList[i]);
			audio = AudioSystem.getClip();
			audio.open(in);
		} catch(Exception e) {
			e.printStackTrace();		
		}
	}
	public void play() {
		
		boolean run = true;
		while(!audio.isRunning()) {
			if(run == true) {
				audio.start();
				run = false;
			}
		}
	
	
		if(!audio.isRunning()) {
			audio.start();

		}
		
	}
	public void stop() {
		audio.stop();
	}
	public void loop() {
		audio.loop(Clip.LOOP_CONTINUOUSLY);
	}

}
