package daos;

import java.util.List;
import models.MetodoPago;

public interface IMetodoPagoDAO {

    MetodoPago guardar(MetodoPago metodoPago);

    MetodoPago buscarPorId(Integer id);

    MetodoPago buscarPorTipo(String tipo);

    List<MetodoPago> listar();
}
