package dao;

import java.sql.Connection;
import java.util.ArrayList;

import BD.BD;
import model.Cancion;

public interface CancionDao<T> {
	static Connection c = BD.getConnection();
	
	static ArrayList<Cancion> canciones = new ArrayList<Cancion>();
	
	Boolean add (T entity);
	
	String findAll();
	
	Boolean delete (Integer id);
	
	static ArrayList<Cancion> getCanciones() {
		return canciones;
	}
}
