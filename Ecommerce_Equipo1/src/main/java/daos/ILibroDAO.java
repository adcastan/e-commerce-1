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
public interface ILibroDAO {
    void guardar(Libro libro);
    
    Libro buscarPorGenero(String genero);

    void actualizar(Libro libro);

    void eliminar(int id);
}
