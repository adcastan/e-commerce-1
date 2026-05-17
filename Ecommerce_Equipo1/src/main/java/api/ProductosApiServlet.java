package api;

import DTOs.LibroDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.ILibroService;
import service.LibroService;
import service.NegocioException;
import util.RespuestaJSON;

@WebServlet(urlPatterns = {"/api/productos", "/api/productos/*"})
public class ProductosApiServlet extends HttpServlet {

    private final ILibroService libroService = new LibroService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();

            if (pathInfo == null || pathInfo.equals("/")) {
                String texto = req.getParameter("q");
                Integer idGenero = parsearInt(req.getParameter("genero"));
                Double precioMin = parsearDouble(req.getParameter("precioMin"));
                Double precioMax = parsearDouble(req.getParameter("precioMax"));
                String destacados = req.getParameter("destacados");

                if ("true".equalsIgnoreCase(destacados)) {
                    int limite = 8;
                    String l = req.getParameter("limite");
                    if (l != null) {
                        try { limite = Integer.parseInt(l); } catch (NumberFormatException ignored) {}
                    }
                    RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK,
                            libroService.listarDestacados(limite));
                    return;
                }

                if (texto == null && idGenero == null && precioMin == null && precioMax == null) {
                    RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, libroService.listar());
                    return;
                }

                RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK,
                        libroService.filtrar(texto, idGenero, precioMin, precioMax));
                return;
            }

            String idStr = pathInfo.substring(1);
            Integer id;
            try {
                id = Integer.valueOf(idStr);
            } catch (NumberFormatException e) {
                RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, "id inv\u00e1lido");
                return;
            }

            LibroDTO libro = libroService.buscarPorIdConDetalles(id);
            if (libro == null) {
                RespuestaJSON.error(resp, HttpServletResponse.SC_NOT_FOUND, "libro no encontrado");
                return;
            }
            RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, libro);
        } catch (NegocioException e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error: " + e.getMessage());
        }
    }

    private Integer parsearInt(String s) {
        if (s == null || s.isBlank()) return null;
        try { return Integer.valueOf(s); } catch (NumberFormatException e) { return null; }
    }

    private Double parsearDouble(String s) {
        if (s == null || s.isBlank()) return null;
        try { return Double.valueOf(s); } catch (NumberFormatException e) { return null; }
    }
}
