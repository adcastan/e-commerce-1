package service;

import DTOs.CrearVentaDTO;
import DTOs.ItemCarritoDTO;
import DTOs.VentaDTO;
import daos.ClienteDAO;
import daos.IClienteDAO;
import daos.ILibroDAO;
import daos.IMetodoPagoDAO;
import daos.IVentaDAO;
import daos.LibroDAO;
import daos.MetodoPagoDAO;
import daos.VentaDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import models.Cliente;
import models.DetalleVenta;
import models.EstadoVenta;
import models.Libro;
import models.MetodoPago;
import models.Venta;
import util.Mappers;
import util.PagoSimulador;

public class VentaService implements IVentaService {

    private final IVentaDAO ventaDAO;
    private final IClienteDAO clienteDAO;
    private final ILibroDAO libroDAO;
    private final IMetodoPagoDAO metodoPagoDAO;

    public VentaService() {
        this.ventaDAO = new VentaDAO();
        this.clienteDAO = new ClienteDAO();
        this.libroDAO = new LibroDAO();
        this.metodoPagoDAO = new MetodoPagoDAO();
    }

    @Override
    public VentaDTO crearPedido(CrearVentaDTO datos) {
        if (datos == null) throw new NegocioException("datos del pedido vac\u00edos");
        if (datos.getIdCliente() == null) throw new NegocioException("id de cliente requerido");
        if (datos.getIdPago() == null) throw new NegocioException("m\u00e9todo de pago requerido");
        if (datos.getDireccionEnvio() == null || datos.getDireccionEnvio().isBlank()) {
            throw new NegocioException("direcci\u00f3n de env\u00edo requerida");
        }
        if (datos.getItems() == null || datos.getItems().isEmpty()) {
            throw new NegocioException("el carrito est\u00e1 vac\u00edo");
        }

        Cliente cliente = clienteDAO.buscarPorId(datos.getIdCliente());
        if (cliente == null || !cliente.isActivo()) {
            throw new NegocioException("cliente no v\u00e1lido");
        }

        MetodoPago metodoPago = metodoPagoDAO.buscarPorId(datos.getIdPago());
        if (metodoPago == null) {
            throw new NegocioException("m\u00e9todo de pago no v\u00e1lido");
        }

        List<DetalleVenta> detalles = new ArrayList<>();
        double total = 0.0;
        for (ItemCarritoDTO item : datos.getItems()) {
            if (item.getIdLibro() == null || item.getCantidad() <= 0) {
                throw new NegocioException("item de carrito inv\u00e1lido");
            }
            Libro libro = libroDAO.buscarPorId(item.getIdLibro());
            if (libro == null || !libro.isActivo()) {
                throw new NegocioException("libro no disponible: id " + item.getIdLibro());
            }
            if (libro.getStock() < item.getCantidad()) {
                throw new NegocioException("stock insuficiente para: " + libro.getTitulo());
            }
            DetalleVenta dv = new DetalleVenta(null, libro, item.getCantidad(), libro.getPrecio());
            detalles.add(dv);
            total += dv.getSubTotal();
        }

        PagoSimulador.ResultadoPago resultado = PagoSimulador.procesar(metodoPago.getTipo(), datos.getDatosPago());
        if (!resultado.aprobado) {
            throw new NegocioException("pago rechazado: " + resultado.mensaje);
        }

        Venta venta = new Venta();
        venta.setNumeroPedido(generarNumeroPedido());
        venta.setFechaHora(LocalDateTime.now());
        venta.setTotal(total);
        venta.setDireccionEnvio(datos.getDireccionEnvio().trim());
        venta.setEstado(EstadoVenta.PENDIENTE);
        venta.setCliente(cliente);
        venta.setMetodoPago(metodoPago);

        for (DetalleVenta dv : detalles) {
            dv.setVenta(venta);
            venta.getDetalles().add(dv);
        }

        for (DetalleVenta dv : detalles) {
            libroDAO.descontarStock(dv.getLibro().getIdLibro(), dv.getCantidad());
        }

        Venta guardada = ventaDAO.guardar(venta);
        return Mappers.toVentaDTO(guardada);
    }

    @Override
    public VentaDTO buscarPorId(Integer id) {
        Venta v = ventaDAO.buscarPorId(id);
        return Mappers.toVentaDTO(v);
    }

    @Override
    public List<VentaDTO> listar() {
        return ventaDAO.listar().stream()
                .map(Mappers::toVentaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaDTO> listarPorCliente(Integer idCliente) {
        return ventaDAO.listarPorCliente(idCliente).stream()
                .map(Mappers::toVentaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VentaDTO actualizarEstado(Integer idVenta, EstadoVenta nuevoEstado) {
        Venta v = ventaDAO.actualizarEstado(idVenta, nuevoEstado);
        if (v == null) throw new NegocioException("venta no encontrada");
        return Mappers.toVentaDTO(v);
    }

    private String generarNumeroPedido() {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String aleatorio = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "PED-" + fecha + "-" + aleatorio;
    }
}
