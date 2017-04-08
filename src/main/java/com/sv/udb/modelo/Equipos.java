/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

/**
 *
 * @author Jose Lira
 */
public class Equipos {
    private int codiEqui;
    private String nombEqui;
    private String descEqui;
    private byte[] imag;
    
    public Equipos(){
    
    }

    public Equipos(int codiEqui, String nombEqui, String descEqui, byte[] imag) {
        this.codiEqui = codiEqui;
        this.nombEqui = nombEqui;
        this.descEqui = descEqui;
        this.imag = imag;
    }

    public int getCodiEqui() {
        return codiEqui;
    }

    public void setCodiEqui(int codiEqui) {
        this.codiEqui = codiEqui;
    }

    public String getNombEqui() {
        return nombEqui;
    }

    public void setNombEqui(String nombEqui) {
        this.nombEqui = nombEqui;
    }

    public String getDescEqui() {
        return descEqui;
    }

    public void setDescEqui(String descEqui) {
        this.descEqui = descEqui;
    }

    public byte[] getImag() {
        return imag;
    }

    public void setImag(byte[] imag) {
        this.imag = imag;
    }

    @Override
    public String toString() {
        return this.nombEqui;
    }
    
    
    
}
