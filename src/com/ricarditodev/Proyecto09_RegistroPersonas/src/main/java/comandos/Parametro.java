package comandos;

/**
 *
 * @author KA EduSoft
 */
public class Parametro {
    public static enum Tipo {STRING, ENTERO, DOUBLE}
    
    private String valorString;
    private Tipo tipo;
    private int valorEntero;
    private double valorDouble;
    
    /**
     * Crea un nuevo parámetro con el valor String pasado como argumento. Si
     * la cadena contiene un entero (int) se establecerá el tipo del parámetro
     * como ENTERO, si posee un real (double) se establecerá como DOUBLE, y en
     * cualquier otro caso como STRING. Esto permitirá usar las operaciones getInt
     * y getDouble respectivamente.
     * @param valor El contenido del parámetro.
     */
    public Parametro(String valor) {
        this.valorString = valor;
        
        try {
            this.valorEntero = Integer.parseInt(valor);
            this.tipo = Tipo.ENTERO;
            return;
        } catch (NumberFormatException ex) {
            
        }
        
        try {
            this.valorDouble = Double.parseDouble(valor);
            this.tipo = Tipo.DOUBLE;
            return;
        } catch (NumberFormatException | NullPointerException ex) {
            
        }
        
        this.tipo = Tipo.STRING;
    }
    
    /**
     * Retorna el valor del parámetro como un String, sin importar si su contenido
     * es int, double o String.
     * @return El contenido del parámetro como un String.
     */
    public String getString() {
        return this.valorString;
    }
    
    /**
     * Retorna el valor entero del parámetro sí y solo si éste es de tipo ENTERO.
     * <br><b>PRECONDICIÓN</b>: El parámetro es de tipo ENTERO.
     * @return El valor entero del parámetro.
     * @see getTipo()
     */
    public int getInt() {
        return this.valorEntero;
    }
    
    /**
     * Retorna el valor real del parámetro como tipo double sí y solo si éste es de tipo DOUBLE.
     * <br><b>PRECONDICIÓN</b>: El parámetro es de tipo DOUBLE.
     * @return El valor double del parámetro.
     * @see getTipo()
     */
    public double getDouble() {
        return this.valorDouble;
    }
    
    /**
     * Retorna el tipo de datos contenido en el parámetro. Si éste contiene un
     * valor que puede ser convertido y obtenido como int se retorna ENTERO; si
     * el valor puede ser convertido y obtenido como dobule se retorna DOUBLE;
     * en cualquier otro caso se retorna STRING. Asimismo, todo valor siempre se
     * puede obtener como String.
     * @return ENTERO si se puede obtener el valor como un número; DOUBLE si se
     * puede obtener como double; STRING en cualquier otro caso.
     */
    public Tipo getTipo() {
        return this.tipo;
    }
}
