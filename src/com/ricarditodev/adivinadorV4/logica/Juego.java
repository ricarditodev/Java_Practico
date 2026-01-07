package com.ricarditodev.adivinadorV4.logica;

import java.util.Random;

/**
 * Modelo del juego:<br>
 * <ul>
 *     <li><b>dificultad:</b> Arreglo de 1 celda conteniendo la dificultad del juego</li>
 *     <li><b>maxIntentos:</b> Cantidad máxima de intentos con que se inició el juego según su dificultad</li>
 *     <li><b>estado:</b> Arreglo de 1 celda conteniendo el estado actual del juego</li>
 *     <li><b>numeroSecreto:</b> El número secreto generado aleatoriamente entre 1 y NUMERO_MAXIMO</li>
 *     <li><b>intentoActual:</b> El intento actual del jugador</li>
 * </ul>
 */
public class Juego {
    /**
     * cantidad maxima de intentos en modo facil
     */
    public static int MAX_INTENTOS_FACIL = 15;

    /**
     * cantidad maxima de intentos en modo dificil
     */
    public static int MAX_INTENTOS_DIFICIL = 10;

    /**
     * numero maximo a adivinar, se genera un numero aleatorio entre 1 y NUMERO_MAXIMO
     */
    public static int NUMERO_MAXIMO = 100;

    /**
     * Las dificultades disponibles en el juego
     */
    public static enum Dificultad {FACIL, DIFICIL}

    /**
     *Los estados del juego.<br>
     * <ul>
     *     <li>
     *         <b>JUGANDO</b>: El juego ha iniciado. No se ha ganado ni perdido
     *     </li>
     *     <li>
     *         <b>GANO</b>: El jugador adivinó el número secreto antes de agotar los intentos
     *     </li>
     *     <li>
     *         <b>PERDIO</b>: El jugador agotó los intentos antes de adivinar el número secreto
     *     </li>
     * </ul>
     */
    public static enum Estado {JUGANDO, GANO, PERDIO}

    /**
     * se utilizará para comparar el número ingresado por el usuario frente al número secreto
     */
    public static enum Resultado {ES_MAYOR, ES_IGUAL, ES_MENOR}

    private final Dificultad dificultad;
    private final int maxIntentos;
    private Estado estado;
    private final int numeroSecreto;
    private int intentoActual;

    /**
     * Inicializa un juego con los valores correctos acorde a la dificultad asignada.
     * El estado inicial será jugando JUGANDO y el intento actual será 0.
     * También se generará un número aleatorio entre 1 y NUMERO_MAXIMO
     * @param d La dificultad con que se iniciará el juego
     * @see Dificultad
     * @see Estado
     * @see NUMERO_MAXIMO
     */
    public Juego(Dificultad d) {
        this.intentoActual = 0;
        this.dificultad = d;

        if (d == Dificultad.FACIL) {
            this.maxIntentos = MAX_INTENTOS_FACIL;
        } else {
            this.maxIntentos = MAX_INTENTOS_DIFICIL;
        }

        this.estado = Estado.JUGANDO;
        this.numeroSecreto = (new Random().nextInt(NUMERO_MAXIMO) + 1);
    }

    /**
     * Si n es igual que el número secreto, se retorna ES_IGUAL
     * Si el número secreto es menor que n se retorna ES_MENOR
     * Si no, se retorna ES_MAYOR
     * Además se aumenta el número de intentos y se cambia el estado del juego por GANO si el usuario adivinó el número
     * PERDIO si agotó los intentos. El parámetro mensaje obtendrá un mensaje adecuado en función de lo que ocurra
     * @param n El número ingresado por el usuario
     * @param mensaje El mensaje se obtendrá aquí
     * @return ES_IGUAL, ES_MENOR, ES_MAYOR en función del número secreto comparado con n
     */
    public Resultado verificarNumero(int n,StringBuilder mensaje) {
        this.intentoActual++;
        mensaje.delete(0, mensaje.length());
        Resultado resultado;

        if (n == this.numeroSecreto) {
            this.estado = Estado.GANO;
            mensaje.append("¡¡¡GANASTE!!!");
            resultado = Resultado.ES_IGUAL;
        } else if (this.numeroSecreto < n) {
            mensaje.append("El numero a adivinar es menor");
            resultado = Resultado.ES_MENOR;
        } else {
            mensaje.append("El numero a adivinar es mayor");
            resultado = Resultado.ES_MAYOR;
        }

        if ((this.estado != Estado.GANO) && (this.intentoActual == this.maxIntentos)) {
            mensaje.delete(0, mensaje.length());
            mensaje.append("¡¡¡PERDISTE!!! EL numero era ").append(this.numeroSecreto);
            this.estado = Estado.PERDIO;
        }

        return resultado;
    }

    public Dificultad getDificultad() {
        return this.dificultad;
    }

    public int  getMaxIntentos() {
        return this.maxIntentos;
    }
    public Estado getEstado() {
        return this.estado;
    }

    public int getNumeroSecreto() {
        return this.numeroSecreto;
    }

    public int getIntentoActual() {
        return this.intentoActual;
    }
}
