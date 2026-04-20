package service;

import java.util.List;
import models.Genero;

public interface IGeneroService {
    void agregarGenero(Genero genero);
    Genero obtenerGeneroPorId(int id);
    List<Genero> obtenerTodosLosGeneros();
    void actualizarGenero(Genero genero);
    void eliminarGenero(int id);
}
