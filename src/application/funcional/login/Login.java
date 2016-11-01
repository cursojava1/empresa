package application.funcional.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import application.basededatos.Database;

public class Login {

	Database database = Database.GetDatabase();
	
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
	
	public HashMap<Integer, Boolean> VerificarUsuarioDB(String nombre, String password) {
		HashMap<Integer, Boolean> estados = new HashMap<>();
		HashMap<String, String> datos = new HashMap<>();
		database.Conectar();
		datos = database.GetNombreContraseña(nombre);
		
		
		
		
		
		return estados;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
