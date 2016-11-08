package application.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.funcional.login.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginControlador implements Initializable {

	Login login;

	@FXML
	TextField nombreUsuario;

	@FXML
	PasswordField passwordUsuario;

	@FXML
	Text usuarioError;

	@FXML
	Button botonIngresar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		login = new Login();
	}

	@FXML
	private void ClickBtnIngresar(ActionEvent event) throws IOException {
		//boolean resultado = login.VerificarUsuario(nombreUsuario.getText(), passwordUsuario.getText());
		int estado = login.VerificarUsuarioDB(nombreUsuario.getText(), passwordUsuario.getText());
		switch (estado) {
		case 0:
			Main.ShowApp();
			break;
		case 1:
			usuarioError.setText("El usuario no existe.");
			botonIngresar.setLayoutX(118.00);
			botonIngresar.setLayoutY(224.00);
			usuarioError.setVisible(true);
			break;
		case 2:
			usuarioError.setText("La contraseña es incorrecta.");
			botonIngresar.setLayoutX(118.00);
			botonIngresar.setLayoutY(224.00);
			usuarioError.setVisible(true);
			break;
		}
		
	}

}
