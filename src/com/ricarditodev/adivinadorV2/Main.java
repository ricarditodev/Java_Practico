package com.ricarditodev.adivinadorV2;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in); //clase para leer datos de entrada
        Random numeros = new Random(); //clase para generar numeros aleatorios
        int numeroSecreto = numeros.nextInt(1000) + 1; //se genera un numero aleatorio entre 1 y 1000

        final int MAX_INTENTOS = 10;
        //valor ingresado por el usuario
        int valorUsuario;
        boolean acierto = false;

        //mostramos informacion en pantalla
        System.out.println("Adivinador 2.0 – Dispones de 10 intentos para adivinar.");
        System.out.println("Rango de valores: 1 a 1000 inclusive.\n");

        for (int i = 1; i <= MAX_INTENTOS; i++) {
            System.out.print("Intento " + i + ">> ");
            //leemos el dato ingresado por el usuario
            valorUsuario = entrada.nextInt();
            entrada.nextLine();

            if (i == 10) {
                break;
            } else if (valorUsuario == numeroSecreto) {
                System.out.println("¡¡¡GANASTE!!!");
                acierto = true;
                break;
            } else if (valorUsuario < numeroSecreto) {
                System.out.println("• El número a adivinar es mayor");
            } else if (valorUsuario > numeroSecreto) {
                System.out.println("• El número a adivinar es menor");
            }
        }

        //si el usuario no acerto el numero, pierde y se muestra el numero secreto
        if (!acierto) {
            System.out.println("¡¡¡PERDISTE!!! El número era " + numeroSecreto);
        }
    }
}
