package application.fxml.app;

public class ModeloEmpleado {

	String legajoTabla, nombreTabla, apellidoTabla, dniTabla, edadTabla, calleTabla, numeroTabla, lenguajeTabla, rangoTabla;

	public ModeloEmpleado() {

	}

	public ModeloEmpleado(String legajoTabla, String nombreTabla, String apellidoTabla, String dniTabla, String edadTabla,
			String calleTabla, String numeroTabla, String lenguajeTabla,String rangoTabla) {
		super();
		this.legajoTabla = legajoTabla;
		this.nombreTabla = nombreTabla;
		this.apellidoTabla = apellidoTabla;
		this.dniTabla = dniTabla;
		this.edadTabla = edadTabla;
		this.calleTabla = calleTabla;
		this.numeroTabla = numeroTabla;
		this.lenguajeTabla = lenguajeTabla;
		this.rangoTabla = rangoTabla;
	}

	public String getLegajoTabla() {
		return legajoTabla;
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
	
	public String getRangoTabla() {
		return rangoTabla;
	}

	public void setRangoTabla(String rangoTabla) {
		this.rangoTabla = rangoTabla;
	}
}
