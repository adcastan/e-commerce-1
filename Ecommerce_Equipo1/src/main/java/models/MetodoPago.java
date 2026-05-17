package models;

import jakarta.persistence.*;

@Entity
@Table(name = "metodos_pago")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    @Column(name = "tipo", nullable = false, length = 40, unique = true)
    private String tipo;

    public MetodoPago() {
    }

    public MetodoPago(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdPago() { return idPago; }
    public void setIdPago(Integer idPago) { this.idPago = idPago; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
