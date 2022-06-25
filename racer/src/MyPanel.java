import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	
	int fps = 60;
	int panelWidth = 592;
    int panelHeight = 990;
    int score = 0;

    BufferedImage track;
    BufferedImage mark;
    BufferedImage mainMenu;
    BufferedImage settingsMenu;
    ImageIcon endingScreen;
    
    Thread thread1;
    //Mouse & KeyListener
    KeyInput keyIn = new KeyInput();
    MouseIn mouseIn = new MouseIn();
    
    //sound object - stores car sounds 
    Sound soundtrack = new Sound();
    
    //car objects 
    Car porsche = new Car("porsche.png");
    Car ferrari = new Car("ferrari.png");
    Car lamborghini = new Car("lamborghini.png");
    
    ai lambo = new ai("ailamborghini.png");
    ai pors = new ai("aiporsche.png");
    ai lotus = new ai("ailotus.png");
    ai mcLar = new ai("aimcLaren.png");
    ai ferra = new ai("aiferrari.png");
    ai rightLotus = new ai("lotus.png");
  
    
    Car genericCar = porsche; 
    
    int trackPosx = 0;
    int trackPosy = 0;
    
    int trackVelx;
    int trackVely = -6;
    

    
    //game states
    boolean outOfTracks = false;
    boolean brakeState = false;
    boolean hornState = false;
    boolean start = false;
    boolean settings = false;
    boolean tutorial = false;
    boolean settingsInterface = false;
    boolean tutorialInterface = false;
    boolean gameOver = false;
    boolean playSound = true;
    boolean loopEngine = true;


    //methods for selecting, then playing/stopping/looping audio files from the Sound class by given index where audio files are stored in a File array
    public void playAudio(int audioSelection)  {
    	soundtrack.setAudioClip(audioSelection);
    	soundtrack.play();
    }
    public void stopAudio(int audioSelection) {
    	soundtrack.setAudioClip(audioSelection);
    	soundtrack.stop();
    }
    public void playEffect(int audioSelection) {
    	soundtrack.setAudioClip(audioSelection);
    	soundtrack.play();
    }
    
    
    MyPanel(){
    	this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    	this.setBackground(Color.black);
    	this.setDoubleBuffered(true);
    	this.addKeyListener(keyIn);
    	this.setFocusable(true);
    	this.addMouseListener(mouseIn);
    	
    	try {
			track = ImageIO.read(new File("track.png"));
			mainMenu = ImageIO.read(new File("mainMenu.png"));
			settingsMenu = ImageIO.read(new File("settings.png"));
		    endingScreen = new ImageIcon("gameOverStatic.png");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void startThread1() {
    	thread1 = new Thread(this);
    	thread1.start();
    }
   
    
    public void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);

    	if(start == false && gameOver == false) {
    		
    		//main menu screen
    		g.drawImage(mainMenu, 0, 0, this);
	        Font myFont = new Font ("Courier New", 1, 10);
	        g.setFont(myFont);
			g.setColor(Color.white);
	        g.drawString("   About: Game by Yusuf Gokce -  Sound Design: SmartSoundFX -  Images by: Michal Kalinowski ",0,panelHeight-20);    		


    		
    		// play button
    		if(mouseIn.x > 181 && mouseIn.x < 431 && mouseIn.y > 350 && mouseIn.y < 421 && settingsInterface == false ) {
    			start = true;
    		}
    		// settings button
    		if(mouseIn.x > 181 && mouseIn.x < 431 && mouseIn.y > 448 && mouseIn.y < 516 || settingsInterface == true) {
    			settingsInterface = true;
    			settings = true;
    			
    			g.drawImage(settingsMenu, 0, 0, this);
    			
    			//car selection area for lamborghini
    			if(mouseIn.x > 140 && mouseIn.x < 215 && mouseIn.y > 335 && mouseIn.y < 520){
    				genericCar = lamborghini;
    				start = true;

    			}
    			//car selection area for porsche
    			if(mouseIn.x > 380 && mouseIn.x < 458 && mouseIn.y > 335 && mouseIn.y < 520){
    				genericCar = porsche;
    				start = true;

    			}
    			//car selection area for ferrari
    			if(mouseIn.x > 254 && mouseIn.x < 334 && mouseIn.y > 647 && mouseIn.y < 830)
    			{
    				genericCar = ferrari;
    				start = true;
    			}

    		}
    		// tutorial button
    		// plays a gif of the gameplay
    		if(mouseIn.x > 181 && mouseIn.x < 431 && mouseIn.y > 547 && mouseIn.y < 617) {
    			tutorial = true;
    			ImageIcon icon = new ImageIcon("tutorial.gif");
    			Graphics2D g2 = (Graphics2D)g;
    			g2.scale(3,3);
				g.drawImage(icon.getImage(), -200,0, this);
		        g.dispose();
    		}
    	} 

    	
    	else if(gameOver == false){
    		//play state is executed under this conditional statement:
    		//track, user controlled car and ai's are redrawn in this block.
    		//engine sound is looped 
    		//userControlled car draws different images based on the state of the car, if the car is under braking, the brake lights are turned on 
	    	super.paintComponent(g);
	    	Graphics2D g2 = (Graphics2D)g;
	    	
	    	g2.drawImage(track, trackPosx, trackPosy, this); //drawing the track first so that it doesn't get affected by the scaling
	    	//add second image here
	    	
	    	
	    	g2.drawImage(track, trackPosx, trackPosy + panelHeight, this);
	    	g2.drawImage(track, trackPosx, trackPosy - panelHeight, this);
	    	
	    	
	    	if(loopEngine == true) {
	    		soundtrack.setAudioClip(3);
	    		soundtrack.loop();
	    		loopEngine = false;
	    	}
	    	
	        if(brakeState == false) {
	            g2.drawImage(genericCar.image, genericCar.X, genericCar.Y, null);
	        }
	        if(brakeState == true) {
	            g2.drawImage(genericCar.brakingImage, genericCar.X, genericCar.Y, null);
	        }
//	        g.drawImage(pors.image, pors.X, pors.Y, null);
//	        g.drawImage(ferra.image, ferra.X, ferra.Y, null);
	        g.drawImage(lotus.image, lotus.X, lotus.Y, null);
	        g.drawImage(lambo.image, lambo.X, lambo.Y, null);
	        g.drawImage(mcLar.image, mcLar.X, mcLar.Y, null);
	        g.drawImage(rightLotus.image, rightLotus.X, rightLotus.Y, null);
	        
	        Font myFont = new Font ("Courier New", 1, 35);
	        g.setFont(myFont);
			g.setColor(Color.white);
			
	        g.drawString(Integer.toString(score),125,40);
	        
	    	

    	}
    	else { 		
    		
    		//if a collision occurs, ending screen and final score is displayed.
       		g.drawImage(endingScreen.getImage(), 0,0, this);
	        Font myFont = new Font ("Courier New", 1, 40);
	        g.setFont(myFont);
			g.setColor(Color.white);
	        g.drawString(Integer.toString(score),260,645);
	        
	        //plays a crashing and alarm sound
	        if(playSound == true) {
	        	soundtrack.setAudioClip(1);
	        	soundtrack.play();
	        	soundtrack.setAudioClip(2);
	        	soundtrack.play();
	        	playSound = false;
	        }
    	}
   		g.dispose();    	
    }

    public void update() {
		brakeState = false;
		
		//collision check between ai and user controller cars
    	if(start == true) {
    		if(pors.getBounds().intersects(genericCar.getBounds())) {
    			gameOver = true;
    			start = false;
    		}
    		if(lambo.getBounds().intersects(genericCar.getBounds())) {
    			gameOver = true;
    			start = false;
    		}
    		if(lotus.getBounds().intersects(genericCar.getBounds())) {
    			gameOver = true;
    			start = false;
    		}
    		if(ferra.getBounds().intersects(genericCar.getBounds())) {
    			gameOver = true;
    			start = false;
    		}
    		if(mcLar.getBounds().intersects(genericCar.getBounds())) {
    			gameOver = true;
    			start = false;
    		}
    		if(rightLotus.getBounds().intersects(genericCar.getBounds())) {
    			gameOver = true;
    			start = false;
    		}
    		
    		
//    		some ai is not implemented due to overlapping
//	    	pors.incrementAI();
//    		ferra.incrementAI();
    		mcLar.incrementAI();
    		lotus.incrementAI();
    		lambo.incrementAI();
    		rightLotus.incrementAI();
    		
    		//updating the trackLocation
    		trackPosy -= trackVely;
    		if(trackPosy < -panelHeight) {
    			trackPosy = 0;			
    		}
    		else if(trackPosy > panelHeight) {
    			trackPosy = 0;			
    		}
    		

    		//Commands below update the car's location based on keyIn (keyListener in KeyInput class), and increments the location of the car accordingly.
    		
    		//up + left
    		if(keyIn.up == true && keyIn.left == true) {
    			genericCar.Y -= genericCar.yVel;
    			genericCar.X -= genericCar.xVel;
    			carBoundaryY();
    			carBoundaryX();
    		}
    		
    		//up + right
    		if(keyIn.up == true && keyIn.right == true) {
    			
    			genericCar.Y -= genericCar.yVel;
    			genericCar.X += genericCar.xVel;
    			
    			carBoundaryY();
    			carBoundaryX();
    		}
    		
    		//down + left
    		if(keyIn.down == true && keyIn.left == true) {
    			genericCar.Y += genericCar.yVel/2;
    			genericCar.X -= genericCar.xVel/3;
    			
    			
    			brakeState = true;
    			carBoundaryY();
    			carBoundaryX();
    		}
    		
    		//down + right
    		if(keyIn.down == true && keyIn.right == true) {
    			
    			genericCar.Y += genericCar.yVel/2;
    			genericCar.X += genericCar.xVel/3;
    			brakeState = true;
    			carBoundaryY();
    			carBoundaryX();
    		}
    		
    		if(keyIn.up == true) {
    			genericCar.Y -= 2*genericCar.yVel;
    			carBoundaryY();
    		}
    		else if(keyIn.down == true) {
    			genericCar.Y += 1.5*genericCar.yVel;
    			brakeState = true;
    			carBoundaryY();
    		}
    		else if(keyIn.right == true) {
    			genericCar.X += genericCar.xVel;
    			carBoundaryX();
    		}    	
    		else if(keyIn.left == true) {
    			genericCar.X -= genericCar.xVel;
    			carBoundaryX();	
    		}
    		else if(keyIn.boost == true) {
    			genericCar.Y -= 3*genericCar.yVel;
    		}
    		genericCar.Y += genericCar.yVel; //cruse speed
    		
    		carBoundaryY();
    		carBoundaryX();
    		aiBoundaryY();
    	}
    	

    	
    }
    
    public void aiBoundaryY() { //checks the boundaries for ai, if an ai car leaves the panel, they return to their original y position
    	
    	//spacing randomizer was intended to create random spaces between cars, however overlaps are not checked.
    	//the randomizer works but the spacing between the cars is not checked for overlapping when randomized, so randomizing is not used.
//    	if(pors.Y>panelHeight) {
//    		pors.Y = pors.initialY-pors.spacingRandomizer();
//    	}
//    	if(lambo.Y>panelHeight) {
//    		lambo.Y = lambo.initialY-pors.spacingRandomizer();
//    	}
//    	if(lotus.Y>panelHeight) {
//    		lotus.Y = lotus.initialY-pors.spacingRandomizer();
//    	}
//    	if(mcLar.Y>panelHeight) {
//    		mcLar.Y = mcLar.initialY-pors.spacingRandomizer();
//    	}
//    	if(ferra.Y>panelHeight) {
//    		ferra.Y = ferra.initialY-pors.spacingRandomizer();
//    	}
    	
    	
    	//Resets the ai cars' location to their initial position
    	if(pors.Y>panelHeight) {
    		pors.Y = pors.initialY;
    	}
    	if(lambo.Y>panelHeight) {
    		lambo.Y = lambo.initialY;
    	}
    	if(lotus.Y>panelHeight) {
    		lotus.Y = lotus.initialY;
    	}
    	if(mcLar.Y>panelHeight) {
    		mcLar.Y = mcLar.initialY;
    	}
    	if(ferra.Y>panelHeight) {
    		ferra.Y = ferra.initialY;
    	}
    	if(rightLotus.Y>panelHeight) {
    		rightLotus.Y = rightLotus.initialY;
    	}
    	
    	
    }
    public void carBoundaryY() { //checks the boundaries of the user controlled car(s), ensuring that they exit the frame
    	  
    	if(genericCar.Y <0) {
    		genericCar.Y = 0;
    	}
    	if(genericCar.Y>panelHeight-genericCar.height) { 
    		genericCar.Y = panelHeight - genericCar.height;
    	}
    }
    public void carBoundaryX() { //checks the boundaries of the user controlled car(s), ensuring that they exit the frame
  
    	if(genericCar.X < 0) {
    		genericCar.X = 0;
    	}
    	if((genericCar.X > panelWidth-genericCar.width)) {
    		genericCar.X = panelWidth-genericCar.width;
    	}
    	
    }


	// Threading Method
	public void run()
	{
		double drawInterval = 1000000000/fps;
		double nextDrawTime = System.nanoTime() + drawInterval;
		while(thread1 != null) {


			//updating the location of the car + AIs and the background 
			update();
			repaint(); 	
			hornState = false;
			if(start ==true) { //high score counter
				score++;				
			}
			try
			{	
				double remainingTime = nextDrawTime	- System.nanoTime();
				remainingTime/=1000000;
				
				if(remainingTime<0) {
					remainingTime = 0;
				}
				Thread.sleep((long)remainingTime); 
				nextDrawTime += drawInterval;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

    
    
}