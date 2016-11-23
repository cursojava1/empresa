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
	
	public Integer ObtenerIdSexo (Empleado nuevo){
		try {
			java.sql.Statement ps = conexion.createStatement();
			String consulta = "SELECT Id FROM Sexo WHERE Sexo = '" + nuevo.getSexo() + "'";
			ResultSet rs = ps.executeQuery(consulta);
			rs.next();
			return  rs.getInt("Id");
		}catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

	
	public Integer ObtenerIdEstadoCivil (Empleado nuevo){
		try {
			java.sql.Statement ps = conexion.createStatement();
			String consulta = "SELECT Id FROM EstadoCivil WHERE Estado = '" + nuevo.GetEstadoCivil() + "'";
				ResultSet rs = ps.executeQuery(consulta);
				rs.next();
				return rs.getInt("Id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
				
	
	public void InsertPersona(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();


			int idSexo 			= ObtenerIdSexo(nuevo);
			int idEstadoCivil  	= ObtenerIdEstadoCivil(nuevo);
			

			StringBuilder consultaInsertarPersona = new StringBuilder("INSERT INTO Persona VALUES ('")
					.append(nuevo.Nombre()).append("', '").append(nuevo.Apellido()).append("', ")
					.append(nuevo.getDocumento()).append(", ").append(nuevo.Edad()).append(", ").append(idSexo)
					.append(", ").append(idEstadoCivil).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarPersona.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer ObtenerIdVivienda (Empleado nuevo){
		try {
			java.sql.Statement ps = conexion.createStatement();
			String consulta = "SELECT Id FROM TipoVivienda WHERE Vivienda = '" + nuevo.getVivienda() + "'";
			ResultSet rs = ps.executeQuery(consulta);
			rs.next();
			return rs.getInt("Id");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
	public void InsertDomicilio(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			
			int idVivienda			= ObtenerIdVivienda(nuevo);

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
			
			int idPersona 		= ObtenerIdPersona(nuevo);
			int idDomicilio 	= ObtenerIdDomicilio(nuevo);
			

			StringBuilder consultaInsertarMultiDomicilio = new StringBuilder("INSERT INTO MultiDomicilio Values (")
					.append(idPersona).append(", ").append(idDomicilio).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarMultiDomicilio.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer ObtenerIdPersona(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Id FROM Persona WHERE Dni = " + nuevo.getDocumento());
			rs.next();
			return rs.getInt("Id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public Integer ObtenerIdDomicilio(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Max(Id) AS Id FROM Domicilio");
			rs.next();
			return rs.getInt("Id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer ObtenerIdTipoEmpleado (Empleado nuevo){
		try{
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Id FROM TipoEmpleado WHERE Tipo = '" + nuevo.getClass().getSimpleName() + "'");
			rs.next();
			return rs.getInt("Id");
		} catch (SQLException e){
					e.printStackTrace();
				}
				return null;
		}
		
	public void InsertEmpleado(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			
			int idPersona 		= ObtenerIdPersona(nuevo);
			int idTipoEmpleado  = ObtenerIdTipoEmpleado(nuevo);
	
			StringBuilder consultaInsertarEmpleado = new StringBuilder("INSERT INTO Empleado Values('")
					.append(new SimpleDateFormat("yyyy-MM-dd").format(nuevo.getFechaDeIngreso())).append("', ")
					.append(idPersona).append(", ").append(idTipoEmpleado).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarEmpleado.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer ObtenerLegajo (Empleado nuevo){
		try{
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Max(Legajo) AS Legajo FROM Empleado");
			rs.next();
			return rs.getInt("Id"); 
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String ObtenerTipoEmpleado (Empleado nuevo){
		try{
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Tipo FROM TipoEmpleado WHERE Id = " + ObtenerIdTipoEmpleado(nuevo));
			rs.next();
			return rs.getString("Tipo"); 
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}


	public void InsertGerente(Empleado nuevo) {
		
		try {
			java.sql.Statement ps = conexion.createStatement();

					int legajoEmpleado 		= ObtenerLegajo(nuevo);
					int idTipoEmpleado  	= ObtenerIdTipoEmpleado(nuevo);
					String TipoEmpleado  	= ObtenerTipoEmpleado(nuevo);
					
			
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

			int legajoEmpleado 		= ObtenerLegajo(nuevo);
			int idTipoEmpleado  	= ObtenerIdTipoEmpleado(nuevo);
			String TipoEmpleado  	= ObtenerTipoEmpleado(nuevo);
			
			
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

			StringBuilder consultaUpdateUsuario = new StringBuilder("UPDATE Usuario SET('").append(nuevo.getNombre())
					.append("', '").append(nuevo.getContraseña()).append("', ").append(nuevo.getEmail()).append(", ")
					.append(nuevo.getNivel()).append(")").append(" WHERE id = " + nuevo.getEmail());

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
