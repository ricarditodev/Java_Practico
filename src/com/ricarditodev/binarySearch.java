package com.ricarditodev;

import java.util.Scanner;

public class binarySearch {
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in); //lectura de datos

        //array de numeros primos de 25 elementos
        int[] array = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        int min = 0; //primer indice del array
        int max = array.length - 1; //ultimo indice del array
        int intento = 1;

        //guardamos el valor del usuario
        int target;

        //mostramos informacion al usuario
        System.out.print("Introduce un numero para saber si es primo: ");
        target = consola.nextInt(); //pedimos un valor al usuario


        while (min <= max) {
            //formula para encontrar el promedio del array y redondearlo hacia abajo
            int guess = (int) Math.floor((max + min) / 2);

            //si el numero ingresado por el usuario es igual al promedio en su indice
            if (target == array[guess]) {
                System.out.print("Lo encontraste" + " en " + intento + " intentos" + " en el indice: " + guess);
                break;
            } else if (array[guess] < target) {
                System.out.println("El intento fue demasiado bajo");
                min = guess + 1;
            } else if (array[guess] > target) {
                System.out.println("El intento fue demasiado alto");
                max = guess - 1;
            }

            //si max es menor que minimo significa que no existe el numero ingresado por el usuario en el array
            if (max < min) {
                System.out.println(-1);
                break;
            }

            intento++;
        }
    }
}

/*
var doSearch = function(array, targetValue) {
	var min = 0;
	var max = array.length - 1;
	var intento = 1;

    var guess;

    while (min <= max) {

        guess  = Math.floor((max + min) / 2);

        if (targetValue === array[guess]) {
            println("lo encontraste " + guess);
            println("numero total de intentos " + intento);
            return guess;
        } else if (array[guess] < targetValue) {
            min = guess + 1;

        } else if (array[guess] > targetValue) {
            max = guess - 1;
        }

        intento++;
    }



	return -1;
};

var primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
		41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97];

var result = doSearch(primes, 73);
println("Found prime at index " + result);

Program.assertEqual(doSearch(primes, 73), 20);
Program.assertEqual(doSearch(primes, 3), 1);
Program.assertEqual(doSearch(primes, 5), 2);
*/
