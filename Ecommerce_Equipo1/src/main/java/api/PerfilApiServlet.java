package api;

import DTOs.ClienteDTO;
import DTOs.EditarPerfilDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.ClienteService;
import service.IClienteService;
import service.NegocioException;
import util.JSONMapper;
import util.RespuestaJSON;

@WebServlet(urlPatterns = {"/api/perfil"})
public class PerfilApiServlet extends HttpServlet {

    private final IClienteService clienteService = new ClienteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Integer idCliente = (Integer) req.getAttribute("idCliente");
            ClienteDTO cliente = clienteService.buscarPorId(idCliente);
            if (cliente == null) {
                RespuestaJSON.error(resp, HttpServletResponse.SC_NOT_FOUND, "cliente no encontrado");
                return;
            }
            RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, cliente);
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Integer idCliente = (Integer) req.getAttribute("idCliente");
            EditarPerfilDTO datos = JSONMapper.mapper.readValue(req.getInputStream(), EditarPerfilDTO.class);
            ClienteDTO actualizado = clienteService.actualizarPerfil(idCliente, datos);
            RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, actualizado);
        } catch (NegocioException e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
