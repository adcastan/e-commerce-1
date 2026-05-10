package controllers;

import DTOs.LibroDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.GeneroService;
import service.IGeneroService;
import service.ILibroService;
import service.LibroService;
import service.NegocioException;

@WebServlet(urlPatterns = {"/admin/libros", "/admin/libros/*"})
public class AdminLibrosServlet extends HttpServlet {

    private final ILibroService libroService = new LibroService();
    private final IGeneroService generoService = new GeneroService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            req.setAttribute("libros", libroService.listar());
            req.getRequestDispatcher("/WEB-INF/admin/libros/lista.jsp").forward(req, resp);
            return;
        }
        if (pathInfo.equals("/nuevo")) {
            req.setAttribute("generos", generoService.listar());
            req.getRequestDispatcher("/WEB-INF/admin/libros/formulario.jsp").forward(req, resp);
            return;
        }
        if (pathInfo.startsWith("/editar/")) {
            try {
                Integer id = Integer.valueOf(pathInfo.substring("/editar/".length()));
                LibroDTO libro = libroService.buscarPorId(id);
                if (libro == null) {
                    resp.sendRedirect(req.getContextPath() + "/admin/libros");
                    return;
                }
                req.setAttribute("libro", libro);
                req.setAttribute("generos", generoService.listar());
                req.getRequestDispatcher("/WEB-INF/admin/libros/formulario.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect(req.getContextPath() + "/admin/libros");
            }
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/admin/libros");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/";

        try {
            if (pathInfo.equals("/nuevo")) {
                LibroDTO datos = leerLibro(req);
                libroService.crear(datos);
                resp.sendRedirect(req.getContextPath() + "/admin/libros");
                return;
            }
            if (pathInfo.startsWith("/editar/")) {
                Integer id = Integer.valueOf(pathInfo.substring("/editar/".length()));
                LibroDTO datos = leerLibro(req);
                libroService.actualizar(id, datos);
                resp.sendRedirect(req.getContextPath() + "/admin/libros");
                return;
            }
            if (pathInfo.startsWith("/eliminar/")) {
                Integer id = Integer.valueOf(pathInfo.substring("/eliminar/".length()));
                libroService.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/admin/libros");
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/admin/libros");
        } catch (NegocioException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("generos", generoService.listar());
            req.setAttribute("libro", leerLibro(req));
            req.getRequestDispatcher("/WEB-INF/admin/libros/formulario.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/libros");
        }
    }

    private LibroDTO leerLibro(HttpServletRequest req) {
        LibroDTO d = new LibroDTO();
        d.setIsbn(req.getParameter("isbn"));
        d.setTitulo(req.getParameter("titulo"));
        d.setAutor(req.getParameter("autor"));
        d.setDescripcion(req.getParameter("descripcion"));
        d.setEditorial(req.getParameter("editorial"));
        d.setImagenUrl(req.getParameter("imagenUrl"));
        d.setDestacado(req.getParameter("destacado") != null);
        d.setActivo(req.getParameter("activo") != null);
        try { d.setPrecio(Double.parseDouble(req.getParameter("precio"))); } catch (Exception e) {}
        try { d.setStock(Integer.parseInt(req.getParameter("stock"))); } catch (Exception e) {}
        try {
            String anio = req.getParameter("anioPublicacion");
            if (anio != null && !anio.isBlank()) d.setAnioPublicacion(Integer.parseInt(anio));
        } catch (Exception e) {}
        try {
            String genero = req.getParameter("idGenero");
            if (genero != null && !genero.isBlank()) d.setIdGenero(Integer.parseInt(genero));
        } catch (Exception e) {}
        return d;
    }
}
