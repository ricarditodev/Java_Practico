/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author mstrv
 */
public final class Constantes {
    public static final String PROMPT= ">> ";
    
    public static final int DEFAULT_ARRAY_LENGTH= 1000;
    public static final String NOMBRE_ARCHIVO_CLIENTES= "clientes.txt";
    public static final String NOMBRE_ARCHIVO_PRODUCTOS= "producto.txt";
    public static final String NOMBRE_ARCHIVO_VENTAS= "ventas.txt";
    
    public static final String NOMBRE_COMANDO_NUEVO= "NUEVO";
    public static final String PARAM_COMANDO_CLIENTE= "-C";
    public static final int COMANDO_NUEVO_C_COUNT= 6;
    public static final String NOMBRE_COMANDO_SALIR= "SALIR";
    
    public static final int MSJ_COMMIT_OK= 10;
    public static final int MSJ_CLIENTE_REGISTRADO_OK= 11;
    public static final int MSJ_CLIENTE_DOCUMENTO_EXISTE_ERROR= 101;
    public static final int MSJ_CLIENTE_NUMERO_EXISTE_ERROR= 102;
    public static final int MSJ_COMANDO_NO_EXISTE_ERROR= 103;
    public static final int MSJ_CANTIDAD_PARAMETROS_ERROR= 104;
    public static final int MSJ_PARAMETRO_NO_EXISTE_ERROR= 105;
    
    /**
     * Este constructor es privado para evitar que se creen objetos de esta clase.
     */
    private Constantes(){}
    
    /**
     * Retorna un mensaje adecuado al código pasado como parámetro. Los argumentos
     * necesarios dependen del mensaje en sí. Se asume que se pasa siempre la cantidad
     * correcta de los mismos.<br>
     * <ul>
     * <li><b>10 (MSJ_COMMIT_OK)</b>: No requiere argumentos.
     * <li><b>11 (MSJ_CLIENTE_REGISTRADO_OK)</b>: Requiere el <b>documento</b>.</li>
     * <li><b>101 (MSJ_CLIENTE_DOCUMENTO_EXISTE_ERROR)</b>: Requiere el <b>documento</b>.</li>
     * <li><b>102 (MSJ_CLIENTE_NUMERO_EXISTE_ERROR)</b>: Requiere el <b>número</b> de cliente.</li>
     * <li><b>103 (MSJ_COMANDO_NO_EXISTE)</b>: Requiere el nombre del <b>comando</b> ingresado.</li>
     * <li><b>104 (MSJ_CANTIDAD_PARAMETROS_ERROR)</b>: Requiere el nombre del <b>comando</b> ingresado.</li>
     * <li><b>105 (MSJ_PARAMETRO_NO_EXISTE_ERROR)</b>: Requiere el <b>parámetro</b> ingresado y el nombre del <b>comando</b>.</li>
     * </ul>
     * @param codigo El código de error.
     * @param args La lista de argumentos necesarios para el mensaje de error.
     * @return El mensaje correspondiente al código ingresado.<br>
     * <ul>
     * <li><code>MSJ_COMMIT_OK</code>: "Los cambios han sido guardados exitosamente."</li>
     * <li><code>MSJ_CLIENTE_REGISTRADO_OK</code>: "El cliente con documento <code>args[0]</code> ha sido resgistrado con éxito."</li>
     * <li><code>MSJ_CLIENTE_DOCUMENTO_EXISTE_ERROR</code>: "El cliente con documento <code>args[0]</code> ya existe."<li>
     * <li><code>MSJ_CLIENTE_NUMERO_EXISTE_ERROR</code>: "El cliente con número <code>args[0]</code> ya existe."</li>
     * <li><code>MSJ_COMANDO_NO_EXISTE_ERROR</code>: "El comando con nombre <code>args[0]</code> no existe."</li>
     * <li><code>MSJ_COMANDO_NO_EXISTE</code>: "El comando <code>args[0]</code> no existe."</li>
     * <li><code>MSJ_CANTIDAD_PARAMETROS_ERROR</code>: "La cantdiad de parámetros para el comando <code>args[0]</code> es incorrecta."</li>
     * <li><code>MSJ_PARAMETRO_NO_EXISTE_ERROR</code>: "El parámetro <code>args[0]</code> no es indicado para el comando <code>args[1]</code>."</li>
     * </ul>
     */
    public static String getMensaje(int codigo, String... args){
        switch(codigo){
            case MSJ_COMMIT_OK:
                return "Actualización de archivos (commit) correcta.";
            case MSJ_CLIENTE_REGISTRADO_OK:
                return "El cliente con documento "+args[0]+" ha sido resgistrado con éxito.";
            case MSJ_CLIENTE_DOCUMENTO_EXISTE_ERROR:
                return "El cliente con documento "+args[0]+" ya existe.";
            case MSJ_COMANDO_NO_EXISTE_ERROR:
                return "El comando con nombre "+args[0]+" no existe.";
            case MSJ_CANTIDAD_PARAMETROS_ERROR:  
                return "La cantidad de parámetros para el comando "+args[0]+" es incorrecta.";
            case MSJ_PARAMETRO_NO_EXISTE_ERROR:
                return "El parámetro "+args[0]+" no es indicado para el comando "+args[1]+".";
            default:
                return "Código "+codigo+" es desconocido para el sistema.";
        }
    }
}
