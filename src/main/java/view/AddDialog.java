package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.GestionController;
import model.Cancion;
import utils.Colors;
import utils.Sizes;

public class AddDialog extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private final MyContentPane contentPanel = new MyContentPane(true);
	
	private static final GestionController gc = new GestionController();
	
	private TopBar movingBar;
	private JLabel lblTitle;
	private XButton exitButton;
	
	private InfoInput txtTitulo;
	private InfoInput txtAutor;
	private DelButton deleteSelection;
	private SelectFileButton chooseFileButton;
	private JLabel lblError;
	private MyFileChooser fc;
	
	private AcceptButton acceptButton;

	private String filePath;
	
	private final String TITLE_PLACEHOLDER = "Nombre de la cancion";
	private final String AUTOR_PLACEHOLDER = "Nombre del autor";
	
	
	/**
	 * Create the dialog.
	 */
	public AddDialog(Window parent) {
		// Done to have the same Icon as the main window
		super(parent);
		
		setResizable(false);
		setModal(true);
		setUndecorated(true);
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, Sizes.DialogWidth, Sizes.DialogHeight);
		
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		movingBar = new TopBar(this);
		movingBar.setBounds(new Rectangle(1, 1, Sizes.DialogWidth, Sizes.TopBarHeight));
		contentPanel.add(movingBar);
		
		lblTitle = new JLabel("TFGPlay");
		lblTitle.setFont(new Font("Monda", Font.BOLD | Font.ITALIC, 14));
		lblTitle.setBounds(30, 0, 70, Sizes.TopBarHeight);
		lblTitle.setForeground(Colors.NARANJA_800);
		movingBar.add(lblTitle);
		
		exitButton = new XButton("X", this, false);
		exitButton.setBounds(Sizes.DialogWidth - Sizes.TopBarHeight, 0, Sizes.TopBarHeight, Sizes.TopBarHeight);
		movingBar.add(exitButton);
		
		JLabel lblTitulo = new JLabel("TITULO");
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(156, 88, 130, 35);
		lblTitulo.setFont(new Font("Monda", Font.BOLD, 16));
		contentPanel.add(lblTitulo);
		
		txtTitulo = new InfoInput(TITLE_PLACEHOLDER);
		txtTitulo.setBounds(308, 88, 282, 35);
		txtTitulo.addKeyListener(this);
		contentPanel.add(txtTitulo);
		
		JSeparator tituloSeparator = new JSeparator();
		tituloSeparator.setForeground(new Color(255, 255, 255));
		tituloSeparator.setBounds(308, 117, 282, 2);
		contentPanel.add(tituloSeparator);
		
		JLabel lblAutor = new JLabel("AUTOR");
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor.setForeground(Color.WHITE);
		lblAutor.setBounds(156, 154, 130, 35);
		lblAutor.setFont(new Font("Monda", Font.BOLD, 16));
		contentPanel.add(lblAutor);
		
		txtAutor = new InfoInput(AUTOR_PLACEHOLDER);
		txtAutor.setBounds(308, 154, 282, 35);
		txtAutor.addKeyListener(this);
		contentPanel.add(txtAutor);
		
		JSeparator autorSeparator = new JSeparator();
		autorSeparator.setForeground(new Color(255, 255, 255));
		autorSeparator.setBounds(308, 183, 282, 2);
		contentPanel.add(autorSeparator);
		
		deleteSelection = new DelButton(null);
		deleteSelection.addActionListener(this);
		deleteSelection.setToolTipText("Eliminar archivo seleccionado");
		deleteSelection.setBounds(236, 215, 50, 35);
		contentPanel.add(deleteSelection);
		
		chooseFileButton = new SelectFileButton("Seleccionar fichero");
		chooseFileButton.setLocation(308, 215);
		chooseFileButton.setSize(282, 35);
		chooseFileButton.addActionListener(this);
		contentPanel.add(chooseFileButton);
		
		acceptButton = new AcceptButton("Añadir");
		acceptButton.setBounds(308, 297, 282, 29);
		acceptButton.setEnabled(false);
		acceptButton.addActionListener(this);
		contentPanel.add(acceptButton);
		
		lblError = new JLabel("");
		lblError.setBounds(308, 260, 282, 13);
		lblError.setFont(new Font("Monda", Font.BOLD, 12));
		contentPanel.add(lblError);

		
		fc = new MyFileChooser();
		
	}
	
	public Boolean IsDataFilled() {
		Boolean anythingMissing = (txtTitulo.getText().isBlank() || txtTitulo.getText().equals(TITLE_PLACEHOLDER))
		|| (txtAutor.getText().isBlank() || txtAutor.getText().equals(AUTOR_PLACEHOLDER) 
		|| filePath == null);
		return !anythingMissing;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (chooseFileButton == e.getSource()) {
			// got it from https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemoProject/src/components/FileChooserDemo.java
			// gets the same Icon as the parent given in params
			int returnVal = fc.showOpenDialog(this);
			
			if (returnVal == MyFileChooser.APPROVE_OPTION) {
				lblError.setText("");
                File file = fc.getSelectedFile();
                filePath = file.getAbsolutePath();
                // Tantos //: https://chatgpt.com/share/dad38cd0-44d9-4e2b-9612-351dbc5d9fc6
                String fileName = file.getName().split("\\.")[0];
                chooseFileButton.setText("Seleccionado: " + fileName);
                
                if (IsDataFilled()) {
        			acceptButton.setEnabled(true);
        		} else {
        			acceptButton.setEnabled(false);
        		}
			}
		} else if (deleteSelection == e.getSource()) {
			filePath = null;
			acceptButton.setEnabled(false);
			chooseFileButton.setText("Seleccionar fichero");
		} else if (acceptButton == e.getSource()) {
			
			String titulo = txtTitulo.getText();
			String autor = txtAutor.getText();
			Cancion song = new Cancion(0, titulo, autor, filePath, Cancion.getDuration(new File(filePath)));
			
			Boolean añadido = gc.add(song);
			VentanaPrincipal.fillList();
			VentanaPrincipal.getLista().revalidate();
			VentanaPrincipal.getLista().repaint();
			
			filePath = null;
			this.txtTitulo.setPlaceholder();
			this.txtAutor.setPlaceholder();
			chooseFileButton.setText("Seleccionar fichero");
			acceptButton.setEnabled(false);
			
			if (añadido) {
				lblError.setForeground(Color.GREEN);
				lblError.setText("Añadida correctamente");
			} else {
				lblError.setForeground(Color.RED);
				lblError.setText("Esta canción ya esta registrada");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if (txtTitulo == e.getSource() ||
			txtAutor == e.getSource()) {
			if (IsDataFilled()) {
				acceptButton.setEnabled(true);
			} else {
				acceptButton.setEnabled(false);
			}
		}
	}
}
