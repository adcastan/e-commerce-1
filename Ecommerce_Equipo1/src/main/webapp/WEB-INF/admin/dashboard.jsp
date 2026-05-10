<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="seccion" value="dashboard" />
<c:set var="tituloPagina" value="Inicio" />
<jsp:include page="_layout-inicio.jsp" />
    <header class="encabezado">
        <h1>Hola, ${sessionScope.adminNombre}</h1>
        <p>Resumen general de la tienda</p>
    </header>

    <section class="grid-tarjetas">
        <div class="tarjeta">
            <span class="numero">${totalLibros}</span>
            <span class="etiqueta">libros en cat&aacute;logo</span>
        </div>
        <div class="tarjeta">
            <span class="numero">${totalPedidos}</span>
            <span class="etiqueta">pedidos registrados</span>
        </div>
        <div class="tarjeta">
            <span class="numero">${totalClientes}</span>
            <span class="etiqueta">clientes</span>
        </div>
        <div class="tarjeta">
            <span class="numero">${totalResenias}</span>
            <span class="etiqueta">rese&ntilde;as</span>
        </div>
    </section>

    <section class="bloque-acciones">
        <h2>Atajos</h2>
        <div class="acciones">
            <a href="${pageContext.request.contextPath}/admin/libros/nuevo" class="boton-principal">Nuevo libro</a>
            <a href="${pageContext.request.contextPath}/admin/pedidos" class="boton-secundario">Ver pedidos</a>
            <a href="${pageContext.request.contextPath}/admin/resenas" class="boton-secundario">Moderar rese&ntilde;as</a>
        </div>
    </section>
<jsp:include page="_layout-fin.jsp" />
