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
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
	AnchorPane AnchorAgregarEmpleado, AnchorAdministrarEmpleado, AnchorAgregarUsuario, AnchorAdministrarUsuario, AnchorAutores;
	@FXML
	Label label1, labelPassMal, Lusuario, Laviso;
	@FXML
	Button Baceptar, btnEliminarEmpleado;
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
		nombreTabla.setOnEditCommit(
	            new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
	                @Override
	                public void handle(CellEditEvent<ModeloEmpleado, String> t) {
	                    ((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNombreTabla(t.getNewValue());
	                app.ModificarEmpleadoDB(((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).getLegajoTabla(), nombreTabla.getText(),t.getNewValue());
	                RefrescarEmpleados();
	                }
	            }
	        );

		apellidoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("ApellidoTabla"));
		apellidoTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		apellidoTabla.setOnEditCommit(
	            new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
	                @Override
	                public void handle(CellEditEvent<ModeloEmpleado, String> t) {
	                    ((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNombreTabla(t.getNewValue());
	                app.ModificarEmpleadoDB(((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).getLegajoTabla(), apellidoTabla.getText(),t.getNewValue());
	                RefrescarEmpleados();
	                }
	            }
	        );
		dniTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("DniTabla"));
		dniTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		dniTabla.setOnEditCommit(
	            new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
	                @Override
	                public void handle(CellEditEvent<ModeloEmpleado, String> t) {
	                    ((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNombreTabla(t.getNewValue());
	                app.ModificarEmpleadoDB(((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).getLegajoTabla(), dniTabla.getText(),t.getNewValue());
	                RefrescarEmpleados();
	                }
	            }
	        );
		edadTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("EdadTabla"));
		edadTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		edadTabla.setOnEditCommit(
	            new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
	                @Override
	                public void handle(CellEditEvent<ModeloEmpleado, String> t) {
	                    ((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNombreTabla(t.getNewValue());
	                app.ModificarEmpleadoDB(((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).getLegajoTabla(), edadTabla.getText(),t.getNewValue());
	                RefrescarEmpleados();
	                }
	            }
	        );
		calleTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("CalleTabla"));
		calleTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		calleTabla.setOnEditCommit(
	            new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
	                @Override
	                public void handle(CellEditEvent<ModeloEmpleado, String> t) {
	                    ((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNombreTabla(t.getNewValue());
	                app.ModificarEmpleadoDB(((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).getLegajoTabla(), calleTabla.getText(),t.getNewValue());
	                RefrescarEmpleados();
	                }
	            }
	        );
		numeroTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("NumeroTabla"));
		numeroTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		numeroTabla.setOnEditCommit(
	            new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
	                @Override
	                public void handle(CellEditEvent<ModeloEmpleado, String> t) {
	                    ((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNombreTabla(t.getNewValue());
	                app.ModificarEmpleadoDB(((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).getLegajoTabla(), numeroTabla.getText(),t.getNewValue());
	                RefrescarEmpleados();
	                }
	            }
	        );
		pisoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("PisoTabla"));
		pisoTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		pisoTabla.setOnEditCommit(
	            new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
	                @Override
	                public void handle(CellEditEvent<ModeloEmpleado, String> t) {
	                    ((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNombreTabla(t.getNewValue());
	                app.ModificarEmpleadoDB(((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).getLegajoTabla(), pisoTabla.getText(),t.getNewValue());
	                RefrescarEmpleados();
	                }
	            }
	        );
		departamentoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("DepartamentoTabla"));
		departamentoTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		departamentoTabla.setOnEditCommit(
	            new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
	                @Override
	                public void handle(CellEditEvent<ModeloEmpleado, String> t) {
	                    ((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNombreTabla(t.getNewValue());
	                app.ModificarEmpleadoDB(((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).getLegajoTabla(), departamentoTabla.getText(),t.getNewValue());
	                RefrescarEmpleados();
	                }
	            }
	        );
		lenguajeTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("LenguajeTabla"));
		lenguajeTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		lenguajeTabla.setOnEditCommit(
	            new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
	                @Override
	                public void handle(CellEditEvent<ModeloEmpleado, String> t) {
	                    ((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNombreTabla(t.getNewValue());
	                app.ModificarEmpleadoDB(((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).getLegajoTabla(), lenguajeTabla.getText(),t.getNewValue());
	                RefrescarEmpleados();
	                }
	            }
	        );
		rangoTabla.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado, String>("RangoTabla"));
		rangoTabla.setCellFactory(TextFieldTableCell.forTableColumn());
		rangoTabla.setOnEditCommit(
	            new EventHandler<CellEditEvent<ModeloEmpleado, String>>() {
	                @Override
	                public void handle(CellEditEvent<ModeloEmpleado, String> t) {
	                    ((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setNombreTabla(t.getNewValue());
	                app.ModificarEmpleadoDB(((ModeloEmpleado) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).getLegajoTabla(), rangoTabla.getText(),t.getNewValue());
	                RefrescarEmpleados();
	                }
	            }
	        );

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


		//ListarEmpleados();

		TUnick.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("NombreTabla"));
		TUemail.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("EmailTable"));
		TUcontraseña.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("PassTabla"));
		TUnivel.setCellValueFactory(new PropertyValueFactory<ModeloUsuario, String>("NivelTabla"));

		ListarUsuarios();
		
		//Diferencia Administrador/Visitante
		
		if(app.GetNivelUsuarioDB(LoginControlador.usuarioActual) == 1){
			AgregEmpleado.setDisable(false);
			Usuarios.setDisable(false);
			btnEliminarEmpleado.setDisable(false);
		}
		else{
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
		//Guarda en Index ID de tabla selecionada
		int index = this.tablaEmpleados.getSelectionModel().getSelectedIndex();
		//Guarda en un string el Valor de la columna Index(Seleccionada)
		String LegajoString = legajoTabla.getCellObservableValue(index).getValue();
		//Crea un Integer
		Integer legajo = new Integer(0);
		//Le cambio el valor de un String A un Integer
		legajo = Integer.parseInt(LegajoString);
		//Elimino el empleado a traves del legajo
		app.EliminarEmpleadoDB(legajo);
		//Refresco Tabla
		this.RefrescarEmpleados();
	}
	
	@FXML
	private void ConfirmarEliminarEmpleado(){
		int aux = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea eliminar el empleado?",   "Mensaje",
                JOptionPane.YES_NO_OPTION);
		if(aux == 0){
			EliminarEmpleado();
			JOptionPane.showMessageDialog(null, "El empleado ha sido eliminado",   "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE);
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

	private void RefrescarEmpleados(){
		tablaEmpleados.getItems().clear();
		ListarEmpleados();
	}

	private void ListarUsuarios(){
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
		Usuario nuevo = new Usuario();
		if (CompletarUsuarioNuevo(nuevo)) {
			app.GrabarUsuarioDB(nuevo);
		}
		Tusuarios.getItems().clear();
		ListarUsuarios();
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

	private void DatosEliminar(Usuario nuevo){
		nuevo.setNombre(Ueliminar.getText());
	}
	
	

}