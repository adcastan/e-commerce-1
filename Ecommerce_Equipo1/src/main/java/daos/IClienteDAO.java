/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import java.util.List;
import models.Cliente;

/**
 *
 * @author Adrián
 */
public interface IClienteDAO {
     void guardar(Cliente cliente);

    Cliente buscarPorId(Long id);

    Cliente buscarPorCorreo(String correo);

    Cliente buscarPorCorreoYContrasenia(String correo, String contrasenia);

    Cliente buscarPorPseudonimo(String pseudonimo);

    List<Cliente> listarTodos();

    void actualizar(Cliente cliente);

    void eliminar(Long id);
    List<Cliente> listarTop(int limite);
    List<Cliente> listarPaginado(int pagina, int tamanioPagina);
    long contarUsuarios();
}
