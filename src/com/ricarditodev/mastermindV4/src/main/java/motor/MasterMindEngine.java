package motor;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author ricarditodev
 */
public class MasterMindEngine {
    public static final int MAX_INTENTOS = 10;
    public static final int LARGO_CODIGO = 4;
    public static final char PRIMERA_LETRA = 'A'; //65 su codigo ASCII
    public static final char ULTIMA_LETRA = 'H'; //72 su codigo ASCII
    
    public static enum ModoJuego {ADIVINADOR, PENSADOR}
    public static enum EstadoJuego {INICIADO, GANO, PERDIO, TRAMPA}

    private Historia historial;
    private int intentoActual;
    private char[] codigoAuxiliar;
    private char[] primerCodigo;
    private int contador = 0; //contador para detectar trampas
    private ModoJuego modo;
    private EstadoJuego estado;

    private RegistroNota registro;
    private static final Random generador = new Random();
    
    /**
     * Crea un juego (partida) con el modo indicado. Si es PENSADOR se debe
     * inciar la Historia vacía y además generar un primer código de Adivinador
     * para presentar al usuario. Es conveniente almacenar dicho código por separado
     * para futuros usos, como puede ser la operación {@link siguienteCodigo} o
     * {@link siguienteAdecuado}. Si el modo es ADIVINADOR se generará un código
     * al azar que será el que el jugador deberá adivinar. En cualquier caso el
     * estado de juego será INICIADO y el intento actual se establecerá en 0.
     * @param modo El modo de partida. Puede ser PENSADOR o ADIVINADOR.
     */
    public MasterMindEngine(ModoJuego modo) {
        this.modo = modo;

        if (modo == ModoJuego.PENSADOR) {
            this.historial = new Historia();
            this.codigoAuxiliar = generarCodigo();
        } else if (modo == ModoJuego.ADIVINADOR) {
            this.historial = new Historia();
            this.codigoAuxiliar = generarCodigo();
        }

        //aquí guardamos el primer código generado al azar
        this.primerCodigo = this.codigoAuxiliar;

        this.estado = EstadoJuego.INICIADO;
        this.intentoActual = 0;
    }
    
    /**
     * Genera un código al azar y lo retorna. Puede contener letras repetidas.
     * @return Un código de LARGO_CODIGO caracteres generados aleatoriamente.
     */
    public static char[] generarCodigo() {
        char[] codigo = new char[LARGO_CODIGO];

        for (int i = 0; i < LARGO_CODIGO; i++) {
            //dentro de la variable indice generamos numeros al azar entre 65 y 72
            int indice = generador.nextInt((int) ULTIMA_LETRA - (int) PRIMERA_LETRA + 1) + (int) PRIMERA_LETRA;

            //guardarmos en la variable caracter el indice generado al azar y lo casteamos a tipo char
            char caracter = (char) indice;

            codigo[i] = caracter; //guardamos en cada posicion del arreglo codigo el caracter generado previamente
        }

        return codigo;
    }
    
    /** Evalúa el código pasado como argumento.
    * Retorna el valor TRUE si el codigo leído es correcto, FALSE sino.
    * El codigo puede ser incorrecto si:<br>
    * <ul><li>Contiene uno o mas caracteres fuera del rango [PRIMERA_LETRA,ULTIMA_LETRA].</li>
    *  <li>No contiene el largo LARGO_CODIGO</li>
    * </ul> <br><br>
    * Si el largo del código no es LARGO_CODIGO se asignará a errorMessage el mensaje:<br>
    * <b>ERROR: El largo del código debe ser de LARGO_CODIGO caracteres.</b><br>
    * Si uno o más de los caracteres del código no están entre PRIMERA_LETRA y ULTIMA_LETRA:<br>
    * <b>ERROR: El código debe contener caracteres solo entre PRIMERA_LETRA y ULTIMA_LETRA.</b>
    * @param codigo Contendrá el código que debe ser evaluado.
    * @param errorMessage Contendrá el mensaje de error si lo hay, acorde a lo ocurrido.
    * @return TRUE si el código cumple con las condiciones, FALSE en caso contrario.
    */
    public static boolean evaluarCodigo(char[] codigo, StringBuilder errorMessage) {
        //si el codigo es distinto de 4 en su cantidad de caracteres retornamos false
        if (codigo.length != LARGO_CODIGO) {
            errorMessage.append("ERROR: El largo del código debe ser de " + LARGO_CODIGO + " caracteres.");
            return false;
        }

        //recorremos el arreglo para verificar si hay una letra fuera de rango
        for (char x : codigo) {
            if ((int) x < PRIMERA_LETRA || (int) x > ULTIMA_LETRA) {
                errorMessage.append("ERROR: El código debe contener caracteres solo entre " + PRIMERA_LETRA + " y " + ULTIMA_LETRA + ".");
                return false;
            }
        }

        return true;
    }
    
