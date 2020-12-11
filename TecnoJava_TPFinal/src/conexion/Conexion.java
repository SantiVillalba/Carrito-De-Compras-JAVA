package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/db_tpfinal";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";
	
	public Connection getConnection() {
		Connection conexion = null;
		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Se conecto correctamente.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error: No se encontró la clase.");
			
		} catch (SQLException e) {
			System.out.print("Error: No se pudo conectar.");
		}
		
		return conexion;
	}
	
}