import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import controller.GestionController;
import dao.CancionDao;
import model.Cancion;

public class Prueba {
	
	private static GestionController contr = new GestionController();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		Cancion c = Cancion.builder()
				.titulo("La cumbia del magna")
				.autor("King magneto")
				.ruta("C:/V/DAM/TFG/Musica/LaCumbiaDeLosMagna.wav")
				.duracion(Cancion.getDuration(new File("C:/V/DAM/TFG/Musica/LaCumbiaDeLosMagna.wav")))
				.build();
		contr.add(c);
		System.out.println(contr.findAll());
		
		//contr.delete(2);
		
		//play();
		reader.nextLine();
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private static Connection c;
//	private static ArrayList<Cancion> canciones = new ArrayList<Cancion>();
//
//	public static void main(String[] args) {
//		Scanner reader = new Scanner(System.in);
//		c = BD.getConnection();
//		show();
//		play();
//		reader.nextLine();
//		
//		reader.close();
//	}
//
//	public static String show() {
//		String sql =	"""
//							SELECT * FROM Canciones
//						""";
//		StringBuffer sb = new StringBuffer();
//		
//		try {
//			Statement stmt = c.createStatement();
//
//			ResultSet rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				Integer id = rs.getInt("Id");
//				String titulo = rs.getString("Titulo");
//				String autor = rs.getString("Autor");
//				String ruta = rs.getString("Ruta");
//				String duracion = rs.getString("Duracion");
//				Cancion cancion = new Cancion(id, titulo, autor, ruta, duracion);
//				System.out.println(Cancion.getDuration(new File(cancion.getRuta())));
//				canciones.add(cancion);
//			}
//
//			sb.append("CANCIONES:\n");
//			sb.append(canciones.toString());
//
//			return sb.toString();
//		} catch (SQLException e) {
//			return "";
//		}
//	}
//	
	public static void play() {
		File file = new File(CancionDao.canciones.get(1).getRuta());
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
	}
}
