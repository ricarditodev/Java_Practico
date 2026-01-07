package com.ricarditodev.mastermindV3;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    //constantes predefinidas para el proyecto
    public static final int MAX_INTENTOS = 10; //maximos intentos para el usuario
    public static final int LARGO_CODIGO = 4; //longitud del codigo para el pensador y el adivinador
    public static final char PRIMERA_LETRA = 'A'; //65 su codigo ASCII
    public static final char ULTIMA_LETRA = 'H'; //72 su codigo ASCII

    /**
     * Representa a un código y las notas que éste ha recibido.
     * @param codigo
     * @param b
     * @param r
     */
    public static record TRegistroNota (
            char[] codigo,
            AtomicInteger b,
            AtomicInteger r
    ) {}

    /**
     * Es un arreglo con tope
     * @param info
     * @param tope
     */
    public static record THistoria (
            TRegistroNota[] info,
            AtomicInteger tope
    ) {}

    public static Scanner entrada = new Scanner(System.in); //para leer la entrada del usuario

    //arreglo para guardar la combinacion de letras al azar del adivinador desde la A a la H
    public static char[] adivinador = new char[LARGO_CODIGO];
    public static char[] primerIntento = new char[LARGO_CODIGO];

    public static int indice; //generamos numeros al azar entre 65 y 72
    public static char caracterAdivinador; //guardarmos en la variable caracter el indice generado al azar
    public static String notasPensador; //variable donde guardamos las notas del usuario
    public static boolean perdedor = true;
    public static Random generador = new Random(); //generador de numeros aleatorios
    public static boolean notasResultado; //aquí guardamos el resultado de la función leerNotas()
    public static AtomicInteger R = new AtomicInteger(0);
    public static AtomicInteger B = new AtomicInteger(0);

    //verificamos si la historia está vacía
    public static boolean esVacia;
    //resultado de la función esAdecuado
    public static boolean esAdecuado;

    //variable para mostrar al usuario si ingresa datos incorrectos
    public static StringBuilder errorMessage = new StringBuilder();

    public static void main(String[] args) {
        System.out.println("MasterMind V3.0");
        System.out.println("Dispones de 10 intentos para adivinar el código.");

        //creamos el registro de la historia
        THistoria historia = crearHistoria();

        //generamos un primer código al azar
        generarCodigo();

        //aquí guardamos el primer código generado al azar
        for (int i = 0; i < LARGO_CODIGO; i++) {
            primerIntento[i] = adivinador[i];
        }

        //hacemos 10 intentos donde el usuario tiene que adivinar el código
        for (int i = 1; i <= MAX_INTENTOS; i++) {
            System.out.print("Nota " + i + " de " + MAX_INTENTOS + " --> ");

            //mostramos el código al usuario
            imprimirCodigo(adivinador);

            //leemos las notas y el resultado lo guardamos en la variable
            notasResultado = leerNotas(B, R, errorMessage);

            //si las notas no son correctas entramos a un bucle hasta que el usuario las ingrese correctamente
            //mientras, le mostramos el error y actualizamos el valor de las notas
            if (!notasResultado) {
                while (!notasResultado) {
                    System.out.print(errorMessage);
                    notasResultado = leerNotas(B, R, errorMessage);
                }
            }

            //guardamos el código con sus notas en la historia
            guardarNota(historia, adivinador, B, R);

            //un bucle que genera los siguientes códigos hasta econtrar uno que es adecuado
            do {
                //generamos el siguiente código a evaluar
                siguienteCodigo(adivinador);

                //contador para detectar trampas
                int contador = 0;

                //sistema de detección de trampas
                //si el código del adivinador es igual al primer código generado al azar, es porque ha habido trampa o algún error del usuario
                for (int j = 0; j < LARGO_CODIGO; j++) {
                    if (adivinador[j] == primerIntento[j]) {
                        contador++;
                    }

                }

                //mostramos que hubo trampa si el contador es igual al largo del código
                if (contador == LARGO_CODIGO) {
                    System.out.println("HAS HECHO TRAMPA!!!");
                    return;
                }

                //evaluamos si el código generado es adecuado para mostrar al usuario, si no, seguimos con el bucle
                esAdecuado = esAdecuado(adivinador, historia);
            } while (!esAdecuado);



            //si en algun momento b es igual a LARGO_CODIGO significa que la CPU adivinó y rompemos la ejecucion del bucle principal
            //y cambiamos a false la variable booleana para que no se ejecute el código del perdedor
            if (B.get() == LARGO_CODIGO) {
                System.out.println("EXCELENTE!!! Gane.");
                perdedor = false;
                break;
            }
        }

        //si después de los 10 intentos no logramos adivinar, el boolean seguirá siendo true y mostramos que la máquina perdió
        if (perdedor) {
            System.out.print("He perdido... :(");
        }
    }

    /**
     * Genera un código al azar y lo asigna a la variable codigo. El código generado
     * puede contener letras repetidas.
     * @return Un código de LARGO_CODIGO caracteres generados aleatoriamente.
     */
    public static char[] generarCodigo() {
        for (int i = 0; i < LARGO_CODIGO; i++) {
            //dentro de la variable indice generamos numeros al azar entre 65 y 72
            indice = generador.nextInt((int) ULTIMA_LETRA - (int) PRIMERA_LETRA + 1) + (int) PRIMERA_LETRA;

            //guardarmos en la variable caracter el indice generado al azar y lo casteamos a tipo char
            caracterAdivinador = (char) indice;

            adivinador[i] = caracterAdivinador; //guardamos en cada posicion del arreglo adivinador el caracter generado previamente
        }

        return adivinador;
    }

    /**
     * Lee dos notas a la vez: B y R y retorna TRUE si son correctas o FALSE si no lo son.
     * En caso de que las notas no sean correctas B y R quedan con el valor 0.
     * El fin de linea es consumido.
     * Para verificar que las notas sean correctas se contempla lo siguiente:
        1. Son valores enteros
        2. Están entre 0 y LARGO_CODIGO
        3. La suma de B + R no puede ser mayor que LARGO_CODIGO
        4. Si B = (LARGO_CODIGO - 1) y R >= 1 las notas están mal.
     * Asigna a la variable errorMessage uno de estos dos mensajes según el caso:
        1 y 2: 'ERROR: Ingresa solo dos números enteros entre 0 y [LARGO_CODIGO] separados por un
            espacio en blanco.'
        3 y 4: 'ERROR: Las notas no son correctas, por favor verifica los valores.'
     * @param b El valor para los buenos
     * @param r El valor para los regulares
     * @param errorMessage Variable que contiene el mensaje de error para mostrar por pantalla
     * @return Retorna true si las notas están correctas, False en caso contrario
     */
    public static boolean leerNotas(AtomicInteger b, AtomicInteger r, StringBuilder errorMessage) {
        errorMessage.setLength(0); //vacíamos el mensaje a mostrar en pantalla en caso de error

        String notas;
        String[] arrayNotas;
        int buenos;
        int regulares;
        int sumaNotas;

        System.out.print(" >> ");
        notasPensador = entrada.nextLine(); //leemos la entrada del usuario

        notas = notasPensador.trim(); //quitamos espacios en blanco a las notas

        arrayNotas = notas.split("\\s+"); //quitamos espacios en blanco a las notas

        //si el usuario ingresa más de un número y menos de un número.
        if (arrayNotas.length != 2) {
            errorMessage.append("ERROR: Ingresa solo dos numeros enteros entre 0 y " + LARGO_CODIGO  + " separados por un espacio en blanco.");
            b.set(0);
            r.set(0);
            return false;
        }

        //si el string ingresado no son números retornamos false
        for (int i = 0; i < notas.length(); i++) {
            int contadorEspacios = 0;

            //recorremos la variable notas para ver cuántos espacios en blanco tiene
            for (int j = 0; j < notas.length(); j++) {
                if (notas.charAt(j) == ' ') {
                    contadorEspacios++;
                }
            }

            //si hay más de un espacio en blanco es porque el usuario ha ingresado de una manera incorrecta los valores
            if (contadorEspacios > 1) {
                errorMessage.append("ERROR: Ingresa solo dos numeros enteros entre 0 y " + LARGO_CODIGO  + " separados por un espacio en blanco.");
                b.set(0);
                r.set(0);
                return false;
            }

            //omitimos el primer espacio en blanco
            if (notas.charAt(i) == 32) {
                continue;
            }

            //si los valores ingresados son distintos de 0 al 9 retornamos false
            if (notas.charAt(i) < 48 || notas.charAt(i) > 57){
                errorMessage.append("ERROR: Ingresa solo dos numeros enteros entre 0 y " + LARGO_CODIGO  + " separados por un espacio en blanco.");
                b.set(0);
                r.set(0);
                return false;
            }

        }

        buenos = Integer.parseInt(arrayNotas[0]);
        regulares = Integer.parseInt(arrayNotas[1]);

        b.set(buenos);
        r.set(regulares);

        sumaNotas = b.get() + r.get();

        //si el string ingresado no es un número entre 0 y LARGO_CODIGO retornamos false
        if (
                b.get() < 0 ||
                b.get() > LARGO_CODIGO ||
                r.get() < 0 ||
                r.get() > LARGO_CODIGO
        ) {
            errorMessage.append("ERROR: Ingresa solo dos numeros enteros entre 0 y " + LARGO_CODIGO  + " separados por un espacio en blanco.");
            b.set(0);
            r.set(0);
            return false;
        }

        //si el usuario ingresa un conjunto de notas mayor que la longitud de LARGO_CODIGO
        if (sumaNotas > LARGO_CODIGO) {
            errorMessage.append("ERROR: Las notas no son correctas, por favor verifica los valores.");
            b.set(0);
            r.set(0);
            return false;
        }

        if (b.get() == LARGO_CODIGO - 1 && r.get() >= 1) {
            errorMessage.append("ERROR: Las notas no son correctas, por favor verifica los valores.");
            b.set(0);
            r.set(0);
            return false;
        }

        return true;
    }

    /*Imprime el codigo en la salida. Deja el cursor justo al final.*/
    public static void imprimirCodigo(char[] codigo) {
        System.out.print(codigo);
    }

    /**
     * Genera el código siguiente al actual en forma circular y lo retorna. Por ejemplo:
     * <ul><li>AAAA --> AAAB</li>
     * <li>ABCH --> ABDA (En este caso H es la letra más grande admitida)</li>
     * <li>HHHH --> AAAA</li></ul>
     * @param codigo El código a partir del cual se generará el siguiente.
     * @return El código siguiente a partir de <b>codigo</b>
     */
    public static char[] siguienteCodigo(char[] codigo) {
        char letraUltima = ULTIMA_LETRA;
        char letraPrimera = PRIMERA_LETRA;

        //creamos una variable donde irá la copia del código que recibimos por parámetros
        char[] copiaCodigo = new char[LARGO_CODIGO];

        //copiamos en el arraycopia lo que recibimos por parámetros
        for (int i = 0; i < copiaCodigo.length; i++) {
            copiaCodigo[i] = codigo[i];
        }

        //recorremos el array que recibimos por parámetro desde el final
        //evaluamos si en el final del arraycopia es igual a letraUltima, de serlo, cambiamos esa última posición por primeraLetra
        //si no, aumentamos en una letra
        for (int i = codigo.length - 1; i >= 0; i--) {
            if (copiaCodigo[i] == letraUltima) {
                copiaCodigo[i] = letraPrimera;
            } else {
                copiaCodigo[i]++;
                break;
            }
        }

        //lo que resulte en arraycopia lo transferimos al array adivinador
        for (int i = 0; i < copiaCodigo.length; i++) {
            adivinador[i] = copiaCodigo[i];
        }


        return adivinador;
    }

    /**
     * Crea una historia vacía y la retorna como valor.
     * @return El objeto THistoria inicializado para hacer uso de ella en el juego.
     */
    public static THistoria crearHistoria() {
        return new THistoria(new TRegistroNota[MAX_INTENTOS], new AtomicInteger(-1));
    }

    /**
     * Retorna TRUE si la historia está vacía, FALSE en caso contrario.
     * @param h La historia de la cual se desea saber si es vacía o no.
     * @return TRUE si la historia está vacía, FALSE en caso contrario.
     */
    public static boolean esHistoriaVacia(THistoria h) {
        return h.tope.get() == -1;
    }

    /**
     * Guarda en la historia un nuevo código con sus respectivas notas asociadas.
     * Los parámetros <b>buenos</b> y <b>regulares</b> no serán agregados a <b>h</b>,
     * sino que se guardarán copias de ellos.
     * @param h La historia en la cual se guardarán las notas.
     * @param codigo El código que se guardará en la historia.
     * @param buenos El valor de buenos a guardar.
     * @param regulares El valor de regulares a guardar.
     */
    public static void guardarNota(THistoria h, char[] codigo, AtomicInteger buenos, AtomicInteger regulares) {
        char[] copiaCodigo = new char[LARGO_CODIGO];

        //hacemos una copia del array que recibimos por parámetros en copiaCodigo
        for (int i = 0; i < LARGO_CODIGO; i++) {
            copiaCodigo[i] = codigo[i];
        }

        //creamos una instancia de nota
        TRegistroNota nota = new TRegistroNota(copiaCodigo, new AtomicInteger(buenos.get()), new AtomicInteger(regulares.get()));

        int indice = h.tope.get() + 1; //creamos una variable indice que será el tope de la historia + 1

        h.info()[indice] = nota; //accedemos a la historia para guardar la nota en su respectivo indice

        h.tope.set(h.tope.get() + 1); //seteamos el tope con una nueva posición


    }

    /**
     * Calcula las notas de <b>codAdivinador</b> en función de <b>codPensador</b>. Asigna los
     * buenos y los regulares a los argumentos con el mismo nombre.
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

    /**
     * Retorna TRUE si el código pasado como argumento es apropiado para postular al
     * pensador o FALSE si no lo es. Para ello se compara el código con todos los códigos
     * guardados en la historia evaluando sus notas. Si estas notas coinciden entonces
     * el código es adecuado, si un caso falla entonces ya no lo será.
     * @param c El código a ser evaluado como posibilidad de adivinación.
     * @param h La historia que se tomará en cuenta para evaluar el código.
     * @return TRUE si el código es adecuado, FALSE si no lo es.
     */
    public static boolean esAdecuado(char[] c, THistoria h) {
        char[] intentoPrevio;

        //evaluamos si la historia está vacía
        if (esVacia) {
            return false;
        }

        //recorremos hasta el tope para evaluar todas las notas
        for (int i = 0; i <= h.tope.get(); i++) {
            AtomicInteger REGULARES = new AtomicInteger(0);
            AtomicInteger BUENOS = new AtomicInteger(0);
            AtomicInteger notasB = new AtomicInteger(0);
            AtomicInteger notasR = new  AtomicInteger(0);

            intentoPrevio = h.info()[i].codigo();

            REGULARES.set(h.info()[i].r().get());
            BUENOS.set(h.info()[i].b().get());

            calcularNota(intentoPrevio, c, notasB, notasR);

            if (notasB.get() != BUENOS.get() || notasR.get() != REGULARES.get()) {
                return false;
            }
        }

        return true;
    }
}