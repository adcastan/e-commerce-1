package DTOs;

import java.time.LocalDateTime;

public class ReseniaDTO {

    private Integer idResenia;
    private LocalDateTime fechaHora;
    private int calificacion;
    private String comentario;
    private Integer idCliente;
    private String nombreCliente;
    private Integer idLibro;
    private String tituloLibro;

    public ReseniaDTO() {
    }

    public Integer getIdResenia() { return idResenia; }
    public void setIdResenia(Integer idResenia) { this.idResenia = idResenia; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public int getCalificacion() { return calificacion; }
    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public Integer getIdLibro() { return idLibro; }
    public void setIdLibro(Integer idLibro) { this.idLibro = idLibro; }

    public String getTituloLibro() { return tituloLibro; }
    public void setTituloLibro(String tituloLibro) { this.tituloLibro = tituloLibro; }
}
