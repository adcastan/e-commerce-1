package daos;

import java.util.ArrayList;
import java.util.List;
import models.Administrador;

/**
 *
 * @author Adrián
 */
public class AdministradorDAO implements IAdministradorDAO {

    // Nuestra "Base de Datos" simulada en memoria
    private static final List<Administrador> administradoresMock = new ArrayList<>();

    // Este bloque estático inicializa a los 4 administradores la primera vez que se usa la clase
    static {
        administradoresMock.add(new Administrador(1, "Admin Uno", "admin1@tienda.com", "admin123"));
        administradoresMock.add(new Administrador(2, "Admin Dos", "admin2@tienda.com", "admin456"));
        administradoresMock.add(new Administrador(3, "Admin Tres", "admin3@tienda.com", "admin789"));
        administradoresMock.add(new Administrador(4, "Admin Cuatro", "admin4@tienda.com", "admin000"));
    }

    @Override
    public void guardar(Administrador administrador) {
        throw new UnsupportedOperationException("ACCESO DENEGADO: Solo se permiten los 4 administradores mockeados."); 
    }

    @Override
    public Administrador crearAdministrador(Administrador administrador) {
        throw new UnsupportedOperationException("ACCESO DENEGADO: Creación deshabilitada."); 
    }

    @Override
    public Administrador buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void actualizar(Administrador administrador) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    // --- MÉTODO DE LOGIN MOCKEADOS ---
    @Override
    public Administrador iniciarSesion(String correo, String contrasenia) {
        
        // Recorremos nuestra lista en memoria para buscar coincidencias
        for (Administrador admin : administradoresMock) {
            
            // Si el correo y la contraseña coinciden exactamente con uno de la lista
            if (admin.getCorreoElectronico().equals(correo) && 
                admin.getContrasenia().equals(contrasenia)) {
                
                return admin; // Retornamos el objeto encontrado (Login exitoso)
            }
        }
        
        // Si termina el ciclo y no encontró nada, retorna null (Login fallido)
        return null;
    }
}