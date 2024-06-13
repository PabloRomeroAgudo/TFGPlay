package view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFileChooser extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyFileChooser() {
		super();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Archivo wav (*.wav)", "wav");
		
		this.setDialogTitle("Elige la cancion a añadir");
		this.setFileFilter(filter);
		this.setCurrentDirectory(new File("D:/V/DAM/TFG/Musica/wav"));
		this.setAcceptAllFileFilterUsed(false);
	}
}
