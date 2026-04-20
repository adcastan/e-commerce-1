package service;

/**
 *
 * @author Adrián
 */

import java.util.List;

import models.Libro;

public interface ILibroService {
    void agregarLibro(Libro libro);
    Libro obtenerLibroPorId(int id);
    List<Libro> obtenerTodosLosLibros();
    List<Libro> buscarLibrosPorTitulo(String titulo);
    List<Libro> obtenerLibrosPorGenero(int idGenero);
    void actualizarLibro(Libro libro);
    void eliminarLibro(int id);
}
