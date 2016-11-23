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
import application.empresa.utils.Utils.ESTADOCIVIL;
import application.empresa.utils.Utils.SEXO;
import application.empresa.utils.Utils.VIVIENDA;
import application.funcional.app.App;
import application.fxml.app.ModeloEmpleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AppControlador implements Initializable {
	
	App app;
	@FXML
	TextField nombre, apellido, dni, edad, calle, piso, numero, dpt, text1;
	@FXML
	RadioButton rbcasa, rbdpt;
	@FXML
	ChoiceBox<String> sexo, estado, usuarios;
	@FXML
	MenuItem CerrarApp;
	@FXML
	Label label1;
	@FXML
	private TableView<ModeloEmpleado> tablaEmpleados;
	@FXML
	private TableColumn<ModeloEmpleado, String> legajoTabla, nombreTabla, apellidoTabla, dniTabla, edadTabla,
			calleTabla, numeroTabla, pisoTabla, departamentoTabla, lenguajeTabla, rangoTabla;

	ObservableList<ModeloEmpleado> empleados = FXCollections.observableArrayList();
	List<String> empleadosArchivo;

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
			} else {
				Junior nuevo = (Junior) FactoryEmpleados.GetEmpleado("junior");
				nuevo.SetLenguajeProgramacion(text1.getText());
				CompletarEmpleado(nuevo);
				app.GrabarEmpleadoDB(nuevo);
			}
		}
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

	private void LimpiarDatos(String[] datos) {
		for (int i = 0; i < datos.length; i++) {
			if (datos[i].equals("null")) {
				datos[i] = "-";
			}
		}
	}
	
	@FXML
	private void CerrarApp(){			
		app.CerrarApp();
		
	}
	
}
