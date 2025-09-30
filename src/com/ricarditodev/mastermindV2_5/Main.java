package com.ricarditodev.mastermindV2_5;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    //constantes predefinidas para el proyecto
    public static final byte MAX_INTENTOS = 10; //maximos intentos para el usuario
    public static final byte LARGO_CODIGO = 4; //longitud del codigo para el pensador y el adivinador
    public static final char PRIMERA_LETRA = 'A'; //65 su codigo ASCII
    public static final char ULTIMA_LETRA = 'H'; //72 su codigo ASCII

    public static Scanner entrada = new Scanner(System.in); //para leer la entrada del usuario

    //arreglo para guardar la combinacion de letras al azar del pensador desde la A a la H
    public static char[] pensador = new char[LARGO_CODIGO];
    public static char[] adivinador = new char[LARGO_CODIGO]; //arreglo para guardamos la combinacion escogida por el usuario
    public static int indice; //generamos numeros al azar entre 65 y 72
    public static char caracterPensador; //guardarmos en la variable caracter el indice generado al azar
    public static String codigoAdivinador; //variable donde guardamos la entrada del usuario
    public static boolean perdedor = true;
    public static Random generador = new Random(); //generador de numeros aleatorios
    public static AtomicInteger R = new AtomicInteger(0);
    public static AtomicInteger B = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("MasterMind V2.5");
        System.out.println("Dispones de 10 intentos para adivinar el código.");

        //generamos el codigo al azar
        generarCodigo();

        //hacemos 10 intentos donde el usuario tiene que adivinar el codigo
        for (int i = 1; i <= MAX_INTENTOS; i++) {
            System.out.print("Código " + i + " de " + MAX_INTENTOS + ">> ");

            //lo que devuelva la funcion lo guardamos en una variable que luego usaremos para ver si el codigo es valido
            boolean resultado = leerCodigo(adivinador);

            //evaluamos si el codigo del usuario es valido
            if (!resultado) {
                while (!resultado) {
                    System.out.print("ERROR: El código no es válido. Ingresa otro con " + LARGO_CODIGO + " letras entre " + PRIMERA_LETRA + " y " + ULTIMA_LETRA + ">> ");
                    resultado = leerCodigo(adivinador);
                }
            }

            //calculamos la nota
            calcularNota(adivinador, pensador, B, R);

            System.out.println("B=" + B + " R=" + R);

            //si en algun momento b es igual a 4 significa que hemos adivinado y rompemos la ejecucion del bucle principal
            //y cambiamos a false la variable booleana para que no se ejecute el codigo del perdedor
            if (B.get() == 4) {
                System.out.println("EXCELENTE!!! Ganaste.");
                perdedor = false;
                break;
            }
        }

        //si despues de los 10 intentos no logramos adivinar, el boolean seguira siendo true y mostramos que perdiste
        if (perdedor) {
            System.out.print("PERDISTE!!! El código era ");
            imprimirCodigo(pensador);
        }
    }

    /**
     * Genera un código al azar y lo asigna a la variable codigo. El codigo generado
     * puede contener letras repetidas.
     * @return Un código de LARGO_CODIGO caracteres generados aleatoriamente.
     */
    public static char[] generarCodigo() {
        for (int i = 0; i < LARGO_CODIGO; i++) {
            //dentro de la variable indice generamos numeros al azar entre 65 y 72
            indice = generador.nextInt((int) ULTIMA_LETRA - (int) PRIMERA_LETRA + 1) + (int) PRIMERA_LETRA;

            //guardarmos en la variable caracter el indice generado al azar y lo casteamos a tipo char
            caracterPensador = (char) indice;
            pensador[i] = caracterPensador; //guardamos en cada posicion del arreglo pensador el caracter generado previamente
        }

        return pensador;
    }

    /** Lee el codigo de la entrada estandar y lo asigna a la variable codigo. Ademas
     * retorna el valor TRUE si el codigo leido es correcto, FALSE si no.
     * El codigo leido puede ser incorrecto si:<br>
     * <ul><li>Contiene uno o más caracteres fuera del rango [PRIMERA_LETRA,ULTIMA_LETRA].</li>
     *  <li>No contiene el largo LARGO_CODIGO</li>
     * </ul>
     * @param codigo Contendrá el código leído desde la entrada. Si hay un error
     * no se garantiza la consistencia del mismo. Verificar, para ello, el retorno
     * de esta operación.
     * @return true si el código cumple con las condiciones, false en caso contrario.
     */
    public static boolean leerCodigo(char[] codigo) {
        codigoAdivinador = entrada.nextLine(); //leemos la entrada del usuario

        //si el codigo es distinto de 4 en su cantidad de caracteres retornamos false
        if (codigoAdivinador.length() != LARGO_CODIGO) {
            return false;
        }

        //en cada iteracion guardamos en el arreglo cada letra del codigo del adivinador
        for (int j = 0; j < codigoAdivinador.length(); j++) {
            codigo[j] = codigoAdivinador.charAt(j);
        }

        //recorremos el arreglo para verificar si hay una letra fuera de rango
        for (char x : codigo) {
            if ((int) x < PRIMERA_LETRA || (int) x > ULTIMA_LETRA) {
                return false;
            }
        }
        return true;
    }

    /**
     * Imprime en la salida estándar el código pasado como argumento. Deja el
     * cursor al final de la impresión sin generar una nueva línea.
     * @param codigo El código que se desea imprimir.
     */
    public static void imprimirCodigo(char[] codigo) {
        System.out.print(codigo);
    }

    /**
     * Calcula las notas de <b>codAdivinador</b> en función de <b>codPensador</b>. Asigna los
     buenos
     * y los regulares a los argumentos con el mismo nombre
     * @param codAdivinador El código del adivinador. Se asume que es un código correcto.
     * @param codPensador El código del pensador. Se asume que es un código correcto.
     * @param buenos El cálculo de buenos será asignado a este parámetro.
     * @param regulares El cálculo de regulares será asignado a este parámetro.
     */
    public static void calcularNota(char[] codAdivinador, char[] codPensador, AtomicInteger buenos, AtomicInteger regulares) {
        boolean[] evaluadasP = new boolean[LARGO_CODIGO];
        boolean[] evaluadasA = new boolean[LARGO_CODIGO];

        buenos.set(0);
        regulares.set(0);

        //comparamos los elementos de ambos arreglos en cada posicion
        //si coinciden aumentamos b en 1 y marcamos esa posicion en true para que no vuelva a ser analizada
        for (int x = 0; x < codPensador.length; x++) {
            if (codPensador[x] == codAdivinador[x]) {
                buenos.getAndIncrement();
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
        for (int x = 0; x < codPensador.length; x++) {
            if (evaluadasP[x] == false) {
                for (int z = 0; z < codAdivinador.length; z++) {
                    if (evaluadasA[z] == false && x != z && codPensador[x] == codAdivinador[z]) {
                        regulares.getAndIncrement();
                        evaluadasP[x] = true;
                        evaluadasA[z] = true;
                        break;
                    }
                }
            }
        }
    }
}