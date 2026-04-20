package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Controlador para cerrar la sesión de cualquier usuario (Admin o Cliente)
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Obtenemos la sesión actual. 
        // El 'false' significa: "Dame la sesión si existe, pero NO crees una nueva si no hay".
        HttpSession sesion = request.getSession(false);
        
        if (sesion != null) {
            // 2. Destruimos la sesión y todos los datos guardados en ella (nombre, correo, rol, etc.)
            sesion.invalidate();
        }
        
        // 3. Redirigimos al usuario de vuelta a la pantalla de inicio de sesión
        response.sendRedirect("IniciarSesion.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si por alguna razón mandan un POST, hacemos que haga lo mismo que el GET
        doGet(request, response);
    }
}