package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.IReseniaService;
import service.ReseniaService;

@WebServlet(urlPatterns = {"/admin/resenas", "/admin/resenas/*"})
public class AdminResenasServlet extends HttpServlet {

    private final IReseniaService reseniaService = new ReseniaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("resenias", reseniaService.listar());
        req.getRequestDispatcher("/WEB-INF/admin/resenas/lista.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.startsWith("/eliminar/")) {
            try {
                Integer id = Integer.valueOf(pathInfo.substring("/eliminar/".length()));
                reseniaService.eliminar(id);
            } catch (NumberFormatException ignored) {}
        }
        resp.sendRedirect(req.getContextPath() + "/admin/resenas");
    }
}
