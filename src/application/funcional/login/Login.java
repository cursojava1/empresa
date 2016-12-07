package application.funcional.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import application.basededatos.Configuracion;
import application.basededatos.ConfiguracionDB;
import application.basededatos.Database;

public class Login {
	
	Database database = Database.GetDatabase();
	ConfiguracionDB config = new ConfiguracionDB();
	
	@SuppressWarnings("resource")
	public boolean VerificarUsuario(String nombre, String password) {
		File archivo = new File("src\\application\\basededatos\\usuarios");
		FileReader fr = null;
		try {
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			System.out.println("LECTURA DE USUARIOS");
			String linea;
			try {
				linea = br.readLine();
				while(linea != null) {
					if (Verificar(linea, nombre, password)) {
						return true;
					}
					linea = br.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
	private boolean Verificar(String linea, String nombre, String password) {
		String[] datos = linea.split(" ");
		if(datos[0].equals(nombre) && datos[1].equals(password)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Verifica un usuario desde base de datos.
	 *
	 * @param nombre El nombre del usuario
	 * @param password La contrase�a del usuario
	 * @return El estado de la verificacion:<br>
	 * 		   Estado 0: Datos correctos<br>
	 * 		   Estado 1: Usuario incorrecto<br>
	 * 		   Estado 2: Contrase�a incorrecta
	 */
	public int VerificarUsuarioDB(String nombre, String password) {
		database.Conectar(config);
		String contrase�a = database.GetContrase�a(nombre);
		if (contrase�a == null) {
			return 1;
		} else if (password.equals(contrase�a)) {
			return 0;
		}
		return 2;
	}

	public void SetearConectar(String nombreDB, String direccionDB, String puerto){
		String a = Configuracion.getAppSetting("dataBaseCatalog");
		if(!Configuracion.getAppSetting("dataBaseCatalog").equals(nombreDB)){
			System.out.println("Nombre de Base de datos no reconocido, cargando dato predeterminado...");
			config.SetNombreDBDefault(nombreDB);
		} else {
			config.SetNombreDB(nombreDB);
		}
		if(!Configuracion.getAppSetting("dataBaseServer").equals(direccionDB)){
			System.out.println("Direcci�n no reconocida, cargando dato predeterminado...");
			config.SetDireccionDefault(direccionDB);
		}else{
			config.SetDireccionDB(direccionDB);
		}
		if(!Configuracion.getAppSetting("dataBasePassword").equals(puerto)){
			System.out.println("Puerto no reconocido, cargando dato predeterminado...");
			config.SetPuertoDefault(puerto);
		} else {
			config.SetPuerto(puerto);
		}
		database.Conectar(config);
	}
	
}
