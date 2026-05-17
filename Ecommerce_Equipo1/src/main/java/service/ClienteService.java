package service;

import DTOs.ClienteDTO;
import DTOs.EditarPerfilDTO;
import DTOs.LoginDTO;
import DTOs.RegistroClienteDTO;
import daos.ClienteDAO;
import daos.IClienteDAO;
import java.util.List;
import java.util.stream.Collectors;
import models.Cliente;
import models.Rol;
import util.JWTUtil;
import util.Mappers;
import util.PasswordUtil;

public class ClienteService implements IClienteService {

    private final IClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    public ClienteService(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public ClienteDTO registrar(RegistroClienteDTO datos) {
        validarRegistro(datos);
        if (clienteDAO.buscarPorCorreo(datos.getCorreo()) != null) {
            throw new NegocioException("ya existe una cuenta con ese correo");
        }
        Cliente nuevo = new Cliente(
                datos.getNombre(),
                datos.getApellido(),
                datos.getCorreo(),
                PasswordUtil.hash(datos.getContrasenia()),
                datos.getTelefono(),
                datos.getDireccion(),
                Rol.CLIENTE
        );
        Cliente guardado = clienteDAO.guardar(nuevo);
        return Mappers.toClienteDTO(guardado);
    }

    @Override
    public String autenticar(LoginDTO login) {
        Cliente c = autenticarBase(login);
        if (c.getRol() != Rol.CLIENTE) {
            throw new NegocioException("este usuario debe iniciar sesi\u00f3n por el panel administrativo");
        }
        return JWTUtil.generarToken(c.getIdCliente(), c.getCorreo(), c.getRol().name());
    }

    @Override
    public String autenticarAdmin(LoginDTO login) {
        Cliente c = autenticarBase(login);
        if (c.getRol() != Rol.ADMIN) {
            throw new NegocioException("acceso denegado, no es administrador");
        }
        return JWTUtil.generarToken(c.getIdCliente(), c.getCorreo(), c.getRol().name());
    }

    private Cliente autenticarBase(LoginDTO login) {
        if (login == null || login.getCorreo() == null || login.getContrasenia() == null) {
            throw new NegocioException("correo y contrase\u00f1a son obligatorios");
        }
        Cliente c = clienteDAO.buscarPorCorreo(login.getCorreo().trim());
        if (c == null || !PasswordUtil.verificar(login.getContrasenia(), c.getContrasenia())) {
            throw new NegocioException("credenciales inv\u00e1lidas");
        }
        if (!c.isActivo()) {
            throw new NegocioException("la cuenta est\u00e1 desactivada");
        }
        return c;
    }

    @Override
    public ClienteDTO buscarPorId(Integer id) {
        Cliente c = clienteDAO.buscarPorId(id);
        return Mappers.toClienteDTO(c);
    }

    @Override
    public List<ClienteDTO> listar() {
        return clienteDAO.listar().stream()
                .map(Mappers::toClienteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO actualizarPerfil(Integer id, EditarPerfilDTO datos) {
        Cliente c = clienteDAO.buscarPorId(id);
        if (c == null) {
            throw new NegocioException("cliente no encontrado");
        }
        if (datos.getNombre() != null && !datos.getNombre().isBlank()) c.setNombre(datos.getNombre().trim());
        if (datos.getApellido() != null && !datos.getApellido().isBlank()) c.setApellido(datos.getApellido().trim());
        if (datos.getTelefono() != null) c.setTelefono(datos.getTelefono().trim());
        if (datos.getDireccion() != null) c.setDireccion(datos.getDireccion().trim());

        if (datos.getCorreo() != null && !datos.getCorreo().equalsIgnoreCase(c.getCorreo())) {
            if (clienteDAO.buscarPorCorreo(datos.getCorreo()) != null) {
                throw new NegocioException("ese correo ya est\u00e1 registrado");
            }
            c.setCorreo(datos.getCorreo().trim());
        }

        if (datos.getContraseniaNueva() != null && !datos.getContraseniaNueva().isBlank()) {
            if (datos.getContraseniaActual() == null
                    || !PasswordUtil.verificar(datos.getContraseniaActual(), c.getContrasenia())) {
                throw new NegocioException("la contrase\u00f1a actual es incorrecta");
            }
            if (datos.getContraseniaNueva().length() < 6) {
                throw new NegocioException("la nueva contrase\u00f1a debe tener al menos 6 caracteres");
            }
            c.setContrasenia(PasswordUtil.hash(datos.getContraseniaNueva()));
        }

        Cliente actualizado = clienteDAO.actualizar(c);
        return Mappers.toClienteDTO(actualizado);
    }

    @Override
    public ClienteDTO cambiarEstado(Integer id, boolean activo) {
        Cliente c = clienteDAO.buscarPorId(id);
        if (c == null) {
            throw new NegocioException("cliente no encontrado");
        }
        c.setActivo(activo);
        return Mappers.toClienteDTO(clienteDAO.actualizar(c));
    }

    @Override
    public boolean eliminar(Integer id) {
        return clienteDAO.eliminar(id);
    }

    private void validarRegistro(RegistroClienteDTO datos) {
        if (datos == null) throw new NegocioException("datos de registro vac\u00edos");
        if (esVacio(datos.getNombre())) throw new NegocioException("el nombre es obligatorio");
        if (esVacio(datos.getApellido())) throw new NegocioException("el apellido es obligatorio");
        if (esVacio(datos.getCorreo()) || !datos.getCorreo().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new NegocioException("correo inv\u00e1lido");
        }
        if (esVacio(datos.getContrasenia()) || datos.getContrasenia().length() < 6) {
            throw new NegocioException("la contrase\u00f1a debe tener al menos 6 caracteres");
        }
    }

    private boolean esVacio(String s) {
        return s == null || s.isBlank();
    }
}
