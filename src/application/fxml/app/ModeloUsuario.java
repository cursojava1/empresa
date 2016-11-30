package application.fxml.app;

public class ModeloUsuario {

	String nombreTabla, emailTabla, passTabla, nivelTabla;

	public ModeloUsuario(String nombreTabla, String emailTable, String passTabla, String nivelTabla) {
		super();
		this.nombreTabla = nombreTabla;
		this.emailTabla = emailTable;
		this.passTabla = passTabla;
		this.nivelTabla = nivelTabla;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getEmailTable() {
		return emailTabla;
	}

	public void setEmailTable(String emailTable) {
		this.emailTabla = emailTable;
	}

	public String getPassTabla() {
		return passTabla;
	}

	public void setPassTabla(String passTabla) {
		this.passTabla = passTabla;
	}

	public String getNivelTabla() {
		return nivelTabla;
	}

	public void setNivelTabla(String nivelTabla) {
		this.nivelTabla = nivelTabla;
	}

}
