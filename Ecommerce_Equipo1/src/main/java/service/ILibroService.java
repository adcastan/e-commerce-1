package service;

import DTOs.LibroDTO;
import java.util.List;

public interface ILibroService {

    LibroDTO crear(LibroDTO datos);

    LibroDTO buscarPorId(Integer id);

    LibroDTO buscarPorIdConDetalles(Integer id);

    List<LibroDTO> listar();

    List<LibroDTO> listarDestacados(int limite);

    List<LibroDTO> listarRecientes(int limite);

    List<LibroDTO> filtrar(String texto, Integer idGenero, Double precioMin, Double precioMax);

    LibroDTO actualizar(Integer id, LibroDTO datos);

    boolean eliminar(Integer id);
}
