package application.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.basededatos.ConfiguracionDB;
import application.funcional.login.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

public class LoginControlador implements Initializable {
	
	ConfiguracionDB config = new ConfiguracionDB();

	Login login;

	@FXML
	TextField nombreUsuario,nombreDB,direccionDB,puerto;

	@FXML
	PasswordField passwordUsuario;

	@FXML
	Text usuarioError;

	@FXML
	Button botonIngresar;
	
	@FXML
	TitledPane desplegableDB;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		login = new Login();
	}

	@FXML
	private void ConfigurarDB (ConfiguracionDB configuracion){
		configuracion.SetNombreDB(nombreDB.getText());
		System.out.println(nombreDB.getText());
		configuracion.SetDireccionDB(direccionDB.getText());
		configuracion.SetPuerto(Integer.parseInt(puerto.getText()));
		 
	}
	
	@FXML
	private void ClickBtnIngresar(ActionEvent event) throws IOException {
		ConfigurarDB(config);
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
