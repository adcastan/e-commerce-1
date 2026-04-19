/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import models.Venta;

/**
 *
 * @author Adrián
 */
public interface IVentaDAO {
    
    void guardar(Venta venta);
    
    public Venta crearVenta(Venta venta);
    
    public void buscarVentaPorId(int id);
    
    public void actualizarVenta(Venta venta);
    
    public void eliminar(int id);
}
