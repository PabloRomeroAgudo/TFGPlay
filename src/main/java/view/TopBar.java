package view;

import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;
import utils.Colors;
import utils.Sizes;

public class TopBar extends JPanel implements MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	Window w;
	@Getter
	@Setter
	private int xMouse;
	@Getter
	@Setter
	private int yMouse;
	
	public TopBar(Window w) {
		this.w = w;
		this.setBounds(new Rectangle(1, 1, Sizes.TopBarWidth, Sizes.TopBarHeight));
		this.setBackground(Colors.BLANCO);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		setLayout(null);
	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();
	}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		w.setLocation(x - xMouse, y - yMouse);
	}

	public void mouseMoved(MouseEvent e) {}
}
