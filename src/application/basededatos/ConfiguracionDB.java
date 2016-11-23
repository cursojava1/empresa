package application.basededatos;

public class ConfiguracionDB {
	private String nombreDB;
	private String direccionDB;
	private int puerto;
	
	public ConfiguracionDB ()
	{
		
	}
	/*public void SetDriver(String driver) {
		this.driver = driver;
		
	}*/

	public void SetNombreDB(String nombreDB) {
		this.nombreDB = nombreDB;
		
	}

	public void SetDireccionDB(String direccionDB) {
		this.direccionDB = direccionDB;
		
	}

	public void SetPuerto(int puerto) {
		this.puerto = puerto;
		
	}
	
	public String GetNombreDB(){
		return nombreDB;
	}
	
	public String GetDireccionDB(){
		return direccionDB;
	}
	public int GetPuerto(){
		return puerto;
	}
	//public abstract String ImprimirArchivoDB();

}