    /**
     * Recibe dos notas a la vez: B y R y retorna TRUE si son correctas o FALSE si no lo 
     * son.
     * Para verificar que las notas sean correctas se contempla lo siguiente:
     *
     *  1: Son valores enteros (esto siempre se cumple)
     *  2: Están entre 0 y LARGO_CODIGO
     *  3: La suma de B+R no puede ser mayor que LARGO_CODIGO
     *  4: Si B=(LARGO_CODIGO-1) y R>=1 las notas están mal.
     *
     * Asigna a la variable errorMessage el siguiente mensaje en caso de que las
     * notas no cumplan las condiciones:
     * <br><br>”ERROR: Las notas deben ser valores enteros positivos correctos para el 
     * largo de código LARGO_CODIGO.”
     * @param b La nota 'buenos' a ser evaluada.
     * @param r La nota 'regulares' a ser evaluada.
     * @param errorMessage Contendrá el mensaje de error en caso de que se retorne FALSE.
     * @return TRUE si las notas son correctas, FALSE si no lo son.
     */

    public static boolean evaluarNotas(int b, int r, StringBuilder errorMessage) {
        errorMessage.setLength(0); //vacíamos el mensaje a mostrar en pantalla en caso de error

        //si los valores ingresados son distintos de 0 al 9 retornamos false
        if ((b < 0 || b > 9) || (r < 0 || r > 9)) {
            errorMessage.append("ERROR: Las notas deben ser valores enteros positivos correctos para el largo de código " + LARGO_CODIGO + ".");
            return false;
        }

        //si los valores ingresados no es un número entre 0 y LARGO_CODIGO retornamos false
        if ((b < 0 || b > LARGO_CODIGO) || (r < 0 || r > LARGO_CODIGO)) {
            errorMessage.append("ERROR: Las notas deben ser valores enteros positivos correctos para el largo de código " + LARGO_CODIGO + ".");
            return false;
        }

        //si el usuario ingresa un conjunto de notas mayor que la longitud de LARGO_CODIGO
        if (b + r > LARGO_CODIGO) {
            errorMessage.append("ERROR: Las notas deben ser valores enteros positivos correctos para el largo de código " + LARGO_CODIGO + ".");
            return false;
        }

        if (b == LARGO_CODIGO - 1 && r >= 1) {
            errorMessage.append("ERROR: Las notas deben ser valores enteros positivos correctos para el largo de código " + LARGO_CODIGO + ".");
            return false;
        }

        return true;
    }
    
    /**
     * Genera el codigo siguiente al actual en forma circular y lo retorna. Por ejemplo:
     * <ul><li>AAAA --> AAAB</li>
     * <li>ABCH --> ABDA (En este caso H es la letra más grande admitida)</li>
     * <li>HHHH --> AAAA</li></ul>
     * @param codigo El código a partir del cual se generará el siguiente.
     * @return El código siguiente a partir de <b>codigo</b>
     */
    public static char[] siguienteCodigo(char[] codigo) {
        char[] siguienteCodigo = new char[LARGO_CODIGO];

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
            siguienteCodigo[i] = copiaCodigo[i];
        }

