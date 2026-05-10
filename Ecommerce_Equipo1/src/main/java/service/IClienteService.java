package service;

import DTOs.ClienteDTO;
import DTOs.EditarPerfilDTO;
import DTOs.LoginDTO;
import DTOs.RegistroClienteDTO;
import java.util.List;

public interface IClienteService {

    ClienteDTO registrar(RegistroClienteDTO datos);

    String autenticar(LoginDTO login);

    String autenticarAdmin(LoginDTO login);

    ClienteDTO buscarPorId(Integer id);

    List<ClienteDTO> listar();

    ClienteDTO actualizarPerfil(Integer id, EditarPerfilDTO datos);

    ClienteDTO cambiarEstado(Integer id, boolean activo);

    boolean eliminar(Integer id);
}
