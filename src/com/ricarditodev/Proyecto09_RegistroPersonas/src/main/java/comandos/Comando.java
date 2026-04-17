package comandos;

/**
 * Esta clase permite trabajar con comandos a partir de una cadena String, lo cual
 * facilita el ingreso de datos por parte del usuario, sea desde la entrada
 * estándar o desde cualquier otro medio de ingreso de cadenas String, evitando
 * tener que estar leyendo línea por línea lo que este ingresa. Esta forma de
 * ingresar datos es ampliamente utilizada hoy día por la consola de Windows, y
 * más aún, en los sistemas Linux y otros basados en Unix, donde el uso de la
 * terminal o consola es de lo más habitual. También se utiliza mucho en MySQL y
 * otros motores de datos. Ya el mundo de los servidores se basa en la línea de comandos.
 * <br><br>
 * Un COMANDO en este modelo no es otra cosa que una línea de texto en la entrada
 * estándar con el siguiente formato:
 * <br><br>
 * <b>NOMBRE_COMANDO</b> PARAMETRO1 PARAMETRO2 ... PARAMETROn
 * <br><br>
 * Por tanto, un comando básicamente separa las palabras de la entrada estándar. La
 * primera palabra es el nombre del comando, y el resto sus parámetros.
 * <br><br>
 * Por ejemplo, en Windows, el comando para copiar directorios en la consola se
 * llama XCOPY, y su formato es:
 * <br><br>
 * <b>XCOPY</b> DIRECTORIO_ORIGINAL DIRECTORIO_DESTINO
 * <br><br>
 * Recibe por tanto dos parámetros, el directorio de origen (desde donde vamos a
 * copiar, y el directorio de destino). También puede recibir más parámetros aún,
 * que indicarán permisos y otros detalles. En Linux, el comando de copiado en
 * vez de llamarse XCOPY se llama CP, pero usa el mismo formato.
 * <br><br>
 * <b>cp</b> DIRECTORIO_ORIGINAL DIRECTORIO_DESTINO
 * <br><br>
 * Esta unidad, dado todo el texto de la entrada estándar, permitirá separar las
 * palabras ingresadas para distinguir el nombre del comando, y luego uno a uno
 * sus parámetros (si los hubiera).
 * <br><br>
 * Admite además trabajar con parámetros compuestos. Por ejemplo, tanto en Windows
 * como en Linux, el comando para cambiar de directorio en la terminal se llama cd
 * (change directory), el cual recibe un solo parámetro: la dirección del directorio
 * al que queremos ir. Por ejemplo:
 * <br><br>
 * <b>cd</b> C:\Juegos\Buscaminas
 * <br><br>
 * Ahora bien ¿Qué pasa cuando una carpeta contiene espacios en su nombre? Por
 * ejemplo, si en vez de "Juegos" se llama "Mis juegos":
 * <br><br>
 * <b>cd</b> C:\Mis juegos\Buscaminas
 * <br><br>
 * El comando anterior no funcionará, porque el espacio en blanco funciona como
 * separador, y por tanto habría dos parámetros: "C:\Mis" y "juegos\Buscaminas".
 * Para solucionar esto, tanto en Windows como en Linux, ha de usarse la comilla
 * simple en el texto para indicar que TODO es un solo parámetro:
 * <br><br>
 * <b>cd</b> 'C:\Mis juegos\Buscaminas'
 * <br><br>
 * Esta unidad admite parámetros compuestos, encerrándolos entre comillas dobles ("),
 * por tanto, un comando así sería permitido:
 * <br><br>
 * <b>INGRESAR</b> "Maria del Rosario" "De Leon"
 * <br><br>
 * El comando se llama INGRESAR, y recibe dos parámetros "Maria del Rosario" y
 * "De Leon". Ambos parámetros son compuestos porque tienen espacios dentro, pero
 * se leen como uno solo.
 * <br><br>
 * Si solamente utilizáramos los espacios en blanco como separadores, tendríamos
 * al menos 5 parámetros en nuestro comando, y eso estaría mal.
 * <br><br>
 * Esta unidad, entonces, solamente distingue la primera palabra como el nombre de
 * un comando y luego le asigna una lista de parámetros (que también son palabras)
 * y nada más. ¿Qué hace cada comando? Eso habrá que programarlo en cada programa
 * individualmente.
 * @author KA EduSoft
 */
