package DTOs;

/**
 *
 * @author Dana Rios
 */
public class AdministradorDTO {

    public AdministradorDTO() {

    }

    public AdministradorDTO(int idAdministrador, String nombreAdministrador, String correoElectronico, String contrasenia) {
        this.idAdministrador = idAdministrador;
        this.nombreAdministrador = nombreAdministrador;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }

    int idAdministrador;
    String nombreAdministrador;
    String correoElectronico;
    String contrasenia;

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Administrador{" + "idAdministrador=" + idAdministrador + ", nombreAdministrador=" + nombreAdministrador + ", correoElectronico=" + correoElectronico + ", contrasenia=" + contrasenia + '}';
    }

}