package DTOs;

public class DetalleVentaDTO {

    private Integer idDetalleVenta;
    private Integer idLibro;
    private String tituloLibro;
    private String autorLibro;
    private String imagenUrl;
    private int cantidad;
    private double precioUnitario;
    private double subTotal;

    public DetalleVentaDTO() {
    }

    public Integer getIdDetalleVenta() { return idDetalleVenta; }
    public void setIdDetalleVenta(Integer idDetalleVenta) { this.idDetalleVenta = idDetalleVenta; }

    public Integer getIdLibro() { return idLibro; }
    public void setIdLibro(Integer idLibro) { this.idLibro = idLibro; }

    public String getTituloLibro() { return tituloLibro; }
    public void setTituloLibro(String tituloLibro) { this.tituloLibro = tituloLibro; }

    public String getAutorLibro() { return autorLibro; }
    public void setAutorLibro(String autorLibro) { this.autorLibro = autorLibro; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public double getSubTotal() { return subTotal; }
    public void setSubTotal(double subTotal) { this.subTotal = subTotal; }
}
