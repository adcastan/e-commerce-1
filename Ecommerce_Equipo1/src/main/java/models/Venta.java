package models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenta;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    private int total;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "idPago")
    private MetodoPago idPago;

    @OneToMany(mappedBy = "idVenta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    public Venta() {
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public MetodoPago getIdPago() {
        return idPago;
    }

    public void setIdPago(MetodoPago idPago) {
        this.idPago = idPago;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", fechaHora=" + fechaHora + ", total=" + total + ", estado=" + estado + ", idCliente=" + idCliente + ", idPago=" + idPago + '}';
    }
}
