import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
		boolean up, down, left, right, boost, horn;
	@Override
	public void keyPressed(KeyEvent e) {
		int character = e.getKeyCode();
		if(character == KeyEvent.VK_W || character == 38) {
			up = true;
		}
		if(character == KeyEvent.VK_S || character == 40) {
			down = true;			
		}
		if(character == KeyEvent.VK_A || character == 37) {
			left = true;
		}
		if(character == KeyEvent.VK_D || character == 39) {
			right = true;
		}
		if(character == KeyEvent.VK_SPACE) {
			boost = true;
		}
		if(character == 72) {
			horn = true;
		}
	}
	public void keyReleased(KeyEvent e) {
		int character = e.getKeyCode();
		if(character == 72) {
			horn = false;
		}
		if(character == KeyEvent.VK_W || character == 38) {
			up = false;
		}
		if(character == KeyEvent.VK_S || character == 40) {
			down = false;			
		}
		if(character == KeyEvent.VK_A || character == 37) {
			left = false;
		}
		if(character == KeyEvent.VK_D || character == 39) {
			right = false;
		}
		if(character == KeyEvent.VK_SPACE) {
			boost = false;
		}
	}
	public void keyTyped(KeyEvent e) {	
	}

}
