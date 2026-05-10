package DTOs;

public class EditarPerfilDTO {

    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;
    private String contraseniaActual;
    private String contraseniaNueva;

    public EditarPerfilDTO() {
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getContraseniaActual() { return contraseniaActual; }
    public void setContraseniaActual(String contraseniaActual) { this.contraseniaActual = contraseniaActual; }

    public String getContraseniaNueva() { return contraseniaNueva; }
    public void setContraseniaNueva(String contraseniaNueva) { this.contraseniaNueva = contraseniaNueva; }
}
