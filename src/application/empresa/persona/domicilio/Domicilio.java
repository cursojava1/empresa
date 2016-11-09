package application.empresa.persona.domicilio;

import application.empresa.utils.Utils.VIVIENDA;

// Esta clase define un domicilio de una persona.
public class Domicilio {
	
	private String calle;
	private short numero;
	private short piso;
	private String departamento;
	
	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	private VIVIENDA vivienda;
	
	public Domicilio() { }
	
	public Domicilio(String calle, short numero) {
		this.calle = calle;
		this.numero = numero;
	}
	
	public String Calle() {
		return calle;
	}
	
	public short GetNumeroDomicilio() {
		calle = "corrientes";
		return numero;
	}
	
	public short Piso() {
		return piso;
	}
	
	public void SetPiso(short piso) {
		this.piso = piso;
	}
	
	public void SetCalle(String calle) {
		this.calle = calle;
	}
	
	public void SetNumeroDomicilio(short nuevoNumero) {
		numero = nuevoNumero;
	}
	
	public void SetVivienda(VIVIENDA nuevaVivienda) {
		vivienda = nuevaVivienda;
	}
	
	public VIVIENDA getVivienda() {
		return vivienda;
	}
	
	public boolean EsCasa() {
		if (vivienda == VIVIENDA.CASA) {
			return true;
		}
		return false;
	}
	
	public String ImprimirEnArchivo() {
		StringBuilder sb = new StringBuilder();
		sb.append(calle).append(" ").append(numero);
		if (vivienda == VIVIENDA.DEPARTAMENTO) {
			sb.append(" ").append(piso)
			.append(" ").append(departamento);
		}
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
}
