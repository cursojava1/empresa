package application.controladores;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.basededatos.Database;
import application.empresa.empleados.Empleado;
import application.empresa.empleados.FactoryEmpleados;
import application.empresa.empleados.Gerente;
import application.empresa.empleados.Junior;
import application.empresa.usuario.Usuario;
import application.empresa.utils.Utils.ESTADOCIVIL;
import application.empresa.utils.Utils.NIVEL;
import application.empresa.utils.Utils.SEXO;
import application.empresa.utils.Utils.VIVIENDA;
import application.funcional.app.App;
import application.fxml.app.ModeloEmpleado;
import application.fxml.app.ModeloUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppControlador implements Initializable {

	App app;
	@FXML
	TextField nombre, apellido, dni, edad, calle, piso, numero, dpt, text1, Unick, Uemail, Upass, Upassconfirmar,driver,nombreDB,direccion,puerto;
	@FXML
	RadioButton rbcasa, rbdpt, Ucheckadmin, Ucheckinvitado;
	@FXML
	ChoiceBox<String> sexo, estado, usuarios;
	@FXML
	MenuItem CerrarApp;
	@FXML
	AnchorPane AnchorAgregarEmpleado, AnchorAdministrarEmpleado, AnchorAgregarUsuario, AnchorAdministrarUsuario;
	@FXML
	Label label1, labelPassMal;
	@FXML
	private TableView<ModeloEmpleado> tablaEmpleados;
	@FXML
	private TableColumn<ModeloEmpleado, String> legajoTabla, nombreTabla, apellidoTabla, dniTabla, edadTabla,
			calleTabla, numeroTabla, pisoTabla, departamentoTabla, lenguajeTabla, rangoTabla;

	ObservableList<ModeloEmpleado> empleados = FXCollections.observableArrayList();
	List<String> empleadosArchivo;

	@FXML
	private TableView<ModeloUsuario> Tusuarios;
	@FXML
	private TableColumn<ModeloUsuario, String> TUnick, TUemail, TUcontraseña, TUnivel;
	ObservableList<ModeloUsuario> observableUsuarios = FXCollections.observableArrayList();
	List<String> usuariosLista;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		app = new App();
		ObservableList<String> opcionesSexo = FXCollections.observableArrayList("Masculino", "Femenino");
		sexo.setItems(opcionesSexo);
		ObservableList<String> opcionesEstado = FXCollections.observableArrayList("Casado", "Soltero", "Viudo",
				"Divorciado", "Concubino");
		estado.setItems(opcionesEstado);
		rbcasa.setSelected(true);

		tablaEmpleados.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		legajoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("LegajoTabla"));
		nombreTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("NombreTabla"));
		apellidoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("ApellidoTabla"));
		dniTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("DniTabla"));
		edadTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("EdadTabla"));
		calleTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("CalleTabla"));
		numeroTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("NumeroTabla"));
		pisoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("PisoTabla"));
		departamentoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("DepartamentoTabla"));
		lenguajeTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("LenguajeTabla"));
		rangoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("RangoTabla"));

		legajoTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));
		nombreTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));
		apellidoTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));
		dniTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));
		edadTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));
		calleTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));
		numeroTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));
		pisoTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));
		departamentoTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));
		lenguajeTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));
		rangoTabla.prefWidthProperty().bind(tablaEmpleados.widthProperty().divide(11));

		ObservableList<String> opcionesUsuarios = FXCollections.observableArrayList("Gerente", "Desarrollador");
		usuarios.setItems(opcionesUsuarios);

		
		ListarEmpleados();

		TUnick.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("NombreTabla"));
		TUemail.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("EmailTable"));
		TUcontraseña.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("PassTabla"));
		TUnivel.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("NivelTabla"));

		usuariosLista = app.ExtraerUsuariosDB();
		ModeloUsuario modeloUsuario;
		String[] datosUsuario = new String[5];
		for (String usuario : usuariosLista) {
			datosUsuario = usuario.split(" ");
			LimpiarDatos(datosUsuario); // 1 = nick / 3 = contraseña / 2 = email
										// / 4 = Nivel
			modeloUsuario = new ModeloUsuario(datosUsuario[1], datosUsuario[3], datosUsuario[2], datosUsuario[4]);
			observableUsuarios.add(modeloUsuario);
		}
		Tusuarios.setItems(observableUsuarios);
	}
	
	@FXML
	private void ClickDpt() {
		piso.setDisable(false);
		dpt.setDisable(false);
	}

	@FXML
	private void ClickCasa() {
		piso.setDisable(true);
		dpt.setDisable(true);
	}

	@FXML
	private void SeleccionarUsuario() {
		String valor = usuarios.getValue();
		if (valor != null) {
			if (valor.equals("Gerente")) {
				label1.setText("Rango");
			} else {
				label1.setText("Lenguaje");
			}
			label1.setVisible(true);
			text1.setVisible(true);
		}
	}

	@FXML
	private void AgregarUsuario() {
		String valor = usuarios.getValue();
		if (valor != null) {
			if (valor.equals("Gerente")) {
				Gerente nuevo = (Gerente) FactoryEmpleados.GetEmpleado("gerente");
				nuevo.setRango(text1.getText());
				CompletarEmpleado(nuevo);
				app.GrabarEmpleadoDB(nuevo);
				LimpiarCamposEmpleado();
			} else {
				Junior nuevo = (Junior) FactoryEmpleados.GetEmpleado("junior");
				nuevo.SetLenguajeProgramacion(text1.getText());
				CompletarEmpleado(nuevo);
				app.GrabarEmpleadoDB(nuevo);
				LimpiarCamposEmpleado();
			}
		}
	}
	
	private void LimpiarCamposEmpleado(){
		nombre.clear();
		apellido.clear();
		dni.clear();
		sexo.setValue(null);
		estado.setValue(null);
		edad.clear();
		rbcasa.setSelected(true);
		calle.clear();
		numero.clear();
		piso.clear();
		dpt.clear();
		usuarios.setValue(null);
		text1.clear();		
	}

	private void CompletarEmpleado(Empleado nuevo) {
		nuevo.setFechaDeIngreso(Calendar.getInstance().getTime());
		nuevo.SetNombre(nombre.getText());
		nuevo.SetApellido(apellido.getText());
		nuevo.setDocumento(Integer.parseInt(dni.getText()));
		switch (sexo.getValue()) {
		case "Masculino":
			nuevo.setSexo(SEXO.MASCULINO);
			break;
		case "Femenino":
			nuevo.setSexo(SEXO.FEMENINO);
			break;
		}
		switch (estado.getValue()) {
		case "Casado":
			nuevo.SetEstadoCivil(ESTADOCIVIL.CASADO);
			break;
		case "Soltero":
			nuevo.SetEstadoCivil(ESTADOCIVIL.SOLTERO);
			break;
		case "Divorciado":
			nuevo.SetEstadoCivil(ESTADOCIVIL.DIVORCIADO);
			break;
		case "Viudo":
			nuevo.SetEstadoCivil(ESTADOCIVIL.VIUDO);
			break;
		case "Concubino":
			nuevo.SetEstadoCivil(ESTADOCIVIL.CONCUBINO);
			break;
		}
		nuevo.setEdad(Short.parseShort(edad.getText()));

		if (rbcasa.isSelected()) {
			nuevo.setVivienda(VIVIENDA.CASA);
			nuevo.SetDomicilio(calle.getText(), Short.parseShort(numero.getText()));
		} else {
			nuevo.setVivienda(VIVIENDA.DEPARTAMENTO);
			nuevo.SetDomicilio(calle.getText(), Short.parseShort(numero.getText()), Short.parseShort(piso.getText()),
					dpt.getText());
		}
	}

	@FXML
	private void EliminarEmpleado() {
		int index = tablaEmpleados.getSelectionModel().getSelectedIndex();
		empleados.remove(index);
		app.EliminarEmpleado(index);
	}
	
	private void ConfigurarDB (Database configuracion){
		configuracion.SetDriver(driver.getText());
		configuracion.SetNombreDB(nombreDB.getText());
		configuracion.SetDireccion(direccion.getText());
		configuracion.SetPuerto(puerto.getText());
		 
	}

	private void LimpiarDatos(String[] datos) {
		for (int i = 0; i < datos.length; i++) {
			if (datos[i].equals("null")) {
				datos[i] = "-";
			}
		}
	}

	@FXML
	private void CerrarApp() {
		app.CerrarApp();

	}

	private void OcultarTodosAnchorPane() {
		AnchorAgregarEmpleado.setVisible(false);
		AnchorAdministrarEmpleado.setVisible(false);
		AnchorAgregarUsuario.setVisible(false);
		AnchorAdministrarUsuario.setVisible(false);
	}

	@FXML
	private void MostrarAgregarEmpleado() {
		OcultarTodosAnchorPane();
		AnchorAgregarEmpleado.setVisible(true);
	}

	@FXML
	private void MostrarAdministrarEmpleado() {
		OcultarTodosAnchorPane();
		AnchorAdministrarEmpleado.setVisible(true);
		tablaEmpleados.getItems().clear();
		ListarEmpleados();
	}

	@FXML
	private void MostrarAgregarUsuario() {
		OcultarTodosAnchorPane();
		AnchorAgregarUsuario.setVisible(true);
	}

	@FXML
	private void MostrarAdministrarUsuario() {
		OcultarTodosAnchorPane();
		AnchorAdministrarUsuario.setVisible(true);
	}
	
	private void ListarEmpleados(){
		empleadosArchivo = app.ExtraerEmpleadosDB();
		ModeloEmpleado modeloEmpleado;
		String[] datos = new String[11];
		for (String empleado : empleadosArchivo) {
			datos = empleado.split(" ");
			LimpiarDatos(datos);
			modeloEmpleado = new ModeloEmpleado(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6],
					datos[7], datos[8], datos[9], datos[10]);
			empleados.add(modeloEmpleado);
		}
		
		tablaEmpleados.setItems(empleados);
	}

	@FXML
	private void AgregarUsuarioNuevo() {
		Usuario nuevo = new Usuario();
		if (CompletarUsuarioNuevo(nuevo)) {
			app.GrabarUsuarioDB(nuevo);
		}
	}

	private boolean CompletarUsuarioNuevo(Usuario nuevo) {
		nuevo.setNombre(Unick.getText());
		nuevo.setEmail(Uemail.getText());
		if (ComprobarContraseñas()) {
			nuevo.setContraseña(Upass.getText());
			if (Ucheckadmin.isSelected()) {
				nuevo.setNivel(NIVEL.ADMINISTRADOR);
			} else {
				nuevo.setNivel(NIVEL.INVITADO);
			}
			return true;
		}
		labelPassMal.setText("La contraseña es incorrecta");
		return false;

	}

	private boolean ComprobarContraseñas() {
		if (Upass.getText().equals(Upassconfirmar.getText())) {
			return true;
		}
		return false;

	}

}
