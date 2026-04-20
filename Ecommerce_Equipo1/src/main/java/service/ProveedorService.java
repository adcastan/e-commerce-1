package service;

import java.util.List;

import daos.IProveedorDAO;
import daos.ProveedorDAO;
import models.Proveedor;

public class ProveedorService implements IProveedorService {

    private final IProveedorDAO proveedorDAO;

    public ProveedorService() {
        this.proveedorDAO = new ProveedorDAO();
    }

    @Override
    public void agregarProveedor(Proveedor proveedor) {
        if (proveedor.getNombre() == null || proveedor.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del proveedor no puede estar vacío");
        }
        proveedorDAO.guardar(proveedor);
    }

    @Override
    public Proveedor obtenerProveedorPorId(int id) {
        Proveedor proveedor = proveedorDAO.buscarPorId(id);
        if (proveedor == null) throw new IllegalArgumentException("No se encontró el proveedor con id: " + id);
        return proveedor;
    }

    @Override
    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorDAO.listarTodos();
    }

    @Override
    public void actualizarProveedor(Proveedor proveedor) {
        if (proveedor.getIdProveedor() <= 0) throw new IllegalArgumentException("ID de proveedor inválido");
        proveedorDAO.actualizar(proveedor);
    }

    @Override
    public void eliminarProveedor(int id) {
        proveedorDAO.eliminar(id);
    }
}
