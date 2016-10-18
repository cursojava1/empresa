package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//Hola Magic!

public class Main extends Application {
	
	private static Stage loginStage;
	private static Stage appStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			loginStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/application/fxml/login/Login.fxml"));
			Scene scene = new Scene(root,300,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			loginStage.setResizable(false);
			loginStage.setScene(scene);
			loginStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void ShowApp() throws IOException {
		Parent root = FXMLLoader.load(Main.class.getResource("/application/fxml/app/App.fxml"));
		appStage = new Stage();
		Scene scene = new Scene(root,600,400);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		appStage.setScene(scene);
		appStage.setTitle("ABM de Usuarios");
		loginStage.close();
		appStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
