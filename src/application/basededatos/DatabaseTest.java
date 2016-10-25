package application.basededatos;

public class DatabaseTest {

	public static void main(String[] args) {
		
		Database database = Database.GetDatabase();
		
		database.Conectar();

		database.Desconectar();
	}

}
