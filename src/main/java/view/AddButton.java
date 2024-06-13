package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import lombok.Getter;
import lombok.Setter;
import utils.Colors;
import utils.Sizes;

public class AddButton extends JButton implements ActionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Window w;
	
	public AddButton(String text, Window w) {
		this.w = w;
		this.setText(text);
		this.setBorder(null);
		this.setFocusPainted(false);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setPreferredSize(new Dimension(Sizes.TopBarHeight, Sizes.TopBarHeight));
		this.setOpaque(true);
		this.setBackground(Colors.BLANCO);
		this.setForeground(Colors.NARANJA_800);
		this.addActionListener(this);
		this.addMouseListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		AddDialog frame = new AddDialog(this.w);
		frame.setLocationRelativeTo(this.w);
		frame.setVisible(true);
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
