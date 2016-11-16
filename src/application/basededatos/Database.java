package application.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import application.empresa.empleados.Empleado;
import application.empresa.empleados.Gerente;
import application.empresa.empleados.Junior;
import application.empresa.persona.Persona;
import application.empresa.utils.Utils.VIVIENDA;

public class Database<Int> {

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
				String url = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;integratedSecurity=true;",
						"localhost", "1433", "Empresa");
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
				if (conexion.isClosed()) {
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
					.append(nuevo.Nombre()).append("', '").append(nuevo.Apellido()).append("', ")
					.append(nuevo.getDocumento()).append(", ").append(nuevo.Edad()).append(", ").append(idSexo)
					.append(", ").append(idEstadoCivil).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarPersona.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void InsertDomicilio(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			// Obtener Id Vivienda.
			String consulta = "SELECT Id FROM TipoVivienda WHERE Vivienda = '" + nuevo.getVivienda() + "'";
			ResultSet rs = ps.executeQuery(consulta);
			rs.next();
			int idVivienda = rs.getInt("Id");

			StringBuilder consultaInsertarDomicilio = new StringBuilder(
					"INSERT INTO Domicilio ([IdTipoVivienda],[Calle],[Numero]) VALUES (").append(idVivienda)
							.append(", '").append(nuevo.getCalle()).append("', ").append(nuevo.getNumero());

			if (nuevo.getVivienda().equals(VIVIENDA.DEPARTAMENTO)) {
				consultaInsertarDomicilio.append(", ").append(nuevo.GetPiso()).append(", '")
						.append(nuevo.GetDepartamento()).append("'");
			}

			consultaInsertarDomicilio.append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarDomicilio.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void InsertMultiDomicilio(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			// Obtener Id Persona.
			ResultSet rs = ps.executeQuery("SELECT Id FROM Persona WHERE Dni = " + nuevo.getDocumento());
			rs.next();
			int idPersona = rs.getInt("Id");

			// Obtener Id Domicilio.
			rs = ps.executeQuery("SELECT Max(Id) AS Id FROM Domicilio");
			rs.next();
			int idDomicilio = rs.getInt("Id");

			StringBuilder consultaInsertarMultiDomicilio = new StringBuilder("INSERT INTO MultiDomicilio Values (")
					.append(idPersona).append(", ").append(idDomicilio).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarMultiDomicilio.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void InsertEmpleado(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			// Obtener Id Persona.
			ResultSet rs = ps.executeQuery("SELECT Id FROM Persona WHERE Dni = " + nuevo.getDocumento());
			rs.next();
			int idPersona = rs.getInt("Id");

			// Obtener Id Tipo de empleado.
			rs = ps.executeQuery("SELECT Id FROM TipoEmpleado WHERE Tipo = '" + nuevo.getClass().getSimpleName() + "'");
			rs.next();
			int idTipoEmpleado = rs.getInt("Id");

			StringBuilder consultaInsertarEmpleado = new StringBuilder("INSERT INTO Empleado Values('")
					.append(new SimpleDateFormat("yyyy-MM-dd").format(nuevo.getFechaDeIngreso())).append("', ")
					.append(idPersona).append(", ").append(idTipoEmpleado).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarEmpleado.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void InsertGerente(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			// Obtener legajo.
			ResultSet rs = ps.executeQuery("SELECT Max(Legajo) AS Legajo FROM Empleado");
			rs.next();
			int legajoEmpleado = rs.getInt("Legajo");

			// Obtener Id Tipo de empleado.
			rs = ps.executeQuery("SELECT Id FROM TipoEmpleado WHERE Tipo = '" + nuevo.getClass().getSimpleName() + "'");
			rs.next();
			int idTipoEmpleado = rs.getInt("Id");

			// Obtener tipo de empleado.
			rs = ps.executeQuery("SELECT Tipo FROM TipoEmpleado WHERE Id = " + idTipoEmpleado);
			rs.next();
			String tipo = rs.getString("Tipo");

			Gerente gerente = (Gerente) nuevo;
			StringBuilder consultaInsertarGerente = new StringBuilder("INSERT INTO Gerente Values('")
					.append(gerente.getRango()).append("', ").append(legajoEmpleado).append(")");
			int cantidadAfectadas = ps.executeUpdate(consultaInsertarGerente.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void InsertJunior(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			// Obtener legajo.
			ResultSet rs = ps.executeQuery("SELECT Max(Legajo) AS Legajo FROM Empleado");
			rs.next();
			int legajoEmpleado = rs.getInt("Legajo");

			// Obtener Id Tipo de empleado.
			rs = ps.executeQuery("SELECT Id FROM TipoEmpleado WHERE Tipo = '" + nuevo.getClass().getSimpleName() + "'");
			rs.next();
			int idTipoEmpleado = rs.getInt("Id");

			// Obtener tipo de empleado.
			rs = ps.executeQuery("SELECT Tipo FROM TipoEmpleado WHERE Id = " + idTipoEmpleado);
			rs.next();
			String tipo = rs.getString("Tipo");

			Junior junior = (Junior) nuevo;
			StringBuilder consultaInsertarJunior = new StringBuilder("INSERT INTO Junior Values('")
					.append(junior.GetLenguajes()).append("', ").append(legajoEmpleado).append(")");
			int cantidadAfectadas = ps.executeUpdate(consultaInsertarJunior.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UpdatePersona(Empleado IdPersona) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Id FROM Persona WHERE Dni = " + IdPersona.getDocumento());
			rs.next();
			int idPersona = rs.getInt("Id");

			StringBuilder consultaModificarPersona = new StringBuilder();
			consultaModificarPersona.append("UPDATE Persona SET('");
			consultaModificarPersona.append(IdPersona.Nombre()).append("', '");
			consultaModificarPersona.append(IdPersona.Apellido()).append("', ");
			consultaModificarPersona.append(IdPersona.getDocumento()).append(", ");
			consultaModificarPersona.append(IdPersona.Edad()).append(", ");
			consultaModificarPersona.append(IdPersona.getSexo()).append(", ");
			consultaModificarPersona.append(IdPersona.GetEstadoCivil()).append(", ");
			consultaModificarPersona.append(" WHERE id = " + idPersona);

			int cantidadAfectadas = ps.executeUpdate(consultaModificarPersona.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UpdateDomicilio(Empleado empleado) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			// Obtener Id Vivienda.
			String consulta = "SELECT Id FROM TipoVivienda WHERE Vivienda = '" + empleado.getVivienda() + "'";
			ResultSet rs = ps.executeQuery(consulta);
			rs.next();
			int idVivienda = rs.getInt("Id");

			StringBuilder consultaModificarDomicilio = new StringBuilder();
			consultaModificarDomicilio.append("UPDATE Domicilio SET('");
			consultaModificarDomicilio.append(empleado.getVivienda()).append("', '");
			consultaModificarDomicilio.append(empleado.getCalle()).append("', ");
			consultaModificarDomicilio.append(empleado.getNumero()).append(", ");

			if (empleado.getVivienda().equals(VIVIENDA.DEPARTAMENTO)) {
				consultaModificarDomicilio.append(", ");
				consultaModificarDomicilio.append(empleado.GetPiso());
				consultaModificarDomicilio.append(", '");
				consultaModificarDomicilio.append(empleado.GetDepartamento()).append("'");

			}
			consultaModificarDomicilio.append(" WHERE id = " + idVivienda);
			int cantidadAfectadas = ps.executeUpdate(consultaModificarDomicilio.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UpdateMultiDomicilio(Empleado empleado) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			ResultSet rs = ps.executeQuery("SELECT Id FROM Persona WHERE Dni = " + empleado.getDocumento());
			rs.next();
			int idPersona = rs.getInt("Id");

			rs = ps.executeQuery("SELECT Max(Id) AS Id FROM Domicilio");
			rs.next();
			int idDomicilio = rs.getInt("Id");

			StringBuilder consultaModificarMultiDomicilio = new StringBuilder();
			consultaModificarMultiDomicilio.append("UPDATE MultiDomicilio SET ('");
			consultaModificarMultiDomicilio.append(idPersona).append(", ");
			consultaModificarMultiDomicilio.append(idDomicilio).append(")");
			consultaModificarMultiDomicilio.append(" WHERE id = " + idDomicilio);
			int cantidadAfectadas = ps.executeUpdate(consultaModificarMultiDomicilio.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UpdateEmpleado(Empleado empleado) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			ResultSet rs = ps.executeQuery("SELECT Id FROM Persona WHERE Dni = " + empleado.getDocumento());
			rs.next();
			int idPersona = rs.getInt("Id");

			rs = ps.executeQuery(
					"SELECT Id FROM TipoEmpleado WHERE Tipo = '" + empleado.getClass().getSimpleName() + "'");
			rs.next();
			int idTipoEmpleado = rs.getInt("Id");

			StringBuilder consultaModificarEmpleado = new StringBuilder();
			consultaModificarEmpleado.append("UPDATE Empleado SET('");
			consultaModificarEmpleado.append(new SimpleDateFormat("yyyy-MM-dd").format(empleado.getFechaDeIngreso()))
					.append("', ");
			consultaModificarEmpleado.append(idPersona).append(", ");
			consultaModificarEmpleado.append(idTipoEmpleado).append(")");
			consultaModificarEmpleado.append(" WHERE id = " + idTipoEmpleado);
			int cantidadAfectadas = ps.executeUpdate(consultaModificarEmpleado.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UpdateGerente(Empleado empleado) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Max(Legajo) AS Legajo FROM Empleado");
			rs.next();
			int legajoEmpleado = rs.getInt("Legajo");

			rs = ps.executeQuery(
					"SELECT Id FROM TipoEmpleado WHERE Tipo = '" + empleado.getClass().getSimpleName() + "'");
			rs.next();
			int idTipoEmpleado = rs.getInt("Id");

			rs = ps.executeQuery("SELECT Tipo FROM TipoEmpleado WHERE Id = " + idTipoEmpleado);
			rs.next();
			String tipo = rs.getString("Tipo");

			Gerente gerente = (Gerente) empleado;
			StringBuilder consultaModificarGerente = new StringBuilder();
			consultaModificarGerente.append("UPDATE Gerente SET('");
			consultaModificarGerente.append(gerente.getRango()).append("', ");
			consultaModificarGerente.append(legajoEmpleado).append(")");
			consultaModificarGerente.append(" WHERE id = " + tipo);
			int cantidadAfectadas = ps.executeUpdate(consultaModificarGerente.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UpdateJunior(Empleado empleado) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Max(Legajo) AS Legajo FROM Empleado");
			rs.next();
			int legajoEmpleado = rs.getInt("Legajo");

			rs = ps.executeQuery(
					"SELECT Id FROM TipoEmpleado WHERE Tipo = '" + empleado.getClass().getSimpleName() + "'");
			rs.next();
			int idTipoEmpleado = rs.getInt("Id");

			rs = ps.executeQuery("SELECT Tipo FROM TipoEmpleado WHERE Id = " + idTipoEmpleado);
			rs.next();
			String tipo = rs.getString("Tipo");

			Junior junior = (Junior) empleado;
			StringBuilder consultaModificarJunior = new StringBuilder();
			consultaModificarJunior.append("UPDATE Junior SET('");
			consultaModificarJunior.append(junior.GetLenguajes()).append("', ");
			consultaModificarJunior.append(legajoEmpleado).append(")");
			consultaModificarJunior.append(" WHERE id = " + tipo);
			int cantidadAfectadas = ps.executeUpdate(consultaModificarJunior.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
	