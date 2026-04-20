package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Proveedor;
import service.IProveedorService;
import service.ProveedorService;

@WebServlet(name = "AdminProveedorServlet", urlPatterns = {"/admin/proveedores"})
public class AdminProveedorServlet extends HttpServlet {

    private IProveedorService proveedorService;

    @Override
    public void init() {
        proveedorService = new ProveedorService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");
        
        if ("nuevo".equals(accion)) {
            request.getRequestDispatcher("/admin/FormularioProveedor.jsp").forward(request, response);
            return;
        }

        if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Proveedor proveedor = proveedorService.obtenerProveedorPorId(id);
            request.setAttribute("proveedor", proveedor);
            request.getRequestDispatcher("/admin/FormularioProveedor.jsp").forward(request, response);
            return;
        }

        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            proveedorService.eliminarProveedor(id);
            response.sendRedirect(request.getContextPath() + "/admin/proveedores?msg=eliminado");
            return;
        }

        List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
        request.setAttribute("proveedores", proveedores);
        request.getRequestDispatcher("/admin/ListaProveedores.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idParam   = request.getParameter("idProveedor");
        String nombre    = request.getParameter("nombre");
        String correo    = request.getParameter("correo");
        String telefono  = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        try {
            if (idParam != null && !idParam.isBlank()) {
                //edicion
                Proveedor prov = proveedorService.obtenerProveedorPorId(Integer.parseInt(idParam));
                prov.setNombre(nombre);
                prov.setCorreo(correo);
                prov.setTelefono(telefono);
                prov.setDireccion(direccion);
                proveedorService.actualizarProveedor(prov);
                response.sendRedirect(request.getContextPath() + "/admin/proveedores?msg=actualizado");
            } else {
                //nuevo
                Proveedor prov = new Proveedor(nombre, correo, telefono, direccion);
                proveedorService.agregarProveedor(prov);
                response.sendRedirect(request.getContextPath() + "/admin/proveedores?msg=guardado");
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/admin/FormularioProveedor.jsp").forward(request, response);
        }
    }
}
