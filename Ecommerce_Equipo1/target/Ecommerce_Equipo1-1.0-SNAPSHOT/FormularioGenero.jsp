<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty genero ? 'Nuevo Género' : 'Editar Género'} - Admin</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #f4f6f8; color: #333; }
        header { background: #2c3e50; color: white; padding: 16px 32px; display: flex; justify-content: space-between; align-items: center; }
        header h1 { font-size: 1.4rem; }
        header a { color: #aed6f1; text-decoration: none; font-size: 0.9rem; }
        .container { max-width: 480px; margin: 60px auto; padding: 0 16px; }
        .card { background: white; border-radius: 10px; padding: 32px; box-shadow: 0 2px 10px rgba(0,0,0,0.09); }
        h2 { margin-bottom: 24px; font-size: 1.3rem; color: #2c3e50; }
        .form-group { margin-bottom: 18px; }
        label { display: block; margin-bottom: 6px; font-size: 0.88rem; font-weight: 600; color: #555; }
        input[type="text"] { width: 100%; padding: 9px 12px; border: 1px solid #ccc; border-radius: 6px; font-size: 0.93rem; }
        input:focus { outline: none; border-color: #2980b9; box-shadow: 0 0 0 2px #d6eaf8; }
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
    <a href="${pageContext.request.contextPath}/admin/generos">← Volver a Géneros</a>
</header>

<div class="container">
    <div class="card">
        <h2>${empty genero ? '➕ Nuevo Género' : '✏️ Editar Género'}</h2>

        <c:if test="${not empty error}">
            <div class="alert-error">⚠️ ${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/admin/generos">
            <c:if test="${not empty genero}">
                <input type="hidden" name="idGenero" value="${genero.idGenero}">
            </c:if>

            <div class="form-group">
                <label for="nombreGenero">Nombre del Género</label>
                <input type="text" id="nombreGenero" name="nombreGenero"
                       value="${genero.nombreGenero}" required placeholder="ej: Ciencia Ficción">
            </div>

            <div style="margin-top: 24px;">
                <button type="submit" class="btn btn-primary">
                    ${empty genero ? 'Guardar Género' : 'Actualizar Género'}
                </button>
                <a href="${pageContext.request.contextPath}/admin/generos" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
