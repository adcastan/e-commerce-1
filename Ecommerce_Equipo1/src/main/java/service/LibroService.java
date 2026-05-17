package service;

import DTOs.LibroDTO;
import daos.GeneroDAO;
import daos.IGeneroDAO;
import daos.ILibroDAO;
import daos.IReseniaDAO;
import daos.LibroDAO;
import daos.ReseniaDAO;
import java.util.List;
import java.util.stream.Collectors;
import models.Genero;
import models.Libro;
import util.Mappers;

public class LibroService implements ILibroService {

    private final ILibroDAO libroDAO;
    private final IGeneroDAO generoDAO;
    private final IReseniaDAO reseniaDAO;

    public LibroService() {
        this.libroDAO = new LibroDAO();
        this.generoDAO = new GeneroDAO();
        this.reseniaDAO = new ReseniaDAO();
    }

    @Override
    public LibroDTO crear(LibroDTO datos) {
        validarDatosLibro(datos, true);
        if (libroDAO.buscarPorIsbn(datos.getIsbn()) != null) {
            throw new NegocioException("ya existe un libro con ese ISBN");
        }
        Genero genero = null;
        if (datos.getIdGenero() != null) {
            genero = generoDAO.buscarPorId(datos.getIdGenero());
            if (genero == null) {
                throw new NegocioException("g\u00e9nero no encontrado");
            }
        }
        Libro nuevo = new Libro(
                datos.getIsbn().trim(),
                datos.getTitulo().trim(),
                datos.getAutor().trim(),
                datos.getDescripcion(),
                datos.getPrecio(),
                datos.getStock(),
                datos.getAnioPublicacion(),
                datos.getEditorial(),
                datos.getImagenUrl(),
                datos.isDestacado(),
                genero
        );
        return Mappers.toLibroDTO(libroDAO.guardar(nuevo));
    }

    @Override
    public LibroDTO buscarPorId(Integer id) {
        Libro l = libroDAO.buscarPorId(id);
        return Mappers.toLibroDTO(l);
    }

    @Override
    public LibroDTO buscarPorIdConDetalles(Integer id) {
        Libro l = libroDAO.buscarPorId(id);
        if (l == null) return null;
        LibroDTO dto = Mappers.toLibroDTO(l);
        dto.setCalificacionPromedio(reseniaDAO.calificacionPromedio(id));
        dto.setCantidadResenias(reseniaDAO.listarPorLibro(id).size());
        return dto;
    }

    @Override
    public List<LibroDTO> listar() {
        return libroDAO.listar().stream()
                .map(Mappers::toLibroDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LibroDTO> listarDestacados(int limite) {
        return libroDAO.listarDestacados(limite).stream()
                .map(Mappers::toLibroDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LibroDTO> listarRecientes(int limite) {
        return libroDAO.listarRecientes(limite).stream()
                .map(Mappers::toLibroDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LibroDTO> filtrar(String texto, Integer idGenero, Double precioMin, Double precioMax) {
        return libroDAO.filtrar(texto, idGenero, precioMin, precioMax).stream()
                .map(Mappers::toLibroDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LibroDTO actualizar(Integer id, LibroDTO datos) {
        Libro l = libroDAO.buscarPorId(id);
        if (l == null) {
            throw new NegocioException("libro no encontrado");
        }
        validarDatosLibro(datos, false);

        if (datos.getIsbn() != null && !datos.getIsbn().equals(l.getIsbn())) {
            if (libroDAO.buscarPorIsbn(datos.getIsbn()) != null) {
                throw new NegocioException("ya existe un libro con ese ISBN");
            }
            l.setIsbn(datos.getIsbn().trim());
        }
        if (datos.getTitulo() != null) l.setTitulo(datos.getTitulo().trim());
        if (datos.getAutor() != null) l.setAutor(datos.getAutor().trim());
        if (datos.getDescripcion() != null) l.setDescripcion(datos.getDescripcion());
        if (datos.getPrecio() > 0) l.setPrecio(datos.getPrecio());
        l.setStock(datos.getStock());
        if (datos.getAnioPublicacion() != null) l.setAnioPublicacion(datos.getAnioPublicacion());
        if (datos.getEditorial() != null) l.setEditorial(datos.getEditorial());
        if (datos.getImagenUrl() != null) l.setImagenUrl(datos.getImagenUrl());
        l.setDestacado(datos.isDestacado());
        l.setActivo(datos.isActivo());

        if (datos.getIdGenero() != null) {
            Genero g = generoDAO.buscarPorId(datos.getIdGenero());
            if (g == null) throw new NegocioException("g\u00e9nero no encontrado");
            l.setGenero(g);
        }

        return Mappers.toLibroDTO(libroDAO.actualizar(l));
    }

    @Override
    public boolean eliminar(Integer id) {
        return libroDAO.eliminar(id);
    }

    private void validarDatosLibro(LibroDTO datos, boolean esCreacion) {
        if (datos == null) throw new NegocioException("datos vac\u00edos");
        if (esCreacion) {
            if (datos.getIsbn() == null || datos.getIsbn().isBlank()) throw new NegocioException("isbn obligatorio");
            if (datos.getTitulo() == null || datos.getTitulo().isBlank()) throw new NegocioException("t\u00edtulo obligatorio");
            if (datos.getAutor() == null || datos.getAutor().isBlank()) throw new NegocioException("autor obligatorio");
            if (datos.getPrecio() <= 0) throw new NegocioException("el precio debe ser mayor a cero");
            if (datos.getStock() < 0) throw new NegocioException("el stock no puede ser negativo");
        }
    }
}
