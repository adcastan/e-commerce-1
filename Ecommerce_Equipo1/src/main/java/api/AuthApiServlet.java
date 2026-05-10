package api;

import DTOs.LoginDTO;
import DTOs.RegistroClienteDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import service.ClienteService;
import service.IClienteService;
import service.NegocioException;
import util.JSONMapper;
import util.RespuestaJSON;

@WebServlet(urlPatterns = {"/api/auth/*"})
public class AuthApiServlet extends HttpServlet {

    private final IClienteService clienteService = new ClienteService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_NOT_FOUND, "ruta no encontrada");
            return;
        }
        try {
            switch (path) {
                case "/login":
                    login(req, resp);
                    break;
                case "/admin-login":
                    loginAdmin(req, resp);
                    break;
                case "/registro":
                    registro(req, resp);
                    break;
                default:
                    RespuestaJSON.error(resp, HttpServletResponse.SC_NOT_FOUND, "ruta no encontrada");
            }
        } catch (NegocioException e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error interno: " + e.getMessage());
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LoginDTO datos = JSONMapper.mapper.readValue(req.getInputStream(), LoginDTO.class);
        String token = clienteService.autenticar(datos);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("token", token);
        respuesta.put("cliente", clienteService.buscarPorId(
                util.JWTUtil.obtenerIdClienteDeToken(token)));
        RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, respuesta);
    }

    private void loginAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LoginDTO datos = JSONMapper.mapper.readValue(req.getInputStream(), LoginDTO.class);
        String token = clienteService.autenticarAdmin(datos);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("token", token);
        RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, respuesta);
    }

    private void registro(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RegistroClienteDTO datos = JSONMapper.mapper.readValue(req.getInputStream(), RegistroClienteDTO.class);
        var creado = clienteService.registrar(datos);
        RespuestaJSON.enviar(resp, HttpServletResponse.SC_CREATED, creado);
    }
}
