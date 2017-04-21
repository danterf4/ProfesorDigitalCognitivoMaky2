package logicaIntegracion;

import java.sql.SQLException;

import logicaAccesoADatos.conexion;
import logicaDeNegocios.Usuario;

public class logIn {
	private Usuario user;
	private String email;
	private String password;
	
	public logIn(String pEmail,String pPassword) throws ClassNotFoundException, SQLException{
		setEmail(pEmail);
		setPassword(pPassword);
	}
	
	public Usuario getUser() {
		return user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public String verificacion() throws ClassNotFoundException, SQLException{

			Usuario user = (Usuario) conexion.deSerializeJavaObjectFromDB(conexion.conexionDB(),getEmail());
			if(user!=null){
				if(user.getContrasena().equals(getPassword())){
					return "Inicio correcto";
				}
				else{
					return "Inicio incorrecto";
					
				}
			}
		return "Usuario no existe o no ingreso el usuario correcto";
		
	
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		logIn log = new logIn("k.andres0620@gmail.com","aaaAAA123");
		System.out.println(log.verificacion());
	}

}
