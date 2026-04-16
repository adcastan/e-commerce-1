/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.Date;

/**
 *
 * @author Adrián
 */
public class VentaDTO {
    
    public VentaDTO() {
    }
   
    int idVenta;
    Date fechaHora;
    int total;
    ClienteDTO idCliente;
    MetodoPagoDTO idPago;

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

    public ClienteDTO getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ClienteDTO idCliente) {
        this.idCliente = idCliente;
    }

    public MetodoPagoDTO getIdPago() {
        return idPago;
    }

    public void setIdPago(MetodoPagoDTO idPago) {
        this.idPago = idPago;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", fechaHora=" + fechaHora + ", total=" + total + ", idCliente=" + idCliente + ", idPago=" + idPago + '}';
    }
    
    
}
