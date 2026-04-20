package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Genero;
import service.GeneroService;
import service.IGeneroService;

@WebServlet(name = "AdminGeneroServlet", urlPatterns = {"/admin/generos"})
public class AdminGeneroServlet extends HttpServlet {

    private IGeneroService generoService;

    @Override
    public void init() {
        generoService = new GeneroService();
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("nuevo".equals(accion)) {
            request.getRequestDispatcher("/admin/FormularioGenero.jsp").forward(request, response);
            return;
        }

        if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Genero genero = generoService.obtenerGeneroPorId(id);
            request.setAttribute("genero", genero);
            request.getRequestDispatcher("/admin/FormularioGenero.jsp").forward(request, response);
            return;
        }

        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            generoService.eliminarGenero(id);
            response.sendRedirect(request.getContextPath() + "/admin/generos?msg=eliminado");
            return;
        }

        //default listar
        List<Genero> generos = generoService.obtenerTodosLosGeneros();
        request.setAttribute("generos", generos);
        request.getRequestDispatcher("/admin/ListaGeneros.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idParam      = request.getParameter("idGenero");
        String nombreGenero = request.getParameter("nombreGenero");

        try {
            if (idParam != null && !idParam.isBlank()) {
                //ediucion
                Genero genero = generoService.obtenerGeneroPorId(Integer.parseInt(idParam));
                genero.setNombreGenero(nombreGenero);
                generoService.actualizarGenero(genero);
                response.sendRedirect(request.getContextPath() + "/admin/generos?msg=actualizado");
            } else {
                //nuevo
                Genero genero = new Genero(nombreGenero);
                generoService.agregarGenero(genero);
                response.sendRedirect(request.getContextPath() + "/admin/generos?msg=guardado");
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/admin/FormularioGenero.jsp").forward(request, response);
        }
    }
}
