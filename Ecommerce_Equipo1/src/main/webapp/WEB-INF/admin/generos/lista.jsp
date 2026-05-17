<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="seccion" value="generos" />
<c:set var="tituloPagina" value="G&eacute;neros" />
<jsp:include page="../_layout-inicio.jsp" />
    <header class="encabezado">
        <h1>G&eacute;neros literarios</h1>
        <p>${generos.size()} g&eacute;neros</p>
    </header>

    <c:if test="${not empty error}">
        <div class="alerta error">${error}</div>
    </c:if>

    <section class="caja">
        <h2>Nuevo g&eacute;nero</h2>
        <form method="post" action="${pageContext.request.contextPath}/admin/generos/nuevo" class="formulario en-linea">
            <input type="text" name="nombre" placeholder="nombre del g&eacute;nero" required>
            <button type="submit" class="boton-principal">Agregar</button>
        </form>
    </section>

    <table class="tabla">
        <thead>
            <tr><th>ID</th><th>Nombre</th><th>Acciones</th></tr>
        </thead>
        <tbody>
            <c:forEach var="g" items="${generos}">
                <tr>
                    <td>${g.idGenero}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/admin/generos/editar/${g.idGenero}" class="formulario en-linea pequeno">
                            <input type="text" name="nombre" value="${g.nombre}" required>
                            <button type="submit" class="boton-link">Guardar</button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/admin/generos/eliminar/${g.idGenero}" onsubmit="return confirm('\u00bfEliminar este g\u00e9nero?');" style="display:inline;">
                            <button type="submit" class="boton-link peligro">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<jsp:include page="../_layout-fin.jsp" />
