package application.empresa.persona;

import application.empresa.persona.domicilio.Domicilio;
import application.empresa.utils.Utils.ESTADOCIVIL;
import application.empresa.utils.Utils.SEXO;
import application.empresa.utils.Utils.VIVIENDA;


public class Persona {

	
	//Izi pizi 100% real no fake
	
	protected String nombre;
	protected String apellido;
	protected short edad;
	protected Integer documento;
	protected float altura;
	protected SEXO sexo;
	protected ESTADOCIVIL estadoCivil;
	protected Domicilio domicilio;
	
	public Persona() {
		domicilio = new Domicilio();
	}
	
	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}
	
	public String Nombre() {
		return nombre;
	}
	
	public String Apellido() {
		return apellido;
	}
	
	public short Edad() {
		return edad;
	}
	
	public void SetNombre(String nuevoNombre) {
		nombre = nuevoNombre;
	}
	
	public void SetApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void SetNombre(String nuevoNombre, Persona per) {
		nombre = nuevoNombre;
		nuevoNombre = "juan";
		per.nombre = nuevoNombre;
	}
	
	public void SetDomicilio(String calle, short numero) {
		try {
			domicilio.SetCalle(calle);
			domicilio.SetNumero(numero);
		} catch (Exception e) {
			System.out.println("Error en la clase: " + this.getClass() + " el metodo SetDomicilio con el mensaje: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void SetDomicilio(String calle, short numero, short piso, String departamento) {
		try {
			domicilio.SetCalle(calle);
			domicilio.SetNumero(numero);
			domicilio.setDepartamento(departamento);
			domicilio.SetPiso(piso);
		} catch (Exception e) {
			System.out.println("Error en la clase: " + this.getClass() + " el metodo SetDomicilio con el mensaje: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void SetEstadoCivil(ESTADOCIVIL nuevoEstado) {
		this.estadoCivil = nuevoEstado;
	}
	
	public String getCalle() {
		return domicilio.Calle();
	}
	
	public short getNumero() {
		return domicilio.Numero();
	}
	
	public void setEdad(short nuevaEdad) {
		this.edad = nuevaEdad;
	}
	
	public void setSexo(SEXO nuevoSexo) {
		this.sexo = nuevoSexo;
	}
	
	public SEXO getSexo() {
		return sexo;
	}
	
	public void setVivienda(VIVIENDA nuevaVivienda) {
		domicilio.SetVivienda(nuevaVivienda);
	}
	
	public VIVIENDA getVivienda() {
		return domicilio.getVivienda();
	}
	
	public String EsCasa() {
		boolean resultado = domicilio.EsCasa();
		if (resultado == true) {
			String si = "Es correcto";
			return si;
		}
		String no = "No es correcto";
		return no;
	}
	
	public String EsMayorTreinta() {
		if (edad > 30) {
			String esmayor = "Si, es mayor a 30";
			return esmayor;
		} else if (edad >= 18){
			String esmayordeedad = "Es mayor de edad";
			return esmayordeedad;
		} else {
			String esjoven = "Es joven";
			return esjoven;
		}
	}
	
	public void SituacionActual() {
		switch (estadoCivil) {
			case CASADO : System.out.println("Estoy Casado");
			break;
			case SOLTERO : System.out.println("Estoy soltero");
			break;
			case CONCUBINO : System.out.println("Estoy en concubinato");
			break;
			case DIVORCIADO : System.out.println("Estoy divorsiado");
			break;
			case VIUDO : System.out.println("Soy viudo");
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
