package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import utils.Colors;

public class ListaMusica extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static ArrayList<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
	
	public ListaMusica() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public static ArrayList<Tarjeta> getTarjetas() {
		return tarjetas;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
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
