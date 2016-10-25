package application.fxml.app;

public class ModeloEmpleadoTM {

	String nombreTablaM, apellidoTablaM, dniTablaM, edadTablaM, calleTablaM, numeroTablaM, lenguajeTablaM;

	public ModeloEmpleadoTM() {

	}

	public ModeloEmpleadoTM(String nombreTablaM, String apellidoTablaM, String dniTablaM, String edadTablaM,
			String calleTablaM, String numeroTablaM, String lenguajeTablaM) {
		super();
		this.nombreTablaM = nombreTablaM;
		this.apellidoTablaM = apellidoTablaM;
		this.dniTablaM = dniTablaM;
		this.edadTablaM = edadTablaM;
		this.calleTablaM = calleTablaM;
		this.numeroTablaM = numeroTablaM;
		this.lenguajeTablaM = lenguajeTablaM;
	}

	public String getNombreTablaM() {
		return nombreTablaM;
	}

	public void setNombreTablaM(String nombreTablaM) {
		this.nombreTablaM = nombreTablaM;
	}

	public String getApellidoTablaM() {
		return apellidoTablaM;
	}

	public void setApellidoTablaM(String apellidoTablaM) {
		this.apellidoTablaM = apellidoTablaM;
	}

	public String getDniTablaM() {
		return dniTablaM;
	}

	public void setDniTablaM(String dniTablaM) {
		this.dniTablaM = dniTablaM;
	}

	public String getEdadTablaM() {
		return edadTablaM;
	}

	public void setEdadTablaM(String edadTablaM) {
		this.edadTablaM = edadTablaM;
	}

	public String getCalleTablaM() {
		return calleTablaM;
	}

	public void setCalleTablaM(String calleTablaM) {
		this.calleTablaM = calleTablaM;
	}

	public String getNumeroTablaM() {
		return numeroTablaM;
	}

	public void setNumeroTablaM(String numeroTablaM) {
		this.numeroTablaM = numeroTablaM;
	}

	public String getLenguajeTablaM() {
		return lenguajeTablaM;
	}

	public void setLenguajeTablaM(String lenguajeTablaM) {
		this.lenguajeTablaM = lenguajeTablaM;
	}
}
