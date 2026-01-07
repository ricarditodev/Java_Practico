package com.ricarditodev.adivinadorV4;

import java.util.Scanner;

import com.ricarditodev.adivinadorV4.logica.Juego;

/**
 * Programa ADIVINADOR utilizando enumerados, registros y rule-switch
 */
public class Main {

    public static void main(String[] args) {
        String dif;
        var consola = new Scanner(System.in);
        Juego juego;
        int numeroUsuario;
        Juego.Resultado resultado;
        StringBuilder mensaje = new StringBuilder();

        System.out.print("Elige la dificultad [F]FACIL/[D]DIFICIL >> ");
        dif = consola.nextLine();

        while ((dif.length() != 1) || ((dif.charAt(0) != 'F') && (dif.charAt(0) != 'D'))) {
            System.out.print("ERROR: Ingresa D o F >> ");
            dif = consola.nextLine();
        }

        juego = switch(dif) {
            case "F" -> new Juego(Juego.Dificultad.FACIL);
            case "D" -> new Juego(Juego.Dificultad.DIFICIL);
            default -> new Juego(Juego.Dificultad.FACIL);
        };

        System.out.println("Dispones de " + juego.getMaxIntentos() + " intentos para adivinar");

        do {
            System.out.print("Intento " + (juego.getIntentoActual() + 1) + " >> ");
            numeroUsuario = consola.nextInt();
            resultado = juego.verificarNumero(numeroUsuario, mensaje);
            System.out.println(mensaje);
        } while (juego.getEstado() == Juego.Estado.JUGANDO);
    }
}
