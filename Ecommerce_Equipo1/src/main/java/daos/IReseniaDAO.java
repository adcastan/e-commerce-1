/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import models.Resenia;

/**
 *
 * @author Adrián
 */
public interface IReseniaDAO {
     void guardar(Resenia resenia);
    
    public Resenia crearCliente(Resenia resenia);

    Long buscarPorId(Long id);

    void actualizar(Resenia resenia);

    Long eliminar(Long id);
}
