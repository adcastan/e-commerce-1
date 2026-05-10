package api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.IMetodoPagoService;
import service.MetodoPagoService;
import util.RespuestaJSON;

@WebServlet(urlPatterns = {"/api/metodos-pago"})
public class MetodosPagoApiServlet extends HttpServlet {

    private final IMetodoPagoService metodoPagoService = new MetodoPagoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, metodoPagoService.listar());
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
