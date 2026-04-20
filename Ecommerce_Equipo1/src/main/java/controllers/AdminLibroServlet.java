package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Genero;
import models.Libro;
import models.Proveedor;
import service.GeneroService;
import service.IGeneroService;
import service.ILibroService;
import service.IProveedorService;
import service.LibroService;
import service.ProveedorService;

@WebServlet(name = "AdminLibroServlet", urlPatterns = {"/admin/libros"})
public class AdminLibroServlet extends HttpServlet {

    private ILibroService libroService;
    private IGeneroService generoService;
    private IProveedorService proveedorService;

    @Override
    public void init() {
        libroService    = new LibroService();
        generoService   = new GeneroService();
        proveedorService = new ProveedorService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("nuevo".equals(accion)) {
            cargarCatalogos(request);
            request.getRequestDispatcher("/admin/FormularioLibro.jsp").forward(request, response);
            return;
        }

        if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Libro libro = libroService.obtenerLibroPorId(id);
            request.setAttribute("libro", libro);
            cargarCatalogos(request);
            request.getRequestDispatcher("/admin/FormularioLibro.jsp").forward(request, response);
            return;
        }

        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            libroService.eliminarLibro(id);
            response.sendRedirect(request.getContextPath() + "/admin/libros?msg=eliminado");
            return;
        }

        List<Libro> libros = libroService.obtenerTodosLosLibros();
        request.setAttribute("libros", libros);
        request.getRequestDispatcher("/admin/ListaLibros.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("idLibro");
        String isbn    = request.getParameter("isbn");
        String titulo  = request.getParameter("titulo");
        String autor   = request.getParameter("autor");
        double precio  = Double.parseDouble(request.getParameter("precio"));
        int stock      = Integer.parseInt(request.getParameter("stock"));
        int idGenero   = Integer.parseInt(request.getParameter("idGenero"));
        int idProv     = Integer.parseInt(request.getParameter("idProveedor"));
        String imagen  = request.getParameter("imagen");

        Date fecha = null;
        try {
            String fechaStr = request.getParameter("anioPublicacion");
            if (fechaStr != null && !fechaStr.isBlank()) {
                fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
            }
        } catch (Exception ignored) {}

        Genero genero     = generoService.obtenerGeneroPorId(idGenero);
        Proveedor prov    = proveedorService.obtenerProveedorPorId(idProv);

        try {
            if (idParam != null && !idParam.isBlank()) {
                //edicion
                Libro libro = libroService.obtenerLibroPorId(Integer.parseInt(idParam));
                libro.setISBN(isbn);
                libro.setTitulo(titulo);
                libro.setAutor(autor);
                libro.setPrecio(precio);
                libro.setStock(stock);
                libro.setAnioPublicacion(fecha);
                libro.setGenero(genero);
                libro.setProveedor(prov);
                libro.setImagen(imagen);
                libroService.actualizarLibro(libro);
                response.sendRedirect(request.getContextPath() + "/admin/libros?msg=actualizado");
            } else {
                //nuevo
                Libro libro = new Libro(isbn, titulo, autor, precio, stock, fecha, genero, prov, imagen);
                libroService.agregarLibro(libro);
                response.sendRedirect(request.getContextPath() + "/admin/libros?msg=guardado");
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            cargarCatalogos(request);
            request.getRequestDispatcher("/admin/FormularioLibro.jsp").forward(request, response);
        }
    }
    
    private void cargarCatalogos(HttpServletRequest request) {
        request.setAttribute("generos",    generoService.obtenerTodosLosGeneros());
        request.setAttribute("proveedores", proveedorService.obtenerTodosLosProveedores());
    }
}
