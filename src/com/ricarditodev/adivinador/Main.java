package com.ricarditodev.adivinador;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in); //lectura de entrada
        Random numeros = new Random(); //clase para generar numeros aleatorios

        //se genera un numero entre 1 y 100
        int numeroSecret = numeros.nextInt(100) + 1;
        int valorUsuario;
        boolean acierto = false;

        for (int i = 1; i <= 10; i++) {
            System.out.print("Intento " + i + " >> ");
            valorUsuario = entrada.nextInt();
            entrada.nextLine();

            if (valorUsuario == numeroSecret) {
                System.out.print("Acertaste");
                acierto = true;
                break;
            }
        }

        if (!acierto) {
            System.out.println("No acertaste, el numero secreto era: " + numeroSecret);
        }
    }
}
