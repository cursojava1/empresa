package application.empresa.empleados;

public class Gerente extends Empleado {

	String rango;
	short evaluacion;
	
	public String EvaluacionMensual() {
		return ("La evaluacion mensual es: " + evaluacion);
	}
	
	protected void CargarEvaluacionMensual(short evaluacion) {
		this.evaluacion = evaluacion;
	}
	
	public void Saludar() {
		System.out.println("Hola, soy un Gerente");
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public short getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(short evaluacion) {
		this.evaluacion = evaluacion;
	}

	@Override
	public String Legajo() {
		String datos = new String();
		StringBuilder sb = new StringBuilder();
		sb.append("Legajo: ").append(legajo).append(",")
		.append("Rango: ").append(rango).append(",")
		.append("Evaluacion: ").append(evaluacion);
		datos = sb.toString();
		return datos;
	}

	@Override
	public String ImprimirEnArchivo() {
		String datos = "";
		datos.concat(nombre).concat(" ")
		.concat(apellido).concat(" ")
		.concat(String.valueOf(documento)).concat(" ")
		.concat(String.valueOf(edad)).concat(" ")
		.concat(String.valueOf(sexo)).concat(" ")
		.concat(String.valueOf(estadoCivil))
		.concat(domicilio.ImprimirEnArchivo()).concat(" ")
		.concat(rango);
		return datos;
	}
	
	
	
	
	
	
	
	
	
	
	
}
