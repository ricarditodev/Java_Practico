/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JButton;
import motor.Posicion;

/**
 *
 * @author mstrv
 */
public class CeldaVisual extends JButton{
    public static int LARGO=25, ANCHO=25;
    
    private final Posicion posicionCelda;
    
    public CeldaVisual(Posicion p){
        super();
        this.posicionCelda= p;
    }
    
    public byte getFila(){
        return this.posicionCelda.getFila();
    }
    
    public byte getColumna(){
        return this.posicionCelda.getColumna();
    }
    
    public Posicion getPosicion(){
        return this.posicionCelda;
    }

}
