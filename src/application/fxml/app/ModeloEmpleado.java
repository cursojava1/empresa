package application.fxml.app;

public class ModeloEmpleado {

	String nombreTabla, apellidoTabla, dniTabla, edadTabla, calleTabla, numeroTabla, lenguajeTabla;

	public ModeloEmpleado() {

	}

	public ModeloEmpleado(String nombreTabla, String apellidoTabla, String dniTabla, String edadTabla,
			String calleTabla, String numeroTabla, String lenguajeTabla) {
		super();
		this.nombreTabla = nombreTabla;
		this.apellidoTabla = apellidoTabla;
		this.dniTabla = dniTabla;
		this.edadTabla = edadTabla;
		this.calleTabla = calleTabla;
		this.numeroTabla = numeroTabla;
		this.lenguajeTabla = lenguajeTabla;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTablaM) {
		this.nombreTabla = nombreTablaM;
	}

	public String getApellidoTabla() {
		return apellidoTabla;
	}

	public void setApellidoTabla(String apellidoTabla) {
		this.apellidoTabla = apellidoTabla;
	}

	public String getDniTabla() {
		return dniTabla;
	}

	public void setDniTabla(String dniTabla) {
		this.dniTabla = dniTabla;
	}

	public String getEdadTabla() {
		return edadTabla;
	}

	public void setEdadTabla(String edadTabla) {
		this.edadTabla = edadTabla;
	}

	public String getCalleTabla() {
		return calleTabla;
	}

	public void setCalleTablaM(String calleTabla) {
		this.calleTabla = calleTabla;
	}

	public String getNumeroTabla() {
		return numeroTabla;
	}

	public void setNumeroTabla(String numeroTabla) {
		this.numeroTabla = numeroTabla;
	}

	public String getLenguajeTabla() {
		return lenguajeTabla;
	}

	public void setLenguajeTabla(String lenguajeTabla) {
		this.lenguajeTabla = lenguajeTabla;
	}
}
