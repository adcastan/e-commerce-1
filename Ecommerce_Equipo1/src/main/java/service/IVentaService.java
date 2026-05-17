package service;

import DTOs.CrearVentaDTO;
import DTOs.VentaDTO;
import java.util.List;
import models.EstadoVenta;

public interface IVentaService {

    VentaDTO crearPedido(CrearVentaDTO datos);

    VentaDTO buscarPorId(Integer id);

    List<VentaDTO> listar();

    List<VentaDTO> listarPorCliente(Integer idCliente);

    VentaDTO actualizarEstado(Integer idVenta, EstadoVenta nuevoEstado);
}
