package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import utils.Colors;

public class InfoInput extends JTextField implements FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String placeholder;
	
	public InfoInput(String placeholder) {
		this.placeholder = placeholder;
		
		this.setFont(new Font("Monda", Font.BOLD, 16));
		this.setBorder(new EmptyBorder(3, 3, 3, 3));
		this.setOpaque(false);
		this.setPlaceholder();
		this.setCaretColor(Colors.BLANCO);
		this.setCaret(getCaret());
		
		this.addFocusListener(this);
	}
	
	public void setPlaceholder() {
		this.setForeground(new Color(1f, 1f, 1f, 0.5f));
		this.setText(placeholder);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (this.getText().equals(this.placeholder)) {
			this.setText("");
			this.setForeground(Colors.BLANCO);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (this.getText().isEmpty()) {
			this.setPlaceholder();
		}
	}

}
