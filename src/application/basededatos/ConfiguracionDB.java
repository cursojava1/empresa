package application.basededatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import application.empresa.empleados.Empleado;

public abstract class ConfiguracionDB {
	private String driver;
	private String nombreDB;
	private String direccionDB;
	private int puerto;
	
	public ConfiguracionDB ()
	{
		
	}
	public void SetDriver(String driver) {
		this.driver = driver;
		
	}

	public void SetNombreDB(String nombreDB) {
		this.nombreDB = nombreDB;
		
	}

	public void SetDireccion(String direccionDB) {
		this.direccionDB = direccionDB;
		
	}

	public void SetPuerto(int puerto) {
		this.puerto = puerto;
		
	}
	public abstract String ImprimirArchivoDB();
}
