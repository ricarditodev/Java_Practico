package com.ricarditodev.mastermindV2;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final byte MAX_INTENTOS = 10; //maximos intentos para el usuario
        final byte LARGO_CODIGO = 4; //longitud del codigo para el pensador y el adivinador
        final char PRIMERA_LETRA = 'A'; //65 su codigo ASCII
        final char ULTIMA_LETRA = 'H'; //72 su codigo ASCII

        Scanner entrada = new Scanner(System.in); //para leer la entrada del usuario

        //arreglo para guardar la combinacion de letras al azar del pensador desde la A a la H
        char[] pensador = new char[LARGO_CODIGO];
        char[] adivinador = new char[LARGO_CODIGO]; //arreglo para guardamos la combinacion escogida por el usuario
        int indice;
        char caracterPensador;
        String codigoAdivinador;
        boolean perdedor = true;
        Random generador = new Random(); //generador de numeros aleatorios

        for (int i = 0; i < LARGO_CODIGO; i++) {
            //dentro de la variable indice generamos numeros al azar entre 65 y 72
            indice = generador.nextInt((int) ULTIMA_LETRA - (int) PRIMERA_LETRA + 1) + (int) PRIMERA_LETRA;

            //guardarmos en la variable caracter el indice generado al azar y lo casteamos a tipo char
            caracterPensador = (char) indice;
            pensador[i] = caracterPensador; //guardamos en cada posicion del arreglo pensador el caracter generado previamente
        }

        System.out.println("MasterMind V1.0");
        System.out.println("Dispones de 10 intentos para adivinar el código.");

        //hacemos 10 intentos donde el usuario tiene que adivinar el codigo
        for (int i = 1; i <= MAX_INTENTOS; i++) {
            boolean[] evaluadasP = new boolean[LARGO_CODIGO];
            boolean[] evaluadasA = new boolean[LARGO_CODIGO];
            int R = 0;
            int B = 0;
            System.out.print("Código " + i + " de " + MAX_INTENTOS + ">> ");

            codigoAdivinador = entrada.nextLine();

            //en cada iteracion guardamos en el arreglo cada letra del codigo del adivinador
            for (int j = 0; j < LARGO_CODIGO; j++) {
                adivinador[j] = codigoAdivinador.charAt(j);
            }

            //comparamos los elementos de ambos arreglos en cada posicion
            //si coinciden aumentamos b en 1 y marcamos esa posicion en true para que no vuelva a ser analizada
            for (int x = 0; x < pensador.length; x++) {
                if (pensador[x] == adivinador[x]) {
                    B++;
                    evaluadasP[x] = true;
                    evaluadasA[x] = true;
                }
            }

            /*
            * accedemos al arreglo pensador
            * verificamos si ya fue utilizada esa posicion, sino accedemos al arreglo adivinador
            * recorremos el arreglo adivinador y verificamos si en su posicion no ha sido marcada
            * tambien verificamos si estan en distintas posisiones
            * tambien verificamos si son iguales, de serlo aumentamos r en 1 y marcamos para que no vuelvan a ser verificadas*/
            for (int x = 0; x < pensador.length; x++) {
                if (evaluadasP[x] == false) {
                    for (int z = 0; z < adivinador.length; z++) {
                        if (evaluadasA[z] == false && x != z && pensador[x] == adivinador[z]) {
                            R++;
                            evaluadasP[x] = true;
                            evaluadasA[z] = true;
                            break;
                        }
                    }
                }
            }

            System.out.println("B=" + B + " R=" + R);

            //si en algun momento b es igual a 4 significa que hemos adivinado y rompemos la ejecucion del bucle principal
            //y cambiamos a false la variable booleana para que no se ejecute el codigo del perdedor
            if (B == 4) {
                System.out.println("EXCELENTE!!! Ganaste.");
                perdedor = false;
                break;
            }
        }

        //si despues de los 10 intentos no logramos adivinar, el boolean seguira siendo true y mostramos que perdiste
        if (perdedor) {
            String codigoPensador = new String(pensador);
            System.out.print("PERDISTE!!! El código era " + codigoPensador);
        }
    }
}