<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${tituloPagina} - Hojas Sueltas Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin.css">
</head>
<body>
<aside class="sidebar">
    <div class="brand">
        <span class="logo">Hojas Sueltas</span>
        <span class="tag">panel administrativo</span>
    </div>
    <nav>
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="${seccion eq 'dashboard' ? 'activo' : ''}">Inicio</a>
        <a href="${pageContext.request.contextPath}/admin/libros" class="${seccion eq 'libros' ? 'activo' : ''}">Libros</a>
        <a href="${pageContext.request.contextPath}/admin/generos" class="${seccion eq 'generos' ? 'activo' : ''}">G&eacute;neros</a>
        <a href="${pageContext.request.contextPath}/admin/pedidos" class="${seccion eq 'pedidos' ? 'activo' : ''}">Pedidos</a>
        <a href="${pageContext.request.contextPath}/admin/resenas" class="${seccion eq 'resenas' ? 'activo' : ''}">Rese&ntilde;as</a>
        <a href="${pageContext.request.contextPath}/admin/clientes" class="${seccion eq 'clientes' ? 'activo' : ''}">Clientes</a>
    </nav>
    <div class="usuario">
        <span>${sessionScope.adminNombre}</span>
        <a href="${pageContext.request.contextPath}/admin/logout" class="boton-secundario">Cerrar sesi&oacute;n</a>
    </div>
</aside>
<main class="contenido">
