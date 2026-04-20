package service;

import java.util.List;

import daos.ILibroDAO;
import daos.LibroDAO;
import models.Libro;

/**
 *
 * @author Adrián
 */

public class LibroService implements ILibroService {

    private final ILibroDAO libroDAO;

    public LibroService() {
        this.libroDAO = new LibroDAO();
    }

    @Override
    public void agregarLibro(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título del libro no puede estar vacío");
        }
        if (libro.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (libro.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        libroDAO.guardar(libro);
    }

    @Override
    public Libro obtenerLibroPorId(int id) {
        Libro libro = libroDAO.buscarPorId(id);
        if (libro == null) throw new IllegalArgumentException("No se encontró el libro con id: " + id);
        return libro;
    }

    @Override
    public List<Libro> obtenerTodosLosLibros() {
        return libroDAO.listarTodos();
    }

    @Override
    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) return libroDAO.listarTodos();
        return libroDAO.buscarPorTitulo(titulo);
    }

    @Override
    public List<Libro> obtenerLibrosPorGenero(int idGenero) {
        return libroDAO.listarPorGenero(idGenero);
    }

    @Override
    public void actualizarLibro(Libro libro) {
        if (libro.getIdLibro() <= 0) throw new IllegalArgumentException("ID de libro inválido");
        libroDAO.actualizar(libro);
    }

    @Override
    public void eliminarLibro(int id) {
        libroDAO.eliminar(id);
    }
}
