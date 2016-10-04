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

import application.empresa.empleados.Empleado;

public class App {

	public App() {
		
	}
	
	public void GrabarEmpleado(Empleado nuevo) {
		File archivo = new File("D:\\Workspace-Mars-2\\EmpresaFX\\src\\application\\basededatos\\empleados");
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
	
	public List<String> ExtraerEmpleados() {
		File archivo = new File("D:\\Workspace-Mars-2\\EmpresaFX\\src\\application\\basededatos\\empleados");
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
		File archivo = new File("D:\\Workspace-Mars-2\\EmpresaFX\\src\\application\\basededatos\\empleados");
		File archivoTemporal = new File(archivo.getAbsolutePath() + ".tmp");
		FileReader fr = null;
		PrintWriter pw = null;
		try {
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
