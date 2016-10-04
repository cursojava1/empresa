package application.empresa.empleados;

public class Junior extends Desarrollador {
	
	public Junior() {
		iniciar();
	}

	public void Saludar() {
		System.out.println("Hola, soy un Junior");
	}

	@Override
	public String Legajo() {
		String datos = new String();
		StringBuilder sb = new StringBuilder();
		sb.append("Legajo: ").append(legajo).append(",")
		.append("Lenguaje: ").append(lenguajeProgramacion);
		datos = sb.toString();
		return datos;
	}
	
	@Override
	public String ImprimirEnArchivo() {
		StringBuilder sb = new StringBuilder();
		sb.append(nombre).append(" ")
		.append(apellido).append(" ")
		.append(documento).append(" ")
		.append(edad).append(" ")
		.append(domicilio.ImprimirEnArchivo()).append(" ")
		.append(lenguajeProgramacion);
		return sb.toString();
	}
	
}
