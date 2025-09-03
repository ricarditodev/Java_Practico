package com.ricarditodev.adivinadorV2;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in); //lectura de entrada
        Random numeros = new Random(); //generar numeros aleatorios

        //genera numeros aleatorios entre 0 y 99 por eso sumamos 1
        int numeroSecreto = numeros.nextInt(100) + 1;
        //guardamos en una variable el numero ingresado por el usuario
        int numeroLeido;
        //si el usuario adivinó, cambiamos esta variable a true para controlar el flujo
        boolean adivino = false;

        final int MAX_INTENTOS = 10; //numero maximo de intentos

        //mostramos al usuario el nombre del juego y los intentos que dispone para jugar
        System.out.println("Adivinador 2.0 – Dispones de 10 intentos para adivinar.");

        //bucle que hace 10 intentos para que el usuario adivine el numero
        for (int i = 1; i <= MAX_INTENTOS; i++) {
            //mostramos el numero de intento al usuario
            System.out.print("Intento " + i + ": ");
            //pedimos que el usuario ingrese un numero
            numeroLeido = entrada.nextInt();

            //evaluamos si el numero secreto coincide con el numero ingresado por el usuario
            if (numeroSecreto == numeroLeido) {
                System.out.println("¡¡¡GANASTE!!!");
                adivino = true; //si se cumple cambiamos adivino a true
                break;
            } else if (i == MAX_INTENTOS) {
                break;
            } else if (numeroLeido < numeroSecreto) {
                System.out.println("El número a adivinar es mayor");
            } else if (numeroLeido > numeroSecreto) {
                System.out.println("El número a adivinar es menor");
            }
        }

        //si al final el usuario no adivina el numero, negamos la variable que está en false para ejecutar el condicional
        if (!adivino) {
            System.out.print("¡¡¡PERDISTE!!! El numero era " + numeroSecreto);
        }
    }
}
