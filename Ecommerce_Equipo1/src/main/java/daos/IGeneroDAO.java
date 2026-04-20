package daos;

import java.util.List;

import models.Genero;

public interface IGeneroDAO {
    void guardar(Genero genero);
    Genero buscarPorId(int id);
    List<Genero> listarTodos();
    void actualizar(Genero genero);
    void eliminar(int id);
}
