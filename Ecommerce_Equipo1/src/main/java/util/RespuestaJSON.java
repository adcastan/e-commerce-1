package util;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RespuestaJSON {

    public static void enviar(HttpServletResponse response, int status, Object cuerpo) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONMapper.mapper.writeValueAsString(cuerpo));
    }

    public static void error(HttpServletResponse response, int status, String mensaje) throws IOException {
        Map<String, Object> err = new HashMap<>();
        err.put("error", mensaje);
        err.put("status", status);
        enviar(response, status, err);
    }

    public static void exito(HttpServletResponse response, String mensaje) throws IOException {
        Map<String, Object> ok = new HashMap<>();
        ok.put("mensaje", mensaje);
        enviar(response, HttpServletResponse.SC_OK, ok);
    }
}
