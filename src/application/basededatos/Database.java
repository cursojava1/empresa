package application.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import application.empresa.empleados.Empleado;
import application.empresa.empleados.Gerente;
import application.empresa.empleados.Junior;
import application.empresa.usuario.Usuario;
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
	
	public void EliminarEmpleado(Empleado desempleado){
		java.sql.Statement ps;
		//NO COMPILAR NO PUSHEAR NO TOCAR
		try {
			ps = conexion.createStatement();
			ResultSet lg = ps.executeQuery("SELECT IdPersona From Empleado Where Legajo = "+desempleado.getLegajo());
			lg.next();
			int idPersona = lg.getInt("IdPersona");
			lg = ps.executeQuery("SELECT idDomicilio From MultiDomicilio Where IdPersona = "+idPersona);
			List<Integer> idDomicilio = new ArrayList<Integer>();
			while(lg.next()){
				idDomicilio.add(lg.getInt("IdDomicilio"));
			}
			ps.executeQuery("delete from Persona Where Id = " + idPersona);
			
			for(int i = 0; i > idDomicilio.size();i++)			
			ps.executeQuery("delete from Domicilio Where Id = "+idDomicilio.get(i));
			ps.executeQuery("delete from MultiDomicilio Where IdPersona =" + idPersona);
			//
			lg = ps.executeQuery("SELECT Tipo From TipoEmpleados WHERE id =" + idPersona);
			if(lg.getInt("idTipo") == 1)
			ps.executeQuery("delete from Gerente Where");
			else
			ps.executeQuery("delete from Junior Where");
				
		} catch (SQLException e) {
			
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

	public List<String> SelectEmpleados() {
		try {
			java.sql.Statement ps = conexion.createStatement();
			String consulta = "SELECT E.Legajo, P.Nombre, P.Apellido, P.Dni, P.Edad, D.Calle, D.Numero, D.Piso, D.Departamento, J.Lenguaje, G.Rango  FROM Persona P LEFT JOIN Empleado E ON P.Id=E.IdPersona LEFT JOIN MultiDomicilio M ON P.Id=M.IdPersona  LEFT JOIN Domicilio D ON M.IdDomicilio=D.Id LEFT JOIN Gerente G ON E.IdTipoEmpleado = G.Id LEFT JOIN Junior J ON E.IdTipoEmpleado=J.Id";
			ResultSet rs = ps.executeQuery(consulta);

			List<String> empleados = new ArrayList<>();
			String datosEmpleado;
			while (rs.next()) {
				datosEmpleado = new StringBuilder("").append(String.valueOf(rs.getInt("Legajo"))).append(" ")
						.append(rs.getString("Nombre")).append(" ").append(rs.getString("Apellido")).append(" ")
						.append(rs.getString("Dni")).append(" ").append(rs.getString("Edad")).append(" ")
						.append(rs.getString("Calle")).append(" ").append(rs.getString("Numero")).append(" ")
						.append(rs.getString("Piso")).append(" ").append(rs.getString("Departamento")).append(" ")
						.append(rs.getString("Lenguaje")).append(" ").append(rs.getString("Rango")).toString();

				empleados.add(datosEmpleado);
			}

			return empleados;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public void InsertUsuario(Usuario nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			// Obtener Id Nivel.
			String consulta = "SELECT Id FROM Nivel WHERE Nivel = '" + nuevo.getNivel() + "'";
			ResultSet rs = ps.executeQuery(consulta);
			rs.next();
			int idNivel = rs.getInt("Id");

			StringBuilder consultaInsertarUsuario = new StringBuilder("INSERT INTO Usuario VALUES ('")
					.append(nuevo.getNombre()).append("', '").append(nuevo.getContraseña()).append("', ")
					.append(nuevo.getEmail()).append(", ").append(idNivel).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarUsuario.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateUsuario(Usuario nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();


			StringBuilder consultaUpdateUsuario = new StringBuilder("UPDATE Usuario SET('")
					.append(nuevo.getNombre()).append("', '").append(nuevo.getContraseña()).append("', ")
					.append(nuevo.getEmail()).append(", ").append(nuevo.getNivel()).append(")")
					.append(" WHERE id = " + nuevo.getEmail());

			int cantidadAfectadas = ps.executeUpdate(consultaUpdateUsuario.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteUsuario(Usuario nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			String consultaDeleteUsuario = "DELETE FROM Usuario WHERE Id = '" + nuevo.getEmail() + "'";			
			int cantidadAfectadas = ps.executeUpdate(consultaDeleteUsuario.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<String> SelectUsuarios() {
		try {
			java.sql.Statement ps = conexion.createStatement();
			String consulta = "SELECT U.Id, U.Nombre, U.Contraseña, U.Email, N.Tipo FROM Usuario U LEFT JOIN Nivel N ON U.IdNivel=N.Id";
			ResultSet rs = ps.executeQuery(consulta);

			List<String> usuarios = new ArrayList<>();
			String datosUsuario;
			while (rs.next()) {
				datosUsuario = new StringBuilder("").append(String.valueOf(rs.getInt("Id"))).append(" ")
						.append(rs.getString("Nombre")).append(" ").append(rs.getString("Contraseña")).append(" ")
						.append(rs.getString("Email")).append(" ").append(rs.getString("Tipo")).toString();

				usuarios.add(datosUsuario);
			}

			return usuarios;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
