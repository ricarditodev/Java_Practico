package motor;

/**
 * Representa una posición dada por dos corrdenadas: fila y columna.
 * @author ricarditodev
 */
public class Posicion {
    private byte fila, columna;
    
    /**
     * Crea una nueva posición con los valores pasados como argumentos.
     * @param fila La fila de la posición o coordenada.
     * @param columna La columna de la posicíon o coordenada.
     */
    public Posicion(byte fila, byte columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Retorna la fila de coordenada.
     * @return La fila.
     */
    public byte getFila() {
        return this.fila;
    }

    /**
     * Asigna la fila de la coordenada. Si esta es menor que 0 se establece en cero. 
     * @param fila La fila de la coordenada.
     */
    public void setFila(byte fila) {
        if (fila < 0) {
            this.fila = 0;
        } else {
            this.fila = fila;
        }
    }

    /**
     * La columna de la coordenada o posición.
     * @return La columna de la posición.
     */
    public byte getColumna() {
        return this.columna;
    }

    /**
     * Asigna la columna de la coordenada. Si esta es menor que 0 se establece en cero.
     * @param columna La columna de la coordenada.
     */
    public void setColumna(byte columna) {
        if (columna < 0) {
            this.columna = 0;
        } else {
            this.columna = columna;
        }
    }
}
