import java.awt.event.*;

public class MouseIn implements MouseListener {


	int x;
	int y;

	@Override
	public void mouseClicked(MouseEvent e) {		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

}
