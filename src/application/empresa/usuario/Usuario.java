package application.empresa.usuario;

import application.empresa.utils.Utils.NIVEL;

public class Usuario {

		protected String nombre;
		protected String contraseña;
		protected String email;
		protected NIVEL nivel;
		
		public Usuario() {
		
		}			

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getContraseña() {
			return contraseña;
		}

		public void setContraseña(String contraseña) {
			this.contraseña = contraseña;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public NIVEL getNivel() {
			return nivel;
		}

		public void setNivel(NIVEL nivel) {
			this.nivel = nivel;
		}	
			

}
