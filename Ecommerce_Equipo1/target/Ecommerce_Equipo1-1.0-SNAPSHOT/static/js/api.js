const API_BASE = (() => {
    const path = window.location.pathname;
    const parts = path.split('/').filter(Boolean);
    // si el primer segmento parece nombre de webapp (no es archivo), lo usamos como context path
    if (parts.length > 1 && !parts[0].includes('.')) {
        return '/' + parts[0] + '/api';
    }
    return '/api';
})();

const Auth = {
    obtenerToken() {
        return localStorage.getItem('token');
    },
    obtenerCliente() {
        const raw = localStorage.getItem('cliente');
        return raw ? JSON.parse(raw) : null;
    },
    estaAutenticado() {
        return !!this.obtenerToken();
    },
    guardarSesion(token, cliente) {
        localStorage.setItem('token', token);
        localStorage.setItem('cliente', JSON.stringify(cliente));
    },
    cerrarSesion() {
        localStorage.removeItem('token');
        localStorage.removeItem('cliente');
        Carrito.limpiar();
    }
};

async function llamarApi(metodo, ruta, body = null, requiereAuth = false) {
    const headers = { 'Content-Type': 'application/json' };
    if (requiereAuth) {
        const token = Auth.obtenerToken();
        if (!token) {
            redirigirALogin();
            throw new Error('sesi\u00f3n requerida');
        }
        headers['Authorization'] = 'Bearer ' + token;
    }
    const opciones = { method: metodo, headers };
    if (body) opciones.body = JSON.stringify(body);

    const respuesta = await fetch(API_BASE + ruta, opciones);
    let datos = null;
    const texto = await respuesta.text();
    if (texto) {
        try { datos = JSON.parse(texto); } catch (e) { datos = { mensaje: texto }; }
    }

    if (!respuesta.ok) {
        if (respuesta.status === 401) {
            Auth.cerrarSesion();
            redirigirALogin();
        }
        const error = new Error(datos?.error || 'error en la petici\u00f3n');
        error.status = respuesta.status;
        error.datos = datos;
        throw error;
    }
    return datos;
}

function redirigirALogin() {
    if (!window.location.pathname.endsWith('/iniciarSesion.html')) {
        const destino = window.location.pathname + window.location.search;
        sessionStorage.setItem('regresoLogin', destino);
        window.location.href = 'iniciarSesion.html';
    }
}

function notificar(mensaje, tipo = 'exito') {
    const existente = document.querySelector('.notificacion');
    if (existente) existente.remove();
    const div = document.createElement('div');
    div.className = 'notificacion ' + tipo;
    div.textContent = mensaje;
    document.body.appendChild(div);
    setTimeout(() => div.classList.add('visible'), 10);
    setTimeout(() => {
        div.classList.remove('visible');
        setTimeout(() => div.remove(), 300);
    }, 3000);
}

function formatearMoneda(valor) {
    return '$' + Number(valor || 0).toFixed(2);
}

function formatearFecha(fecha) {
    if (!fecha) return '';
    const d = new Date(fecha);
    return d.toLocaleDateString('es-MX', {
        day: '2-digit', month: '2-digit', year: 'numeric',
        hour: '2-digit', minute: '2-digit'
    });
}

function escaparHtml(s) {
    if (s === null || s === undefined) return '';
    const div = document.createElement('div');
    div.textContent = String(s);
    return div.innerHTML;
}
