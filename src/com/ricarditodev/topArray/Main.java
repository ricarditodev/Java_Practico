package com.ricarditodev.topArray;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static final int MAX_CELDAS = 100;

    public static record TopArrayOrdenado(
            int[] arreglo,
            AtomicInteger tope
    ) {}

    /**
     * Crea un arreglo vacío que recibe como argumento la cantidad máxima de celdas que tendrá
     * @param n Cantidad máxima de celdas que tendrá
     * @return Retorna el arreglo creado
     */
    public static TopArrayOrdenado iniciarArreglo(int n) {
        return new TopArrayOrdenado(new int[n], new AtomicInteger(-1));
    }

    /**
     * Retorna TRUE si el arreglo está vacío, FALSE en caso contrario
     * @param x El arreglo a evaluar
     * @return TRUE si está vacío, FALSE en caso contrario
     */
    public static boolean esVacio(TopArrayOrdenado x) {
        return x.tope.get() == -1;
    }

    /**
     * Retorna TRUE si el arreglo está lleno, es decir, si tiene MAX_CELDAS elementos. FALSE en caso contraio
     * @param x El arreglo a evaluar
     * @return TRUE si está lleno, FALSE en caso contrario
     */
    public static boolean esCompleto(TopArrayOrdenado x) {
        return x.tope.get() == x.arreglo.length - 1;
    }

    /**
     * Muestra la cantidad máxima de elementos que el arreglo puede contener
     * @param x El arreglo que al que vamos a acceder para saber cuantos elementos puede contener
     * @return Retorna la cantidad máxima de elementos que puede contener el arreglo
     */
    public static int cantidadMaxima(TopArrayOrdenado x) {
        return x.arreglo.length;
    }

    /**
     * Cantidad de elementos en x
     * @param x El arreglo cuya cantidad de elementos se desea conocer
     * @return La cantidad de elementos guardados en x
     */
    public static int cantidad(TopArrayOrdenado x) {
        return x.tope.get() + 1;
    }

    /**
     * Agrega en el arreglo el entero pasado como argumento ordenado de forma ascendente. <br>
     * <b>PRECONDICIÓN:</b> El arreglo no debe estar lleno
     * @param n El elemento a añadir al arreglo
     * @param x El arreglo en el cual se ingresa el elemento
     */
    public static void add(int n, TopArrayOrdenado x) {
        boolean insertado = false;

        if (esCompleto(x)) {
            System.out.println("El arreglo está lleno, no se pueden agregar más números");
            return;
        }

        if (esVacio(x)) {
            x.arreglo[0] = n;
            x.tope.set(0);
            return;
        }

        for (int i = 0; i <= x.tope.get(); i++) {
            if (n <= x.arreglo[i]) {
                for (int j = x.tope.get() + 1; j > i; j--) {
                    x.arreglo[j] = x.arreglo[j - 1];
                }
                x.arreglo[i] = n;
                x.tope.set(x.tope.get() + 1);
                insertado = true;
                break;
            }
        }

        if (!insertado) {
            x.tope.set(x.tope.get() + 1);
            x.arreglo[x.tope.get()] = n;
        }
    }

    /**
     * Retorna TRUE si el entero pasado como argumento existe en el arreglo, FALSE si no
     * Si el arreglo está vacío también se retorna FALSE
     * @param n El elemento que estamos verificando si existe
     * @param x El arreglo que vamos a recorrer
     * @return Retorna false si el elemento n no existe
     */
    public static boolean existe(int n, TopArrayOrdenado x) {
        if (x.arreglo.length == 0) {
            return false;
        }

        for (int i = 0; i <= x.tope.get(); i++) {
            if (x.arreglo[i] == n) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retorna la cantidad de ocurrencias del entero pasado como argumento
     * Cero si dicho número no existe dentro del arreglo
     * @param n El número a verificar si se repite n cantidad de veces
     * @param x El arreglo donde se encuentra dicho número
     * @return Retorna la cantidad de veces que se repite el número
     */
    public static int ocurrencias(int n, TopArrayOrdenado x) {
        int cantidad = 0;

        for (int i = 0; i <= x.tope.get(); i++) {
            if (x.arreglo[i] == n) {
                cantidad++;
            }
        }

        return cantidad;
    }

    /**
     * Retorna un arreglo de enteros conteniendo los índices en que un número aparece n veces
     * @param n El número a evluar
     * @param x El arreglo a recorrer
     * @return Retorna el arreglo con los índices
     */
    public static int[] indices(int n, TopArrayOrdenado x) {
        int cantidadOcurrencias = ocurrencias(n, x);
        int pos = 0;
        int[] indices = new int[cantidadOcurrencias];

        if (cantidadOcurrencias == 0) {
            return new int[] {-1};
        }

        for (int i = 0; i <= x.tope.get(); i++) {
            if (n == x.arreglo[i]) {
                indices[pos] = i;
                pos++;
            }
        }

        return indices;
    }

    /**
     * Elimina la primera ocurrencia del elemento pasado como argumento
     * Si no existe en el arreglo el valor indicado esta operación no hace nada
     * @param n El número a evaluar
     * @param x El arreglo donde se evaluará n
     */
    public static void eliminarPrimera(int n, TopArrayOrdenado x) {
        for (int i = 0; i <= x.tope.get(); i++) {
            if (n == x.arreglo[i]) {
                for (int j = i; j <= x.tope.get() - 1; j++) {
                    x.arreglo[j] = x.arreglo[j + 1];
                }
                x.tope.set(x.tope.get() - 1);
                break;
            }
        }
    }

    /**
     * Elimina la última ocurrencia del elemento pasado como argumento
     * Si no existe en el arreglo el valor indicado esta operación no hace nada
     * @param n El número a evaluar
     * @param x El arreglo donde se evaluará n
     */
    public static void eliminarUltimo(int n, TopArrayOrdenado x) {
        for (int i = x.tope.get(); i >= 0; i--) {
            if (n == x.arreglo[i]) {
                for (int j = i; j <= x.tope.get() - 1; j++) {
                    x.arreglo[j] = x.arreglo[j + 1];
                }
                x.tope.set(x.tope.get() - 1);
                break;
            }
        }
    }

    /**
     * Elimina todos los elementos del arreglo y lo deja vacío
     * @param x El arreglo que vamos a vacíar
     */
    public static void vaciar(TopArrayOrdenado x) {
        x.tope.set(-1);
    }

    /**
     * Imprime los elementos de x en formato: <br>
     * X1 X2 Xn <br>
     * Si el arreglo está vacío no hace nada
     * @param x El arreglo a imprimir
     */
    public static void imprimir(TopArrayOrdenado x) {
        for (int i = 0; i <= x.tope.get(); i++) {
            System.out.print(x.arreglo[i] + " ");
        }
    }

    public static void main(String[] args) {
        Random generar = new Random();

        TopArrayOrdenado miArreglo = iniciarArreglo(MAX_CELDAS);
        int cantidad = generar.nextInt(10) + 1;

        System.out.println("Se van a agregar " + cantidad + " números al arreglo");

        do {
            add(generar.nextInt(10), miArreglo);
        } while (cantidad(miArreglo) < cantidad);

        imprimir(miArreglo);
        System.out.println("\nLa cantidad máxima de elementos que pueden ser insertados en el arreglo: " + cantidadMaxima(miArreglo));
        System.out.println("Cantidad de elementos en el arreglo: " + cantidad(miArreglo));
        System.out.println("El número " + cantidad + " ya existe en el arreglo: " + existe(cantidad, miArreglo));
        System.out.println("El número " + cantidad + " se repite: " + ocurrencias(cantidad, miArreglo) + " veces");
        System.out.println("El número " + cantidad + " aparece en estos índices " + Arrays.toString(indices(cantidad, miArreglo)));

        System.out.println("Se elimina la última ocurrencia del número " + cantidad);
        eliminarUltimo(cantidad, miArreglo);
        imprimir(miArreglo);
    }
}