package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import utils.Colors;

public class MyScrollBarUI extends BasicScrollBarUI {
	
	@Override
	protected void configureScrollBarColors() {
		this.thumbColor = Colors.NARANJA_950;
		this.thumbHighlightColor = Colors.NARANJA_950;  // Color de alrededor izda
		this.thumbLightShadowColor = Colors.NARANJA_950; // Color de alrededor dcha
		
		this.trackColor = Colors.NARANJA_800; // Color del fondo del track (detras del deslizador)
	}
				
	@Override
	protected JButton createDecreaseButton(int orientation) {
		JButton button = new JButton("zero button");
	    Dimension zeroDim = new Dimension(0,0);
	    button.setPreferredSize(zeroDim);
	    button.setMinimumSize(zeroDim);
	    button.setMaximumSize(zeroDim);
	    return button;
    }
	
	@Override
	protected JButton createIncreaseButton(int orientation) {
		JButton button = new JButton("zero button");
	    Dimension zeroDim = new Dimension(0,0);
	    button.setPreferredSize(zeroDim);
	    button.setMinimumSize(zeroDim);
	    button.setMaximumSize(zeroDim);
	    return button;
    }
}
