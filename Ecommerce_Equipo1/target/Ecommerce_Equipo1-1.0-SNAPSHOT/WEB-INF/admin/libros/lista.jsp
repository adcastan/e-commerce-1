<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="seccion" value="libros" />
<c:set var="tituloPagina" value="Libros" />
<jsp:include page="../_layout-inicio.jsp" />
    <header class="encabezado">
        <div>
            <h1>Cat&aacute;logo de libros</h1>
            <p>${libros.size()} libros registrados</p>
        </div>
        <a href="${pageContext.request.contextPath}/admin/libros/nuevo" class="boton-principal">+ Nuevo libro</a>
    </header>

    <table class="tabla">
        <thead>
        <tr>
            <th>Imagen</th>
            <th>T&iacute;tulo</th>
            <th>Autor</th>
            <th>G&eacute;nero</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="libro" items="${libros}">
            <tr>
                <td>
                    <c:if test="${not empty libro.imagenUrl}">
                        <img src="${libro.imagenUrl}" alt="" class="miniatura">
                    </c:if>
                </td>
                <td>${libro.titulo}<br><small>${libro.isbn}</small></td>
                <td>${libro.autor}</td>
                <td>${libro.nombreGenero}</td>
                <td><fmt:formatNumber value="${libro.precio}" type="currency" currencySymbol="$"/></td>
                <td>${libro.stock}</td>
                <td>
                    <c:choose>
                        <c:when test="${libro.activo}"><span class="estado activo">activo</span></c:when>
                        <c:otherwise><span class="estado inactivo">inactivo</span></c:otherwise>
                    </c:choose>
                    <c:if test="${libro.destacado}"><span class="estado destacado">destacado</span></c:if>
                </td>
                <td class="acciones-fila">
                    <a href="${pageContext.request.contextPath}/admin/libros/editar/${libro.idLibro}" class="boton-link">Editar</a>
                    <form method="post" action="${pageContext.request.contextPath}/admin/libros/eliminar/${libro.idLibro}" onsubmit="return confirm('\u00bfDesactivar este libro?');" style="display:inline;">
                        <button type="submit" class="boton-link peligro">Desactivar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<jsp:include page="../_layout-fin.jsp" />
