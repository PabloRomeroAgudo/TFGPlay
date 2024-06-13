package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.GestionController;
import utils.Iconos;

public class DelButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Tarjeta padre;
	private static final GestionController gc = new GestionController();

	public DelButton(Tarjeta padre) {
		this.padre = padre;
		this.setOpaque(false);
		this.setIcon(new ImageIcon(Iconos.DeleteIcon));
		this.addActionListener(this);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setBorderPainted(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.padre != null) {
			if (Tarjeta.getClip() != null && Tarjeta.getClip().isRunning() && Tarjeta.getTarjetaAnterior() == this.padre) {
				Tarjeta.getClip().stop();
				VentanaPrincipal.getBottomBar().init();
				Tarjeta.setClip(null);
			}
			gc.delete(padre.getC().getId());
			VentanaPrincipal.fillList();
			VentanaPrincipal.getLista().revalidate();
			VentanaPrincipal.getLista().repaint();
		}
	}
	
}
