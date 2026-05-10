package daos;

import java.util.List;
import models.Genero;

public interface IGeneroDAO {

    Genero guardar(Genero genero);

    Genero buscarPorId(Integer id);

    Genero buscarPorNombre(String nombre);

    List<Genero> listar();

    Genero actualizar(Genero genero);

    boolean eliminar(Integer id);
}
