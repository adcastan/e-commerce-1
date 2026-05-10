package daos;

import java.util.List;
import models.EstadoVenta;
import models.Venta;

public interface IVentaDAO {

    Venta guardar(Venta venta);

    Venta buscarPorId(Integer id);

    Venta buscarPorNumeroPedido(String numero);

    List<Venta> listar();

    List<Venta> listarPorCliente(Integer idCliente);

    List<Venta> listarPorEstado(EstadoVenta estado);

    Venta actualizarEstado(Integer idVenta, EstadoVenta nuevoEstado);
}
