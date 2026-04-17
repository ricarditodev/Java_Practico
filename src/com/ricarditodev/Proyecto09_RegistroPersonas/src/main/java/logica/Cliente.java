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
public class Cliente {
    private String nombre, apellido, documento, numero, direccion;

    /**
     * Crea un nuevo cliente con los datos pasados como argumento. La dirección
     * quedará vacía.
     * @param nombre El nombre del cliente.
     * @param apellido El apellido del cliente.
     * @param documento El documento del cliente.
     * @param numero El número del cliente.
     */
    public Cliente(String nombre, String apellido, String documento, String numero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.numero = numero;
        this.direccion = "";
    }

    /**
     * Crea un nuevo cliente con los datos pasados como argumento.
     * @param nombre El nombre del cliente.
     * @param apellido El apellido del cliente.
     * @param documento El documento del cliente.
     * @param numero El número del cliente.
     * @param direccion La dirección del cliente.
     */
    public Cliente(String nombre, String apellido, String documento, String numero, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.numero = numero;
        this.direccion = direccion;
    }

    /**
     * El nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nuevo nombre al cliente.
     * @param nombre El nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * El apellido del cliente.
     * @return El apellido del cliente.
     */
    public String getApellido() {
        return apellido;
    }
    
    /**
     * Asigna un nuevo apellildo al cliente.
     * @param apellido El apellido a asignar.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * El documento del cliente.
     * @return El documento del cliente.
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Asigna un nuevo documento al cliente.
     * @param documento El documento a asignar.
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * El número de cliente.
     * @return El número de cliente.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Asigna un nuevo número al cliente.
     * @param numero El nuevo número a asignar.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * La dirección del cliente.
     * @return La dirección de cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Asigna una nueva dirección al cliente.
     * @param direccion La nueva dirección a asignar.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
