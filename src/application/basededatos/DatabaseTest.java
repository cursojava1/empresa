package application.basededatos;

public class DatabaseTest {

	static ConfiguracionDB config;
	
	public static void main(String[] args) {
		
		Database database = Database.GetDatabase();

		database.Conectar(config);

		database.Desconectar();
	}

}
