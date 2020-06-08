package gt.edu.umg.p1;

import  java.util.ArrayList ;
import  java.util.List ;

public class Entidad {
	
	private int indice;
	private String nombres;
	private int cantidad;
	private long posicion; //posicion donde inician sus atributos
	private byte[] bytesNombres;
	private int bytes = 1; //inicia en uno que representa el cambio de linea
	
	private List<Atributo> atributos;

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
		bytesNombres = new byte[30]; //arreglo de bytes de longitud 30
		//convertir caracter por caracter a byte y agregarlo al arreglo
		for (int i = 0; i < nombres.length(); i++) {
			bytesNombres[i] = (byte)nombres.charAt(i);
		}
	}
	
	public byte[] getBytesNombres() {
		return bytesNombres;
	}
	
	public void setBytesNombre(byte[] bytesNombres) {
		this.bytesNombres = bytesNombres;
		nombres = new String(bytesNombres);
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}
	
	public void setAtributo(Atributo atributo) {
		if (this.atributos == null) {
			this.atributos = new ArrayList<>();
		}
		this.atributos.add(atributo);
		this.cantidad = this.atributos.size();
	}
	
	public void removeAtributo(Atributo atributo) {
		if (this.atributos != null) {
			if (this.atributos.size() > 0) {
				this.atributos.remove(atributo);
				this.cantidad = this.atributos.size();
			}
		}
	}

	public long getPosicion() {
		return posicion;
	}

	public void setPosicion(long posicion) {
		this.posicion = posicion;
	}

	public int getBytes() {	
		bytes = 1;
		for (Atributo atributo : atributos) {
			bytes += atributo.getBytes();
		}
		return bytes;
	}
	
	public void setBytes(int bytes) {
		this.bytes = bytes;
	}


}
