package api;

import DTOs.CrearVentaDTO;
import DTOs.VentaDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.EstadoVenta;
import service.IVentaService;
import service.NegocioException;
import service.VentaService;
import util.JSONMapper;
import util.RespuestaJSON;

@WebServlet(urlPatterns = {"/api/pedidos", "/api/pedidos/*"})
public class PedidosApiServlet extends HttpServlet {

    private final IVentaService ventaService = new VentaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            Integer idAutenticado = (Integer) req.getAttribute("idCliente");
            String rol = (String) req.getAttribute("rol");

            if (pathInfo == null || pathInfo.equals("/")) {
                if (!"ADMIN".equals(rol)) {
                    RespuestaJSON.error(resp, HttpServletResponse.SC_FORBIDDEN, "acceso denegado");
                    return;
                }
                RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, ventaService.listar());
                return;
            }

            String[] partes = pathInfo.substring(1).split("/");

            if (partes.length == 2 && "usuario".equals(partes[0])) {
                Integer idCliente = Integer.valueOf(partes[1]);
                if (!"ADMIN".equals(rol) && !idCliente.equals(idAutenticado)) {
                    RespuestaJSON.error(resp, HttpServletResponse.SC_FORBIDDEN, "acceso denegado");
                    return;
                }
                RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK,
                        ventaService.listarPorCliente(idCliente));
                return;
            }

            if (partes.length == 1) {
                Integer id = Integer.valueOf(partes[0]);
                VentaDTO venta = ventaService.buscarPorId(id);
                if (venta == null) {
                    RespuestaJSON.error(resp, HttpServletResponse.SC_NOT_FOUND, "pedido no encontrado");
                    return;
                }
                if (!"ADMIN".equals(rol) && !venta.getIdCliente().equals(idAutenticado)) {
                    RespuestaJSON.error(resp, HttpServletResponse.SC_FORBIDDEN, "acceso denegado");
                    return;
                }
                RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, venta);
                return;
            }
            RespuestaJSON.error(resp, HttpServletResponse.SC_NOT_FOUND, "ruta no encontrada");
        } catch (NumberFormatException e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, "id inv\u00e1lido");
        } catch (NegocioException e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            CrearVentaDTO datos = JSONMapper.mapper.readValue(req.getInputStream(), CrearVentaDTO.class);
            Integer idAutenticado = (Integer) req.getAttribute("idCliente");
            datos.setIdCliente(idAutenticado);

            VentaDTO venta = ventaService.crearPedido(datos);
            RespuestaJSON.enviar(resp, HttpServletResponse.SC_CREATED, venta);
        } catch (NegocioException e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String rol = (String) req.getAttribute("rol");
            if (!"ADMIN".equals(rol)) {
                RespuestaJSON.error(resp, HttpServletResponse.SC_FORBIDDEN, "acceso denegado");
                return;
            }
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.length() < 2) {
                RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, "id requerido");
                return;
            }
            String[] partes = pathInfo.substring(1).split("/");
            if (partes.length != 2 || !"estado".equals(partes[1])) {
                RespuestaJSON.error(resp, HttpServletResponse.SC_NOT_FOUND, "ruta no encontrada");
                return;
            }
            Integer id = Integer.valueOf(partes[0]);
            CambioEstadoBody body = JSONMapper.mapper.readValue(req.getInputStream(), CambioEstadoBody.class);
            EstadoVenta nuevo = EstadoVenta.valueOf(body.estado.toUpperCase());
            VentaDTO actualizado = ventaService.actualizarEstado(id, nuevo);
            RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, actualizado);
        } catch (IllegalArgumentException e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, "estado inv\u00e1lido");
        } catch (NegocioException e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public static class CambioEstadoBody {
        public String estado;
    }
}
