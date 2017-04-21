package logicaDeNegocios;

import java.io.Serializable;
import java.sql.Date;

public class Usuario implements Serializable{
	private int id;
	private int carnet;
	private Date fechaNacimiento;
	private String primerApellido;
	private String segundoApellido;
	private String nombre;
	private String correo;
	private String contrasena;
	
	
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCarnet() {
		return carnet;
	}
	public void setCarnet(int carnet) {
		this.carnet = carnet;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public Usuario(int id, int carnet, String primerApellido, String segundoApellido, String nombre, String correo,String password){
		setId(id);
		setCarnet(carnet);
		setPrimerApellido(primerApellido);
		setSegundoApellido(segundoApellido);
		setNombre(nombre);
		setCorreo(correo);
		setContrasena(password);
	}
	
}
