package daos;

import models.Administrador;

/**
 *
 * @author Dana Rios
 */
public interface IAdministradorDAO {
    
    void guardar(Administrador administrador);
    
    Administrador crearAdministrador(Administrador administrador);

    Administrador buscarPorId(int id);

    void actualizar(Administrador administrador);

    int eliminar(int id);
    
    // Nuestro nuevo método exclusivo para el login
    Administrador iniciarSesion(String correo, String contrasenia);
}