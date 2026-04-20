<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty libro ? 'Nuevo Libro' : 'Editar Libro'} - Admin</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #f4f6f8; color: #333; }
        header { background: #2c3e50; color: white; padding: 16px 32px; display: flex; justify-content: space-between; align-items: center; }
        header h1 { font-size: 1.4rem; }
        header a { color: #aed6f1; text-decoration: none; font-size: 0.9rem; }
        .container { max-width: 680px; margin: 40px auto; padding: 0 16px; }
        .card { background: white; border-radius: 10px; padding: 32px; box-shadow: 0 2px 10px rgba(0,0,0,0.09); }
        h2 { margin-bottom: 24px; font-size: 1.3rem; color: #2c3e50; }
        .form-group { margin-bottom: 18px; }
        label { display: block; margin-bottom: 6px; font-size: 0.88rem; font-weight: 600; color: #555; }
        input[type="text"], input[type="number"], input[type="date"], select {
            width: 100%; padding: 9px 12px; border: 1px solid #ccc; border-radius: 6px; font-size: 0.93rem;
        }
        input:focus, select:focus { outline: none; border-color: #2980b9; box-shadow: 0 0 0 2px #d6eaf8; }
        .row { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }
        .btn { display: inline-block; padding: 10px 22px; border-radius: 6px; font-size: 0.95rem; border: none; cursor: pointer; text-decoration: none; }
        .btn-primary { background: #2980b9; color: white; }
        .btn-primary:hover { background: #1a6fa8; }
        .btn-secondary { background: #95a5a6; color: white; margin-left: 8px; }
        .btn-secondary:hover { background: #7f8c8d; }
        .alert-error { background: #fde8e8; color: #922b21; border: 1px solid #f5c6cb; padding: 10px 16px; border-radius: 6px; margin-bottom: 18px; }
    </style>
</head>
<body>

<header>
    <h1>📚 Panel de Administración</h1>
    <a href="${pageContext.request.contextPath}/admin/libros">← Volver a Libros</a>
</header>

<div class="container">
    <div class="card">
        <h2>${empty libro ? '➕ Nuevo Libro' : '✏️ Editar Libro'}</h2>

        <c:if test="${not empty error}">
            <div class="alert-error">⚠️ ${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/admin/libros">
            <!-- ID oculto para edición -->
            <c:if test="${not empty libro}">
                <input type="hidden" name="idLibro" value="${libro.idLibro}">
            </c:if>

            <div class="row">
                <div class="form-group">
                    <label for="isbn">ISBN</label>
                    <input type="text" id="isbn" name="isbn" value="${libro.ISBN}" required>
                </div>
                <div class="form-group">
                    <label for="anioPublicacion">Año de publicación</label>
                    <input type="date" id="anioPublicacion" name="anioPublicacion"
                           value="<fmt:formatDate value='${libro.anioPublicacion}' pattern='yyyy-MM-dd'/>">
                </div>
            </div>

            <div class="form-group">
                <label for="titulo">Título</label>
                <input type="text" id="titulo" name="titulo" value="${libro.titulo}" required>
            </div>

            <div class="form-group">
                <label for="autor">Autor</label>
                <input type="text" id="autor" name="autor" value="${libro.autor}" required>
            </div>

            <div class="row">
                <div class="form-group">
                    <label for="precio">Precio ($)</label>
                    <input type="number" id="precio" name="precio" step="0.01" min="0"
                           value="${libro.precio}" required>
                </div>
                <div class="form-group">
                    <label for="stock">Stock</label>
                    <input type="number" id="stock" name="stock" min="0"
                           value="${libro.stock}" required>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label for="idGenero">Género</label>
                    <select id="idGenero" name="idGenero" required>
                        <option value="">-- Seleccionar --</option>
                        <c:forEach var="g" items="${generos}">
                            <option value="${g.idGenero}"
                                ${libro.genero.idGenero == g.idGenero ? 'selected' : ''}>
                                ${g.nombreGenero}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="idProveedor">Proveedor</label>
                    <select id="idProveedor" name="idProveedor" required>
                        <option value="">-- Seleccionar --</option>
                        <c:forEach var="p" items="${proveedores}">
                            <option value="${p.idProveedor}"
                                ${libro.proveedor.idProveedor == p.idProveedor ? 'selected' : ''}>
                                ${p.nombre}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="imagen">URL / nombre de imagen</label>
                <input type="text" id="imagen" name="imagen" value="${libro.imagen}"
                       placeholder="ej: portada_libro.jpg">
            </div>

            <div style="margin-top: 24px;">
                <button type="submit" class="btn btn-primary">
                    ${empty libro ? 'Guardar Libro' : 'Actualizar Libro'}
                </button>
                <a href="${pageContext.request.contextPath}/admin/libros" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
