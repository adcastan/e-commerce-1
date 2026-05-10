package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.ClienteService;
import service.IClienteService;

@WebServlet(urlPatterns = {"/admin/clientes", "/admin/clientes/*"})
public class AdminClientesServlet extends HttpServlet {

    private final IClienteService clienteService = new ClienteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("clientes", clienteService.listar());
        req.getRequestDispatcher("/WEB-INF/admin/clientes/lista.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/";
        try {
            if (pathInfo.startsWith("/activar/")) {
                Integer id = Integer.valueOf(pathInfo.substring("/activar/".length()));
                clienteService.cambiarEstado(id, true);
            } else if (pathInfo.startsWith("/desactivar/")) {
                Integer id = Integer.valueOf(pathInfo.substring("/desactivar/".length()));
                clienteService.cambiarEstado(id, false);
            } else if (pathInfo.startsWith("/eliminar/")) {
                Integer id = Integer.valueOf(pathInfo.substring("/eliminar/".length()));
                clienteService.eliminar(id);
            }
        } catch (Exception ignored) {}
        resp.sendRedirect(req.getContextPath() + "/admin/clientes");
    }
}
