package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.GestionController;
import lombok.Getter;
import lombok.Setter;
import model.Cancion;
import utils.Colors;
import utils.Iconos;
import utils.Sizes;

public class Tarjeta extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private static Tarjeta tarjetaAnterior;
	
	@Setter
	private static Clip clip = null;
	
	private Cancion c;
	
	@Getter
	private JLabel lblOrdenacion;
	@Getter
	private JLabel lblTitulo;
	@Getter
	private JLabel lblAutor;
	@Getter
	private JLabel lblDuracion;
	private DelButton btnBorrar;

	public Tarjeta(String ordenacion, String titulo, String autor, String duracion, Cancion c) {
		this.c = c;
		
		setPreferredSize(new Dimension(Sizes.CardWidth, Sizes.CardHeight));
		//setBounds(1, 0, Sizes.TopBarWidth, Sizes.CardHeight);
		setBackground(new Color(192, 192, 192));
		setLayout(null);
		setBorder(null);
		addMouseListener(this);
		
		lblOrdenacion = new JLabel(ordenacion);
		lblOrdenacion.setFont(new Font("Monda", Font.BOLD, 12));
		lblOrdenacion.setOpaque(false);
		lblOrdenacion.setForeground(Colors.BLANCO);
		lblOrdenacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdenacion.setBounds(10, 1, 46, Sizes.CardLabelHeight);
		add(lblOrdenacion);
		
		lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Monda", Font.BOLD, 12));
		lblTitulo.setOpaque(false);
		lblTitulo.setForeground(Colors.BLANCO);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setBounds(66, 1, 444, Sizes.CardLabelHeight);
		add(lblTitulo);
		
		lblAutor = new JLabel(autor);
		lblAutor.setFont(new Font("Monda", Font.BOLD, 12));
		lblAutor.setOpaque(false);
		lblAutor.setForeground(Colors.BLANCO);
		lblAutor.setHorizontalAlignment(SwingConstants.LEFT);
		lblAutor.setBounds(520, 1, 164, Sizes.CardLabelHeight);
		add(lblAutor);
		
		lblDuracion = new JLabel(duracion);
		lblDuracion.setFont(new Font("Monda", Font.BOLD | Font.ITALIC, 12));
		lblDuracion.setOpaque(false);
		lblDuracion.setForeground(Colors.BLANCO);
		lblDuracion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDuracion.setBounds(779, 1, 51, Sizes.CardLabelHeight);
		add(lblDuracion);
		
		btnBorrar = new DelButton(this);
		btnBorrar.setBounds(931, 11, 50, 28);
		add(btnBorrar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Colors.BLANCO);
		separator.setBackground(Colors.BLANCO);
		separator.setBounds(10, 49, 1020, 1);
		add(separator);
	}
	
	public String getTitulo() {
		return  this.lblTitulo.getText();
	}
	
	public Cancion getC() {
		return this.c;
	}
	
	public static Clip getClip() {
		return clip;
	}
	
	public static Tarjeta getTarjetaAnterior() {
		return tarjetaAnterior;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		playCurrentSong();
	}
	
	public void setToCurrent() {
		this.getLblOrdenacion().setForeground(Colors.NARANJA_500);
		this.getLblTitulo().setForeground(Colors.NARANJA_500);
		this.getLblAutor().setForeground(Colors.NARANJA_500);
		this.getLblDuracion().setForeground(Colors.NARANJA_500);
		tarjetaAnterior = this;
	}

	public void playCurrentSong() {
		if (tarjetaAnterior != this) {
			// Reset the previous song
			if (tarjetaAnterior != null) {
				tarjetaAnterior.getLblOrdenacion().setForeground(Colors.BLANCO);
				tarjetaAnterior.getLblTitulo().setForeground(Colors.BLANCO);
				tarjetaAnterior.getLblAutor().setForeground(Colors.BLANCO);
				tarjetaAnterior.getLblDuracion().setForeground(Colors.BLANCO);
			}
			setToCurrent();
		}
		
		BottomBar.getPausePlayButton().setPause(false);
		BottomBar.getPausePlayButton().setIcon(new ImageIcon(Iconos.PauseIcon));
		BottomBar.getPausePlayButton().setEnabled(true);
		BottomBar.getNextButton().setEnabled(true);
		BottomBar.getPreviousButton().setEnabled(true);
		BottomBar.getProgressBar().setMaximum();
		BottomBar.getProgressBar().startProgressBar();
		
		Integer ordenacion = Integer.valueOf(this.lblOrdenacion.getText()) - 1;
		Cancion c1 = GestionController.getCanciones().get(ordenacion);
		
		File file = new File(c1.getRuta());
		
		AudioInputStream audioStream;
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
			if (clip != null && clip.isRunning()) {
				clip.stop();
				clip.setMicrosecondPosition(0);
			}
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e1) {
			e1.printStackTrace();
		}
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

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
