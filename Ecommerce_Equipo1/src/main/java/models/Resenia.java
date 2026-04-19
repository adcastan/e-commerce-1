/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Date;

/**
 *
 * @author Adrián
 */
@Entity
public class Resenia {
    
    public Resenia(){
        
    }
    
    @Id
    int idResenia;
    @OneToOne
    Date fechaHora;
    @OneToOne
    int calificacion;
    @OneToMany
    Cliente idCliente;   

    public Resenia(int idResenia, Date fechaHora, int calificacion, Cliente idCliente) {
        this.idResenia = idResenia;
        this.fechaHora = fechaHora;
        this.calificacion = calificacion;
        this.idCliente = idCliente;
    }

    public int getIdResenia() {
        return idResenia;
    }

    public void setIdResenia(int idResenia) {
        this.idResenia = idResenia;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }
    
    
    
}
