package application.basededatos;

public class ConfiguracionDB {
	protected String nombreDB;
	protected String direccionDB;
	protected int puerto;
	
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

	public void SetPuerto(int puerto) {
		this.puerto = puerto;
		
	}
	
	
	public String GetDireccionDB(){
		return direccionDB;
	}
	public int GetPuerto(){
		return puerto;
	}

}
