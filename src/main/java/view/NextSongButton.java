package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import utils.Iconos;

public class NextSongButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	public NextSongButton() {
		this.setIcon(new ImageIcon(Iconos.NextIcon));
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.addActionListener(this);
		this.setEnabled(false);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		playNextSong();
	}

	public static void playNextSong() {
		Integer ordenacion = Integer.valueOf(Tarjeta.getTarjetaAnterior().getLblOrdenacion().getText());
		Tarjeta tarjetaSiguiente;
		try {
			tarjetaSiguiente = ListaMusica.getTarjetas().get(ordenacion);
		} catch (IndexOutOfBoundsException e1) {
			tarjetaSiguiente = ListaMusica.getTarjetas().get(0);
		}

		tarjetaSiguiente.playCurrentSong();
	}
}
