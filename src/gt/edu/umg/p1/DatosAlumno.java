package gt.edu.umg.p1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatosAlumno {
	
	private final DateFormat formatoFecha = new SimpleDateFormat("EEE MMM d HH:MM:ss z yyyy");
	private int carnet;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private int telefono;
	private String correo;
	private byte[] bytesNombres;
	private byte[] bytesApellidos;
	private byte[] bytesFechaNacimiento;
	private byte[] bytesCorreo;
	
	
	public int getCarnet() {
		return carnet;
	}
	
	
	public void setCarnet(int carnet) {
		this.carnet = carnet;
	}
	
	
	public String getNombres() {
		return nombres;
	}
	
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
		bytesNombres = new byte [80]; //arreglo de bytes de longitud 80
		//convertir caracter por caracter
		for (int i = 0; i < nombres.length(); i++) {
			bytesNombres[i] = (byte)nombres.charAt(i);
		}
	}
	
	
	public String getApellidos() {
		return apellidos;
	}
	
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
		bytesApellidos = new byte [80]; //arreglo de bytes de longitud 80
		//convetir caracter por caracter
		for (int j = 0; j < apellidos.length(); j++) {
			bytesApellidos[j] = (byte)apellidos.charAt(j);
		}
	}
	
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		String strFecha = formatoFecha.format(fechaNacimiento);
		bytesFechaNacimiento = strFecha.getBytes();
	}
	
	
	public int getTelefono() {
		return telefono;
	}
	
	
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	
	public String getCorreo() {
		return correo;
	}
	
	
	public void setCorreo(String correo) {
		this.correo = correo;
		bytesCorreo = new byte [50]; //arreglo de bytes de longitud 80
		//convetir caracter por caracter
		for (int k = 0; k < correo.length(); k++) {
			bytesCorreo[k] = (byte)correo.charAt(k);
		}
	}
	
	
	public byte[] getBytesNombres() {
		return bytesNombres;
	}
	
	
	public void setBytesNombres(byte[] bytesNombres) {
		this.bytesNombres = bytesNombres;
		nombres = new String(bytesNombres);	
	}
	
	
	public byte[] getBytesApellidos() {
		return bytesApellidos;
	}
	
	
	public void setBytesApellidos(byte[] bytesApellidos) {
		this.bytesApellidos = bytesApellidos;
	}
	
	
	public byte[] getBytesFechaNacimiento() {
		return bytesFechaNacimiento;
	}
	
	
	public void setBytesFechaNacimiento(byte[] bytesFechaNacimiento) {
		this.bytesFechaNacimiento = bytesFechaNacimiento;
		String strFecha = new String (bytesFechaNacimiento); //convertir bytes a String
		try {
			this.fechaNacimiento = formatoFecha.parse(strFecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //convertir a tipo de dato Date
	}
	
	
	public byte[] getBytesCorreo() {
		return bytesCorreo;
	}
	
	
	public void setBytesCorreo(byte[] bytesCorreo) {
		this.bytesCorreo = bytesCorreo;
	}
	
	
}

