package controllers;

import daos.AdministradorDAO;
import daos.ClienteDAO;
import daos.IAdministradorDAO;
import daos.IClienteDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Administrador;
import models.Cliente;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    // Instanciamos AMBOS DAOs
    private IAdministradorDAO adminDAO = new AdministradorDAO();
    private IClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        // 1. PRIMERA PRUEBA: ¿Es un administrador?
        Administrador adminLogueado = adminDAO.iniciarSesion(correo, contrasena);
        
        if (adminLogueado != null) {
            // ¡Es administrador!
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuarioLogueado", adminLogueado); // Guardamos el objeto entero
            sesion.setAttribute("rol", "ADMIN"); 
            
            // Lo mandamos a la vista maximalista de administración
            response.sendRedirect("PanelAdmin.jsp");
            return; // Detenemos la ejecución aquí
        }

        // 2. SEGUNDA PRUEBA: Si no fue administrador, ¿es un cliente?
        Cliente clienteLogueado = clienteDAO.iniciarSesion(correo, contrasena);
        
        if (clienteLogueado != null) {
            // ¡Es cliente!
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuarioLogueado", clienteLogueado);
            sesion.setAttribute("rol", "CLIENTE"); 
            
            // Lo mandamos al catálogo de la tienda
            response.sendRedirect("Catalogo.jsp"); // O el nombre de tu página principal
            return; // Detenemos la ejecución aquí
        }

        // 3. TERCERA PRUEBA: No es ninguno de los dos
        request.setAttribute("error", "Correo o contraseña incorrectos.");
        request.getRequestDispatcher("IniciarSesion.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Aquí manejamos el cierre de sesión para AMBOS tipos de usuarios
        String accion = request.getParameter("accion");
        if ("logout".equals(accion)) {
            HttpSession sesion = request.getSession(false);
            if (sesion != null) {
                sesion.invalidate();
            }
            response.sendRedirect("IniciarSesion.jsp");
        }
    }
}