package service;

import DTOs.MetodoPagoDTO;
import daos.IMetodoPagoDAO;
import daos.MetodoPagoDAO;
import java.util.List;
import java.util.stream.Collectors;
import util.Mappers;

public class MetodoPagoService implements IMetodoPagoService {

    private final IMetodoPagoDAO metodoPagoDAO;

    public MetodoPagoService() {
        this.metodoPagoDAO = new MetodoPagoDAO();
    }

    @Override
    public List<MetodoPagoDTO> listar() {
        return metodoPagoDAO.listar().stream()
                .map(Mappers::toMetodoPagoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MetodoPagoDTO buscarPorId(Integer id) {
        return Mappers.toMetodoPagoDTO(metodoPagoDAO.buscarPorId(id));
    }
}
