package application.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import application.empresa.empleados.Empleado;
import application.empresa.empleados.Gerente;
import application.empresa.empleados.Junior;
import application.empresa.utils.Utils.VIVIENDA;

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

	public String GetContrase�a(String nombre) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Contrase�a FROM Usuario where Nombre = '" + nombre + "'");
			while (rs.next()) {
				return rs.getString("Contrase�a");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void InsertPersona(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			
			// Obtener Id Sexo.
			String consulta = "SELECT Id FROM Sexo WHERE Sexo = '" + nuevo.getSexo() + "'";
			ResultSet rs = ps.executeQuery(consulta);
			rs.next();
			int idSexo = rs.getInt("Id");
			// Obtener id estado civil.
			consulta = "SELECT Id FROM EstadoCivil WHERE Estado = '" + nuevo.GetEstadoCivil() + "'";
			rs = ps.executeQuery(consulta);
			rs.next();
			int idEstadoCivil = rs.getInt("Id");
			
			StringBuilder consultaInsertarPersona = new StringBuilder("INSERT INTO Persona VALUES ('")
					.append(nuevo.Nombre()).append("', '")
					.append(nuevo.Apellido()).append("', ")
					.append(nuevo.getDocumento()).append(", ")
					.append(nuevo.Edad()).append(", ")
					.append(idSexo).append(", ")
					.append(idEstadoCivil).append(")");
			
			int cantidadAfectadas = ps.executeUpdate(consultaInsertarPersona.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void InsertEmpleado(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
	
			// Obtener Id Vivienda.
			String consulta = "SELECT Id FROM TipoVivienda WHERE Vivienda = '" + nuevo.getVivienda() + "'";
			ResultSet rs = ps.executeQuery(consulta);
			rs.next();
			int idVivienda = rs.getInt("Id");
			
			StringBuilder consultaInsertarDomicilio = new StringBuilder("INSERT INTO Domicilio ([IdTipoVivienda],[Calle],[Numero]) VALUES (")
					.append(idVivienda).append(", '")
					.append(nuevo.getCalle()).append("', ")
					.append(nuevo.getNumero());
			
			if (nuevo.getVivienda().equals(VIVIENDA.DEPARTAMENTO)) {
				consultaInsertarDomicilio.append(", ").append(nuevo.GetPiso())
				.append(", '").append(nuevo.GetDepartamento()).append("'");
			}
			
			consultaInsertarDomicilio.append(")");
			
			int cantidadAfectadas = ps.executeUpdate(consultaInsertarDomicilio.toString());
			
			//Obtener Id Persona.
			rs = ps.executeQuery("SELECT Id FROM Persona WHERE Dni = " + nuevo.getDocumento());
			rs.next();
			int idPersona = rs.getInt("Id");
			
			//Obtener Id Domicilio.
			rs = ps.executeQuery("SELECT Max(Id) AS Id FROM Domicilio");
			rs.next();
			int idDomicilio = rs.getInt("Id");
			
			StringBuilder consultaInsertarMultiDomicilio = new StringBuilder("INSERT INTO MultiDomicilio Values (")
					.append(idPersona).append(", ").append(idDomicilio).append(")");
			
			cantidadAfectadas = ps.executeUpdate(consultaInsertarMultiDomicilio.toString());
			
			//Obtener Id Tipo de empleado.
			rs = ps.executeQuery("SELECT Id FROM TipoEmpleado WHERE Tipo = '" + nuevo.getClass().getSimpleName() + "'");
			rs.next();
			int idTipoEmpleado = rs.getInt("Id");
			
			StringBuilder consultaInsertarEmpleado = new StringBuilder("INSERT INTO Empleado Values('")
					.append(new SimpleDateFormat("yyyy-MM-dd").format(nuevo.getFechaDeIngreso())).append("', ")
					.append(idPersona).append(", ")
					.append(idTipoEmpleado).append(")");
			
			cantidadAfectadas = ps.executeUpdate(consultaInsertarEmpleado.toString());
			
			//Obtener legajo.
			rs = ps.executeQuery("SELECT Max(Legajo) AS Legajo FROM Empleado");
			rs.next();
			int legajoEmpleado = rs.getInt("Legajo");
			
			//Obtener tipo de empleado.
			rs = ps.executeQuery("SELECT Tipo FROM TipoEmpleado WHERE Id = " + idTipoEmpleado);
			rs.next();
			String tipo  = rs.getString("Tipo");
			
			if (tipo.equals("Gerente")) {
				Gerente gerente = (Gerente)nuevo;
				StringBuilder consultaInsertarGerente = new StringBuilder("INSERT INTO Gerente Values('")
						.append(gerente.getRango()).append("', ").append(legajoEmpleado).append(")");
				cantidadAfectadas = ps.executeUpdate(consultaInsertarGerente.toString());
			} else if (tipo.equals("Junior")) {
				Junior junior = (Junior)nuevo;
				StringBuilder consultaInsertarJunior = new StringBuilder("INSERT INTO Junior Values('")
						.append(junior.GetLenguajes()).append("', ").append(legajoEmpleado).append(")");
				cantidadAfectadas = ps.executeUpdate(consultaInsertarJunior.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
