package daos;

import java.util.List;
import models.Libro;

public interface ILibroDAO {

    Libro guardar(Libro libro);

    Libro buscarPorId(Integer id);

    Libro buscarPorIsbn(String isbn);

    List<Libro> listar();

    List<Libro> listarActivos();

    List<Libro> listarDestacados(int limite);

    List<Libro> listarRecientes(int limite);

    List<Libro> filtrar(String texto, Integer idGenero, Double precioMin, Double precioMax);

    Libro actualizar(Libro libro);

    boolean eliminar(Integer id);

    boolean descontarStock(Integer idLibro, int cantidad);
}
