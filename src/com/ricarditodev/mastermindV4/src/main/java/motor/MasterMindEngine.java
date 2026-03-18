
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
    public static final char PRIMERA_LETRA = 'A';
    public static final char ULTIMA_LETRA = 'H';
    
    public static enum ModoJuego {ADIVINADOR, PENSADOR}
    public static enum EstadoJuego {INICIADO, GANO, PERDIO, TRAMPA}

    private Historia historial;
    private int intentoActual;
    private char[] adivinador, pensador;
    private ModoJuego modo;
    private EstadoJuego estado;
    
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
    public MasterMindEngine(ModoJuego modo){

    }
    
    /**
     * Genera un código al azar y lo retorna. Puede contener letras repetidas.
     * @return Un código de LARGO_CODIGO caracteres generados aleatoriamente.
     */
    public static char[] generarCodigo(){
 
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
    public static boolean evaluarCodigo(char[] codigo, StringBuilder errorMessage){

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

    public static boolean evaluarNotas(int b, int r, StringBuilder errorMessage){
        
    }
    
    /**
     * Genera el codigo siguiente al actual en forma circular y lo retorna. Por ejemplo:
     * <ul><li>AAAA --> AAAB</li>
     * <li>ABCH --> ABDA (En este caso H es la letra más grande admitida)</li>
     * <li>HHHH --> AAAA</li></ul>
     * @param codigo El código a partir del cual se generará el siguiente.
     * @return El código siguiente a partir de <b>codigo</b>
     */
    public static char[] siguienteCodigo(char[] codigo){
        
    }
    
    /**
     * Calcula las notas de <b>codAdivinador</b> en función de <b>codPensador</b>. Asigna los buenos
     * y los regulares a los argumentos con el mismo nombre
     * @param codAdivinador El código del adivinador. Se asume que es un código correcto.
     * @param codPensador El código del pensador. Se asume que es un código correcto.
     * @param buenos El cálculo de buenos será asignado a este parámetro.
     * @param regulares El cálculo de regulares será asignado a este parámetro.
     */
    public static void calcularNota(char[] codAdivinador, char[] codPensador, AtomicInteger buenos, AtomicInteger regulares){
        
    }
    
    /**
     * Retorna TRUE si el código pasado como argumento es apropiado para postular al
     * pensador o FALSE si no lo es. Para ello se compara el código con todos los códigos
     * guardados en la historia evaluando sus notas. Si estas notas coinciden entonces
     * el código es adecuado, si un caso falla entonces ya no lo será.
     * @param c El código a ser evaluado como posibilidad de adivinación
     * @return TRUE si el código es adecuado, FALSE si no lo es.
     */
    public boolean esAdecuado(char[] c){
        
    }
    
    /**
     * Para el modo Pensador, esta operación retorna el siguiente código que es
     * factible de ser adecuado como código del Adivinador.
     * @return El código adecuado siguiendo los datos de la historia.
     */
    public char[] siguienteAdecuado(){
        
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
    public RegistroNota presentarCodigo(char[] adivinador){
        
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
    public RegistroNota presentarNotas(int b, int r, char[] adivinador){
        
    }
    
    /**
     * Retorna el estado actual del juego.
     * @return El estado actual del juego.
     */
    public EstadoJuego obtenerEstado(){
        
    }
    
    /**
     * Retorna el modo actual de juego
     * @return El modo de partida actual.
     */
    public ModoJuego obtenerModo(){
        
    }
    
    /**
     * Retorna el código del pensador como un String. Si,
     * por ejemplo, el código es ABCD se retorna "ABCD".
     * @return Un string con los caracteres del código consecutivos.
     */
    public String obtenerCodigoPensador(){
        
    }
    
    /**
     * Retorna el objeto Historia.
     * @return El historial del juego.
     */
    public Historia obtenerHistoria(){
        
    }
    
    /**
     * Retorna el número de intento actual.
     * @return El número de intento actual.
     */
    public int obtenerIntento(){
        
    }
    
    /**
     * Retorna el código del adivinador como un String. Si,
     * por ejemplo, el código es ABCD se retorna "ABCD".
     * @return Un string con los caracteres del código consecutivos.
     */
    public String obtenerCodigoAdivinador(){
        
    }
}
