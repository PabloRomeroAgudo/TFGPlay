package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import utils.Colors;
import utils.Sizes;

public class SelectFileButton extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SelectFileButton(String text) {
		this.setText(text);
		this.setBorder(null);
		this.setFocusPainted(false);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setPreferredSize(new Dimension(Sizes.TopBarHeight, Sizes.TopBarHeight));
		this.setOpaque(true);
		this.setBackground(Colors.BLANCO);
		this.setForeground(Colors.NARANJA_800);
		this.addMouseListener(this);
	
	}

	public void mouseClicked(MouseEvent e) {}


	public void mousePressed(MouseEvent e) {}


	public void mouseReleased(MouseEvent e) {}


	public void mouseEntered(MouseEvent e) {
		this.setOpaque(true);
		this.setBackground(Colors.NARANJA_500);
		this.setForeground(Color.WHITE);
	}


	public void mouseExited(MouseEvent e) {
		this.setBackground(Colors.BLANCO);
		this.setForeground(Colors.NARANJA_800);
	}
}
