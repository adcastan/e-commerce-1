package controllers;

import DTOs.ClienteDTO;
import DTOs.LoginDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import service.ClienteService;
import service.IClienteService;
import service.NegocioException;

@WebServlet(urlPatterns = {"/admin/login"})
public class AdminLoginServlet extends HttpServlet {

    private final IClienteService clienteService = new ClienteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("adminId") != null) {
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/admin/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String contrasenia = req.getParameter("contrasenia");
        try {
            LoginDTO login = new LoginDTO();
            login.setCorreo(correo);
            login.setContrasenia(contrasenia);
            String token = clienteService.autenticarAdmin(login);
            ClienteDTO admin = clienteService.buscarPorId(util.JWTUtil.obtenerIdClienteDeToken(token));
            HttpSession session = req.getSession(true);
            session.setAttribute("adminId", admin.getIdCliente());
            session.setAttribute("adminNombre", admin.getNombre() + " " + admin.getApellido());
            session.setAttribute("token", token);
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
        } catch (NegocioException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("correo", correo);
            req.getRequestDispatcher("/WEB-INF/admin/login.jsp").forward(req, resp);
        }
    }
}
