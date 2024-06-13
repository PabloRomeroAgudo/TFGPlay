package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import utils.Colors;

public class MyContentPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Boolean wantBackground;
	
	public MyContentPane(Boolean wantBackground) {
		this.wantBackground = wantBackground;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.paintComponent(g);
		if (wantBackground) {			
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
}
