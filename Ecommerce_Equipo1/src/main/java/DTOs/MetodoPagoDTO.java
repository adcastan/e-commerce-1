package DTOs;

public class MetodoPagoDTO {

    private Integer idPago;
    private String tipo;

    public MetodoPagoDTO() {
    }

    public MetodoPagoDTO(Integer idPago, String tipo) {
        this.idPago = idPago;
        this.tipo = tipo;
    }

    public Integer getIdPago() { return idPago; }
    public void setIdPago(Integer idPago) { this.idPago = idPago; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
