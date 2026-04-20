package service;

import daos.GeneroDAO;
import daos.IGeneroDAO;
import java.util.List;
import models.Genero;

public class GeneroService implements IGeneroService {

    private final IGeneroDAO generoDAO;

    public GeneroService() {
        this.generoDAO = new GeneroDAO();
    }

    @Override
    public void agregarGenero(Genero genero) {
        if (genero.getNombreGenero() == null || genero.getNombreGenero().isBlank()) {
            throw new IllegalArgumentException("El nombre del género no puede estar vacío");
        }
        generoDAO.guardar(genero);
    }

    @Override
    public Genero obtenerGeneroPorId(int id) {
        Genero genero = generoDAO.buscarPorId(id);
        if (genero == null) throw new IllegalArgumentException("No se encontró el género con id: " + id);
        return genero;
    }

    @Override
    public List<Genero> obtenerTodosLosGeneros() {
        return generoDAO.listarTodos();
    }

    @Override
    public void actualizarGenero(Genero genero) {
        if (genero.getIdGenero() <= 0) throw new IllegalArgumentException("ID de género inválido");
        generoDAO.actualizar(genero);
    }

    @Override
    public void eliminarGenero(int id) {
        generoDAO.eliminar(id);
    }
}
