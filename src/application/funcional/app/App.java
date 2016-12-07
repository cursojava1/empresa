package application.funcional.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import application.Main;
import application.basededatos.ConfiguracionDB;
import application.basededatos.Database;
import application.empresa.empleados.Empleado;
import application.empresa.usuario.Usuario;
import application.fxml.app.ModeloEmpleado;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class App {

	Database database = Database.GetDatabase();
	ConfiguracionDB config;
	public App() {

	}

	public void GrabarEmpleadoDB(Empleado nuevo) {
		
		if (database.InsertPersona(nuevo)) {
			System.out.println("Insert Persona");
		};
		if (database.InsertDomicilio(nuevo)) {
			System.out.println("Insert Domicilio");
		}
		if (database.InsertEmpleado(nuevo)) {
			System.out.println("Insert Empleado");
		}

		//database.InsertMultiDomicilio(nuevo);

		if (nuevo.getClass().getSimpleName().equals("Gerente")) {
			database.InsertGerente(nuevo);
			System.out.println("Insert Gerente");
			
		} else {
			database.InsertJunior(nuevo);
			System.out.println("Insert Junior");

		}

	}

	public void GrabarUsuarioDB(Usuario nuevo) {
		database.InsertUsuario(nuevo);
		//database.Desconectar();
	}
	
	public void EliminarUsuarioDB(Usuario nuevo){
		database.DeleteUsuario(nuevo);
		//database.Desconectar();
	}
	

	public void CerrarApp() {
		Main.CerrarApp();
		database.Desconectar();
	}

	public void GrabarEmpleado(Empleado nuevo) {
		File archivo = new File("src\\application\\basededatos\\empleados");
		File archivoTemporal = new File(archivo.getAbsolutePath() + ".tmp");
		FileReader fr = null;
		PrintWriter pw = null;
		try {
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			pw = new PrintWriter(new FileWriter(archivoTemporal));
			System.out.println("AGREGAR NUEVO USUARIO");
			String linea;
			try {
				while ((linea = br.readLine()) != null) {
					pw.println(linea);
					pw.flush();
				}
				pw.println(nuevo.ImprimirEnArchivo());
				pw.flush();
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
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				fr.close();
				pw.close();
				archivo.delete();
				archivoTemporal.renameTo(archivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<String> ExtraerUsuariosDB() {
		List<String> result = new ArrayList<>();
		result = database.SelectUsuarios();
		return result;
	}

	public List<String> ExtraerEmpleadosDB() {
		List<String> result = new ArrayList<>();
		database.Conectar(config);
		result = database.SelectEmpleados();
		return result;

	}

	public List<String> ExtraerEmpleados() {
		File archivo = new File("src\\application\\basededatos\\empleados");
		FileReader fr = null;
		List<String> empleados = new ArrayList<>();
		try {
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			System.out.println("EXTRACCION DE EMPLEADOS");
			String linea;
			try {
				linea = br.readLine();
				while (linea != null) {
					empleados.add(linea);
					linea = br.readLine();
				}
				br.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return empleados;
	}

	public void EliminarEmpleado(int index) {
		File archivo = new File("src\\application\\basededatos\\empleados");
		File archivoTemporal = new File(archivo.getAbsolutePath() + ".tmp");
		FileReader fr = null;
		PrintWriter pw = null;
		try {
			// Comentario
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			pw = new PrintWriter(new FileWriter(archivoTemporal));
			System.out.println("ELIMINAR EMPLEADO");
			String linea;
			try {
				int i = 0;
				while ((linea = br.readLine()) != null) {
					if (i != index) {
						pw.println(linea);
						pw.flush();
					}
					i++;
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
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				fr.close();
				pw.close();
				archivo.delete();
				archivoTemporal.renameTo(archivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void ModificarEmpleadoDB(String legajoTabla, String columnaTabla, String valorTabla) {
		switch(columnaTabla){
		case "Nombre":
			database.UpdateNombreEmpleado(legajoTabla, valorTabla);
			break;
		case "Apellido":
			database.UpdateApellidoEmpleado(legajoTabla, valorTabla);
			break;		
		case "Dni":			
			database.UpdateDniEmpleado(legajoTabla, valorTabla);
			break;
		case "Edad":
			database.UpdateEdadEmpleado(legajoTabla, valorTabla);
			break;
		case "Calle":
			database.UpdateCalleEmpleado(legajoTabla, valorTabla);
			break;
		case "Numero":
			database.UpdateNumeroEmpleado(legajoTabla, valorTabla);
			break;
		case "Piso":
			database.UpdatePisoEmpleado(legajoTabla, valorTabla);
			break;
		case "Departamento":
			database.UpdateDepartamentoEmpleado(legajoTabla, valorTabla);
			break;
		case "Lenguaje":
			database.UpdateLenguajeEmpleado(legajoTabla, valorTabla);
			break;
		case "Rango":
			database.UpdateRangoEmpleado(legajoTabla, valorTabla);
			break;	
		
		}	
	}
	//Recibo legajo y elimino empleado Puente entre database y Controlador
	public void EliminarEmpleadoDB(int legajo){
		database.EliminarEmpleado(legajo);
	}
	

}
	