        return siguienteCodigo;
    }
    
    /**
     * Calcula las notas de <b>codAdivinador</b> en función de <b>codPensador</b>. Asigna los buenos
     * y los regulares a los argumentos con el mismo nombre
     * @param codAdivinador El código del codigo. Se asume que es un código correcto.
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
     * @param c El código a ser evaluado como posibilidad de adivinación
     * @return TRUE si el código es adecuado, FALSE si no lo es.
     */
    public boolean esAdecuado(char[] c) {
        char[] intentoPrevio;

        //recorremos hasta el tope para evaluar todas las notas
        for (int i = 0; i < this.historial.cantidad(); i++) {
            AtomicInteger REGULARES = new AtomicInteger(0);
            AtomicInteger BUENOS = new AtomicInteger(0);
            AtomicInteger notasB = new AtomicInteger(0);
            AtomicInteger notasR = new  AtomicInteger(0);

            intentoPrevio = this.historial.obtenerRegistro(i).obtenerCodigo();

            REGULARES.set(this.historial.obtenerRegistro(i).obtenerRegulares());
            BUENOS.set(this.historial.obtenerRegistro(i).obtenerBuenos());

            calcularNota(intentoPrevio, c, notasB, notasR);

            if (notasB.get() != BUENOS.get() || notasR.get() != REGULARES.get()) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * Para el modo Pensador, esta operación retorna el siguiente código que es
     * factible de ser adecuado como código del Adivinador.
     * @return El código adecuado siguiendo los datos de la historia.
     */
    public char[] siguienteAdecuado() {
        this.contador = 0; //reseteamos el contador a 0
        char[] codigo = siguienteCodigo(this.codigoAuxiliar);

        while (!esAdecuado(codigo)) {
            codigo = siguienteCodigo(codigo);

            this.contador = 0;
            for (int i = 0; i < LARGO_CODIGO; i++) {
                if (codigo[i] == this.primerCodigo[i]) {
                    this.contador++;
                }
            }

            if (this.contador == LARGO_CODIGO) {
                break;
            }
        }

        this.codigoAuxiliar = codigo;

        return codigo;
    }
    
    /**
     * Para el modo de juego ADIVINADOR (el usuario intenta adivinar), se presenta
     * el intento del jugador con el código dado como argumento. Se aumenta el número
     * de intento y se retorna un registro que contiene, para el código dado, los
     * buenos y regulares obtenidos. Si se acertó el código, el estado del juego
     * pasará a ser GANO; si se agotó el número de intentos el estado pasará a ser PERDIO.
     * <br><br><b>PRECONDICIÓN:</b> Se asume que el código es correcto según lo dispuesto
     * por la operación {@link evaluarCodigo}.
     * @param adivinador El código presentado al juego para intentar adivinar.
     * @return Un registro con el código dado como argumento y las notas recibidas para él.
     */
    public RegistroNota presentarCodigo(char[] adivinador) {
        AtomicInteger b = new AtomicInteger(0);
        AtomicInteger r = new AtomicInteger(0);

        calcularNota(adivinador, codigoAuxiliar, b, r);

        int buenos = b.get();
        int regulares = r.get();

        RegistroNota registro = new RegistroNota(adivinador, buenos, regulares);
        this.registro = registro;

        this.historial.registrar(adivinador, buenos, regulares);

        intentoActual++;

        if (buenos == LARGO_CODIGO) {
            this.estado = EstadoJuego.GANO;
        } else if (intentoActual == MAX_INTENTOS) {
            this.estado = EstadoJuego.PERDIO;
        }

        return registro;
    }
    
    /**
     * Presenta las notas al código dado por el adivinador, el cual se espera
     * sea el propio MasterMind. Se guarda el código y las notas asociadas a él
     * en el historial como un elemento {@link RegistroNota}. Se asume que las
     * notas b y r dadas como argumentos son correctas acorde a {@link evaluarNotas}.
     * Si resulta que b==LARGO_CODIGO y r==0 el estado del juego pasará a ser PERDIO
     * ya que el usuario ha perdido contra el sistema porque éste adivinó el código,
     * en caso contrario, si los intentos se agotaron pasará a ser GANO. Si se detecta
     * <b>trampa</b> acorde a lo descrito en el documento de este proyecto, el estado
     * cambiará a TRAMPA. En cualquier otro caso permanecerá en INICIADO.
     * @param b Los buenos asignados al código adivinador.
     * @param r Los regulares asignados al código adivinador.
     * @param adivinador El código adivinador.
     * @return Un objeto con los valores dados como argumentos a esta operación.
     */
    public RegistroNota presentarNotas(int b, int r, char[] adivinador) {
        RegistroNota registro = new RegistroNota(adivinador, b, r);
        this.registro = registro;

        this.historial.registrar(adivinador, b, r);

        this.intentoActual++;

        if (b == LARGO_CODIGO && r == 0) {
            this.estado = EstadoJuego.PERDIO;
        } else if (intentoActual == MAX_INTENTOS) {
            this.estado = EstadoJuego.GANO;
        }

        //mostramos que hubo trampa si el contador es igual al largo del código
        if (this.contador == LARGO_CODIGO) {
            this.estado = EstadoJuego.TRAMPA;
        }

        return registro;
    }
    
    /**
     * Retorna el estado actual del juego.
     * @return El estado actual del juego.
     */
    public EstadoJuego obtenerEstado() {
        return this.estado;
    }
    
    /**
     * Retorna el modo actual de juego
     * @return El modo de partida actual.
     */
    public ModoJuego obtenerModo() {
        return this.modo;
    }
    
    /**
     * Retorna el código del pensador como un String. Si,
     * por ejemplo, el código es ABCD se retorna "ABCD".
     * @return Un string con los caracteres del código consecutivos.
     */
    public String obtenerCodigoPensador() {
        String codigo = "";

        for (char c : this.primerCodigo) {
            codigo += c;
        }

        return codigo;
    }
    
    /**
     * Retorna el objeto Historia.
     * @return El historial del juego.
     */
    public Historia obtenerHistoria() {
        return this.historial;
    }
    
    /**
     * Retorna el número de intento actual.
     * @return El número de intento actual.
     */
    public int obtenerIntento() {
        return this.intentoActual;
    }
    
    /**
     * Retorna el código del adivinador como un String. Si,
     * por ejemplo, el código es ABCD se retorna "ABCD".
     * @return Un string con los caracteres del código consecutivos.
     */
    public String obtenerCodigoAdivinador() {
        String codigo = "";

        for (char c : this.codigoAuxiliar) {
            codigo += c;
        }

        return codigo;
    }
}
