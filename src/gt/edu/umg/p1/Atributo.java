package gt.edu.umg.p1;

public class Atributo {
	
	private int indice;
	private String nombres;
	private int valorTiposDeDatos;
	private String nombreTiposDeDatos;
	private int longitud;
	private int bytes;
	private boolean requiereLongitud;
	private byte[] bytesNombres;
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

	public void setBytesNombres(byte[] bytesNombres) {
		this.bytesNombres = bytesNombres;
		nombres = new String(bytesNombres);
	}

	public int getValorTipoDato() {
		return valorTiposDeDatos;
	}

	public void setValorTipoDato(int valorTiposDeDatos) {
		this.valorTiposDeDatos = valorTiposDeDatos;
		if (valorTiposDeDatos == TiposDeDatos.STRING.getValue()) {
			this.requiereLongitud = true;
		}		
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public boolean isRequiereLongitud() {
		return requiereLongitud;
	}

	public String getNombreTipoDato() {
		return nombreTiposDeDatos;
	}
	
	public void setNombreTipoDato() {
		if (this.valorTiposDeDatos == TiposDeDatos.STRING.getValue()) {			
			this.nombreTiposDeDatos = TiposDeDatos.STRING.name();
			this.bytes = this.longitud;
		}
		if (this.valorTiposDeDatos == TiposDeDatos.INT.getValue()) {
			this.nombreTiposDeDatos = TiposDeDatos.INT.name();
			this.bytes = 4;
		}
		if (this.valorTiposDeDatos == TiposDeDatos.LONG.getValue()) {
			this.nombreTiposDeDatos= TiposDeDatos.LONG.name();
			this.bytes = 8;
		}
		if (this.valorTiposDeDatos == TiposDeDatos.DOUBLE.getValue()) {
			this.nombreTiposDeDatos = TiposDeDatos.DOUBLE.name();
			this.bytes = 8;
		}
		if (this.valorTiposDeDatos == TiposDeDatos.FLOAT.getValue()) {
			this.nombreTiposDeDatos = TiposDeDatos.FLOAT.name();
			this.bytes = 4;
		}
		if (this.valorTiposDeDatos== TiposDeDatos.DATE.getValue()) {
			this.nombreTiposDeDatos = TiposDeDatos.DATE.name();
			this.bytes = 28;
		}
		if (this.valorTiposDeDatos == TiposDeDatos.CHAR.getValue()) {
			this.nombreTiposDeDatos = TiposDeDatos.CHAR.name();
			/*
			 * la documentacion de Java indica que un tipo CHAR ocupa dos bytes
			 * sin embargo RamdomAccessFile no tiene una escritura de tipo char
			 * por lo que se usara tipo byte, es la razon del por que char sera de un byte 
			 */
			this.bytes = 1;
		}
	}
	
	/**
	 * @return the bytes
	 */
	public int getBytes() {
		return bytes;
	}


	public int getTiposDeDatos() {
		// TODO Auto-generated method stub
		return 0;
	}

}
