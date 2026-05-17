package service;

import DTOs.GeneroDTO;
import daos.GeneroDAO;
import daos.IGeneroDAO;
import java.util.List;
import java.util.stream.Collectors;
import models.Genero;
import util.Mappers;

public class GeneroService implements IGeneroService {

    private final IGeneroDAO generoDAO;

    public GeneroService() {
        this.generoDAO = new GeneroDAO();
    }

    @Override
    public GeneroDTO crear(GeneroDTO datos) {
        if (datos == null || datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new NegocioException("el nombre del g\u00e9nero es obligatorio");
        }
        if (generoDAO.buscarPorNombre(datos.getNombre()) != null) {
            throw new NegocioException("ya existe un g\u00e9nero con ese nombre");
        }
        Genero g = new Genero(datos.getNombre().trim());
        return Mappers.toGeneroDTO(generoDAO.guardar(g));
    }

    @Override
    public GeneroDTO buscarPorId(Integer id) {
        return Mappers.toGeneroDTO(generoDAO.buscarPorId(id));
    }

    @Override
    public List<GeneroDTO> listar() {
        return generoDAO.listar().stream()
                .map(Mappers::toGeneroDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GeneroDTO actualizar(Integer id, GeneroDTO datos) {
        Genero g = generoDAO.buscarPorId(id);
        if (g == null) {
            throw new NegocioException("g\u00e9nero no encontrado");
        }
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new NegocioException("el nombre es obligatorio");
        }
        g.setNombre(datos.getNombre().trim());
        return Mappers.toGeneroDTO(generoDAO.actualizar(g));
    }

    @Override
    public boolean eliminar(Integer id) {
        return generoDAO.eliminar(id);
    }
}
