package service;

import java.util.List;
import models.Proveedor;

public interface IProveedorService {
    void agregarProveedor(Proveedor proveedor);
    Proveedor obtenerProveedorPorId(int id);
    List<Proveedor> obtenerTodosLosProveedores();
    void actualizarProveedor(Proveedor proveedor);
    void eliminarProveedor(int id);
}
