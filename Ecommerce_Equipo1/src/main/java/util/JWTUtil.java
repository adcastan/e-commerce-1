package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

public class JWTUtil {

    private static final String SECRET = "mi_super_secreta_firma_jwt_1234567891011";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final long DURACION_MS = 1000L * 60 * 60 * 8;

    public static String generarToken(Integer idCliente, String correo, String rol) {
        return Jwts.builder()
                .subject(String.valueOf(idCliente))
                .claim("correo", correo)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + DURACION_MS))
                .signWith(KEY)
                .compact();
    }

    public static Claims validarToken(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static Integer obtenerIdClienteDeToken(String token) {
        return Integer.valueOf(validarToken(token).getSubject());
    }

    public static String obtenerRolDeToken(String token) {
        return (String) validarToken(token).get("rol");
    }
}
