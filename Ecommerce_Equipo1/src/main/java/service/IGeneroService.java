package service;

import DTOs.GeneroDTO;
import java.util.List;

public interface IGeneroService {

    GeneroDTO crear(GeneroDTO datos);

    GeneroDTO buscarPorId(Integer id);

    List<GeneroDTO> listar();

    GeneroDTO actualizar(Integer id, GeneroDTO datos);

    boolean eliminar(Integer id);
}
