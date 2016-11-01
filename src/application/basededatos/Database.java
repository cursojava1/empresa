package application.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Database {

	private static Database database = null;
	private Connection conexion = null;

	private Database() {

	}

	public static Database GetDatabase() {
		if (database == null) {
			database = new Database();
		}
		return database;
	}

	public void Conectar() {
		if (conexion == null) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;integratedSecurity=true;", "localhost", "1433", "Empresa");
				try {
					conexion = DriverManager.getConnection(url);
					
					if (conexion != null) {
						System.out.println("Conexion Exitosa");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void Desconectar() {
		if (conexion != null) {
			try {
				conexion.close();
				if(conexion.isClosed()) {
	                System.out.println("Desconexion Exitosa");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public HashMap<String, String> GetNombreContraseña(String nombre) {
		HashMap<String, String> datos = new HashMap<>();
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Nombre, Contraseña FROM Usuario where Nombre = " + nombre);
			while (rs.next()) {
				datos.put("Nombre", rs.getString("Nombre"));
				datos.put("Contraseña", rs.getString("Contraseña"));
				return datos;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
