package service;

import DTOs.ReseniaDTO;
import daos.ClienteDAO;
import daos.IClienteDAO;
import daos.ILibroDAO;
import daos.IReseniaDAO;
import daos.LibroDAO;
import daos.ReseniaDAO;
import java.util.List;
import java.util.stream.Collectors;
import models.Cliente;
import models.Libro;
import models.Resenia;
import util.Mappers;

public class ReseniaService implements IReseniaService {

    private final IReseniaDAO reseniaDAO;
    private final IClienteDAO clienteDAO;
    private final ILibroDAO libroDAO;

    public ReseniaService() {
        this.reseniaDAO = new ReseniaDAO();
        this.clienteDAO = new ClienteDAO();
        this.libroDAO = new LibroDAO();
    }

    @Override
    public ReseniaDTO crear(Integer idCliente, ReseniaDTO datos) {
        if (datos == null || datos.getIdLibro() == null) {
            throw new NegocioException("se requiere id del libro");
        }
        if (datos.getCalificacion() < 1 || datos.getCalificacion() > 5) {
            throw new NegocioException("la calificaci\u00f3n debe ser entre 1 y 5");
        }
        Cliente c = clienteDAO.buscarPorId(idCliente);
        if (c == null) throw new NegocioException("cliente no encontrado");

        Libro l = libroDAO.buscarPorId(datos.getIdLibro());
        if (l == null) throw new NegocioException("libro no encontrado");

        Resenia r = new Resenia(datos.getCalificacion(), datos.getComentario(), c, l);
        return Mappers.toReseniaDTO(reseniaDAO.guardar(r));
    }

    @Override
    public List<ReseniaDTO> listar() {
        return reseniaDAO.listar().stream()
                .map(Mappers::toReseniaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReseniaDTO> listarPorLibro(Integer idLibro) {
        return reseniaDAO.listarPorLibro(idLibro).stream()
                .map(Mappers::toReseniaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReseniaDTO> listarPorCliente(Integer idCliente) {
        return reseniaDAO.listarPorCliente(idCliente).stream()
                .map(Mappers::toReseniaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean eliminar(Integer id) {
        return reseniaDAO.eliminar(id);
    }
}
