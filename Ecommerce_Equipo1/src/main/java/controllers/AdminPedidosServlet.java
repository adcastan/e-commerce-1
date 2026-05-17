package controllers;

import DTOs.VentaDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.EstadoVenta;
import service.IVentaService;
import service.VentaService;

@WebServlet(urlPatterns = {"/admin/pedidos", "/admin/pedidos/*"})
public class AdminPedidosServlet extends HttpServlet {

    private final IVentaService ventaService = new VentaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            req.setAttribute("pedidos", ventaService.listar());
            req.getRequestDispatcher("/WEB-INF/admin/pedidos/lista.jsp").forward(req, resp);
            return;
        }
        if (pathInfo.startsWith("/detalle/")) {
            try {
                Integer id = Integer.valueOf(pathInfo.substring("/detalle/".length()));
                VentaDTO pedido = ventaService.buscarPorId(id);
                if (pedido == null) {
                    resp.sendRedirect(req.getContextPath() + "/admin/pedidos");
                    return;
                }
                req.setAttribute("pedido", pedido);
                req.setAttribute("estados", EstadoVenta.values());
                req.getRequestDispatcher("/WEB-INF/admin/pedidos/detalle.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect(req.getContextPath() + "/admin/pedidos");
            }
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/admin/pedidos");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.startsWith("/estado/")) {
            try {
                Integer id = Integer.valueOf(pathInfo.substring("/estado/".length()));
                String nuevo = req.getParameter("estado");
                ventaService.actualizarEstado(id, EstadoVenta.valueOf(nuevo));
                resp.sendRedirect(req.getContextPath() + "/admin/pedidos/detalle/" + id);
            } catch (Exception e) {
                resp.sendRedirect(req.getContextPath() + "/admin/pedidos");
            }
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/admin/pedidos");
    }
}
