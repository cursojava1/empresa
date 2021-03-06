package application.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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

	Login login;

	static String usuarioActual = "";
	
	@FXML
	TextField nombreUsuario, nombreDB, direccionDB, puerto;

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
	private void ClickBtnIngresar(ActionEvent event) throws IOException {
		login.SetearConectar(nombreDB.getText(), direccionDB.getText(),puerto.getText());
		// boolean resultado = login.VerificarUsuario(nombreUsuario.getText(),
		// passwordUsuario.getText());
		int estado = login.VerificarUsuarioDB(nombreUsuario.getText(), passwordUsuario.getText());
		switch (estado) {
		case 0:
			JOptionPane.showMessageDialog(null, "Bienvenido "+ nombreUsuario.getText()+"\n"
	                    + "Has ingresado satisfactoriamente al sistema",   "Mensaje de bienvenida",
	                    JOptionPane.INFORMATION_MESSAGE);
			usuarioActual = nombreUsuario.getText();
			Main.ShowApp();
			break;
		case 1:
			usuarioError.setText("El usuario no existe.");
			botonIngresar.setLayoutX(118.00);
			botonIngresar.setLayoutY(224.00);
			usuarioError.setVisible(true);
			break;
		case 2:
			usuarioError.setText("La contraseņa es incorrecta.");
			botonIngresar.setLayoutX(118.00);
			botonIngresar.setLayoutY(224.00);
			usuarioError.setVisible(true);
			break;
		}

	}

}
