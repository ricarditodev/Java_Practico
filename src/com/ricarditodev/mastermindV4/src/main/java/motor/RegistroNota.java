package motor;

/**
 * Representa el registro de una nota en el juego (buenos y regulares) asociadas
 * a un código presentado por el adivinador, según la evaluación dada por el pensador
 * a dicho código.
 * @author ricarditodev
 */
public class RegistroNota {
    private final char[] codigo;
    private final int buenos, regulares;
    
    /**
     * Crea un nuevo objeto RegistroNota con el código y notas asociadas a él.
     * @param codigo El código del adivinador presentado.
     * @param buenos Los buenos recibidos para dicho código.
     * @param regulares Los regulares recibidos para dicho código.
     */
    public RegistroNota(char[] codigo, int buenos, int regulares) {

    }

    /**
     * Retorna el código almacenado en este registro.
     * @return El código almacenado.
     */
    public char[] obtenerCodigo() {

    }

    /**
     * Retorna los buenos asociados a este código.
     * @return La nota buenos recibida para este código.
     */
    public int obtenerBuenos() {

    }

    /**
     * Retorna los regulares recibidos para este código.
     * @return Los regulares recibidos para este código.
     */
    public int obtenerRegulares() {

    } 
}
