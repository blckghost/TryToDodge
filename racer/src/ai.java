

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ai {

	String carName;
//	final int initialX;
	int initialY;
	
	int X;
	int Y;
	double xVel;
	double yVel;
	BufferedImage image;
	int width;
	int height;
	boolean outOfTracks;
	boolean outBefore;
	
	int spacer = 2000;
	
	private static double porscheXvel = 7;
	private static double porscheYvel = 9;
	
	private static double lamborghiniXvel = 9;
	private static double lamborghiniYvel = 9;
	
	private static double ferrariXvel = 9;
	private static double ferrariYvel = 9;
	
	private static double lotusXvel = 9;
	private static double lotusYvel = 9;
	
	private static double mcLarenXvel = 9;
	private static double mcLarenYvel = 9;
	
	public ai(String carName) {
		
		try {
			this.image = ImageIO.read(new File(carName));
			this.width = image.getWidth();
			this.height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Car class crashed for being unable to locate car image ");
		}
		carName = carName.substring(0,carName.indexOf("."));
		if(carName.equals("aiporsche")) {
			this.carName = carName;
			this.xVel = (int) porscheXvel;
			this.yVel = (int) porscheYvel;	
			this.Y = -900;
			this.initialY = -900;
		}
		if(carName.equals("ailamborghini")) {
			this.carName = carName;
			this.xVel = (int) lamborghiniXvel;
			this.yVel = (int) lamborghiniYvel;
			this.Y = -1800;
			this.initialY = -1800;
		}
		if(carName.equals("aiferrari")) {
			this.carName = carName;
			this.xVel = (int) ferrariXvel;
			this.yVel = (int) ferrariYvel;
			this.Y = -4000;
			this.initialY = -4000;
		}
		if(carName.equals("ailotus")) {
			this.carName = carName;
			this.xVel = (int) lotusXvel;
			this.yVel = (int) lotusYvel;	
			this.Y = -7000;
			this.initialY = -7000;

		}
		if(carName.equals("aimcLaren")) {
			this.carName = carName;
			this.xVel = (int) mcLarenXvel;
			this.yVel = (int) mcLarenYvel;	
			this.Y = -11000;
			this.initialY = -11000;
		}
		this.X = 125;
		if(carName.equals("lotus")) {
			this.carName = carName;
			this.xVel = (int) lotusXvel;
			this.yVel = (int) lotusYvel/3;
			this.X = 365;
			this.Y = -1800;
			this.initialY = -2400;
		}
	}
	
	//setters
	public void setCarX(int x) {
		X = x;
	}
	public void setCarY(int y) {
		Y = y;
	}
	public void incrementAI() {
		Y += this.yVel;
	}

	
	
	//getters
	public int getCarX() {
		return X;
	}
	public int getCarY() {
		return Y;
	}

	public String getCarName() {
		return carName;
	}
	public BufferedImage getImage() {
		return this.image;
	}

	public Rectangle getBounds() {
		return new Rectangle(X, Y, image.getWidth(), image.getHeight());
	}
	public int spacingRandomizer() {
		return (int) (Math.random()*(800)) + (213*2); //puts a min of two car lengths between every ai sprite
	}

	
}
