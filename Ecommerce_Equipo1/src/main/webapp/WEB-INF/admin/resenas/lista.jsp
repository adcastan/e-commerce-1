<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="seccion" value="resenas" />
<c:set var="tituloPagina" value="Rese&ntilde;as" />
<jsp:include page="../_layout-inicio.jsp" />
    <header class="encabezado">
        <h1>Moderaci&oacute;n de rese&ntilde;as</h1>
        <p>${resenias.size()} rese&ntilde;as totales</p>
    </header>

    <table class="tabla">
        <thead>
        <tr>
            <th>Fecha</th>
            <th>Cliente</th>
            <th>Libro</th>
            <th>Calificaci&oacute;n</th>
            <th>Comentario</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="r" items="${resenias}">
            <tr>
                <td><fmt:parseDate var="f" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${r.fechaHora}" type="both"/><fmt:formatDate value="${f}" pattern="dd/MM/yyyy HH:mm"/></td>
                <td>${r.nombreCliente}</td>
                <td>${r.tituloLibro}</td>
                <td>
                    <c:forEach begin="1" end="${r.calificacion}">&#9733;</c:forEach><c:forEach begin="${r.calificacion + 1}" end="5">&#9734;</c:forEach>
                </td>
                <td>${r.comentario}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/admin/resenas/eliminar/${r.idResenia}" onsubmit="return confirm('\u00bfEliminar esta rese\u00f1a?');">
                        <button type="submit" class="boton-link peligro">Eliminar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<jsp:include page="../_layout-fin.jsp" />
