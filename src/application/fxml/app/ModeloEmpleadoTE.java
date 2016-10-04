package application.fxml.app;

public class ModeloEmpleadoTE {

	String nombreTablaE, apellidoTablaE, dniTablaE, edadTablaE, calleTablaE, numeroTablaE, lenguajeTablaE;

	public ModeloEmpleadoTE() {
		
	}
	
	public ModeloEmpleadoTE(String nombreTablaE, String apellidoTablaE, String dniTablaE, String edadTablaE,
			String calleTablaE, String numeroTablaE, String lenguajeTablaE) {
		super();
		this.nombreTablaE = nombreTablaE;
		this.apellidoTablaE = apellidoTablaE;
		this.dniTablaE = dniTablaE;
		this.edadTablaE = edadTablaE;
		this.calleTablaE = calleTablaE;
		this.numeroTablaE = numeroTablaE;
		this.lenguajeTablaE = lenguajeTablaE;
	}

	public String getNombreTablaE() {
		return nombreTablaE;
	}

	public void setNombreTablaE(String nombreTablaE) {
		this.nombreTablaE = nombreTablaE;
	}

	public String getApellidoTablaE() {
		return apellidoTablaE;
	}

	public void setApellidoTablaE(String apellidoTablaE) {
		this.apellidoTablaE = apellidoTablaE;
	}

	public String getDniTablaE() {
		return dniTablaE;
	}

	public void setDniTablaE(String dniTablaE) {
		this.dniTablaE = dniTablaE;
	}

	public String getEdadTablaE() {
		return edadTablaE;
	}

	public void setEdadTablaE(String edadTablaE) {
		this.edadTablaE = edadTablaE;
	}

	public String getCalleTablaE() {
		return calleTablaE;
	}

	public void setCalleTablaE(String calleTablaE) {
		this.calleTablaE = calleTablaE;
	}

	public String getNumeroTablaE() {
		return numeroTablaE;
	}

	public void setNumeroTablaE(String numeroTablaE) {
		this.numeroTablaE = numeroTablaE;
	}

	public String getLenguajeTablaE() {
		return lenguajeTablaE;
	}

	public void setLenguajeTablaE(String lenguajeTablaE) {
		this.lenguajeTablaE = lenguajeTablaE;
	}
	
}
