package application.empresa.empleados;

public abstract class Desarrollador extends Empleado {

	protected String lenguajeProgramacion;
	
	public Desarrollador() {
		iniciar();
	}
	
	public void SetLenguajeProgramacion(String lenguajes) {
		lenguajeProgramacion = lenguajes;
	}
	
	public String GetLenguajes() {
		return lenguajeProgramacion;
	}
	
	
	
}
