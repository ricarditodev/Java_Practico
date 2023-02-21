package com.ricarditodev.mastermindV1;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final byte MAX_INTENTOS = 10;
        final byte LARGO_CODIGO = 4;
        final char PRIMERA_LETRA = 'A';
        final char ULTIMA_LETRA = 'H';

        int indice;
        int b;
        int r;
        var letrasEntrada = "";
        boolean perdiste = false;

        char[] letrasPensador = new char[LARGO_CODIGO];
        char[] letrasAdivinador = new char[LARGO_CODIGO];

        Scanner entrada = new Scanner(System.in);
        Random gerador = new Random();

        //generamos letras aleatorias y las guardamos en un arreglo de caracteres
        for (int i = 0; i < LARGO_CODIGO; i++) {
            indice = gerador.nextInt((int) ULTIMA_LETRA - (int) PRIMERA_LETRA + 1) + (int) (PRIMERA_LETRA);

            int l = 0;
            while (l < i) {
                if (letrasPensador[l] == indice) {
                    l = 0;
                    indice = gerador.nextInt((int) ULTIMA_LETRA - (int) PRIMERA_LETRA + 1) + (int) (PRIMERA_LETRA);
                } else {
                    l++;
                }
            }

            letrasPensador[i] = (char) indice;
        }

        System.out.println("MasterMind V1.0");
        System.out.println("Dispones de 10 intentos para adivinar el código.");

        //con un bucle, hacemos la ejecucion completa del programa
        for (int i = 1; i <= MAX_INTENTOS; i++) {
            b = 0;
            r = 0;

            System.out.print("Código " + i + " de 10>> ");
            letrasEntrada = entrada.nextLine();

            if (i == MAX_INTENTOS) {
                perdiste = true;
            }

            //la entrada del usuario la guardamos en el arreglo de caracteres
            // Taking the input from the user and putting it into an array.
            for (int j = 0; j < LARGO_CODIGO; j++) {
                letrasAdivinador[j] = letrasEntrada.charAt(j);
            }

            //con dos arreglos anidados comparamos los elementos de ambos arreglos en cada posicion y la poisicion siguiente respectivamente
            // Comparing the letters of the word that the user has to guess with the letters of the word that the user has guessed.
            for (int x = 0; x < letrasPensador.length; x++) {
                for (int z = 0; z < letrasAdivinador.length; z++) {
                    //si los elementos son iguales en la misma posicion, aumentamos b en 1, si son iguales pero en distintas posiciones, aumentamos r en 1
                    if (letrasPensador[x] == letrasAdivinador[z]) {
                        if (x == z) {
                            b++;
                        } else {
                            r++;
                        }
                        break;
                    }
                }
            }

            System.out.println("B = " + b + " R = " + r);

            if (b == 4) {
                perdiste = false;
                break;
            }
        }

        if (perdiste) {
            System.out.println("PERDISTE!!! El código era " + letrasPensador[0] + letrasPensador[1] + letrasPensador[2] + letrasPensador[3]);
        } else {
            System.out.println("EXCELENTE!!! Ganaste.");
        }
    }
}