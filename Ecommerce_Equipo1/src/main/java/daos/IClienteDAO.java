package daos;

import java.util.List;
import models.Cliente;

public interface IClienteDAO {

    Cliente guardar(Cliente cliente);

    Cliente buscarPorId(Integer id);

    Cliente buscarPorCorreo(String correo);

    List<Cliente> listar();

    Cliente actualizar(Cliente cliente);

    boolean eliminar(Integer id);
}
