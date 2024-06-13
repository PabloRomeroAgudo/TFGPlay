package controller;

import java.util.ArrayList;

import dao.CancionDao;
import dao.CancionDaoImpl;
import model.Cancion;

public class GestionController {
	private CancionDaoImpl dao = new CancionDaoImpl();
	
	public Boolean add(Cancion entity){
		return dao.add(entity);
	}
	
	public String findAll(){
		return dao.findAll();
	}
	
	public Boolean delete(Integer id){
		return dao.delete(id);
	}
	
	public static ArrayList<Cancion> getCanciones() {
		return CancionDao.getCanciones();
	}
}
