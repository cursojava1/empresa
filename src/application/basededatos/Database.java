package application.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.empresa.empleados.Empleado;

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

	public String GetContraseña(String nombre) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Contraseña FROM Usuario where Nombre = '" + nombre + "'");
			while (rs.next()) {
				return rs.getString("Contraseña");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void InsertEmpleado(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			// Obtener Id Sexo.
			ResultSet rs = ps.executeQuery("SELECT Id FROM Sexo WHERE Sexo = '" + nuevo.getSexo() + "'");
			rs.next();
			int idSexo = rs.getInt("Id");
			// Obtener id estado civil.
			rs = ps.executeQuery("SELECT Id FROM EstadoCivil WHERE Estado = '" + nuevo.GetEstadoCivil() + "'");
			rs.next();
			int idEstadoCivil = rs.getInt("Id");
			
			StringBuilder consulta = new StringBuilder("INSERT INTO Persona VALUES ('")
					.append(nuevo.Nombre()).append("', '")
					.append(nuevo.Apellido()).append("', ")
					.append(nuevo.getDocumento()).append(", ")
					.append(nuevo.Edad()).append(", ")
					.append(idSexo).append(", ")
					.append(idEstadoCivil).append(")");
			int cantidadAfectadas = ps.executeUpdate(consulta.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
