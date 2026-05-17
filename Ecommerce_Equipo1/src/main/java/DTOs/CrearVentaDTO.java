package DTOs;

import java.util.List;

public class CrearVentaDTO {

    private Integer idCliente;
    private String direccionEnvio;
    private Integer idPago;
    private List<ItemCarritoDTO> items;
    private DatosPagoDTO datosPago;

    public CrearVentaDTO() {
    }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getDireccionEnvio() { return direccionEnvio; }
    public void setDireccionEnvio(String direccionEnvio) { this.direccionEnvio = direccionEnvio; }

    public Integer getIdPago() { return idPago; }
    public void setIdPago(Integer idPago) { this.idPago = idPago; }

    public List<ItemCarritoDTO> getItems() { return items; }
    public void setItems(List<ItemCarritoDTO> items) { this.items = items; }

    public DatosPagoDTO getDatosPago() { return datosPago; }
    public void setDatosPago(DatosPagoDTO datosPago) { this.datosPago = datosPago; }
}
