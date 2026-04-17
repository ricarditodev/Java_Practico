package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import logica.*;

/**
 *
 * @author KA EduSoft
 */
public class Controlador {

    private final File archivoClientes;
    private final File archivoProductos;
    private final File archivoVentas;
    
    private final TopArrayClientes bdClientes;
    private final TopArrayProductos bdProductos;
    private final TopArrayVentas bdVentas;
    
    /**
     * Se inicializa todo lo necesario para trabajar. Las listas de Clientes,
     * Productos y Ventas quedarán vacías. Para realizar la carga debe invocarse
     * manualmente a la operación {@link cargar()}.
     */
    public Controlador(){
        this.bdClientes= new TopArrayClientes();
        this.bdProductos= new TopArrayProductos();
        this.bdVentas= new TopArrayVentas();
        
        this.archivoClientes= new File(Constantes.NOMBRE_ARCHIVO_CLIENTES);
        this.archivoProductos= new File(Constantes.NOMBRE_ARCHIVO_PRODUCTOS);
        this.archivoVentas= new File(Constantes.NOMBRE_ARCHIVO_VENTAS);
    }
    
    /**
     * Intenta cargar los datos de los archivos. Si algo sale mal se retorna FALSE,
     * si no, TRUE.<br><br>
     * <b>PRECONDICIÓN</b>: No se realizó previamente una invocación a esta operación.<br>
     * <b>POSTCONDICIÓN</b>: Las listas de Clientes, Productos y Ventas quedan
     * correctamente inicializadas. Si no hay datos para ellas éstas quedarán 
     * vacías.
     * @return TRUE si la carga es exitosa, FALSE en caso de que exista cualquier
     * error. No se dan detalles del error.
     */
    public boolean cargar(){
        try{
            //Aseguramos la existencia de los archivos.
            this.archivoClientes.createNewFile();
            this.archivoProductos.createNewFile();
            this.archivoVentas.createNewFile();
            
            //Creamos flujos para leer los archivos usando Scanner.
            Scanner entradaClientes= new Scanner(archivoClientes);
            Scanner entradaProductos= new Scanner(archivoProductos);
            Scanner entradaVentas= new Scanner(archivoVentas);
            String linea;
            String[] campos;
            
            //Cargamos primero los clientes
            //NOMBRE;APELLIDO;DOCUMENTO;NUMERO;DIRECCION
            while(entradaClientes.hasNext()){
                linea= entradaClientes.nextLine();
                campos= linea.split(";");
                this.bdClientes.insertar(new Cliente(campos[0],campos[1],campos[2],campos[3],campos[4]));
            }
            entradaClientes.close();
            
            //Cargamos los productos
            //String nombre, String marca, double precio, String codigo
            while(entradaProductos.hasNext()){
                linea= entradaProductos.nextLine();
                campos= linea.split(";");
                this.bdProductos.insertar(new Producto(campos[0],campos[1],Double.parseDouble(campos[2]),campos[3]));
            }
            entradaProductos.close();
            
            //Cargamos las ventas
            //String codigo, Cliente cliente(documento), Producto producto(codigo), int cantidad
            while(entradaVentas.hasNext()){
                linea= entradaVentas.nextLine();
                campos= linea.split(";");
                
                //Necesitamos el cliente
                Cliente c=null;
                for(int i=0; i<this.bdClientes.getCantidad(); i++){
                    if(this.bdClientes.getCliente(i).getDocumento().equals(campos[1])){
                        c= this.bdClientes.getCliente(i);
                        break;
                    }
                }
                //Si no encontramos el cliente debemos salir.
                if(c==null){
                    return false;
                }
                
                //Necesitamos el producto
                Producto p= null;
                for(int i=0; i<this.bdProductos.getCantidad();i++){
                    if(this.bdProductos.getProducto(i).getCodigo().equals(campos[2])){
                        p= this.bdProductos.getProducto(i);
                        break;
                    }
                }
                //Si no encontramos el producto debemos salir.
                if(p==null){
                    return false;
                }
                
                this.bdVentas.insertar(new Venta(campos[0],c,p,Integer.parseInt(campos[3])));
            }
            entradaVentas.close();
        }catch(Exception e){
            return false;
        }
        return true;
    }
    
    /**
     * Se crea un nuevo cliente con los datos pasados como argumento y se registra
     * en la lista de clientes. Si todo sale bien se retorna TRUE y se deja el contenido
     * de message como un string vacío. Si el cliente ya existe se asignará el mensaje
     * de error 101 a message (documento existente) o 102 (número existente).
     * @param nombre El nombre del cliente.
     * @param apellido El apellido del cliente.
     * @param documento El documento del cliente.
     * @param numero El numero de cliente.
     * @param direccion La dirección del cliente.
     * @param message Contendrá el mensaje correspondiente luego de la ejecución.
     * @return TRUE si el cliente se registra correctamente, FALSE si no.
     * @see Constantes#getMensaje(int, java.lang.String...) 
     */
    public boolean registrarCliente(String nombre, String apellido, String documento, String numero, String direccion, StringBuilder message){
                        message.delete(0, message.length());
        //Debemos verificar que no exista ya un cliente con el documento dado.
        Cliente c= null;
        for(int i=0; i<this.bdClientes.getCantidad(); i++){
            c= this.bdClientes.getCliente(i);
            if(c.getDocumento().equals(documento)){
                message.append(Constantes.getMensaje(Constantes.MSJ_CLIENTE_DOCUMENTO_EXISTE_ERROR,documento));
                return false;
            }
            
            if(c.getNumero().equals(numero)){
                message.append(Constantes.getMensaje(Constantes.MSJ_CLIENTE_NUMERO_EXISTE_ERROR, numero));
                return false;
            }
        }
        
        //El cliente no existe así que lo creamos y agregamos a la lista.
        this.bdClientes.insertar(new Cliente(nombre,apellido,documento,numero,direccion));
        message.append(Constantes.getMensaje(Constantes.MSJ_CLIENTE_REGISTRADO_OK, documento));
        return true;
    }
    
    /**
     * Actualiza la información en los archivos del disco duro.
     * @param message Contendrá el mensaje generado según si la actualización
     * ha sido correcta o no.
     * @return TRUE si la actualización se lleva a cabo sin problemas, FALSE
     * en caso contrario, tras lo cual se asigna a message el mensaje de error.
     * Si no ocurre ningún error se dejará el mensaje vacío.
     */
    public boolean commit(StringBuilder message){
        message.delete(0, message.length()); 
        try{
            FileWriter fw= new FileWriter(this.archivoClientes);
            PrintWriter salida= new PrintWriter(fw);
            
            Cliente c= null;
            for(int i=0; i<bdClientes.getCantidad();i++){
                c= bdClientes.getCliente(i);
                salida.println(c.getNombre()+";"+c.getApellido()+";"+c.getDocumento()+";"+c.getNumero()+";"+c.getDireccion());
            }
            salida.close();
            fw.close();
        }catch(IOException ex){
            message.append(ex.getMessage());
        }
        
        message.append("");
        return true;
    }
}
