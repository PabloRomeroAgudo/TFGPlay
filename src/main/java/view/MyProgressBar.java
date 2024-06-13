package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import lombok.Getter;
import model.Cancion;
import utils.Colors;

public class MyProgressBar extends JProgressBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Timer timer;
	
	public MyProgressBar() {
		init();
	}
	
	public void init() {
		this.setMinimum(0);
		this.setValue(0);
		timer = new Timer(1000, this);
		this.setBorder(new LineBorder(Colors.BLANCO));
		this.setForeground(Colors.NARANJA_500);
	}
	
	public void startProgressBar() {
        timer.start(); // Iniciar el temporizador
    }

    public void stopProgressBar() {
        timer.stop(); // Detener el temporizador
    }

    public void updateProgressBar() {
    	Integer position = (int) Tarjeta.getClip().getMicrosecondPosition() / 1000000;
    	this.setValue(position);
    	BottomBar.getLblProgreso().setText(convertSeconds(position));
    	
    	if (this.getValue() == this.getMaximum()) {
    		NextSongButton.playNextSong();
    	}
    }
    
    public void setMaximum() {
    	Cancion c1 = Tarjeta.getTarjetaAnterior().getC();
    	int duracion = (int) Cancion.getDurationSecs(new File(c1.getRuta()));
    	this.setMaximum(duracion);
    	BottomBar.getLblDuracion().setText(Tarjeta.getTarjetaAnterior().getLblDuracion().getText());;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		updateProgressBar();
	}
	
	public String convertSeconds(int secs) {
		 long minutes = (secs % 3600) / 60;
         long seconds = secs % 60;
         
         return String.format("%02d:%02d",minutes, seconds);
	}
}
