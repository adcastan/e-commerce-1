package api;

import DTOs.ReseniaDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.IReseniaService;
import service.NegocioException;
import service.ReseniaService;
import util.JSONMapper;
import util.RespuestaJSON;

@WebServlet(urlPatterns = {"/api/resenas", "/api/resenas/*"})
public class ResenasApiServlet extends HttpServlet {

    private final IReseniaService reseniaService = new ReseniaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, reseniaService.listar());
                return;
            }
            String[] partes = pathInfo.substring(1).split("/");
            if (partes.length == 2 && "producto".equals(partes[0])) {
                Integer idLibro = Integer.valueOf(partes[1]);
                RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK,
                        reseniaService.listarPorLibro(idLibro));
                return;
            }
            if (partes.length == 2 && "cliente".equals(partes[0])) {
                Integer idCliente = Integer.valueOf(partes[1]);
                RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK,
                        reseniaService.listarPorCliente(idCliente));
                return;
            }
            RespuestaJSON.error(resp, HttpServletResponse.SC_NOT_FOUND, "ruta no encontrada");
        } catch (NumberFormatException e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, "id inv\u00e1lido");
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Integer idCliente = (Integer) req.getAttribute("idCliente");
            ReseniaDTO datos = JSONMapper.mapper.readValue(req.getInputStream(), ReseniaDTO.class);
            ReseniaDTO creada = reseniaService.crear(idCliente, datos);
            RespuestaJSON.enviar(resp, HttpServletResponse.SC_CREATED, creada);
        } catch (NegocioException e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
