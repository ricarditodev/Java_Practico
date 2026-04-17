package main;

import comandos.*;
import controlador.Controlador;
import java.util.Scanner;
import logica.Constantes;

public class Administrador {
    private final Controlador manager;
    private boolean salir;
    
    public Administrador(){
        this.manager= new Controlador();
        this.salir= false;
    }//Fin del constructor.

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Administrador application= new Administrador();
        if(application.manager.cargar()){
            Scanner entrada= new Scanner(System.in);
            StringBuilder message= new StringBuilder();
            Comando comando= null;
            Parametro parametro= null;
            System.out.println("KA EduSoft - www.kaedusoft.edu.uy - 2021\n");
            
            do{
                System.out.print(Constantes.PROMPT);
                comando= new Comando(entrada.nextLine());
                //SWITCH para distinguir qué comando se introdujo.
                switch(comando.getNombre().toUpperCase()){
                    case Constantes.NOMBRE_COMANDO_NUEVO: //COMANDO NUEVO**********
                        if(comando.getCantidadParametros()!=Constantes.COMANDO_NUEVO_C_COUNT){
                            System.out.println(Constantes.getMensaje(Constantes.MSJ_CANTIDAD_PARAMETROS_ERROR, comando.getNombre()));
                            continue;
                        }
                        parametro= comando.siguienteParametro();
                        //SWITCH para distinguir qué parámetro se ingresó para el comando NUEVO
                        switch(parametro.getString().toUpperCase()){
                            case Constantes.PARAM_COMANDO_CLIENTE:
                                application.manager.registrarCliente(
                                        comando.siguienteParametro().getString(),
                                        comando.siguienteParametro().getString(),
                                        comando.siguienteParametro().getString(),
                                        comando.siguienteParametro().getString(),
                                        comando.siguienteParametro().getString(),
                                        message
                                );
                                System.out.println(message);
                                break; //Se cargó la persona correctamente.
                            default: System.out.println(Constantes.getMensaje(Constantes.MSJ_PARAMETRO_NO_EXISTE_ERROR,parametro.getString(),comando.getNombre()));
                        }
                        break; //Finalizar ejecución de comando NUEVO
                    case Constantes.NOMBRE_COMANDO_SALIR: //COMANDO SALIR*******
                        application.salir= true;
                        break;
                    default: //COMANDO desconocido******************************
                        System.out.println(Constantes.getMensaje(Constantes.MSJ_COMANDO_NO_EXISTE_ERROR, comando.getNombre()));      
                }
            }while(!application.salir); //Bucle principal
            //Habiendo salido del bucle principal hacemos COMMIT para guardar los cambios.
            application.manager.commit(message);
            System.out.println(message);
        }else{
            System.out.println("Carga fallida");
        }
    }//Fin de main.
    
}
