/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Adrián
 */
public class Cliente {
    
    public Cliente(){
        
    }

    public Cliente(int idCliente, String nombreCliente, String correoElectronico, String contrasenia) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }
    
    
    int idCliente;
    String nombreCliente;
    String correoElectronico;
    String contrasenia;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombreCliente=" + nombreCliente + ", correoElectronico=" + correoElectronico + ", contrasenia=" + contrasenia + '}';
    }
    
    
}
