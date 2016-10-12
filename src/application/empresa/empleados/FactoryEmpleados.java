package application.empresa.empleados;

public class FactoryEmpleados {

	public static Empleado GetEmpleado(String tipo) {
		switch (tipo) {
			case "gerente" : return new Gerente();
			case "junior" : return new Junior();
			default : return null;
		}
	}
	
}