public class Comando {
    public static int MAX_PARAM_COMANDO = 25;
    public static String COMILLA_COMPUESTO = "\"";
    
    private final String nombre;
    private final Parametro[] parametros;
    private int tope, indice;
    
    /**
     * Crea un nuevo comando desglosando la cadena de caracteres según lo explicado
     * en la documentación de esta clase. Para el ejemplo:<br><br>
     * <b>CrearCelula</b> Eucariota "Con un nombre" 25<br><br>
     * El nombre el del comando será <b>CrearCelula</b>, luego el primer parámetro
     * será <b>Eucariota</b>, el segundo <b>Con un nombre</b> (nótese que las comillas
     * no se incluyen, y el tercero será <b>25</b>.<br><br>
     * <b>PRECONDICIÓN</b>: La cadena no es nula ni vacía.
     * @param com La cadena de línea de comandos que será usada para generar el comando
     * y sus parámetros.
     */
    public Comando(String com) {
        boolean isCompuesto;
        String[] splited = com.trim().split("\\s+");
        String palabra, nextParam = "";
        
        this.indice = -1;

        if (splited.length > 1) {
            this.parametros = new Parametro[MAX_PARAM_COMANDO];
        } else {
            this.parametros = null;
        }

        this.nombre = splited[0];
        this.tope = - 1;
        isCompuesto = false;

        for (int i = 1; i < splited.length; i++) {
            palabra = splited[i];

            if (palabra.startsWith(COMILLA_COMPUESTO) && (palabra.endsWith(COMILLA_COMPUESTO))) {
                nextParam = palabra.replace(COMILLA_COMPUESTO, "");
                isCompuesto = false;
            } else if (palabra.startsWith(COMILLA_COMPUESTO) && !isCompuesto) {
                isCompuesto = true;
                nextParam = palabra.replace(COMILLA_COMPUESTO, "");
            } else if (palabra.endsWith(COMILLA_COMPUESTO) && isCompuesto) {
                isCompuesto = false;
                nextParam = nextParam + " " + palabra.replace(COMILLA_COMPUESTO, "");
            } else if (isCompuesto) {
                nextParam = nextParam + " " + palabra;
            } else if (!isCompuesto) {
                nextParam = palabra;
            }

            if (!isCompuesto) {
                this.tope++;
                this.parametros[this.tope] = new Parametro(nextParam);
            }
        }
    }
    
    /**
     * Indica si existen parámetros para el comando en cuestión.
     * @return TRUE si hay uno o más parámetros, FALSE si no.
     */
    public boolean hayParametros() {
        return this.parametros != null;
    }
    
    /**
     * Retorna la cantdiad de parámetros ingresados para el comando. Si no tiene
     * ninguno se retorna 0.
     * @return La cantidad de parámetros del comando.
     */
    public int getCantidadParametros() {
        return (this.parametros == null) ? 0: this.tope + 1;
    }
    
    /**
     * Indica el nombre del comando.
     * @return El nombre del comando.
     */
    public String getNombre() {
        return this.nombre;
    }
    
    /**
     * Indica si hay más parámetros por recorrer.
     * @return TRUE si hay al menos un parámetro más por recorrer, FALSE si no
     * lo hay.
     */
    public boolean hayParametroSiguiente() {
        return (this.parametros != null) && (this.indice < this.tope);
    }
    
    /**
     * Retorna el siguiente parámetro dispnible. Si no {@link hayParametroSiguiente()}
     * se retorna <b>null</b>.
     * @return El siguiente parámetro a recorrer o <b>null</b> si no hay ninguno.
     */
    public Parametro siguienteParametro() {
        if (this.hayParametroSiguiente()) {
            this.indice++;
            return this.parametros[this.indice];
        } else {
            return null;
        }
    }
    
    /**
     * Resetea el puntero de parámetros para volver a recorrer desde el principio.
     */
    public void reset() {
        this.indice = -1;
    }
    
    /**
     * Retorna todos los parámetros como Strings en un arreglo. Si no hay parámetros
     * se retorna null;
     * @return Un arreglo de strings con los parámetros, null si no hay ninguno.
     */
    public String[] getArgs() {
        if (!this.hayParametros()) {
            return null;
        }

        String[] params  = new String[this.parametros.length];

        for (int i = 0; i < this.parametros.length; i++) {
            params[i] = this.parametros[i].getString();
        }

        return params;
    }
}