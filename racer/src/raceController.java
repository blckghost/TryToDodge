import javax.swing.JFrame;



/*
 * Yusuf Gokce 
 * Ms.Wong - ICS4U - Final project
 * This program is a retro 2D mini game where the objective is the dodge cars that are blocking your or the oncoming traffic lane. 
 * 
 */



public class raceController {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("TryToDodge");
		
		MyPanel gamePanel = new MyPanel();
		frame.add(gamePanel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		gamePanel.startThread1();

	}

}
