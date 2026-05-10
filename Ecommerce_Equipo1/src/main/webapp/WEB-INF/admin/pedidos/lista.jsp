<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="seccion" value="pedidos" />
<c:set var="tituloPagina" value="Pedidos" />
<jsp:include page="../_layout-inicio.jsp" />
    <header class="encabezado">
        <h1>Pedidos</h1>
        <p>${pedidos.size()} pedidos en el sistema</p>
    </header>

    <table class="tabla">
        <thead>
        <tr>
            <th>N&uacute;mero</th>
            <th>Fecha</th>
            <th>Cliente</th>
            <th>Total</th>
            <th>Pago</th>
            <th>Estado</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${pedidos}">
            <tr>
                <td>${p.numeroPedido}</td>
                <td><fmt:parseDate var="f" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${p.fechaHora}" type="both"/><fmt:formatDate value="${f}" pattern="dd/MM/yyyy HH:mm"/></td>
                <td>${p.nombreCliente}</td>
                <td><fmt:formatNumber value="${p.total}" type="currency" currencySymbol="$"/></td>
                <td>${p.tipoPago}</td>
                <td><span class="estado ${p.estado.toLowerCase()}">${p.estado}</span></td>
                <td><a href="${pageContext.request.contextPath}/admin/pedidos/detalle/${p.idVenta}" class="boton-link">Ver</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<jsp:include page="../_layout-fin.jsp" />
