package api;

import DTOs.DetalleVentaDTO;
import DTOs.ItemCarritoDTO;
import DTOs.LibroDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.ILibroService;
import service.LibroService;
import util.JSONMapper;
import util.RespuestaJSON;

@WebServlet(urlPatterns = {"/api/carrito"})
public class CarritoApiServlet extends HttpServlet {

    private final ILibroService libroService = new LibroService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            CarritoBody body = JSONMapper.mapper.readValue(req.getInputStream(), CarritoBody.class);
            if (body == null || body.items == null) {
                RespuestaJSON.error(resp, HttpServletResponse.SC_BAD_REQUEST, "items requeridos");
                return;
            }

            List<DetalleVentaDTO> detalles = new ArrayList<>();
            double total = 0.0;
            int totalItems = 0;
            List<String> advertencias = new ArrayList<>();

            for (ItemCarritoDTO item : body.items) {
                if (item.getIdLibro() == null || item.getCantidad() <= 0) continue;
                LibroDTO libro = libroService.buscarPorId(item.getIdLibro());
                if (libro == null || !libro.isActivo()) {
                    advertencias.add("libro id " + item.getIdLibro() + " no disponible");
                    continue;
                }
                int cantidadFinal = item.getCantidad();
                if (cantidadFinal > libro.getStock()) {
                    advertencias.add("stock limitado para " + libro.getTitulo()
                            + " (disponible: " + libro.getStock() + ")");
                    cantidadFinal = libro.getStock();
                }
                if (cantidadFinal <= 0) continue;
                DetalleVentaDTO d = new DetalleVentaDTO();
                d.setIdLibro(libro.getIdLibro());
                d.setTituloLibro(libro.getTitulo());
                d.setAutorLibro(libro.getAutor());
                d.setImagenUrl(libro.getImagenUrl());
                d.setCantidad(cantidadFinal);
                d.setPrecioUnitario(libro.getPrecio());
                d.setSubTotal(libro.getPrecio() * cantidadFinal);
                detalles.add(d);
                total += d.getSubTotal();
                totalItems += cantidadFinal;
            }

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("items", detalles);
            respuesta.put("total", total);
            respuesta.put("totalItems", totalItems);
            respuesta.put("advertencias", advertencias);
            RespuestaJSON.enviar(resp, HttpServletResponse.SC_OK, respuesta);
        } catch (Exception e) {
            RespuestaJSON.error(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public static class CarritoBody {
        public List<ItemCarritoDTO> items;
    }
}
