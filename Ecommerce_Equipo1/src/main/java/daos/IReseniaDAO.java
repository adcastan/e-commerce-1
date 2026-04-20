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
    
    Resenia buscarPorId(int id);

    void actualizar(Resenia resenia);

    int eliminar(int id);
}
