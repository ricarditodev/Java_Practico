package com.ricarditodev.truco_de_las_21_cartas;

import java.util.Random;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        //Constantes para estandarizar los valores del programa
        final short MAX_TARJETAS_GRUPO = 7; //Tarjetas por grupo
        final short MAX_GRUPOS = 3; //Cantidad de grupos
        final short MAX_TARJETAS = MAX_TARJETAS_GRUPO * MAX_GRUPOS; //Total de tarjetas.
        final short MIN_TARJETA_VALOR = 'A'; //Tarjeta incial, en este caso letra A.
        final short MAX_TARJETA_VALOR = (char)(MAX_TARJETAS + (int)('A') -1); //Tarjeta final.

        //Variables que deben usarse para resolver el problema.
        char[] grupo1= new char[MAX_TARJETAS_GRUPO];
        char[] grupo2= new char[MAX_TARJETAS_GRUPO];
        char[] grupo3= new char[MAX_TARJETAS_GRUPO];
        char[] deck= new char[MAX_TARJETAS];

        var generador = new Random(); //generador de numeros aleatorios
        int indice; //aqui guardamos el numero generado al azar entre 65 y 85
        int secuencia; //
        var consola = new Scanner(System.in); //entrada por consola
        int opcion = 0; //opcion del usuario donde esta su carta

        //recorremos el arreglo deck
        for (int i = 0; i < deck.length; i++) {
            //en cada iteracion generamos un numero al azar entre 65 y 85
            indice = generador.nextInt((int) MAX_TARJETA_VALOR - (int) MIN_TARJETA_VALOR + 1) + (int) (MIN_TARJETA_VALOR);

            int j = 0; //variable auxiliar para verificar que los numeros no se repiten en el arreglo deck
            while (j < i) {
                //si deck en indice de j es igual al numero aleatorio
                //restablecemos j a 0 y volvemos a generar un numero al azar para que lo vuelva a comprobar desde el inicio
                if (deck[j] == indice) {
                    j = 0;
                    indice = generador.nextInt((int) MAX_TARJETA_VALOR - (int) MIN_TARJETA_VALOR + 1) + (int) (MIN_TARJETA_VALOR);
                } else {
                    j++;
                }
            }

            //guardamos en cada posicion del arreglo el numero al azar casteado a un caracter
            deck[i] = (char)indice;
        }

        System.out.println("Haremos 3 secuencias. Empecemos...");

        for (secuencia = 1; secuencia < 4; secuencia++ ) {
            System.out.println("Secuencia " + secuencia + ":");

            //asignar a los 3 grupos sus 7 cartas
            for (int i = 0; i < MAX_TARJETAS; i++) {
                int grupo = i % MAX_GRUPOS;
                int posicion = i / MAX_GRUPOS;
                if (grupo == 0) {
                    grupo1[posicion] = deck[i];
                } else if (grupo == 1) {
                    grupo2[posicion] = deck[i];
                } else {
                    grupo3[posicion] = deck[i];
                }
            }

            //mostrar las cartas en pantalla
            for (int i = 0; i < MAX_TARJETAS_GRUPO; i++) {
                System.out.println(grupo1[i] + "\t" + grupo2[i] + "\t" + grupo3[i]);
            }

            //pedimos al usuario que ingrese un numero entre 1 y 3
            //si el numero es distinto de 1 y 3 repetimos el bucle hasta que ingrese una opcion correcta
            do {
                System.out.print("En qué grupo está tu tarjeta [1,2,3]: " );
                opcion = consola.nextInt();

                if (opcion < 1 || opcion > 3) {
                    System.out.println("ERROR - Opción incorrecta, ingresa una opción válida [1,2,3]: ");
                }
            } while (opcion < 1 || opcion > 3);

            if (opcion == 1) {
                // grupo2 primero
                for (int i = 0; i < 7; i++) {
                    deck[i] = grupo2[i];
                }
                // grupo1 en el medio
                for (int i = 7; i < 14; i++) {
                    deck[i] = grupo1[i - 7];
                }
                // grupo3 al final
                for (int i = 14; i < 21; i++) {
                    deck[i] = grupo3[i - 14];
                }
            } else if (opcion == 2) {
                // grupo1 primero
                for (int i = 0; i < 7; i++) {
                    deck[i] = grupo1[i];
                }
                // grupo2 en el medio
                for (int i = 7; i < 14; i++) {
                    deck[i] = grupo2[i - 7];
                }
                // grupo3 al final
                for (int i = 14; i < 21; i++) {
                    deck[i] = grupo3[i - 14];
                }
            } else if (opcion == 3) {
                // grupo1 primero
                for (int i = 0; i < 7; i++) {
                    deck[i] = grupo1[i];
                }
                // grupo3 en el medio
                for (int i = 7; i < 14; i++) {
                    deck[i] = grupo3[i - 7];
                }
                // grupo2 al final
                for (int i = 14; i < 21; i++) {
                    deck[i] = grupo2[i - 14];
                }
            }
        }

        System.out.println("Obviamente elegiste la " + deck[10]);
    }
}