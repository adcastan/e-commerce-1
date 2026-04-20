package daos;

import java.util.List;
import models.Proveedor;

public interface IProveedorDAO {
    void guardar(Proveedor proveedor);
    Proveedor buscarPorId(int id);
    List<Proveedor> listarTodos();
    void actualizar(Proveedor proveedor);
    void eliminar(int id);
}
