<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="seccion" value="pedidos" />
<c:set var="tituloPagina" value="Detalle de pedido" />
<jsp:include page="../_layout-inicio.jsp" />
    <header class="encabezado">
        <div>
            <h1>Pedido ${pedido.numeroPedido}</h1>
            <a href="${pageContext.request.contextPath}/admin/pedidos" class="link-secundario">&larr; volver a pedidos</a>
        </div>
        <span class="estado grande ${pedido.estado.toLowerCase()}">${pedido.estado}</span>
    </header>

    <section class="grid-dos">
        <div class="caja">
            <h3>Cliente</h3>
            <p>${pedido.nombreCliente}</p>
            <h3>Direcci&oacute;n de env&iacute;o</h3>
            <p>${pedido.direccionEnvio}</p>
        </div>
        <div class="caja">
            <h3>Pago</h3>
            <p>M&eacute;todo: ${pedido.tipoPago}</p>
            <p>Total: <fmt:formatNumber value="${pedido.total}" type="currency" currencySymbol="$"/></p>
            <p>Fecha: <fmt:parseDate var="f" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${pedido.fechaHora}" type="both"/><fmt:formatDate value="${f}" pattern="dd/MM/yyyy HH:mm"/></p>
        </div>
    </section>

    <section class="caja">
        <h3>Art&iacute;culos</h3>
        <table class="tabla">
            <thead>
                <tr><th></th><th>T&iacute;tulo</th><th>Autor</th><th>Cantidad</th><th>Precio</th><th>Subtotal</th></tr>
            </thead>
            <tbody>
                <c:forEach var="d" items="${pedido.detalles}">
                    <tr>
                        <td><c:if test="${not empty d.imagenUrl}"><img src="${d.imagenUrl}" alt="" class="miniatura"></c:if></td>
                        <td>${d.tituloLibro}</td>
                        <td>${d.autorLibro}</td>
                        <td>${d.cantidad}</td>
                        <td><fmt:formatNumber value="${d.precioUnitario}" type="currency" currencySymbol="$"/></td>
                        <td><fmt:formatNumber value="${d.subTotal}" type="currency" currencySymbol="$"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>

    <section class="caja">
        <h3>Cambiar estado</h3>
        <form method="post" action="${pageContext.request.contextPath}/admin/pedidos/estado/${pedido.idVenta}" class="formulario en-linea">
            <select name="estado">
                <c:forEach var="e" items="${estados}">
                    <option value="${e}" ${pedido.estado eq e.name() ? 'selected' : ''}>${e}</option>
                </c:forEach>
            </select>
            <button type="submit" class="boton-principal">Actualizar</button>
        </form>
    </section>
<jsp:include page="../_layout-fin.jsp" />
