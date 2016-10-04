package application.empresa.empleados;

import java.util.Date;

import application.empresa.persona.Persona;


public abstract class Empleado extends Persona {

	int legajo;
	String sindicato;
	Date fechaDeIngreso;
	
	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getSindicato() {
		return sindicato;
	}

	public void setSindicato(String sindicato) {
		this.sindicato = sindicato;
	}

	public Date getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	public void setFechaDeIngreso(Date fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}

	public abstract void Saludar();
	
	protected void iniciar() {}
	
	public abstract String Legajo();
	
	public abstract String ImprimirEnArchivo();
	
	
	
	
	
	
	
}
