package models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Libro {

    public Libro() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLibro;

    String ISBN;
    String titulo;
    String autor;
    double precio;
    int stock;
    Date anioPublicacion;

    @ManyToOne
    @JoinColumn(name = "idGenero")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;

    private String imagen; // URL o nombre del archivo de imagen

    public Libro(String ISBN, String titulo, String autor, double precio, int stock, Date anioPublicacion, Genero genero, Proveedor proveedor, String imagen) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.proveedor = proveedor;
        this.imagen = imagen;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Date anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) {
        this.idGenero = idGenero;
    }

    @Override
    public String toString() {
        return "Libro{" + "idLibro=" + idLibro + ", ISBN=" + ISBN + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + ", stock=" + stock + ", anioPublicacion=" + anioPublicacion + ", idGenero=" + idGenero + '}';
    }
}
