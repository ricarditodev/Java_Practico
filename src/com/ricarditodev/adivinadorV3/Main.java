package com.ricarditodev.adivinadorV3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        //guardamos el dato que el usuario ingresa(= < >)
        String opcion;
        //dos variables booleanas para saber cuando adivino o cuando hizo trampa
        boolean adivino;
        boolean trampa;
        //min y max para el rango de valores introducidos por el usuario
        int min;
        int max;
        //cantidad maxima de intentos de la maquina
        int maxIntentos;
        //numero que la maquina va a tratar de adivinar
        int numeroPosible;
        int i;

        //mostramos informacion revelante al usuario en pantalla
        System.out.println("Intentaré adivinar un número que tú elijas.");
        System.out.println("Cuando indique un número, tú deberás ingresar:");
        System.out.println("\t= si acierto al número que tú quieres que adivine.");
        System.out.println("\t> si el número que tú quieres que adivine es mayor al número que mostré.");
        System.out.println("\t< si el número que tú quieres que adivine es menor al número que mostré.");

        //pedimos los datos al usuario
        System.out.print("Dime el número mínimo: ");
        min = entrada.nextInt();
        entrada.nextLine();

        //pedimos los datos al usuario
        System.out.print("Dime el número máximo: ");
        max = entrada.nextInt();
        entrada.nextLine();

        //aca se hace un salto de linea en la interfaz
        System.out.println();

        //obtenemos la cantidad maxima de intentos de la maquina -- se puede mejorar(restandole 2)
        maxIntentos = (int) (Math.log(max - min + 1) / Math.log(2) + 1);

        System.out.println("Excelente. Adivinaré tu número en no más de " + maxIntentos + " intentos.");
        System.out.print("Presiona ENTER cuando quieras comenzar.");
        entrada.nextLine();
        System.out.println();

        adivino = false;
        trampa = false;
        for (i = 1; i <= maxIntentos; i++) {
            numeroPosible = ((max - min) / 2) + min;
            System.out.print("Intento " + i + " -> " + "El número es >> " + numeroPosible + " ");
            opcion = entrada.nextLine();

            if (opcion.equals("=")) {
                adivino = true;
                break;
            } else if (opcion.equals(">")) {
                min = numeroPosible + 1;
            } else if (opcion.equals("<")) {
                max = numeroPosible - 1;
            }

            //cortamos la ejecucion del bucle si el usuario adivino
            if (adivino) {
                break;
            }

            if (min > max || max < min) {
                System.out.println("Estás haciendo trampa >:v");
                trampa = true;
                break;
            }
        }

        //esto no llegaria a cumplirse por el mecanismo antitrampa
        if ((i == maxIntentos) && (!adivino) && (!trampa)) {
            System.out.println("Ganaste, no pude encontrar el número.");
        } else if (adivino) {
            System.out.println("¡¡¡GANÉ!!! Hazlo un poco más difícil.");
        }
    }
}
