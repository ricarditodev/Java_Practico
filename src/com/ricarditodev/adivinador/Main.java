package com.ricarditodev.adivinador;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in); //lectura de entrada
        Random numeros = new Random(); //generador de numeros aleatorios

        //genero un numero entre 1 y 100
        int numeroSecreto = numeros.nextInt(100) + 1;
        int valorLeido;
        boolean adivino = false;

        for (int intento = 1; intento <= 10; intento++) {
            //muestro mensaje al usuario
            System.out.print("Intento " + intento + ">> ");
            valorLeido = entrada.nextInt();
            entrada.nextLine();

            if (valorLeido == numeroSecreto) {
                System.out.print("Acertaste el número");
                adivino = true;
                break;
            }
        } //fin del bucle

        if (!adivino) {
            System.out.print("No adivinaste el número " + numeroSecreto);
        }
    }
}
