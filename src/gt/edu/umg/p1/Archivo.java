package gt.edu.umg.p1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Archivo {
	
	//variables globales
	Scanner sc =  new Scanner(System.in);
	RandomAccessFile fichero = null, entidades = null, atributos = null;
	private final String rutaBase = "C:\\Users\\sheyl\\eclipse-workspace\\ProyectoFinal\\src\\gt\\edu\\umg\\progra1\\proyect";
	//contiene Indice, Nombre de la entidad (30 caracteres maximo), cantidad de atributos, posicion donde inician los atributos
	//=> total bytes = 47 (incluye cambio de linea)
	private final String rutaEntidades = "C:\\Users\\sheyl\\eclipse-workspace\\ProyectoFinal\\src\\gt\\edu\\umg\\progra1\\proyect\\entidades.dat";
	// contiene indice de la entidad, nombre del atributo, tipo de dato, longitud => total de bytes = 43
	private final String rutaAtributos = "C:\\Users\\sheyl\\eclipse-workspace\\ProyectoFinal\\src\\gt\\edu\\umg\\progra1\\proyect\\atirbutos.dat";
}


		