package models;

import jakarta.persistence.*;

@Entity
public class Genero {

    public Genero() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenero;

    private String nombreGenero;

    public Genero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public int getIdGenero() { return idGenero; }
    public void setIdGenero(int idGenero) { this.idGenero = idGenero; }

    public String getNombreGenero() { return nombreGenero; }
    public void setNombreGenero(String nombreGenero) { this.nombreGenero = nombreGenero; }

    @Override
    public String toString() {
        return "Genero{idGenero=" + idGenero + ", nombreGenero=" + nombreGenero + "}";
    }
}
