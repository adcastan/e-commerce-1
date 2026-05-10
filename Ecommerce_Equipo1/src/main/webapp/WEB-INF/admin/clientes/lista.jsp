<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="seccion" value="clientes" />
<c:set var="tituloPagina" value="Clientes" />
<jsp:include page="../_layout-inicio.jsp" />
    <header class="encabezado">
        <h1>Cuentas de clientes</h1>
        <p>${clientes.size()} cuentas registradas</p>
    </header>

    <table class="tabla">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Tel&eacute;fono</th>
            <th>Rol</th>
            <th>Registro</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${clientes}">
            <tr>
                <td>${c.nombre} ${c.apellido}</td>
                <td>${c.correo}</td>
                <td>${c.telefono}</td>
                <td>${c.rol}</td>
                <td><fmt:parseDate var="f" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${c.fechaRegistro}" type="both"/><fmt:formatDate value="${f}" pattern="dd/MM/yyyy"/></td>
                <td>
                    <c:choose>
                        <c:when test="${c.activo}"><span class="estado activo">activo</span></c:when>
                        <c:otherwise><span class="estado inactivo">desactivado</span></c:otherwise>
                    </c:choose>
                </td>
                <td class="acciones-fila">
                    <c:choose>
                        <c:when test="${c.activo}">
                            <form method="post" action="${pageContext.request.contextPath}/admin/clientes/desactivar/${c.idCliente}">
                                <button type="submit" class="boton-link">Desactivar</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form method="post" action="${pageContext.request.contextPath}/admin/clientes/activar/${c.idCliente}">
                                <button type="submit" class="boton-link">Activar</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                    <form method="post" action="${pageContext.request.contextPath}/admin/clientes/eliminar/${c.idCliente}" onsubmit="return confirm('\u00bfEliminar esta cuenta?');" style="display:inline;">
                        <button type="submit" class="boton-link peligro">Eliminar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<jsp:include page="../_layout-fin.jsp" />
