package util;

import DTOs.*;
import models.*;
import java.util.List;
import java.util.stream.Collectors;

public class Mappers {

    public static ClienteDTO toClienteDTO(Cliente c) {
        if (c == null) return null;
        return new ClienteDTO(
                c.getIdCliente(), c.getNombre(), c.getApellido(), c.getCorreo(),
                c.getTelefono(), c.getDireccion(), c.getRol().name(),
                c.isActivo(), c.getFechaRegistro()
        );
    }

    public static GeneroDTO toGeneroDTO(Genero g) {
        if (g == null) return null;
        return new GeneroDTO(g.getIdGenero(), g.getNombre());
    }

    public static LibroDTO toLibroDTO(Libro l) {
        if (l == null) return null;
        LibroDTO dto = new LibroDTO();
        dto.setIdLibro(l.getIdLibro());
        dto.setIsbn(l.getIsbn());
        dto.setTitulo(l.getTitulo());
        dto.setAutor(l.getAutor());
        dto.setDescripcion(l.getDescripcion());
        dto.setPrecio(l.getPrecio());
        dto.setStock(l.getStock());
        dto.setAnioPublicacion(l.getAnioPublicacion());
        dto.setEditorial(l.getEditorial());
        dto.setImagenUrl(l.getImagenUrl());
        dto.setDestacado(l.isDestacado());
        dto.setActivo(l.isActivo());
        if (l.getGenero() != null) {
            dto.setIdGenero(l.getGenero().getIdGenero());
            dto.setNombreGenero(l.getGenero().getNombre());
        }
        return dto;
    }

    public static MetodoPagoDTO toMetodoPagoDTO(MetodoPago m) {
        if (m == null) return null;
        return new MetodoPagoDTO(m.getIdPago(), m.getTipo());
    }

    public static ReseniaDTO toReseniaDTO(Resenia r) {
        if (r == null) return null;
        ReseniaDTO dto = new ReseniaDTO();
        dto.setIdResenia(r.getIdResenia());
        dto.setFechaHora(r.getFechaHora());
        dto.setCalificacion(r.getCalificacion());
        dto.setComentario(r.getComentario());
        if (r.getCliente() != null) {
            dto.setIdCliente(r.getCliente().getIdCliente());
            dto.setNombreCliente(r.getCliente().getNombre() + " " + r.getCliente().getApellido());
        }
        if (r.getLibro() != null) {
            dto.setIdLibro(r.getLibro().getIdLibro());
            dto.setTituloLibro(r.getLibro().getTitulo());
        }
        return dto;
    }

    public static DetalleVentaDTO toDetalleVentaDTO(DetalleVenta d) {
        if (d == null) return null;
        DetalleVentaDTO dto = new DetalleVentaDTO();
        dto.setIdDetalleVenta(d.getIdDetalleVenta());
        dto.setCantidad(d.getCantidad());
        dto.setPrecioUnitario(d.getPrecioUnitario());
        dto.setSubTotal(d.getSubTotal());
        if (d.getLibro() != null) {
            dto.setIdLibro(d.getLibro().getIdLibro());
            dto.setTituloLibro(d.getLibro().getTitulo());
            dto.setAutorLibro(d.getLibro().getAutor());
            dto.setImagenUrl(d.getLibro().getImagenUrl());
        }
        return dto;
    }

    public static VentaDTO toVentaDTO(Venta v) {
        if (v == null) return null;
        VentaDTO dto = new VentaDTO();
        dto.setIdVenta(v.getIdVenta());
        dto.setNumeroPedido(v.getNumeroPedido());
        dto.setFechaHora(v.getFechaHora());
        dto.setTotal(v.getTotal());
        dto.setDireccionEnvio(v.getDireccionEnvio());
        dto.setEstado(v.getEstado().name());
        if (v.getCliente() != null) {
            dto.setIdCliente(v.getCliente().getIdCliente());
            dto.setNombreCliente(v.getCliente().getNombre() + " " + v.getCliente().getApellido());
        }
        if (v.getMetodoPago() != null) {
            dto.setIdPago(v.getMetodoPago().getIdPago());
            dto.setTipoPago(v.getMetodoPago().getTipo());
        }
        List<DetalleVentaDTO> detalles = v.getDetalles().stream()
                .map(Mappers::toDetalleVentaDTO)
                .collect(Collectors.toList());
        dto.setDetalles(detalles);
        return dto;
    }
}
