package gt.edu.umg.p1;

public enum TiposDeDatos {
	
	INT(1), LONG(2), STRING(3), DOUBLE(4), FLOAT(5), DATE(6), CHAR(7);
	
	private final int value;
    private TiposDeDatos(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}
