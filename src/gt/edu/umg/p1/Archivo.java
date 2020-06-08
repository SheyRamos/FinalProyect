package gt.edu.umg.p1;

import java.io.IOException;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Archivo {
	
	Scanner sc = new Scanner(System.in);
	RandomAccessFile fichero = null, entidades = null, atributos = null;
	private final String rutaBase = "C:\\Users\\sheyl\\eclipse-workspace\\FinalProyect";
	// contiene Indice, Nombre de la entidad (30 caracteres maximo), cantidad de
	// atributos, posicion donde inician los atributos => total bytes = 47 (incluye
	// cambio de linea)
	private final String rutaEntidades = "C:\\Users\\sheyl\\eclipse-workspace\\FinalProyect\\entidades.dat";
	// contiene indice de la entidad, nombre del atributo, tipo de dato, longitud =>
	// total de bytes = 43
	private final String rutaAtributos = "C:\\Users\\sheyl\\eclipse-workspace\\FinalProyect\\atributos.dat";
	private final int totalBytes = 83, bytesEntidad = 47, bytesAtributo = 43;
	private final static String formatoFecha = "dd/MM/yyyy";
	static DateFormat format = new SimpleDateFormat(formatoFecha);

	private List<Entidad> listaEntidades = new ArrayList<>();

	public static void main(String[] args) {
		Archivo ad = new Archivo();
		if (ad.validarDefinicion()) {
			ad.menuDefinicion(true);
		} else {
			ad.menuDefinicion(false);
		}
		System.exit(0); // finalize application
	}

	private void menuDefinicion(boolean b) {
		// TODO Auto-generated method stub
		
	}

	// metodos para definicion
	private boolean validarDefinicion() {
		boolean res = false;
		try {
			entidades = new RandomAccessFile(rutaEntidades, "rw");
			atributos = new RandomAccessFile(rutaAtributos, "rw");
			long longitud = entidades.length();
			if (longitud <= 0) {
				System.out.println("No hay registros");
				res = false; // finalizar el procedimiento
			}
			if (longitud >= bytesEntidad) {
				// posicionarse al principio del archivo
				entidades.seek(0);
				Entidad e;
				while (longitud >= bytesEntidad) {
					e = new Entidad();
					e.setIndice(entidades.readInt());
					byte[] bNombres = new byte[30]; // leer 30 bytes para el nombre
					entidades.read(bNombres);
					e.setBytesNombre(bNombres);
					e.setCantidad(entidades.readInt());
					e.setBytes(entidades.readInt());
					e.setPosicion(entidades.readLong());
					entidades.readByte();// leer el cambio de linea
					longitud -= bytesEntidad;
					// leer atributos
					long longitudAtributos = atributos.length();
					if (longitudAtributos <= 0) {
						System.out.println("No hay registros");
						res = false; // finalizar el procedimiento
						break;
					}
					atributos.seek(e.getPosicion());
					Atributo a;
					longitudAtributos = e.getCantidad() * bytesAtributo;
					while (longitudAtributos >= bytesAtributo) {
						a = new Atributo();
						a.setIndice(atributos.readInt());
						byte[] bNombreAtributo = new byte[30]; // leer 30 bytes para el nombre
						atributos.read(bNombreAtributo);
						a.setBytesNombres(bNombreAtributo);
						a.setValorTipoDato(atributos.readInt());
						a.setLongitud(atributos.readInt());
						a.setNombreTipoDato();
						atributos.readByte();// leer el cambio de linea
						e.setAtributo(a);
						longitudAtributos -= bytesAtributo;
					}
					listaEntidades.add(e);
				}
				if (listaEntidades.size() > 0) {
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private void mostrarEntidad(Entidad entidad) {
		System.out.println("Indice: " + entidad.getIndice());
		System.out.println("Nombre: " + entidad.getNombres());
		System.out.println("Cantidad de atributos: " + entidad.getCantidad());
		System.out.println("Atributos:");
		int i = 1;
		for (Atributo atributo : entidad.getAtributos()) {
			System.out.println("\tNo. " + i);
			System.out.println("\tNombre: " + atributo.getNombres());
			System.out.println("\tTipo de dato: " + atributo.getNombreTipoDato());
			if (atributo.isRequiereLongitud()) {
				System.out.println("\tLongitud: " + atributo.getLongitud());
			}
			i++;
		}
	}

	private boolean agregarEntidad() {
		boolean resultado = false;
		try {
			Entidad entidad = new Entidad();
			entidad.setIndice(listaEntidades.size() + 1);
			System.out.println("Ingrese el nombre de la entidad");
			String strNombre = "";
			int longitud = 0;
			do {
				strNombre = sc.nextLine();
				longitud = strNombre.length();
				if (longitud < 2 || longitud > 30) {
					System.out.println("La longitud del nombre no es valida [3 - 30]");
				} else {
					if (strNombre.contains(" ")) {
						System.out
								.println("El nombre no puede contener espacios, sustituya por guion bajo (underscore)");
						longitud = 0;
					}
				}
			} while (longitud < 2 || longitud > 30);
			entidad.setNombres(strNombre);
			System.out.println("Atributos de la entidad");
			int bndDetener = 0;
			do {
				Atributo atributo = new Atributo();
				atributo.setIndice(entidad.getIndice());
				longitud = 0;
				System.out.println("Escriba el nombre del atributo no. " + (entidad.getCantidad() + 1));
				do {
					strNombre = sc.nextLine();
					longitud = strNombre.length();
					if (longitud < 2 || longitud > 30) {
						System.out.println("La longitud del nombre no es valida [3 - 30]");
					} else {
						if (strNombre.contains(" ")) {
							System.out.println(
									"El nombre no puede contener espacios, sustituya por guion bajo (underscore)");
							longitud = 0;
						}
					}
				} while (longitud < 2 || longitud > 30);
				atributo.setNombres(strNombre);
				System.out.println("Seleccione el tipo de dato");
				System.out.println(TiposDeDatos.INT.getValue() + " .......... " + TiposDeDatos.INT.name());
				System.out.println(TiposDeDatos.LONG.getValue() + " .......... " + TiposDeDatos.LONG.name());
				System.out.println(TiposDeDatos.STRING.getValue() + " .......... " + TiposDeDatos.STRING.name());
				System.out.println(TiposDeDatos.DOUBLE.getValue() + " .......... " + TiposDeDatos.DOUBLE.name());
				System.out.println(TiposDeDatos.FLOAT.getValue() + " .......... " + TiposDeDatos.FLOAT.name());
				System.out.println(TiposDeDatos.DATE.getValue() + " .......... " + TiposDeDatos.DATE.name());
				System.out.println(TiposDeDatos.CHAR.getValue() + " .......... " + TiposDeDatos.CHAR.name());
				atributo.setValorTipoDato(sc.nextInt());
				if (atributo.isRequiereLongitud()) {
					System.out.println("Ingrese la longitud");
					atributo.setLongitud(sc.nextInt());
				} else {
					atributo.setLongitud(0);
				}
				atributo.setNombreTipoDato();
				entidad.setAtributo(atributo);
				System.out.println("Desea agregar otro atributo presione cualquier numero, de lo contrario 0");
				bndDetener = sc.nextInt();
			} while (bndDetener != 0);
			System.out.println("Los datos a registrar son: ");
			mostrarEntidad(entidad);
			System.out.println("Presione 1 para guardar 0 para cancelar");
			longitud = sc.nextInt();
			if (longitud == 1) {
				// primero guardar atributos
				// establecer la posicion inicial donde se va a guardar
				entidad.setPosicion(atributos.length());
				atributos.seek(atributos.length());// calcular la longitud el archivo
				for (Atributo atributo : entidad.getAtributos()) {
					atributos.writeInt(atributo.getIndice());
					atributos.write(atributo.getBytesNombres());
					atributos.writeInt(atributo.getValorTipoDato());
					atributos.writeInt(atributo.getLongitud());
					atributos.write("\n".getBytes()); // cambio de linea para que el siguiente registro se agregue abajo
				}
				// guardar la entidad
				entidades.writeInt(entidad.getIndice());
				entidades.write(entidad.getBytesNombres());
				entidades.writeInt(entidad.getCantidad());
				entidades.writeInt(entidad.getBytes());
				entidades.writeLong(entidad.getPosicion());
				entidades.write("\n".getBytes()); // cambio de linea para que el siguiente registro se agregue abajo
				listaEntidades.add(entidad);
				resultado = true;
			} else {
				System.out.println("No se guardo la entidad debido a que el usuario decidio cancelarlo");
				resultado = false;
			}
			// https://www.experts-exchange.com/questions/22988755/Some-system-pause-equivalent-in-java.html
			System.out.println("Presione una tecla para continuar...");
			System.in.read();
		} catch (Exception e) {
			resultado = false;
			e.printStackTrace();
		}
		return resultado;
	}

	private void modificarEntidad() {
		try {
			int indice = 0;
			while (indice < 1 || indice > listaEntidades.size()) {
				for (Entidad entidad : listaEntidades) {
					System.out.println(entidad.getIndice() + " ...... " + entidad.getNombres());
				}
				System.out.println("Seleccione la entidad que desea modificar");
				indice = sc.nextInt();
			}
			Entidad entidad = null;
			for (Entidad e : listaEntidades) {
				if (indice == e.getIndice()) {
					entidad = e;
					break;
				}
			}
			String nombreFichero = formarNombreFichero(entidad.getNombres());
			fichero = new RandomAccessFile(rutaBase + nombreFichero, "rw");
			long longitudDatos = fichero.length();
			fichero.close();
			if (longitudDatos > 0) {
				System.out.println("No es posible modificar la entidad debido a que ya tiene datos asociados");
			} else {
				// bandera para verificar que el registro fue encontrado
				boolean bndEncontrado = false, bndModificado = false;
				// posicionarse al principio del archivo
				entidades.seek(0);
				long longitud = entidades.length();
				int registros = 0, salir = 0, i;
				Entidad e;
				byte[] tmpBytes;
				while (longitud > totalBytes) {
					e = new Entidad();
					e.setIndice(entidades.readInt());
					tmpBytes = new byte[30];
					entidades.read(tmpBytes);
					e.setBytesNombre(tmpBytes);
					e.setCantidad(entidades.readInt());
					e.setBytes(entidades.readInt());
					e.setPosicion(entidades.readLong());
					if (entidad.getIndice() == e.getIndice()) {
						System.out.println("Si no desea modificar el campo presione enter");
						System.out.println("Ingrese el nombre");
						String tmpStr = "";
						int len = 0;
						long posicion;
						do {
							tmpStr = sc.nextLine();
							len = tmpStr.length();
							if (len == 1 || len > 30) {
								System.out.println("La longitud del nombre no es valida [2 - 30]");
							}
						} while (len == 1 || len > 30);
						if (len > 0) {
							e.setNombres(tmpStr);
							posicion = registros * totalBytes;
							fichero.seek(posicion);
							fichero.skipBytes(4); // moverse despues del indice (int = 4 bytes)
							// grabar el cambio
							fichero.write(e.getBytesNombres());
							bndModificado = true;
						}
						i = 1;
						for (Atributo a : entidad.getAtributos()) {
							System.out.println("Modificando atributo 1");
							System.out.println(a.getNombres().trim());
						}
						
						break;
					}
					registros++;
					// restar los bytes del registro leido
					longitud -= totalBytes;
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}



	private void cerrarArchivos() {
		if (fichero != null) {
			try {
				fichero.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (atributos != null) {
			try {
				atributos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (entidades != null) {
			try {
				entidades.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private boolean borrarArchivos() {
		boolean res = false;
		try {
			File file;
			for (Entidad entidad : listaEntidades) {
				file = new File(rutaBase + entidad.getNombres().trim() + ".dat");
				if (file.exists()) {
					file.delete();
				}
				file = null;
			}
			file = new File(rutaAtributos);
			if (file.exists()) {
				file.delete();
			}
			file = null;
			file = new File(rutaEntidades);
			if (file.exists()) {
				file.delete();
			}
			file = null;
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private String formarNombreFichero(String nombre) {
		return nombre.trim() + ".dat";
	}

	// metodos para guardar registros segun la definicion
	private void iniciar(int indice) {
		int opcion = 0;
		String nombreFichero = "";
		try {
			Entidad entidad = null;
			for (Entidad e : listaEntidades) {
				if (indice == e.getIndice()) {
					nombreFichero = formarNombreFichero(e.getNombres());
					entidad = e;
					break;
				}
			}
			fichero = new RandomAccessFile(rutaBase + nombreFichero, "rw");
			System.out.println("Bienvenido (a)");
			Atributo a = entidad.getAtributos().get(0);
			do {
				try {
					System.out.println("Seleccione su opcion");
					System.out.println("1.\t\tAgregar");
					System.out.println("2.\t\tListar");
					System.out.println("3.\t\tBuscar");
					System.out.println("4.\t\tModificar");
					System.out.println("0.\t\tRegresar al menu anterior");
					opcion = sc.nextInt();
					switch (opcion) {
					
					case 1:
						grabarRegistro(entidad);
						break;
					case 2:
						listarRegistros(entidad);
						break;
					case 3:
						System.out.println("Se hara la busqueda en la primera columna ");
						System.out.println("Ingrese " + a.getNombres().trim() + " a buscar");
						// sc.nextLine();
						// encontrarRegistro(carne);
						break;
					case 4:
						System.out.println("Ingrese el carnet a modificar: ");
						// carne = sc.nextInt();
						// sc.nextLine();
						// modificarRegistro(carne);
						break;
					default:
						System.out.println("Opcion no valida");
						break;
					}
				} catch (Exception e) { // capturar cualquier excepcion que ocurra
					System.out.println("Error: " + e.getMessage());
				}
			} while (opcion != 0);
			fichero.close();
		} catch (Exception e) { // capturar cualquier excepcion que ocurra
			System.out.println("Error: " + e.getMessage());
		}
	}

	private boolean grabarRegistro(Entidad entidad) {
		boolean resultado = false;
		try {
			// posicionarse al final para grabar
			fichero.seek(fichero.length());
			boolean valido;
			byte[] bytesString;
			String tmpString = "";
			for (Atributo atributo : entidad.getAtributos()) {
				valido = false;
				System.out.println("Ingrese " + atributo.getNombres().trim());
			} // end for
			fichero.write("\n".getBytes()); // cambio de linea para que el siguiente registro se agregue abajo
			resultado = true;
		} catch (Exception e) {
			resultado = false;
			System.out.println("Error al agregar el registro " + e.getMessage());
		}
		return resultado;
	}

	
	public void listarRegistros(Entidad entidad) {
		try {
			long longitud = fichero.length();
			if (longitud <= 0) {
				System.out.println("No hay registros");
				return; // finalizar el procedimiento
			}
			// posicionarse al principio del archivo
			fichero.seek(0);
			byte[] tmpArrayByte;
			String linea = "";
			for (Atributo atributo : entidad.getAtributos()) {
				linea += atributo.getNombres().toString().trim() + "\t\t";
			}
			System.out.println(linea);
			while (longitud >= entidad.getBytes()) {
				linea = "";
				for (Atributo atributo : entidad.getAtributos()) {
					switch (atributo.getTiposDeDatos()) {
					}
				}
				fichero.readByte();// leer el cambio de linea
				// restar los bytes del registro leido
				longitud -= entidad.getBytes();
				System.out.println(linea);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void encontrarRegistro(int carne) {
		try {
			long longitud = fichero.length();
			if (longitud <= 0) {
				System.out.println("No hay registros");
				return; // finalizar el procedimiento
			}
			// bandera para verificar que el registro fue encontrado
			boolean bndEncontrado = false;
			// posicionarse al principio del archivo
			fichero.seek(0);
			// solo se instancia una vez y se sobreescriben los datos debido a que se
			// mostrara un unico registro
			DatosAlumno a = new DatosAlumno();
			while (longitud >= totalBytes) {
				a.setCarnet(fichero.readInt());
				byte[] bNombre = new byte[50]; // leer 50 bytes para el nombre
				fichero.read(bNombre);
				a.setBytesNombres(bNombre);
				byte[] bFecha = new byte[28]; // 28 bytes para la fecha
				fichero.read(bFecha);
				fichero.readByte();// leer el cambio de linea
				a.setBytesFechaNacimiento(bFecha);
				if (a.getCarnet() == carne) {
					// imprimir los campos del registro
					System.out.println("Carne: " + a.getCarnet());
					System.out.println("Nombre: " + a.getNombres());
					System.out.println("Fecha de nacimiento: " + dateToString(a.getFechaNacimiento()));
					bndEncontrado = true;
					// si el registro se ha encontrado entonces salir del ciclo
					break;
				}
				// restar los bytes del registro leido
				longitud -= totalBytes;
			}
			// solo si el registro no se encontro imprimir un mensaje
			if (!bndEncontrado) { // esto es equivalente a (bndEncontrado == false)
				System.out.println("No se encontro el carne indicado, por favor verifique");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void modificarRegistro(int carne) {
		try {
			// bandera para verificar que el registro fue encontrado
			boolean bndEncontrado = false, bndModificado = false;
			// posicionarse al principio del archivo
			fichero.seek(0);
			long longitud = fichero.length();
			int registros = 0;
			// solo se instancia una vez y se sobreescriben los datos debido a que se
			// mostrara un unico registro
			DatosAlumno a = new DatosAlumno();
			while (longitud > totalBytes) {
				a.setCarnet(fichero.readInt());
				byte[] bNombre = new byte[50]; // leer 50 bytes para el nombre
				fichero.read(bNombre);
				a.setBytesNombres(bNombre);
				byte[] bFecha = new byte[28]; // 28 bytes para la fecha
				fichero.read(bFecha);
				fichero.readByte();// leer el cambio de linea
				a.setBytesFechaNacimiento(bFecha);
				if (a.getCarnet() == carne) {
					System.out.println("Si no desea modificar el campo presione enter");
					System.out.println("Ingrese el nombre");
					String tmpStr = "";
					int len = 0;
					long posicion;
					do {
						tmpStr = sc.nextLine();
						len = tmpStr.length();
						if (len > 50) {
							System.out.println("La longitud del nombre no es valida [1 - 50]");
						}
					} while (len > 50);
					if (len > 0) {
						a.setNombres(tmpStr);
						// encontrar la posicion especifica del campo a modificar
						// primero encontrar la posicion del registro
						posicion = registros * totalBytes;
						fichero.seek(posicion);
						// sumar el tamanio del campo llave
						fichero.skipBytes(4); // moverse despues del carne (int = 4 bytes)
						// grabar el cambio
						fichero.write(a.getBytesNombres());
						bndModificado = true;
					}
					System.out.println("Ingrese la fecha (" + formatoFecha + ")");
					tmpStr = sc.nextLine();
					if (tmpStr.length() > 0) {
						Date date = null;
						while (date == null) {
							date = strintToDate(tmpStr);
						}
						a.setFechaNacimiento(date);
						posicion = registros * totalBytes;
						fichero.seek(posicion);
						fichero.skipBytes(4 + 50); // moverse despues del carne + el nombre (int = 4 bytes, nombre = 50
													// bytes)
						fichero.write(a.getBytesFechaNacimiento());
						bndModificado = true;
					}
					// imprimir los campos del registro
					if (bndModificado) { // equivalente a (bndModificado == true)
						System.out.println("El registro fue modificado correctamente, los nuevos datos son:");
					}
					System.out.println("Carne: " + a.getCarnet());
					System.out.println("Nombre: " + a.getNombres());
					System.out.println("Fecha de nacimiento: " + dateToString(a.getFechaNacimiento()));
					bndEncontrado = true;
					// si el registro se ha encontrado entonces salir del ciclo
					break;
				}
				registros++;
				// restar los bytes del registro leido
				longitud -= totalBytes;
			}
			// solo si el registro no se encontro imprimir un mensaje
			if (!bndEncontrado) { // esto es equivalente a (bndEncontrado == false)
				System.out.println("No se encontro el carne indicado, por favor verifique");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public Date strintToDate(String strFecha) {
		Date date = null;
		try {
			date = format.parse(strFecha);
		} catch (Exception e) {
			date = null;
			System.out.println("Error en fecha: " + e.getMessage());
		}
		return date;
	}

	public String dateToString(Date date) {
		String strFecha;
		strFecha = format.format(date);
		return strFecha;
	}

	public static String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCarnet(String trim) {
		// TODO Auto-generated method stub
		
	}

	public void setNombre(String trim) {
		// TODO Auto-generated method stub
		
	}

	public void setApellidos(String trim) {
		// TODO Auto-generated method stub
		
	}

	public void setFechaNacimiento(String trim) {
		// TODO Auto-generated method stub
		
	}

	public void setTelefono(String trim) {
		// TODO Auto-generated method stub
		
	}

	public void setCorreo(String trim) {
		// TODO Auto-generated method stub
		
	}

	public Object getTelefono() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getNombres() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getFechaNacimiento() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getApellidos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCarnet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCorreo() {
		// TODO Auto-generated method stub
		return null;
	}

}

	