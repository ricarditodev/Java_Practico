package motor;

/**
 * Representa el historial de códigos y notas (RegistroNota) almacenados durante
 * una partida. La historia no es más que una lista de registros de códigos
 * presentados por el adivinador y las notas que éstos recibieron.
 * @author ricarditodev
 * @see RegistroNota
 */
public class Historia {
    private RegistroNota[] info;
    private int tope;
    
    /**
     * Crea una nueva historia vacía.
     */
    public Historia(){

    }
    
    /**
     * Indica si la historia es vacía (no contiene ningún registro).
     * @return TRUE si la historia es vacía, FALSE si no lo es.
     */
    public boolean esVacia(){

    }
    
    /**
     * Agrega un nuevo registro al final de la historia, el cual es, como lo
     * indican los parámetros, el código y las notas que este recibió.
     * @param codigo El código a almacenar.
     * @param buenos Los buenos que este código recibió como nota.
     * @param regulares Los regulares que este código recibió como nota.
     */
    public void registrar(char[] codigo, int buenos, int regulares){

    }
    
    /**
     * Retorna la cantidad de registros contenidos en la historia.
     * @return La cantidad de registros en la historia, o cero si está vacía.
     */
    public int cantidad(){

    }
    
    /**
     * Retorna el código en la posición dada por índice. La historia está indizada
     * desde 0 hasta cantidad()-1.<br><br>
     * <b>PRECONDICIÓN:</b> El índice es válido.
     * @param indice El índice para el cual se quiere obtener el código.
     * @return El registro en la posicíon indice.
     */
    public RegistroNota obtenerRegistro(int indice){

    }
    
    /**
     * Indica si el índice es válido.
     * @param indice El índice a evaluar.
     * @return TRUE si el índice es válido, FALSE si no lo es.
     */
    public boolean esIndiceValido(int indice){

    }
}
