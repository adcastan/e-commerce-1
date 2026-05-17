<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="seccion" value="libros" />
<c:set var="esEdicion" value="${not empty libro.idLibro}" />
<c:set var="tituloPagina" value="${esEdicion ? 'Editar libro' : 'Nuevo libro'}" />
<jsp:include page="../_layout-inicio.jsp" />
    <header class="encabezado">
        <div>
            <h1>${tituloPagina}</h1>
            <a href="${pageContext.request.contextPath}/admin/libros" class="link-secundario">&larr; volver al cat&aacute;logo</a>
        </div>
    </header>

    <c:if test="${not empty error}">
        <div class="alerta error">${error}</div>
    </c:if>

    <form method="post" action="${esEdicion ? pageContext.request.contextPath.concat('/admin/libros/editar/').concat(libro.idLibro) : pageContext.request.contextPath.concat('/admin/libros/nuevo')}" class="formulario">
        <div class="campos-en-fila">
            <label>
                ISBN
                <input type="text" name="isbn" value="${libro.isbn}" required>
            </label>
            <label>
                A&ntilde;o de publicaci&oacute;n
                <input type="number" name="anioPublicacion" value="${libro.anioPublicacion}">
            </label>
        </div>
        <label>
            T&iacute;tulo
            <input type="text" name="titulo" value="${libro.titulo}" required>
        </label>
        <label>
            Autor
            <input type="text" name="autor" value="${libro.autor}" required>
        </label>
        <label>
            Editorial
            <input type="text" name="editorial" value="${libro.editorial}">
        </label>
        <label>
            G&eacute;nero
            <select name="idGenero">
                <option value="">- selecciona -</option>
                <c:forEach var="g" items="${generos}">
                    <option value="${g.idGenero}" ${libro.idGenero eq g.idGenero ? 'selected' : ''}>${g.nombre}</option>
                </c:forEach>
            </select>
        </label>
        <div class="campos-en-fila">
            <label>
                Precio
                <input type="number" step="0.01" name="precio" value="${libro.precio}" required>
            </label>
            <label>
                Stock
                <input type="number" name="stock" value="${libro.stock}" required>
            </label>
        </div>
        <label>
            URL de imagen
            <input type="url" name="imagenUrl" value="${libro.imagenUrl}">
        </label>
        <label>
            Descripci&oacute;n
            <textarea name="descripcion" rows="5">${libro.descripcion}</textarea>
        </label>
        <div class="campos-en-fila">
            <label class="checkbox">
                <input type="checkbox" name="destacado" ${libro.destacado ? 'checked' : ''}>
                marcar como destacado
            </label>
            <label class="checkbox">
                <input type="checkbox" name="activo" ${not esEdicion or libro.activo ? 'checked' : ''}>
                disponible en cat&aacute;logo
            </label>
        </div>
        <div class="acciones">
            <button type="submit" class="boton-principal">${esEdicion ? 'Guardar cambios' : 'Crear libro'}</button>
            <a href="${pageContext.request.contextPath}/admin/libros" class="boton-secundario">Cancelar</a>
        </div>
    </form>
<jsp:include page="../_layout-fin.jsp" />
