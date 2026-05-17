package service;

import DTOs.MetodoPagoDTO;
import java.util.List;

public interface IMetodoPagoService {

    List<MetodoPagoDTO> listar();

    MetodoPagoDTO buscarPorId(Integer id);
}
