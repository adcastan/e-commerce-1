<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catálogo de Libros - Admin</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #f4f6f8; color: #333; }
        header { background: #2c3e50; color: white; padding: 16px 32px; display: flex; justify-content: space-between; align-items: center; }
        header h1 { font-size: 1.4rem; }
        header a { color: #aed6f1; text-decoration: none; font-size: 0.9rem; }
        .container { max-width: 1100px; margin: 32px auto; padding: 0 16px; }
        .toolbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .toolbar h2 { font-size: 1.3rem; }
        .btn { display: inline-block; padding: 8px 16px; border-radius: 6px; text-decoration: none; font-size: 0.9rem; cursor: pointer; border: none; }
        .btn-primary { background: #2980b9; color: white; }
        .btn-primary:hover { background: #1a6fa8; }
        .btn-warning { background: #e67e22; color: white; }
        .btn-warning:hover { background: #ca6f1e; }
        .btn-danger { background: #e74c3c; color: white; }
        .btn-danger:hover { background: #c0392b; }
        .alert-success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; padding: 10px 16px; border-radius: 6px; margin-bottom: 16px; }
        table { width: 100%; border-collapse: collapse; background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
        thead { background: #2c3e50; color: white; }
        th, td { padding: 12px 14px; text-align: left; font-size: 0.9rem; }
        tbody tr:nth-child(even) { background: #f8f9fa; }
        tbody tr:hover { background: #eaf1fb; }
        .badge { display: inline-block; padding: 3px 10px; border-radius: 12px; font-size: 0.78rem; background: #d6eaf8; color: #1a6fa8; }
        .nav-links { display: flex; gap: 16px; margin-bottom: 24px; }
        .nav-links a { padding: 6px 14px; background: white; border-radius: 6px; text-decoration: none; color: #2c3e50; font-size: 0.88rem; box-shadow: 0 1px 4px rgba(0,0,0,0.1); }
        .nav-links a:hover { background: #2980b9; color: white; }
    </style>
</head>
<body>

<header>
    <h1>📚 Panel de Administración</h1>
    <a href="${pageContext.request.contextPath}/admin/dashboard">← Dashboard</a>
</header>

<div class="container">

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/admin/libros">📖 Libros</a>
        <a href="${pageContext.request.contextPath}/admin/generos">🏷️ Géneros</a>
        <a href="${pageContext.request.contextPath}/admin/proveedores">🏢 Proveedores</a>
    </div>

    <div class="toolbar">
        <h2>📖 Catálogo de Libros</h2>
        <a href="${pageContext.request.contextPath}/admin/libros?accion=nuevo" class="btn btn-primary">+ Nuevo Libro</a>
    </div>

    <c:if test="${not empty param.msg}">
        <div class="alert-success">
            <c:choose>
                <c:when test="${param.msg == 'guardado'}">✅ Libro guardado correctamente.</c:when>
                <c:when test="${param.msg == 'actualizado'}">✅ Libro actualizado correctamente.</c:when>
                <c:when test="${param.msg == 'eliminado'}">✅ Libro eliminado correctamente.</c:when>
            </c:choose>
        </div>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>ISBN</th>
                <th>Título</th>
                <th>Autor</th>
                <th>Precio</th>
                <th>Stock</th>
                <th>Género</th>
                <th>Proveedor</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="libro" items="${libros}">
                <tr>
                    <td>${libro.idLibro}</td>
                    <td>${libro.ISBN}</td>
                    <td>${libro.titulo}</td>
                    <td>${libro.autor}</td>
                    <td>$${libro.precio}</td>
                    <td>${libro.stock}</td>
                    <td><span class="badge">${libro.genero.nombreGenero}</span></td>
                    <td>${libro.proveedor.nombre}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/libros?accion=editar&id=${libro.idLibro}" class="btn btn-warning">Editar</a>
                        <a href="${pageContext.request.contextPath}/admin/libros?accion=eliminar&id=${libro.idLibro}"
                           class="btn btn-danger"
                           onclick="return confirm('¿Eliminar el libro ${libro.titulo}?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty libros}">
                <tr><td colspan="9" style="text-align:center; color:#888; padding:24px;">No hay libros registrados.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>

</body>
</html>
