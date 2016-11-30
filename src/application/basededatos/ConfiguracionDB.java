package application.basededatos;

public class ConfiguracionDB {
	protected String nombreDB;
	protected String direccionDB;
	protected String puerto;
	
	public ConfiguracionDB ()
	{
	}

	public void SetNombreDB(String nombreDB) {
		this.nombreDB = nombreDB;
		
	}
	
	public String GetNombreDB(){
		return nombreDB;
	}
	

	public void SetDireccionDB(String direccionDB) {
		this.direccionDB = direccionDB;
		
	}

	public void SetPuerto(String puerto) {
		this.puerto = puerto;
		
	}
	
	
	public String GetDireccionDB(){
		return direccionDB;
	}
	public String GetPuerto(){
		return puerto;
	}

	public void SetNombreDBDefault(String nombreDB) {
		this.nombreDB = Configuracion.getAppSetting("dataBaseCatalog");
	}

	public void SetDireccionDefault(String direccionDB) {
		this.direccionDB = Configuracion.getAppSetting("dataBaseServer");
	}

	public void SetPuertoDefault(String puerto) {
		this.puerto = Configuracion.getAppSetting("dataBasePassword");
	}
}
