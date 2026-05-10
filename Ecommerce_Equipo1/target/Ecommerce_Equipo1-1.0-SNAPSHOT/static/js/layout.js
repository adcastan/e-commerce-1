function pintarEncabezado() {
    const cliente = Auth.obtenerCliente();
    const acciones = document.querySelector('.acciones-encabezado');
    if (!acciones) return;

    if (cliente) {
        acciones.innerHTML = `
            <a href="carrito.html" class="icono-carrito">Carrito <span class="badge badge-carrito">0</span></a>
            <a href="miPerfil.html">${escaparHtml(cliente.nombre)}</a>
        `;
    } else {
        acciones.innerHTML = `
            <a href="carrito.html" class="icono-carrito">Carrito <span class="badge badge-carrito">0</span></a>
            <a href="iniciarSesion.html">Iniciar sesi&oacute;n</a>
        `;
    }
    Carrito.actualizarBadge();
}

function configurarBuscador() {
    const form = document.querySelector('.form-buscador');
    if (!form) return;
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const q = form.querySelector('input[name="q"]').value.trim();
        if (q) {
            window.location.href = 'busqueda.html?q=' + encodeURIComponent(q);
        }
    });
}

document.addEventListener('DOMContentLoaded', () => {
    pintarEncabezado();
    configurarBuscador();
});
