import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Car {

	String carName;
	int X;
	int Y;
	double xVel;
	double yVel;
	int carAcc;
	BufferedImage image;
	BufferedImage brakingImage;
	boolean brakeState;
	int width;
	int height;
	boolean outOfTracks;
	boolean outBefore;
	

	
	private static double porscheXvel = 7;
	private static double porscheYvel = 2;
	
	private static double lamborghiniXvel = 7;
	private static double lamborghiniYvel = 2;
	
	private static double ferrariXvel = 7;
	private static double ferrariYvel = 2;
	
	
	
	public Car(String carName) {
		
		try {
			this.brakingImage = ImageIO.read(new File("braking" + carName)); 
			this.image = ImageIO.read(new File(carName));
			this.width = image.getWidth();
			this.height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Car class crashed for being unable to locate car image ");
		}
		carName = carName.substring(0,carName.indexOf("."));
		if(carName.equals("porsche")) {
			this.carName = carName;
			this.xVel = (int) porscheXvel;
			this.yVel = (int) porscheYvel;	
		}
		if(carName.equals("lamborghini")) {
			this.carName = carName;
			this.xVel = (int) lamborghiniXvel;
			this.yVel = (int) lamborghiniYvel;	
		}
		if(carName.equals("ferrari")) {
			this.carName = carName;
			this.xVel = (int) ferrariXvel;
			this.yVel = (int) ferrariYvel;
		}
		this.brakeState = false;
		this.X = 365;
		this.Y = 980-image.getHeight();
		this.outOfTracks = false;
		this.outBefore = false;

		}
	
	//setters
	public void setCarX(int x) {
		X = x;
	}
	public void setCarY(int y) {
		Y = y;
	}
	public void setCarAcc(int a) {
		carAcc = a;
	}
	public void setBrakeState(boolean b) {
		brakeState = b;
	}
	
	
	//getters
	public int getCarX() {
		return X;
	}
	public int getCarY() {
		return Y;
	}
	public int getCarAcc() {
		return carAcc;
	}
	public String getCarName() {
		return carName;
	}
	public BufferedImage getImage() {
		return this.image;
	}
	public boolean getBrakeState() {
		return this.brakeState;
	}
	public Rectangle getBounds() {
		return new Rectangle(X, Y, image.getWidth(), image.getHeight());
	}

	
}
