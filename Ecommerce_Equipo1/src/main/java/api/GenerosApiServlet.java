package api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.GeneroService;
import service.IGeneroService;
import util.RespuestaJSON;

@WebServlet(urlPatterns = {"/api/generos"})
public class GenerosApiServlet extends HttpServlet {

    private final IGeneroService generoService = new GeneroService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, generoService.listar());
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
