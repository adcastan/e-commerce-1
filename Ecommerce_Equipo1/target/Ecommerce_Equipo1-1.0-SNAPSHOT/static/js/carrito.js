const Carrito = {
    CLAVE: 'carrito_hojas_sueltas',

    leer() {
        const raw = localStorage.getItem(this.CLAVE);
        if (!raw) return [];
        try { return JSON.parse(raw); } catch (e) { return []; }
    },

    guardar(items) {
        localStorage.setItem(this.CLAVE, JSON.stringify(items));
        this.actualizarBadge();
    },

    agregar(libro, cantidad = 1) {
        const items = this.leer();
        const existente = items.find(i => i.idLibro === libro.idLibro);
        if (existente) {
            existente.cantidad += cantidad;
        } else {
            items.push({
                idLibro: libro.idLibro,
                titulo: libro.titulo,
                autor: libro.autor,
                precio: libro.precio,
                imagenUrl: libro.imagenUrl,
                cantidad
            });
        }
        this.guardar(items);
    },

    actualizarCantidad(idLibro, cantidad) {
        const items = this.leer();
        const item = items.find(i => i.idLibro === idLibro);
        if (item) {
            if (cantidad <= 0) {
                this.eliminar(idLibro);
                return;
            }
            item.cantidad = cantidad;
            this.guardar(items);
        }
    },

    eliminar(idLibro) {
        const items = this.leer().filter(i => i.idLibro !== idLibro);
        this.guardar(items);
    },

    limpiar() {
        localStorage.removeItem(this.CLAVE);
        this.actualizarBadge();
    },

    totalItems() {
        return this.leer().reduce((s, i) => s + i.cantidad, 0);
    },

    total() {
        return this.leer().reduce((s, i) => s + i.precio * i.cantidad, 0);
    },

    actualizarBadge() {
        const badges = document.querySelectorAll('.badge-carrito');
        const total = this.totalItems();
        badges.forEach(b => {
            b.textContent = total;
            b.style.display = total > 0 ? 'inline-block' : 'none';
        });
    }
};

document.addEventListener('DOMContentLoaded', () => Carrito.actualizarBadge());
