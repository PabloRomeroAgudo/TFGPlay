package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import utils.Iconos;

public class PausePlayButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private boolean isPause = true;
	
	private Clip clip;

	public PausePlayButton() {
		this.setIcon(new ImageIcon(Iconos.PlayIcon));
		this.addActionListener(this);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setEnabled(false);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void setPause(boolean valor) {
		isPause = valor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setPausePlay();
	}
	
	public void setPausePlay() {
		clip = Tarjeta.getClip();
		isPause = !isPause;
		
		if (isPause) {
			this.setIcon(new ImageIcon(Iconos.PlayIcon));
			clip.stop();
			BottomBar.getProgressBar().stopProgressBar();
		} else {
			this.setIcon(new ImageIcon(Iconos.PauseIcon));
			clip.start();
			BottomBar.getProgressBar().startProgressBar();
		}
	}
}
