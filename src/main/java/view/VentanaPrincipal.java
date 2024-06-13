package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.GestionController;
import lombok.Getter;
import model.Cancion;
import utils.Colors;
import utils.Sizes;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private static final GestionController gc = new GestionController();
	
	private TopBar movingBar;
	private JLabel lblTitle;
	private AddButton addSong;
	private XButton exitButton;
	
	private MyScrollPane scrollPane;
	private static ListaMusica lista;
	
	@Getter
	private static BottomBar bottomBar;

	@Getter
	private static Boolean isProd;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// Al ejecutar desde maven, no hay variable de entorno por lo que da excepcion
				try {
					isProd = System.getenv("mode").equals("prod") ? true : false;
				} catch (NullPointerException e) {
					isProd = true;
				}
				try {
					
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {
		/* Cambiar icono al ejecutar en eclipse PERO NO DEL JAR*/
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass()
				.getClassLoader().getResource("Icons/favicon.png")));
		gc.findAll();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Sizes.WindowWidth, Sizes.WindowHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		movingBar = new TopBar(this);
		contentPane.add(movingBar);
		
		lblTitle = new JLabel("TFGPlay");
		lblTitle.setFont(new Font("Monda", Font.BOLD | Font.ITALIC, 14));
		lblTitle.setBounds(30, 0, 70, Sizes.TopBarHeight);
		lblTitle.setForeground(Colors.NARANJA_800);
		movingBar.add(lblTitle);
		
		addSong = new AddButton("Añadir", this);
		addSong.setFont(new Font("Monda", Font.BOLD | Font.ITALIC, 14));
		addSong.setBounds(100, 0, 50, Sizes.TopBarHeight);
		movingBar.add(addSong);
		
		exitButton = new XButton("X", this, true);
		exitButton.setBounds(Sizes.TopBarWidth - Sizes.TopBarHeight, 0, Sizes.TopBarHeight, Sizes.TopBarHeight);
		movingBar.add(exitButton);
		
		scrollPane = new MyScrollPane();
		contentPane.add(scrollPane);
		
		lista = new ListaMusica();
		scrollPane.setViewportView(lista);
		lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
		
		fillList();
		
		bottomBar = new BottomBar();
		contentPane.add(bottomBar);
	}
	
	public static void fillList() {
		Tarjeta current = Tarjeta.getTarjetaAnterior();
		
		lista.removeAll();
		
		for (Cancion Cancion : GestionController.getCanciones()) {
			Tarjeta t1 = new Tarjeta(String.valueOf(GestionController.getCanciones().indexOf(Cancion) + 1), 
					Cancion.getTitulo(), Cancion.getAutor(), 
					Cancion.getDuracion(),
					Cancion
					);
			ListaMusica.getTarjetas().add(t1);
			
			// When we delete or add song, we want to keep the same song as before as current
			if (current != null && current.getC().getRuta().equals(Cancion.getRuta())) {
				t1.setToCurrent();
			}
			lista.add(t1);
		}
		
		// https://www.cs.rutgers.edu/courses/111/classes/fall_2011_tjang/texts/notes-java/GUI/layouts/42boxlayout-spacing.html
		int tamanyo = GestionController.getCanciones().size() * Sizes.CardHeight;
		if (Sizes.ListHeight > tamanyo) {
			lista.add(Box.createVerticalStrut(Sizes.ListHeight - tamanyo));			
		}
	}
	public static JPanel getLista() {
		return lista;
	}
}
