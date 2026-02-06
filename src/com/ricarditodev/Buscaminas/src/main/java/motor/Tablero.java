package com.ricarditodev.Buscaminas.src.main.java.motor;

import com.ricarditodev.Buscaminas.src.main.java.motor.*;

/**
 * Representa un tablero del juego Buscaminas como una grilla de objetos de tipo
 * {@link Celda}. Se definen además las constantes que se usarán para definir los
 * distintos estados del juego.
 * @author ricarditodev
 */
public class Tablero {
        //Valores predefinidos para las dimensiones maximas y minimas del tablero
    public static byte
        MIN_ANCHO = 5, //filas
        MIN_LARGO = 5, //columnas
        MAX_ANCHO = 100, //filas
        MAX_LARGO = 100; //columnas

    //El tablero: una grilla con topes (tal como hicimos en el Juego de la Vida).
    private final Celda[][] celdas;
    private int cantidadMinas;
    
    /**
     * Crea un nuevo tablero con las dimensiones indicadas. Las celdas del mismo
     * estarán todas OCULTAS y sin minas. Las cantidades de filas y columnas deben
     * ser mayores que MIN_ANCHO y MIN_LARGO, menores que MAX_ANCHO y MAX_LARGO respectivamente, de lo contrario se
     * establecerán con dichos valores.
     * @param cantidadFilas La cantidad de filas que contendrá el tablero.
     * @param cantidadColumnas La cantidad de columnas que contendrá el tablero.
     */
    public Tablero(byte cantidadFilas, byte cantidadColumnas) {
        byte filas; //variable auxiliar para asignar las filas a las celdas
        byte columnas; //variable auxiliar para asignar las columnas a las celdas

        //si la fila ingresada es menor que ancho, seteamos filas en el ancho mínimo
        if (cantidadFilas < MIN_ANCHO) {
            filas = MIN_ANCHO;
        } else if (cantidadFilas > MAX_ANCHO) {
            filas = MAX_ANCHO;
        } else {
            filas = cantidadFilas;
        }

        if (cantidadColumnas < MIN_LARGO) {
            columnas = MIN_LARGO;
        } else if (cantidadColumnas > MAX_LARGO) {
            columnas = MAX_LARGO;
        } else {
            columnas = cantidadColumnas;
        }

        this.celdas = new Celda[filas][columnas];

        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                this.celdas[i][j] = new Celda(false, Celda.EstadoCelda.OCULTA, (byte) 0);
            }
        }

        this.cantidadMinas = 0;
    }
    
    /**
     * Indica el largo (cantidad de columnas) del tablero.
     * @return La cantidad de columnas en el tablero.
     */
    public byte getLargo() {
        return (byte) this.celdas[0].length;
    }
    
    /**
     * Indica el ancho (cantidad de filas) del tablero.
     * @return La cantidad de filas del tablero.
     */
    public byte getAncho() {
        return (byte) this.celdas.length;
    }
    
    /**
     * Indica si una posición es válida para el tablero.
     * @param fila La posición de la fila (la primera fila es 0).
     * @param columna La posición de la columna (la primera columna es 0).
     * @return TRUE si la posición es adecuada en el tablero, FALSE si no lo es.
     */
    public boolean esPosicionValida(byte fila, byte columna) {
         return fila >= 0 && fila < this.celdas.length && columna >= 0 && columna < this.celdas[0].length;
    }
    
    /**
     * Si se pasa TRUE en tieneBomba se establecerá la celda actual como una celda
     * con mina (salvo que ésta ya estuviera marcada así); en tal caso se sumará
     * 1 bomba circundante a todas las celdas alrededor de la posición dada. Si se
     * pasa FALSE en tieneBomba se establecerá la celda actual como una celda sin
     * mina (salvo que ya estuviera marcada así); en tal caso se restará 1 bomba
     * circundante a todas las celdas alrededor de la posición dada.<br><br>
     * <b>PRECONDICIÓN:</b> La posicíon dada {@link esPosicionValida}.
     * @param tieneBomba TRUE para indicar que la celda contiene bomba, FALSE si no.
     * @param fila La fila en que está la celda.
     * @param columna La columna en que está la celda.
     */
    public void asignarBomba(boolean tieneBomba, byte fila, byte columna) {
        Celda celdaCircundante;
        byte minasActuales;

        if (celdas[fila][columna].tieneBomba() == tieneBomba) {
            return;
        }

        celdas[fila][columna].setTieneBomba(tieneBomba);

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (esPosicionValida((byte) i, (byte) j)) {
                    if (i == fila && j == columna) {
                          continue;
                    }

                    celdaCircundante = this.celdas[i][j];

                    minasActuales = celdaCircundante.getBombasCircundantes();

                    if (tieneBomba) {
                        celdaCircundante.setBombasCircundantes((byte) (minasActuales + 1));
                    } else {
                        celdaCircundante.setBombasCircundantes((byte) (minasActuales - 1));
                    }
                }
            }
        }

        if (tieneBomba) {
            cantidadMinas++;
        } else {
            cantidadMinas--;
        }
    }
    
    /**
     * Retorna la celda en la posición dada.<br><br>
     * <b>PRECONDICIÓN</b>: La posición {@link esPosicionValida}.
     * @param fila La fila de la celda buscada.
     * @param columna La columna de la celda buscada.
     * @return La celda en la posición dada.
     */
    public Celda getCelda(byte fila, byte columna) {
        if (esPosicionValida(fila, columna)) {
            return celdas[fila][columna];
        } else {
            return null;
        }
    }
    
    /**
     * Indica la cantidad total de celdas existentes en el tablero.
     * @return La cantidad de celdas en el tablero.
     */
    public int getCantidadCeldas() {
        int cantidadCeldas = 0;

        for (int i = 0; i < this.celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                cantidadCeldas++;
            }
        }

        return cantidadCeldas;
    }
    
    /**
     * Indica la cantidad de minas en el tablero.
     * @return La cantidad de minas en el tablero.
     */
    public int getCantidadMinas() {
        return cantidadMinas;
    }
}
