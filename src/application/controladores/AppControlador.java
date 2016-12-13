package application.controladores;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AppControlador implements Initializable {

	App app;
	@FXML
	TextField nombre, apellido, dni, edad, calle, piso, numero, dpt, text1, Unick, Uemail, Upass, Upassconfirmar,
			driver, nombreDB, direccion, puerto, Ueliminar;
	@FXML
	RadioButton rbcasa, rbdpt, Ucheckadmin, Ucheckinvitado;
	@FXML
	ChoiceBox<String> sexo, estado, usuarios;
	@FXML
	MenuItem CerrarApp, AgregEmpleado, Usuarios;
	@FXML
	AnchorPane AnchorAgregarEmpleado, AnchorAdministrarEmpleado, AnchorAgregarUsuario, AnchorAdministrarUsuario,
			AnchorAutores;
	@FXML
	ImageView ErrorNombre, ErrorApellido, ErrorEdad, ErrorDocumento, ErrorSexo, ErrorEstadoCivil, ErrorCalle,
			ErrorNumero, ErrorPiso, ErrorDpt, ErrorEmpleado, ErrorSeleccionar, ErrorText1, ErrorNick, ErrorMail,
			ErrorPass, ErrorConfirmPass, WarningNombre, WarningApellido, WarningEdad, WarningDocumento, WarningCalle,
			WarningNumero, WarningPiso, WarningDpt, WarningNick, WarningMail, WarningPass, WarningConfirmPass;
	@FXML
	Label label1, labelPassMal, Lusuario, Laviso;
	@FXML
	Button Baceptar, btnEliminarEmpleado, btnReiniciar, btnActualizar;
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
		tablaEmpleados.setEditable(true);

		legajoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("LegajoTabla"));

		nombreTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("NombreTabla"));
		nombreTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		nombreTabla.setOnEditCommit(new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
			@Override
			public void handle(CellEditEvent<ModeloEmpleado, String> t) {
				((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setNombreTabla(t.getNewValue());
				app.ModificarEmpleadoDB(
						((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.getLegajoTabla(),
						nombreTabla.getText(), t.getNewValue());
				RefrescarEmpleados();
			}
		});

		apellidoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("ApellidoTabla"));
		apellidoTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		apellidoTabla.setOnEditCommit(new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
			@Override
			public void handle(CellEditEvent<ModeloEmpleado, String> t) {
				((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setNombreTabla(t.getNewValue());
				app.ModificarEmpleadoDB(
						((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.getLegajoTabla(),
						apellidoTabla.getText(), t.getNewValue());
				RefrescarEmpleados();
			}
		});
		dniTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("DniTabla"));
		dniTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		dniTabla.setOnEditCommit(new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
			@Override
			public void handle(CellEditEvent<ModeloEmpleado, String> t) {
				((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setNombreTabla(t.getNewValue());
				app.ModificarEmpleadoDB(
						((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.getLegajoTabla(),
						dniTabla.getText(), t.getNewValue());
				RefrescarEmpleados();
			}
		});
		edadTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("EdadTabla"));
		edadTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		edadTabla.setOnEditCommit(new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
			@Override
			public void handle(CellEditEvent<ModeloEmpleado, String> t) {
				((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setNombreTabla(t.getNewValue());
				app.ModificarEmpleadoDB(
						((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.getLegajoTabla(),
						edadTabla.getText(), t.getNewValue());
				RefrescarEmpleados();
			}
		});
		calleTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("CalleTabla"));
		calleTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		calleTabla.setOnEditCommit(new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
			@Override
			public void handle(CellEditEvent<ModeloEmpleado, String> t) {
				((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setNombreTabla(t.getNewValue());
				app.ModificarEmpleadoDB(
						((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.getLegajoTabla(),
						calleTabla.getText(), t.getNewValue());
				RefrescarEmpleados();
			}
		});
		numeroTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("NumeroTabla"));
		numeroTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		numeroTabla.setOnEditCommit(new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
			@Override
			public void handle(CellEditEvent<ModeloEmpleado, String> t) {
				((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setNombreTabla(t.getNewValue());
				app.ModificarEmpleadoDB(
						((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.getLegajoTabla(),
						numeroTabla.getText(), t.getNewValue());
				RefrescarEmpleados();
			}
		});
		pisoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("PisoTabla"));
		pisoTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		pisoTabla.setOnEditCommit(new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
			@Override
			public void handle(CellEditEvent<ModeloEmpleado, String> t) {
				((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setNombreTabla(t.getNewValue());
				app.ModificarEmpleadoDB(
						((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.getLegajoTabla(),
						pisoTabla.getText(), t.getNewValue());
				RefrescarEmpleados();
			}
		});
		departamentoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("DepartamentoTabla"));
		departamentoTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		departamentoTabla.setOnEditCommit(new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
			@Override
			public void handle(CellEditEvent<ModeloEmpleado, String> t) {
				((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setNombreTabla(t.getNewValue());
				app.ModificarEmpleadoDB(
						((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.getLegajoTabla(),
						departamentoTabla.getText(), t.getNewValue());
				RefrescarEmpleados();
			}
		});
		lenguajeTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("LenguajeTabla"));
		lenguajeTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		lenguajeTabla.setOnEditCommit(new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
			@Override
			public void handle(CellEditEvent<ModeloEmpleado, String> t) {
				((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setNombreTabla(t.getNewValue());
				app.ModificarEmpleadoDB(
						((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.getLegajoTabla(),
						lenguajeTabla.getText(), t.getNewValue());
				RefrescarEmpleados();
			}
		});
		rangoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("RangoTabla"));
		rangoTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		rangoTabla.setOnEditCommit(new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
			@Override
			public void handle(CellEditEvent<ModeloEmpleado, String> t) {
				((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setNombreTabla(t.getNewValue());
				app.ModificarEmpleadoDB(
						((ModeloEmpleado) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.getLegajoTabla(),
						rangoTabla.getText(), t.getNewValue());
				RefrescarEmpleados();
			}
		});

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

		// ListarEmpleados();

		TUnick.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("NombreTabla"));
		TUemail.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("EmailTable"));
		TUcontraseña.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("PassTabla"));
		TUnivel.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("NivelTabla"));

		ListarUsuarios();

		// Diferencia de nivel Administrador/Visitante
		if (app.GetNivelUsuarioDB(LoginControlador.usuarioActual).equals("Administrador")) {
			AgregEmpleado.setDisable(false);
			Usuarios.setDisable(false);
			btnEliminarEmpleado.setDisable(false);
		} else {
			AnchorAdministrarEmpleado.setVisible(true);
			AnchorAgregarEmpleado.setVisible(false);
			tablaEmpleados.setEditable(false);
		}
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
		piso.clear();
		dpt.clear();
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
		if (ConfirmarCampos()) {
			String valor = usuarios.getValue();
			if (valor != null) {
				if (valor.equals("Gerente")) {
					Gerente nuevo = (Gerente) FactoryEmpleados.GetEmpleado("gerente");
					nuevo.setRango(text1.getText());
					CompletarEmpleado(nuevo);
					app.GrabarEmpleadoDB(nuevo);
					// Mensajes de confirmacion al agregar empleado
					JOptionPane.showMessageDialog(null,
							"El gerente " + nombre.getText() + " ha sido agregado con éxito", "Mensaje",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					Junior nuevo = (Junior) FactoryEmpleados.GetEmpleado("junior");
					nuevo.SetLenguajeProgramacion(text1.getText());
					CompletarEmpleado(nuevo);
					app.GrabarEmpleadoDB(nuevo);
					// Mensajes de confirmacion al agregar empleado
					JOptionPane.showMessageDialog(null, "El junior " + nombre.getText() + " ha sido agregado con éxito",
							"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
				LimpiarCamposEmpleado();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"El	 empleado " + nombre.getText() + " no se ha podido agregar." + "\n"
							+ "Verifique que todos los campos se hayan completado correctamente.",
					"Mensaje", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Confirma los campos de empleados
	// Si los campos estan vacios o tienen error aparecen las advertencias.
	@FXML
	private boolean ConfirmarCampos() {
		OcultarAdvertencias();
		boolean campos = true;
		if (nombre.getText().isEmpty()) {
			ErrorNombre.setVisible(true);
			campos = false;
		} else if (!ContieneSoloLetras(nombre.getText())) {
			WarningNombre.setVisible(true);
			campos = false;
		}
		if (apellido.getText().isEmpty()) {
			ErrorApellido.setVisible(true);
			campos = false;
		} else if (!ContieneSoloLetras(apellido.getText())) {
			WarningApellido.setVisible(true);
			campos = false;
		}
		if (edad.getText().isEmpty()) {
			ErrorEdad.setVisible(true);
			campos = false;
		} else if (!ContieneSoloNumeros(edad.getText()) || edad.getText().length() > 2) {
			WarningEdad.setVisible(true);
			campos = false;
		} else if (ContieneSoloNumeros(edad.getText()) && edad.getText().length() <= 2) {
			int e = Integer.valueOf(edad.getText());
			if (e < 18) {
				WarningEdad.setVisible(true);
				campos = false;
			}
		}
		if (dni.getText().isEmpty()) {
			ErrorDocumento.setVisible(true);
			campos = false;
		} else if (!ContieneSoloNumeros(dni.getText()) || dni.getText().length() < 6 || dni.getText().length() > 8) {
			WarningDocumento.setVisible(true);
			campos = false;
		}
		if (sexo.getValue() == null) {
			ErrorSexo.setVisible(true);
			campos = false;
		}
		if (estado.getValue() == null) {
			ErrorEstadoCivil.setVisible(true);
			campos = false;
		}
		if (calle.getText().isEmpty()) {
			ErrorCalle.setVisible(true);
			campos = false;
		} else if (!ContieneSoloLetras(calle.getText())) {
			WarningCalle.setVisible(true);
			campos = false;
		}
		if (numero.getText().isEmpty()) {
			ErrorNumero.setVisible(true);
			campos = false;
		} else if (!ContieneSoloNumeros(numero.getText())) {
			WarningNumero.setVisible(true);
			campos = false;
		}
		if (!piso.isDisabled() && piso.getText().isEmpty()) {
			ErrorPiso.setVisible(true);
			campos = false;
		} else if (!piso.isDisabled() && !ContieneSoloNumeros(piso.getText())) {
			WarningPiso.setVisible(true);
			campos = false;
		}
		if (!dpt.isDisabled() && dpt.getText().isEmpty()) {
			ErrorDpt.setVisible(true);
			campos = false;
		} else if (!ContieneSoloLetras(dpt.getText()) && !dpt.isDisabled()) {
			WarningDpt.setVisible(true);
			campos = false;
		}
		if (usuarios.getValue() == null) {
			ErrorEmpleado.setVisible(true);
			campos = false;
		}
		if (!text1.isVisible()) {
			ErrorSeleccionar.setVisible(true);
			campos = false;
		}
		if (text1.getText().isEmpty()) {
			ErrorText1.setVisible(true);
			campos = false;
		}
		return campos;
	}

	private boolean ContieneSoloNumeros(String texto) {
		try {
			Integer.valueOf(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean ContieneSoloLetras(String texto) {
		for (int i = 0; i < 10; i++) {
			if (texto.contains(String.valueOf(i))) {
				return false;
			}
		}
		return true;
	}

	@FXML
	private void LimpiarCamposEmpleado() {
		OcultarAdvertencias();
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

	// Oculta los triangulitos
	private void OcultarAdvertencias() {
		ErrorNombre.setVisible(false);
		ErrorApellido.setVisible(false);
		ErrorEdad.setVisible(false);
		ErrorDocumento.setVisible(false);
		ErrorSexo.setVisible(false);
		ErrorEstadoCivil.setVisible(false);
		ErrorCalle.setVisible(false);
		ErrorNumero.setVisible(false);
		ErrorPiso.setVisible(false);
		ErrorDpt.setVisible(false);
		ErrorEmpleado.setVisible(false);
		ErrorSeleccionar.setVisible(false);
		ErrorText1.setVisible(false);
		WarningNombre.setVisible(false);
		WarningApellido.setVisible(false);
		WarningEdad.setVisible(false);
		WarningDocumento.setVisible(false);
		WarningCalle.setVisible(false);
		WarningNumero.setVisible(false);
		WarningPiso.setVisible(false);
		WarningDpt.setVisible(false);
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
		// Guarda en Index ID de tabla selecionada
		int index = this.tablaEmpleados.getSelectionModel().getSelectedIndex();
		// Guarda en un string el Valor de la columna Index(Seleccionada)
		String LegajoString = legajoTabla.getCellObservableValue(index).getValue();
		// Crea un Integer
		Integer legajo = new Integer(0);
		// Le cambio el valor de un String A un Integer
		legajo = Integer.parseInt(LegajoString);
		// Elimino el empleado a traves del legajo
		app.EliminarEmpleadoDB(legajo);
		// Refresco Tabla
		this.RefrescarEmpleados();
	}

	@FXML // Mensajes de advertencia y confirmacion al eliminar empleados
	private void ConfirmarEliminarEmpleado() {
		int index = this.tablaEmpleados.getSelectionModel().getSelectedIndex();
		int aux = JOptionPane.showConfirmDialog(
				null, "Esta seguro de que desea eliminar el empleado "
						+ nombreTabla.getCellObservableValue(index).getValue() + "?",
				"Mensaje", JOptionPane.YES_NO_OPTION);
		if (aux == 0) {
			EliminarEmpleado();
			JOptionPane.showMessageDialog(null, "El empleado" + nombreTabla.getCellObservableValue(index).getValue()
					+ "ha sido eliminado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		}
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
		AnchorAutores.setVisible(false);
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
		RefrescarEmpleados();
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

	@FXML
	private void MostrarAutores() {
		OcultarTodosAnchorPane();
		AnchorAutores.setVisible(true);
	}

	private void ListarEmpleados() {
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

	private void RefrescarEmpleados() {
		tablaEmpleados.getItems().clear();
		ListarEmpleados();
	}

	private void ListarUsuarios() {
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
	private void AgregarUsuarioNuevo() {
		OcultarAdvertenciasUsuario();
		if (ConfirmarCamposUsuario()) {
			Usuario nuevo = new Usuario();
			if (CompletarUsuarioNuevo(nuevo)) {
				app.GrabarUsuarioDB(nuevo);
			}
			Tusuarios.getItems().clear();
			ListarUsuarios();
			LimpiarCamposUsuario();
			ConfirmarCamposUsuario();

			// Mensaje de confirmacion al agregar usuarios
			JOptionPane.showMessageDialog(null, "El usuario " + Unick.getText() + " ha sido agregado con éxito",
					"Mensaje", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null,
					"El usuario " + Unick.getText() + " no se ha podido agregar." + "\n"
							+ "Verifique que todos los campos se hayan completado correctamente.",
					"Mensaje", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Confirma los campos de usuarios.
	@FXML
	private boolean ConfirmarCamposUsuario() {
		OcultarAdvertenciasUsuario();
		boolean campos = true;
		if (Unick.getText().isEmpty()) {
			ErrorNick.setVisible(true);
			campos = false;
		} else if (!ContieneSoloLetras(Unick.getText())) {
			WarningNick.setVisible(true);
			campos = false;
		}
		if (Uemail.getText().isEmpty()) {
			ErrorMail.setVisible(true);
			campos = false;
		} else if (!ContieneArroba(Uemail.getText())) {
			WarningMail.setVisible(true);
			campos = false;
		}
		if (Upass.getText().isEmpty()) {
			ErrorPass.setVisible(true);
			campos = false;
		} 
		if (Upassconfirmar.getText().isEmpty()) {
			ErrorConfirmPass.setVisible(true);
			campos = false;
		} else if (!ComprobarContraseñas()) {
			WarningConfirmPass.setVisible(true);
			campos = false;
		}
		return campos;
	}

	// Oculta todos los triangulitos.
	private void OcultarAdvertenciasUsuario() {
		ErrorText1.setVisible(false);
		ErrorNick.setVisible(false);
		ErrorMail.setVisible(false);
		ErrorPass.setVisible(false);
		ErrorConfirmPass.setVisible(false);
		WarningNick.setVisible(false);
		WarningMail.setVisible(false);
		WarningConfirmPass.setVisible(false);
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

	@FXML
	private void ActivarEliminarUsuario() {
		Lusuario.setVisible(true);
		Ueliminar.setVisible(true);
		Baceptar.setVisible(true);
	}

	@FXML
	private void EliminarUsuario() {
		Usuario nuevo = new Usuario();
		DatosEliminar(nuevo);
		app.EliminarUsuarioDB(nuevo);
		Tusuarios.getItems().clear();
		ListarUsuarios();
		Laviso.setText("El usuario fue eliminado con éxito.");
	}

	@FXML // Mensajes de advertencia y confirmacion al eliminar usuarios
	private void ConfirmarEliminarUsuario() {
		int aux = JOptionPane.showConfirmDialog(null,
				"Esta seguro de que desea eliminar el usuario" + TUnick.getText() + "?", "Mensaje",
				JOptionPane.YES_NO_OPTION);
		if (aux == 0) {
			EliminarUsuario();
		}
	}

	// Limpiar los campos de usuario al agregarlo y los triangulos
	@FXML
	private void LimpiarCamposUsuario() {
		OcultarAdvertenciasUsuario();
		Unick.clear();
		Uemail.clear();
		Upass.clear();
		Upassconfirmar.clear();
		Ucheckinvitado.setSelected(true);
	}

	private void DatosEliminar(Usuario nuevo) {
		nuevo.setNombre(Ueliminar.getText());
	}

	// Busca un @ en el campo email de usuario.
	private boolean ContieneArroba(String texto) {
		if (texto.contains("@")) {
			return true;
		} else {
			return false;
		}
	}
}