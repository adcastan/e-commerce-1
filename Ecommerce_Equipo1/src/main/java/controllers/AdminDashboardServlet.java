package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.ClienteService;
import service.IClienteService;
import service.ILibroService;
import service.IReseniaService;
import service.IVentaService;
import service.LibroService;
import service.ReseniaService;
import service.VentaService;

@WebServlet(urlPatterns = {"/admin/dashboard", "/admin", "/admin/"})
public class AdminDashboardServlet extends HttpServlet {

    private final ILibroService libroService = new LibroService();
    private final IVentaService ventaService = new VentaService();
    private final IClienteService clienteService = new ClienteService();
    private final IReseniaService reseniaService = new ReseniaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("totalLibros", libroService.listar().size());
        req.setAttribute("totalPedidos", ventaService.listar().size());
        req.setAttribute("totalClientes", clienteService.listar().size());
        req.setAttribute("totalResenias", reseniaService.listar().size());
        req.getRequestDispatcher("/WEB-INF/admin/dashboard.jsp").forward(req, resp);
    }
}
