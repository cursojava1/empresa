package application.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.empresa.empleados.Empleado;
import application.empresa.empleados.FactoryEmpleados;
import application.empresa.empleados.Gerente;
import application.empresa.empleados.Junior;
import application.empresa.utils.Utils.ESTADOCIVIL;
import application.empresa.utils.Utils.SEXO;
import application.funcional.app.App;
import application.fxml.app.ModeloEmpleadoTE;
import application.fxml.app.ModeloEmpleadoTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
///comentario
public class AppControlador implements Initializable {

	App app;
	@FXML
	TextField nombre, apellido, dni, edad, calle, piso, numero, dpt, text1;
	@FXML
	RadioButton rbcasa, rbdpt;
	@FXML
	ChoiceBox<String> sexo, estado, usuarios;
	@FXML
	Label label1;
	@FXML private TableView<ModeloEmpleadoTM> tablaEmpleadosM;
	@FXML
	private TableColumn<ModeloEmpleadoTM, String> nombreTablaM, apellidoTablaM, dniTablaM, edadTablaM, calleTablaM,
			numeroTablaM, lenguajeTablaM;
	
	@FXML private TableView<ModeloEmpleadoTE> tablaEmpleadosE;
	@FXML private TableColumn<ModeloEmpleadoTE, String> nombreTablaE, apellidoTablaE, dniTablaE, edadTablaE, calleTablaE, numeroTablaE, lenguajeTablaE;
	
	ObservableList<ModeloEmpleadoTM> empleadosTM = FXCollections.observableArrayList();
	List<String> empleadosArchivoTM;
	
	ObservableList<ModeloEmpleadoTE> empleadosTE = FXCollections.observableArrayList();
	List<String> empleadosArchivoTE;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		app = new App();
		ObservableList<String> opcionesSexo = FXCollections.observableArrayList("Masculino", "Femenino");
		sexo.setItems(opcionesSexo);
		ObservableList<String> opcionesEstado = FXCollections.observableArrayList("Casado", "Soltero", "Viudo",
				"Divorciado", "Concuvino");
		estado.setItems(opcionesEstado);
		rbcasa.setSelected(true);
		
		nombreTablaM.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTM, String>("NombreTablaM"));
		apellidoTablaM.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTM, String>("ApellidoTablaM"));
		dniTablaM.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTM, String>("DniTablaM"));
		edadTablaM.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTM, String>("EdadTablaM"));
		calleTablaM.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTM, String>("CalleTablaM"));
		numeroTablaM.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTM, String>("NumeroTablaM"));
		lenguajeTablaM.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTM, String>("LenguajeTablaM"));
		
		nombreTablaE.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTE, String>("NombreTablaE"));
		apellidoTablaE.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTE, String>("ApellidoTablaE"));
		dniTablaE.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTE, String>("DniTablaE"));
		edadTablaE.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTE, String>("EdadTablaE"));
		calleTablaE.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTE, String>("CalleTablaE"));
		numeroTablaE.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTE, String>("NumeroTablaE"));
		lenguajeTablaE.setCellValueFactory(new PropertyValueFactory<ModeloEmpleadoTE, String>("LenguajeTablaE"));
		
		
		ObservableList<String> opcionesUsuarios = FXCollections.observableArrayList("Gerente", "Desarrollador");
		usuarios.setItems(opcionesUsuarios);
		
		empleadosArchivoTM = app.ExtraerEmpleados();
		ModeloEmpleadoTM modeloEmpleadoTM;
		String[] datosM = new String[7];
		for (String empleado : empleadosArchivoTM) {
			datosM = empleado.split(" ");
			modeloEmpleadoTM = new ModeloEmpleadoTM(datosM[0], datosM[1], datosM[2], datosM[3], datosM[4], datosM[5], datosM[6]);
			empleadosTM.add(modeloEmpleadoTM);
		}
		tablaEmpleadosM.setItems(empleadosTM);
		
		empleadosArchivoTE = app.ExtraerEmpleados();
		ModeloEmpleadoTE modeloEmpleadoTE;
		String[] datos = new String[7];
		for (String empleado : empleadosArchivoTE) {
			datos = empleado.split(" ");
			modeloEmpleadoTE = new ModeloEmpleadoTE(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
			empleadosTE.add(modeloEmpleadoTE);
		}
		tablaEmpleadosE.setItems(empleadosTE);
		
		
		
		
		
		
		
		
		
		
		
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
				app.GrabarEmpleado(nuevo);
			} else {
				Junior nuevo = (Junior) FactoryEmpleados.GetEmpleado("junior");
				nuevo.SetLenguajeProgramacion(text1.getText());
				CompletarEmpleado(nuevo);
				app.GrabarEmpleado(nuevo);
			}
		}

	}

	private void CompletarEmpleado(Empleado nuevo) {
		nuevo.SetNombre(nombre.getText());
		nuevo.SetApellido(apellido.getText());
		nuevo.setDocumento(Integer.parseInt(dni.getText()));
		switch (sexo.getValue()) {
		case "MASCULINO":
			nuevo.setSexo(SEXO.MASCULINO);
			break;
		case "FEMENINO":
			nuevo.setSexo(SEXO.FEMENINO);
			break;
		}
		switch (estado.getValue()) {
		case "CASADO":
			nuevo.SetEstadoCivil(ESTADOCIVIL.CASADO);
			break;
		case "SOLTERO":
			nuevo.SetEstadoCivil(ESTADOCIVIL.SOLTERO);
			break;
		case "DIVORSIADO":
			nuevo.SetEstadoCivil(ESTADOCIVIL.DIVORCIADO);
			break;
		case "VIUDO":
			nuevo.SetEstadoCivil(ESTADOCIVIL.VIUDO);
			break;
		case "CONCUBINO":
			nuevo.SetEstadoCivil(ESTADOCIVIL.CONCUBINO);
			break;
		}
		nuevo.setEdad(Short.parseShort(edad.getText()));

		if (rbcasa.isSelected()) {
			nuevo.SetDomicilio(calle.getText(), Short.parseShort(numero.getText()));
		} else {
			nuevo.SetDomicilio(calle.getText(), Short.parseShort(numero.getText()), Short.parseShort(piso.getText()),
					dpt.getText());
		}
	}

	@FXML
	private void EliminarEmpleado() {
		int index = tablaEmpleadosE.getSelectionModel().getSelectedIndex();
		empleadosTE.remove(index);
		app.EliminarEmpleado(index);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
