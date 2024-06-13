package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import lombok.Getter;
import utils.Colors;
import utils.Sizes;
import java.awt.Font;

public class BottomBar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static PausePlayButton pausePlayButton;
	private static NextSongButton nextButton;
	private static PreviousSongButton previousButton;
	private static JLabel lblProgreso;
	private static MyProgressBar progressBar;
	@Getter
	private static JLabel lblDuracion;
	
	public BottomBar() {
		this.setBounds(1, 453, Sizes.BottomBarWidth, Sizes.BottomBarHeight);
		this.setBackground(Colors.NARANJA_600);
		setLayout(null);
		
		pausePlayButton = new PausePlayButton();
		pausePlayButton.setBounds(513, 10, 30, 30);
		this.add(pausePlayButton);
		
		nextButton = new NextSongButton();
		nextButton.setBounds(563, 10, 30, 30);
		this.add(nextButton);
		
		previousButton = new PreviousSongButton();
		previousButton.setBounds(463, 10, 30, 30);
		this.add(previousButton);
		
		progressBar = new MyProgressBar();
		progressBar.setBounds(128, 50, Sizes.ProgressBarWidth, Sizes.ProgressBarHeight);
		this.add(progressBar);
		
		lblProgreso = new JLabel();
		lblProgreso.setFont(new Font("Monda", Font.BOLD, 10));
		lblProgreso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProgreso.setForeground(Colors.BLANCO);
		lblProgreso.setBounds(71, 47, 45, 10);
		add(lblProgreso);
		
		lblDuracion = new JLabel();
		lblDuracion.setFont(new Font("Monda", Font.BOLD, 10));
		lblDuracion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDuracion.setForeground(Colors.BLANCO);
		lblDuracion.setBounds(940, 47, 45, 10);
		add(lblDuracion);
	}

	public static PausePlayButton getPausePlayButton() {
		return pausePlayButton;
	}
	
	public static NextSongButton getNextButton() {
		return nextButton;
	}

	public static PreviousSongButton getPreviousButton() {
		return previousButton;
	}

	public static JLabel getLblProgreso() {
		return lblProgreso;
	}

	public static MyProgressBar getProgressBar() {
		return progressBar;
	}

	// Put all the bottomBar to the beginning (no song selected)
	public void init() {
		nextButton.setEnabled(false);
		previousButton.setEnabled(false);
		pausePlayButton.setEnabled(false);
		// SetPausePlay method will always cause it to play cos this method is only called when deleting the current song
		pausePlayButton.setPausePlay();
		
		lblProgreso.setText("");
		lblDuracion.setText("");
		progressBar.init();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        Color color1 = Colors.NEGRO;
        Color color2 = Colors.NARANJA_800;
        GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
	}
}
