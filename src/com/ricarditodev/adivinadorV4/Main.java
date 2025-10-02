package com.ricarditodev.adivinadorV4;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Programa ADIVINADOR utilizando enumerados, registros y rule-switch
 */
public class Main {
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

    /**
     * Modelo del juego:<br>
     * <ul>
     *     <li><b>dificultad:</b> Arreglo de 1 celda conteniendo la dificultad del juego</li>
     *     <li><b>maxIntentos:</b> Cantidad máxima de intentos con que se inició el juego según su dificultad</li>
     *     <li><b>estado:</b> Arreglo de 1 celda conteniendo el estado actual del juego</li>
     *     <li><b>numeroSecreto:</b> El número secreto generado aleatoriamente entre 1 y NUMERO_MAXIMO</li>
     *     <li><b>intentoActual:</b> El intento actual del jugador</li>
     * </ul>
     * @param dificultad
     * @param maxIntentos
     * @param estado
     * @param numeroSecreto
     * @param intentoActual
     */
    public static record Juego (
            Dificultad[] dificultad,
            AtomicInteger maxIntentos,
            Estado[] estado,
            AtomicInteger numeroSecreto,
            AtomicInteger intentoActual
    ) {}

    /**
     * Inicializa un juego con los valores correctos acorde a la dificultad asignada.
     * El estado inicial será jugando JUGANDO y el intento actual será 0.
     * También se generará un número aleatorio entre 1 y NUMERO_MAXIMO
     * @param d La dificultad con que se iniciará el juego
     * @return Un juego listo para comenzar
     * @see Dificultad
     * @see Estado
     * @see Juego
     * @see NUMERO_MAXIMO
     */
    public static Juego iniciarJuego(Dificultad d) {
        Juego j = new Juego(new Dificultad[1], new AtomicInteger(), new Estado[1], new AtomicInteger(), new AtomicInteger());
        j.dificultad[0] = d;

        if (d == Dificultad.FACIL) {
            j.maxIntentos.set(MAX_INTENTOS_FACIL);
        } else {
            j.maxIntentos.set(MAX_INTENTOS_DIFICIL);
        }

        j.estado[0] = Estado.JUGANDO;
        j.numeroSecreto.set(new Random().nextInt(NUMERO_MAXIMO) + 1);

        return j;
    }

    /**
     * Si n es igual que el número secreto, se retorna ES_IGUAL
     * Si el número secreto es menor que n se retorna ES_MENOR
     * Si no, se retorna ES_MAYOR
     * Además se aumenta el número de intentos y se cambia el estado del juego por GANO si el usuario adivinó el número
     * PERDIO si agotó los intentos. El parámetro mensaje obtendrá un mensaje adecuado en función de lo que ocurra
     * @param n El número ingresado por el usuario
     * @param j El juego
     * @param mensaje El mensaje se obtendrá aquí
     * @return ES_IGUAL, ES_MENOR, ES_MAYOR en función del número secreto comparado con n
     */
    public static Resultado verificarNumero(int n, Juego j, StringBuilder mensaje) {
        j.intentoActual.set(j.intentoActual.get() + 1);
        mensaje.delete(0, mensaje.length());
        Resultado resultado;

        if (n == j.numeroSecreto.get()) {
            j.estado[0] = Estado.GANO;
            mensaje.append("¡¡¡GANASTE!!!");
            resultado = Resultado.ES_IGUAL;
        } else if (j.numeroSecreto.get() < n) {
            mensaje.append("El numero a adivinar es menor");
            resultado = Resultado.ES_MENOR;
        } else {
            mensaje.append("El numero a adivinar es mayor");
            resultado = Resultado.ES_MAYOR;
        }

        if (j.intentoActual.get() == j.maxIntentos.get()) {
            mensaje.delete(0, mensaje.length());
            mensaje.append("¡¡¡PERDISTE!!! EL numero era ").append(j.numeroSecreto);
            j.estado[0] = Estado.PERDIO;
        }

        return resultado;
    }
    public static void main(String[] args) {
        String dif;
        var consola = new Scanner(System.in);
        Juego juego;
        int numeroUsuario;
        Resultado resultado;
        StringBuilder mensaje = new StringBuilder();

        System.out.print("Elige la dificultad [F]FACIL/[D]DIFICIL >> ");
        dif = consola.nextLine();

        while ((dif.length() != 1) || ((dif.charAt(0) != 'F') && (dif.charAt(0) != 'D'))) {
            System.out.print("ERROR: Ingresa D o F >> ");
            dif = consola.nextLine();
        }

        juego = switch(dif) {
            case "F" -> iniciarJuego(Dificultad.FACIL);
            case "D" -> iniciarJuego(Dificultad.DIFICIL);
            default -> iniciarJuego(Dificultad.FACIL);
        };

        System.out.println("Dispones de " + juego.maxIntentos + " intentos para adivinar");

        do {
            System.out.print("Intento " + (juego.intentoActual.get() + 1) + " >> ");
            numeroUsuario = consola.nextInt();
            resultado = verificarNumero(numeroUsuario, juego, mensaje);
            System.out.println(mensaje);
        } while (juego.estado[0] == Estado.JUGANDO);
    }
}
