/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import java.util.List;
import models.Cliente;
import models.Libro;

/**
 *
 * @author Adrián
 */
public interface IClienteDAO {
     void guardar(Cliente cliente);
    
    Cliente buscarPorId(int id);

    void actualizar(Cliente cliente);

    int eliminar(int id);
    
}
