package model;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Cancion {
	private Integer id;
	private String titulo;
	private String autor;
	private String ruta;
	private String duracion;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static float getDurationSecs(File file) {
		AudioInputStream audioInputStream;
		try {
			float segundosTotales;
			
			audioInputStream = AudioSystem.getAudioInputStream(file);
			AudioFormat format = audioInputStream.getFormat();
			long audioFileLength = file.length();
			int frameSize = format.getFrameSize();
			float frameRate = format.getFrameRate();
			
			segundosTotales = (audioFileLength / (frameSize * frameRate));
			return segundosTotales;
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	// para conseguir la duracion de una cancion
	public static String getDuration(File file) {
		int minutos = 0;
		int segundos = 0;
		
		float segundosTotales;
		
		segundosTotales = getDurationSecs(file);
		
		minutos = (int) (segundosTotales / 60);
		segundos = (int) segundosTotales % 60;
		
		return minutos + ":" + segundos;
	}
}
