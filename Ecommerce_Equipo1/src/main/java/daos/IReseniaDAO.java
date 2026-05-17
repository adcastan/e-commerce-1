package daos;

import java.util.List;
import models.Resenia;

public interface IReseniaDAO {

    Resenia guardar(Resenia resenia);

    Resenia buscarPorId(Integer id);

    List<Resenia> listar();

    List<Resenia> listarPorLibro(Integer idLibro);

    List<Resenia> listarPorCliente(Integer idCliente);

    Double calificacionPromedio(Integer idLibro);

    boolean eliminar(Integer id);
}
