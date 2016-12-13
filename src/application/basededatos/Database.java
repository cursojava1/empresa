package application.basededatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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


/**
 * @author administrador
 *
 */
public class Database {

	
	private static Database database = null;
	private String url;
	private Connection conexion = null;
	ConfiguracionDB config = new ConfiguracionDB();

	private Database() {
	}

	public static Database GetDatabase() {
		if (database == null) {
			database = new Database();
		}
		return database;
	}


	
	public void Conectar(ConfiguracionDB config) {
		if (conexion == null) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				url = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;integratedSecurity=true;",
				config.GetDireccionDB(),config.GetPuerto(),config.GetNombreDB());
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
	
	//Join de usuario con nivel para detectar si el usuario es administrador o visitante
	public String GetNivel(String nombre){
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Nivel.Tipo FROM Usuario JOIN Nivel ON Usuario.IdNivel = Nivel.Id WHERE Usuario.Nombre = '" + nombre + "'");
			while (rs.next()) {
				return rs.getString("Tipo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer ObtenerIdSexo(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			String consulta = "SELECT Id FROM Sexo WHERE Sexo = '" + nuevo.getSexo() + "'";
			ResultSet rs = ps.executeQuery(consulta);
			rs.next();
			return rs.getInt("Id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer ObtenerIdEstadoCivil(Empleado nuevo) {
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

	public boolean InsertPersona(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			int idSexo = ObtenerIdSexo(nuevo);
			int idEstadoCivil = ObtenerIdEstadoCivil(nuevo);

			StringBuilder consultaInsertarPersona = new StringBuilder("INSERT INTO Persona VALUES ('")
					.append(nuevo.Nombre()).append("', '").append(nuevo.Apellido()).append("', ")
					.append(nuevo.getDocumento()).append(", ").append(nuevo.Edad()).append(", ").append(idSexo)
					.append(", ").append(idEstadoCivil).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarPersona.toString());
			if (cantidadAfectadas == 0) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	


	public Integer ObtenerIdVivienda(Empleado nuevo) {
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

	public boolean InsertDomicilio(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			int idVivienda = ObtenerIdVivienda(nuevo);
			StringBuilder consultaInsertarDomicilio;
			
			if (nuevo.getVivienda().equals(VIVIENDA.DEPARTAMENTO)) {
				consultaInsertarDomicilio = new StringBuilder(
						"INSERT INTO Domicilio ([IdTipoVivienda],[Calle],[Numero],[Piso],[Departamento]) VALUES (").append(idVivienda)
								.append(", '").append(nuevo.getCalle()).append("', ").append(nuevo.getNumero()).append(", ").append(nuevo.GetPiso()).append(", '")
								.append(nuevo.GetDepartamento()).append("'").append(")");
			} else {
				consultaInsertarDomicilio = new StringBuilder(
						"INSERT INTO Domicilio ([IdTipoVivienda],[Calle],[Numero]) VALUES (").append(idVivienda)
								.append(", '").append(nuevo.getCalle()).append("', ").append(nuevo.getNumero()).append(")");
			}

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarDomicilio.toString());
			if (cantidadAfectadas == 0) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean InsertMultiDomicilio(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			int idPersona = ObtenerIdPersona(nuevo);
			int idDomicilio = ObtenerUltimoIdDomicilio();

			StringBuilder consultaInsertarMultiDomicilio = new StringBuilder("INSERT INTO MultiDomicilio Values (")
					.append(idPersona).append(", ").append(idDomicilio).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarMultiDomicilio.toString());
			if (cantidadAfectadas == 0) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
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
	
	public Integer ObtenerIdPersona(int legajo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT IdPersona FROM Empleado WHERE Legajo = " + legajo);
			rs.next();
			return rs.getInt("IdPersona");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer ObtenerUltimoIdDomicilio() {
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
	
	public Integer ObtenerIdDomicilio(int idPersona) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Max(IdDomicilio) AS IdDomicilio FROM MultiDomicilio WHERE IdPersona = " + idPersona);
			rs.next();
			return rs.getInt("IdDomicilio");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer ObtenerIdTipoEmpleado(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery(
					"SELECT Id FROM TipoEmpleado WHERE Tipo = '" + nuevo.getClass().getSimpleName() + "'");
			rs.next();
			return rs.getInt("Id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Integer ObtenerIdTipoEmpleado(int legajo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT idTipoEmpleado FROM Empleado WHERE Legajo = " + legajo);
			rs.next();
			return rs.getInt("idTipoEmpleado");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean InsertEmpleado(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			int idPersona = ObtenerIdPersona(nuevo);
			int idTipoEmpleado = ObtenerIdTipoEmpleado(nuevo);

			StringBuilder consultaInsertarEmpleado = new StringBuilder("INSERT INTO Empleado Values('")
					.append(new SimpleDateFormat("yyyy-MM-dd").format(nuevo.getFechaDeIngreso())).append("', ")
					.append(idPersona).append(", ").append(idTipoEmpleado).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarEmpleado.toString());
			if (cantidadAfectadas == 0) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public Integer ObtenerLegajo(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Max(Legajo) AS Legajo FROM Empleado");
			rs.next();
			return rs.getInt("Legajo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String ObtenerTipoEmpleado(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();
			ResultSet rs = ps.executeQuery("SELECT Tipo FROM TipoEmpleado WHERE Id = " + ObtenerIdTipoEmpleado(nuevo));
			rs.next();
			return rs.getString("Tipo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean InsertGerente(Empleado nuevo) {

		try {
			java.sql.Statement ps = conexion.createStatement();

			int legajoEmpleado = ObtenerLegajo(nuevo);

			Gerente gerente = (Gerente) nuevo;
			StringBuilder consultaInsertarGerente = new StringBuilder("INSERT INTO Gerente Values('")
					.append(gerente.getRango()).append("', ").append(legajoEmpleado).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarGerente.toString());
			if (cantidadAfectadas == 0) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean InsertJunior(Empleado nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			int legajoEmpleado = ObtenerLegajo(nuevo);

			Junior junior = (Junior) nuevo;
			StringBuilder consultaInsertarJunior = new StringBuilder("INSERT INTO Junior Values('")
					.append(junior.GetLenguajes()).append("', ").append(legajoEmpleado).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarJunior.toString());
			if (cantidadAfectadas == 0) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<String> SelectEmpleados() {
		try {
			java.sql.Statement ps = conexion.createStatement();
			String consulta = "SELECT E.Legajo, P.Nombre, P.Apellido, P.Dni, P.Edad, D.Calle, D.Numero, D.Piso, D.Departamento, J.Lenguaje, G.Rango FROM Persona P LEFT JOIN Empleado E ON P.Id=E.IdPersona LEFT JOIN MultiDomicilio M ON P.Id=M.IdPersona LEFT JOIN Domicilio D ON M.IdDomicilio=D.Id LEFT JOIN TipoEmpleado T ON E.IdTipoEmpleado = T.Id LEFT JOIN Junior J ON E.Legajo = J.LegajoEmpleado LEFT JOIN Gerente G ON E.Legajo = G.LegajoEmpleado";
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
	public boolean InsertUsuario(Usuario nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			String consulta = "SELECT Id FROM Nivel WHERE Tipo = '" + nuevo.getNivel() + "'";
			ResultSet rs = ps.executeQuery(consulta);
			rs.next();
			int idNivel = rs.getInt("Id");

			StringBuilder consultaInsertarUsuario = new StringBuilder("INSERT INTO Usuario VALUES ('")
					.append(nuevo.getNombre()).append("', '").append(nuevo.getContrase�a()).append("', '")
					.append(nuevo.getEmail()).append("', ").append(idNivel).append(")");

			int cantidadAfectadas = ps.executeUpdate(consultaInsertarUsuario.toString());
			if (cantidadAfectadas == 0) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void UpdateUsuario(Usuario nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			StringBuilder consultaUpdateUsuario = new StringBuilder("UPDATE Usuario SET('").append(nuevo.getNombre())
					.append("', '").append(nuevo.getContrase�a()).append("', ").append(nuevo.getEmail()).append(", ")
					.append(nuevo.getNivel()).append(")").append(" WHERE id = " + nuevo.getEmail());

			int cantidadAfectadas = ps.executeUpdate(consultaUpdateUsuario.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void DeleteUsuario(Usuario nuevo) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			String consultaDeleteUsuario = "DELETE FROM Usuario WHERE Nombre = '" + nuevo.getNombre() + "'";
			int cantidadAfectadas = ps.executeUpdate(consultaDeleteUsuario.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> SelectUsuarios() {
		try {
			java.sql.Statement ps = conexion.createStatement();
			String consulta = "SELECT U.Id, U.Nombre, U.Contrase�a, U.Email, N.Tipo FROM Usuario U LEFT JOIN Nivel N ON U.IdNivel=N.Id";
			ResultSet rs = ps.executeQuery(consulta);

			List<String> usuarios = new ArrayList<>();
			String datosUsuario;
			while (rs.next()) {
				datosUsuario = new StringBuilder("").append(String.valueOf(rs.getInt("Id"))).append(" ")
						.append(rs.getString("Nombre")).append(" ").append(rs.getString("Contrase�a")).append(" ")
						.append(rs.getString("Email")).append(" ").append(rs.getString("Tipo")).toString();

				usuarios.add(datosUsuario);
			}
			return usuarios;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*public void UpdatePersona(Empleado empleado) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			int idPersona = ObtenerIdPersona(empleado);

			StringBuilder consultaModificarPersona = new StringBuilder();
			consultaModificarPersona.append("UPDATE Persona SET('");
			consultaModificarPersona.append(empleado.Nombre()).append("', '");
			consultaModificarPersona.append(empleado.Apellido()).append("', ");
			consultaModificarPersona.append(empleado.getDocumento()).append(", ");
			consultaModificarPersona.append(empleado.Edad()).append(", ");
			consultaModificarPersona.append(empleado.getSexo()).append(", ");
			consultaModificarPersona.append(empleado.GetEstadoCivil()).append(", ");
			consultaModificarPersona.append(" WHERE id = " + idPersona);

			int cantidadAfectadas = ps.executeUpdate(consultaModificarPersona.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UpdateDomicilio(Empleado empleado) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			int idVivienda = ObtenerIdVivienda(empleado);
			StringBuilder consultaModificarDomicilio;

			if (empleado.getVivienda().equals(VIVIENDA.DEPARTAMENTO)) {
				consultaModificarDomicilio = new StringBuilder("UPDATE Domicilio SET('");
				consultaModificarDomicilio.append(empleado.getVivienda()).append("', '");
				consultaModificarDomicilio.append(empleado.getCalle()).append("', ");
				consultaModificarDomicilio.append(empleado.getNumero()).append(", ");
				consultaModificarDomicilio.append(", ");
				consultaModificarDomicilio.append(empleado.GetPiso());
				consultaModificarDomicilio.append(", '");
				consultaModificarDomicilio.append(empleado.GetDepartamento()).append("'");
			} else {
				consultaModificarDomicilio = new StringBuilder("UPDATE Domicilio SET('");
				consultaModificarDomicilio.append(empleado.getVivienda()).append("', '");
				consultaModificarDomicilio.append(empleado.getCalle()).append("', ");
				consultaModificarDomicilio.append(empleado.getNumero()).append(", ");

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

			int idPersona = ObtenerIdPersona(empleado);

			int idDomicilio = ObtenerIdDomicilio(empleado);

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

			int idPersona = ObtenerIdPersona(empleado);

			int idTipoEmpleado = ObtenerIdTipoEmpleado(empleado);

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

			int legajoEmpleado = ObtenerLegajo(empleado);
			String tipo = ObtenerTipoEmpleado(empleado);

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

			int legajoEmpleado = ObtenerLegajo(empleado);
			String tipo = ObtenerTipoEmpleado(empleado);

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
	}*/
	
	public void UpdateNombreEmpleado(String legajoTabla, String valorTabla) {
		int idPersona = ObtenerIdPersona(Integer.parseInt(legajoTabla));
		UpdateColumnaEmpleado(idPersona, "Persona", "Nombre", valorTabla, "Id");
	}
	
	public void UpdateApellidoEmpleado(String legajoTabla, String valorTabla) {
		int idPersona = ObtenerIdPersona(Integer.parseInt(legajoTabla));
		UpdateColumnaEmpleado(idPersona, "Persona", "Apellido", valorTabla, "Id");
	}
	
	public void UpdateDniEmpleado(String legajoTabla, String valorTabla) {
		int idPersona = ObtenerIdPersona(Integer.parseInt(legajoTabla));
		UpdateColumnaEmpleado(idPersona, "Persona", "Dni", valorTabla, "Id");
	}
	
	public void UpdateEdadEmpleado(String legajoTabla, String valorTabla) {
		int idPersona = ObtenerIdPersona(Integer.parseInt(legajoTabla));
		UpdateColumnaEmpleado(idPersona, "Persona", "Edad", valorTabla, "Id");
	}	
	
	public void UpdateCalleEmpleado(String legajoTabla, String valorTabla) {
		int idPersona = ObtenerIdPersona(Integer.parseInt(legajoTabla));
		int idDomicilio = ObtenerIdDomicilio(idPersona);
		UpdateColumnaEmpleado(idDomicilio, "Domicilio", "Calle", valorTabla, "Id");
	}
	
	public void UpdateNumeroEmpleado(String legajoTabla, String valorTabla) {
		int idPersona = ObtenerIdPersona(Integer.parseInt(legajoTabla));
		int idDomicilio = ObtenerIdDomicilio(idPersona);
		UpdateColumnaEmpleado(idDomicilio, "Domicilio", "Calle", valorTabla, "Id");
	}
	
	public void UpdatePisoEmpleado(String legajoTabla, String valorTabla) {
		int idPersona = ObtenerIdPersona(Integer.parseInt(legajoTabla));
		int idDomicilio = ObtenerIdDomicilio(idPersona);
		UpdateColumnaEmpleado(idDomicilio, "Domicilio", "Calle", valorTabla, "Id");
	}
	
	public void UpdateDepartamentoEmpleado(String legajoTabla, String valorTabla) {
		int idPersona = ObtenerIdPersona(Integer.parseInt(legajoTabla));
		int idDomicilio = ObtenerIdDomicilio(idPersona);
		UpdateColumnaEmpleado(idDomicilio, "Domicilio", "Calle", valorTabla, "Id");
	}
	public void UpdateLenguajeEmpleado(String legajoTabla, String valorTabla) {
		int legajo = Integer.parseInt(legajoTabla);
		UpdateColumnaEmpleado(legajo, "Junior", "Lenguaje", valorTabla, "LegajoEmpleado");
	}
	
	public void UpdateRangoEmpleado(String legajoTabla, String valorTabla) {
		int legajo = Integer.parseInt(legajoTabla);
		UpdateColumnaEmpleado(legajo, "Gerente", "Rango", valorTabla, "LegajoEmpleado");
	}
	public void UpdateColumnaEmpleado(int id, String tabla, String columnaTabla, String valorTabla, String ColumnWhere) {
		try {
			java.sql.Statement ps = conexion.createStatement();

			StringBuilder consultaModificarEmpleado = new StringBuilder();
			
			consultaModificarEmpleado.append("UPDATE ").append(tabla)
			.append(" SET ").append(columnaTabla).append(" = '").append(valorTabla)
			.append("' ").append("WHERE "+ColumnWhere+" = " + id);
			
			
			
			int cantidadAfectadas = ps.executeUpdate(consultaModificarEmpleado.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void EliminarEmpleado(int legajo) {
		java.sql.Statement ps;

		try {
			//Crea Conexion con base de datos y si la hay usa esa
			ps = conexion.createStatement();
			// obtiene el Id Persona mediante el legajo y lo guarda
			int idPersona = this.ObtenerIdPersona(legajo);
			//obtiene el idDomicilio mediante idPersona y lo guarda
			int idDomicilio = this.ObtenerIdDomicilio(idPersona);
			//Se fija si es Gerente o empleado por el legajo y lo elimina
			if(this.ObtenerIdTipoEmpleado(legajo) == 1){
				ps.executeUpdate("DELETE Gerente Where LegajoEmpleado = " + legajo);
			}
			else{
				ps.executeUpdate("DELETE FROM Junior Where LegajoEmpleado = " + legajo);
			}
			//Elimina Empleado con la idPersona
			ps.executeUpdate("DELETE FROM empleado Where IdPersona = " + idPersona);
			//Elimina Multidomicilio con la idPersona
			ps.executeUpdate("DELETE FROM MultiDomicilio WHERE IdPersona =" + idPersona);
			//Elimina Persona con la idPersona
			ps.executeUpdate("DELETE FROM Persona Where Id = " + idPersona);
			//Elimina Domicilio con la idDomicilio
			ps.executeUpdate("DELETE FROM Domicilio WHERE Id = " + idDomicilio);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
