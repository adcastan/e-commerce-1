<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio de sesi&oacute;n - Hojas Sueltas Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin.css">
</head>
<body class="centrado">
<div class="caja-login">
    <h1>Hojas Sueltas</h1>
    <p class="subtitulo">panel administrativo</p>
    <c:if test="${not empty error}">
        <div class="alerta error">${error}</div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/admin/login">
        <label>
            Correo
            <input type="email" name="correo" value="${correo}" required autofocus>
        </label>
        <label>
            Contrase&ntilde;a
            <input type="password" name="contrasenia" required>
        </label>
        <button type="submit" class="boton-principal">Iniciar sesi&oacute;n</button>
    </form>
    <a href="${pageContext.request.contextPath}/" class="link-secundario">&larr; volver a la tienda</a>
</div>
</body>
</html>
