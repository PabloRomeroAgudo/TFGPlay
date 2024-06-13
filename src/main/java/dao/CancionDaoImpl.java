package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Cancion;

public class CancionDaoImpl implements CancionDao<Cancion> {

	@Override
	public Boolean add(Cancion entity) {
		String sql = """
				INSERT INTO Canciones(Titulo, Autor, Ruta, Duracion) values (?, ?, ?, ?)
				""";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, entity.getTitulo());
			ps.setString(2, entity.getAutor());
			ps.setString(3, entity.getRuta());
			ps.setString(4, entity.getDuracion());

			Integer filas = ps.executeUpdate();
			
			canciones.clear();
			findAll();
			
			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String findAll() {
		String sql = """
						SELECT * FROM Canciones
					 """;
		StringBuffer sb = new StringBuffer();

		try {
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Integer id = rs.getInt("Id");
				String titulo = rs.getString("Titulo");
				String autor = rs.getString("Autor");
				String ruta = rs.getString("Ruta");
				String duracion = rs.getString("Duracion");
				Cancion cancion = new Cancion(id, titulo, autor, ruta, duracion);
				canciones.add(cancion);
			}
			sb.append("CANCIONES:\n");
			sb.append(canciones.toString());

			return sb.toString();
		} catch (SQLException e) {
			return "";
		}
	}

	@Override
	public Boolean delete(Integer id) {
		String sql = """
						DELETE FROM Canciones where id = ?
					 """;
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			Integer filas = ps.executeUpdate();
			canciones.clear();
			findAll();

			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
