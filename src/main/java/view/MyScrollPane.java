package view;

import javax.swing.JScrollPane;

import utils.Sizes;

public class MyScrollPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public MyScrollPane() {
		this.setBorder(null);
		this.setBounds(1, 36, Sizes.ScrollPaneWidth, Sizes.ListHeight);
		this.getVerticalScrollBar().setUI(new MyScrollBarUI());
	}
}
