/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.persistence.*;

/**
 *
 * @author Adrián
 */
@Entity
public class MetodoPago {
    
    public MetodoPago(){
        
    }
    @Id
    int idPago;
    String tipo;

    public MetodoPago(int idPago, String tipo) {
        this.idPago = idPago;
        this.tipo = tipo;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "MetodoPago{" + "idPago=" + idPago + ", tipo=" + tipo + '}';
    }
    
    
    
}
