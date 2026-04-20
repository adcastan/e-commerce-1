/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import java.util.List;

import models.Libro;

/**
 *
 * @author Adrián
 */

public interface ILibroDAO {
    void guardar(Libro libro);
    public Libro crearLibro(Libro libro);
    Libro buscarPorCorreoYContrasenia(String titulo);
    Libro buscarPorGenero(String genero);

    Libro buscarPorId(int id);
    List<Libro> listarTodos();
    List<Libro> listarPorGenero(int idGenero);
    List<Libro> buscarPorTitulo(String titulo);
    void actualizar(Libro libro);
    void eliminar(int id);
}
