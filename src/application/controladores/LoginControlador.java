package application.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.funcional.login.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControlador implements Initializable {

	Login login;
	
	@FXML
	TextField nombreUsuario;
	
	@FXML
	PasswordField passwordUsuario;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		login = new Login();
	}

	@FXML private void ClickBtnIngresar(ActionEvent event) throws IOException {
		boolean resultado = login.VerificarUsuario(nombreUsuario.getText(), passwordUsuario.getText());
		System.out.println(resultado);
		if(resultado) {
			Main.ShowApp();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
