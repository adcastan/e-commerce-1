package controllers;

import DTOs.GeneroDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.GeneroService;
import service.IGeneroService;
import service.NegocioException;

@WebServlet(urlPatterns = {"/admin/generos", "/admin/generos/*"})
public class AdminGenerosServlet extends HttpServlet {

    private final IGeneroService generoService = new GeneroService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("generos", generoService.listar());
        req.getRequestDispatcher("/WEB-INF/admin/generos/lista.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/";
        try {
            if (pathInfo.equals("/nuevo")) {
                GeneroDTO d = new GeneroDTO();
                d.setNombre(req.getParameter("nombre"));
                generoService.crear(d);
            } else if (pathInfo.startsWith("/editar/")) {
                Integer id = Integer.valueOf(pathInfo.substring("/editar/".length()));
                GeneroDTO d = new GeneroDTO();
                d.setNombre(req.getParameter("nombre"));
                generoService.actualizar(id, d);
            } else if (pathInfo.startsWith("/eliminar/")) {
                Integer id = Integer.valueOf(pathInfo.substring("/eliminar/".length()));
                generoService.eliminar(id);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/generos");
        } catch (NegocioException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("generos", generoService.listar());
            req.getRequestDispatcher("/WEB-INF/admin/generos/lista.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/generos");
        }
    }
}
