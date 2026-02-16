/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author Adrián
 */
public class Venta {

    public Venta() {
    }
    
    
    int idVenta;
    Date fechaHora;
    int total;
    Cliente idCliente;
    MetodoPago idPago;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public MetodoPago getIdPago() {
        return idPago;
    }

    public void setIdPago(MetodoPago idPago) {
        this.idPago = idPago;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", fechaHora=" + fechaHora + ", total=" + total + ", idCliente=" + idCliente + ", idPago=" + idPago + '}';
    }
    
    
}
