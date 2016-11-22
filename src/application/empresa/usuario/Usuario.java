package application.empresa.usuario;

import application.empresa.utils.Utils.NIVEL;

public class Usuario {

		protected String nombre;
		protected String contrase�a;
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

		public String getContrase�a() {
			return contrase�a;
		}

		public void setContrase�a(String contrase�a) {
			this.contrase�a = contrase�a;
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
