package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import utils.Iconos;

public class PreviousSongButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	public PreviousSongButton() {
		this.setIcon(new ImageIcon(Iconos.PreviousIcon));
		this.addActionListener(this);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setEnabled(false);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Integer ordenacion = Integer.valueOf(Tarjeta.getTarjetaAnterior().getLblOrdenacion().getText());
		Tarjeta tarjetaAnterior;
		try {
			tarjetaAnterior = ListaMusica.getTarjetas().get(ordenacion - 2);
		} catch (IndexOutOfBoundsException e1) {
			tarjetaAnterior = ListaMusica.getTarjetas().get(ListaMusica.getTarjetas().size() - 1);
		}

		tarjetaAnterior.playCurrentSong();
	}
}
