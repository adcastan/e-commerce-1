package filters;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import util.JWTUtil;
import util.RespuestaJSON;

@WebFilter(filterName = "JwtAutenticacionFiltro", urlPatterns = {"/api/*"})
public class JwtAutenticacionFiltro implements Filter {

    private static final Set<String> RUTAS_PUBLICAS = new HashSet<>();

    static {
        RUTAS_PUBLICAS.add("/api/auth/login");
        RUTAS_PUBLICAS.add("/api/auth/registro");
        RUTAS_PUBLICAS.add("/api/auth/admin-login");
        RUTAS_PUBLICAS.add("/api/productos");
        RUTAS_PUBLICAS.add("/api/generos");
        RUTAS_PUBLICAS.add("/api/metodos-pago");
        RUTAS_PUBLICAS.add("/api/resenas");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (esRutaPublica(path, request.getMethod())) {
            chain.doFilter(req, res);
            return;
        }

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            RespuestaJSON.error(response, HttpServletResponse.SC_UNAUTHORIZED, "token requerido");
            return;
        }

        try {
            String token = header.substring(7);
            Claims claims = JWTUtil.validarToken(token);
            request.setAttribute("idCliente", Integer.valueOf(claims.getSubject()));
            request.setAttribute("rol", claims.get("rol"));
            request.setAttribute("correo", claims.get("correo"));
            chain.doFilter(req, res);
        } catch (Exception e) {
            RespuestaJSON.error(response, HttpServletResponse.SC_UNAUTHORIZED, "token inv\u00e1lido o expirado");
        }
    }

    private boolean esRutaPublica(String path, String metodo) {
        if (!"GET".equalsIgnoreCase(metodo)) {
            if (path.equals("/api/auth/login") || path.equals("/api/auth/registro")
                    || path.equals("/api/auth/admin-login")
                    || path.equals("/api/carrito")) {
                return true;
            }
            return false;
        }
        for (String publica : RUTAS_PUBLICAS) {
            if (path.equals(publica) || path.startsWith(publica + "/")) {
                return true;
            }
        }
        return false;
    }
}
