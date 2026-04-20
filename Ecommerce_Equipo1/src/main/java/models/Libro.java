package models;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLibro;

    private String ISBN;
    private String titulo;
    private String autor;
    private int precio;
    private int stock;
    private int anioPublicacion;

    @ManyToOne
    @JoinColumn(name = "idGenero")
    private Genero idGenero;

    public Libro() {
    }

    public Libro(int idLibro, String ISBN, String titulo, String autor, int precio, int stock, int anioPublicacion, Genero idGenero) {
        this.idLibro = idLibro;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
        this.anioPublicacion = anioPublicacion;
        this.idGenero = idGenero;
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

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
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
