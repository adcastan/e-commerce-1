package DTOs;

public class ItemCarritoDTO {

    private Integer idLibro;
    private int cantidad;

    public ItemCarritoDTO() {
    }

    public ItemCarritoDTO(Integer idLibro, int cantidad) {
        this.idLibro = idLibro;
        this.cantidad = cantidad;
    }

    public Integer getIdLibro() { return idLibro; }
    public void setIdLibro(Integer idLibro) { this.idLibro = idLibro; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
