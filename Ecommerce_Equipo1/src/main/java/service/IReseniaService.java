package service;

import DTOs.ReseniaDTO;
import java.util.List;

public interface IReseniaService {

    ReseniaDTO crear(Integer idCliente, ReseniaDTO datos);

    List<ReseniaDTO> listar();

    List<ReseniaDTO> listarPorLibro(Integer idLibro);

    List<ReseniaDTO> listarPorCliente(Integer idCliente);

    boolean eliminar(Integer id);
}
