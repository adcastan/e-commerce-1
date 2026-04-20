<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Administrador, models.Cliente"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Librería Fantástica - Catálogo</title>
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Felipa&family=Lugrasimo&family=Quintessential&display=swap" rel="stylesheet">

    <style>
        :root {
            --primario: #388659;
            --secundario: #1C3738;
            --acento: #A79C70;
            --fondo: #121212;
            --error: #C1292E;
        }

        body {
            background-color: var(--fondo);
            color: white;
            font-family: 'Lugrasimo', cursive;
            margin: 0;
        }

        header {
            background: linear-gradient(to bottom, var(--secundario), #000);
            padding: 20px 50px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 3px solid var(--primario);
            box-shadow: 0 5px 15px rgba(0,0,0,0.5);
        }

        .logo {
            font-family: 'Felipa', serif;
            font-size: 32px;
            color: var(--acento);
            text-shadow: 2px 2px 4px #000;
        }

        .search-bar input {
            padding: 10px;
            border-radius: 20px;
            border: 2px solid var(--primario);
            background: #1c1c1c;
            color: white;
            width: 300px;
        }

        .nav-buttons {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .btn {
            background: var(--primario);
            color: white;
            border: none;
            padding: 10px 20px;
            font-family: 'Quintessential', cursive;
            cursor: pointer;
            border-radius: 5px;
            transition: 0.3s;
        }

        .btn-logout {
            background: var(--error);
            color: white;
            border: 2px solid white;
            padding: 8px 15px;
            font-family: 'Quintessential', cursive;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
        }

        .btn-logout:hover {
            background: white;
            color: var(--error);
        }

        .container { padding: 40px; }

        h2 {
            font-family: 'Felipa', serif;
            font-size: 35px;
            color: var(--primario);
            border-bottom: 2px solid var(--acento);
            display: inline-block;
            margin-bottom: 30px;
        }

        .books-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 30px;
        }

        .book-card {
            background: #1c1c1c;
            padding: 15px;
            border-radius: 10px;
            border: 1px solid var(--secundario);
            text-align: center;
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .book-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 10px 20px var(--primario);
            border-color: var(--acento);
        }

        .book-img {
            height: 250px;
            background: var(--secundario);
            margin-bottom: 15px;
            border-radius: 5px;
            /* Aquí irían las portadas de los libros */
        }

        .book-title { font-weight: bold; margin-bottom: 10px; min-height: 50px; }
        .book-price { color: var(--acento); font-size: 20px; }
        .book-link { text-decoration: none; color: inherit; }
    </style>
</head>
<body>

    <header>
        <div class="logo">Librería Equipo 1</div>

        <div class="search-bar">
            <input placeholder="Buscar tu próxima historia...">
        </div>

        <div class="nav-buttons">
            <a href="carrito.jsp"><button class="btn">Carrito 🛒</button></a>

            <% 
                // Lógica para detectar sesión
                Object usuario = session.getAttribute("usuarioLogueado");
                if (usuario != null) {
            %>
                <span style="color: var(--acento); margin-right: 10px;">
                    ¡Hola, <%= session.getAttribute("nombreAdmin") != null ? session.getAttribute("nombreAdmin") : "Lector" %>!
                </span>
                <a href="LogoutServlet" class="btn-logout">Cerrar Sesión ❖</a>
            <% } else { %>
                <a href="IniciarSesion.jsp"><button class="btn" style="background: var(--acento); color: var(--secundario);">Iniciar sesión</button></a>
            <% } %>
        </div>
    </header>

    <div class="container">
        <section class="section">
            <h2>Destacado</h2>
            <div class="books-grid">
                <a href="verProducto.jsp" class="book-link">
                    <div class="book-card">
                        <div class="book-img"></div>
                        <div class="book-title">El nombre del viento</div>
                        <div class="book-price">$350</div>
                    </div>
                </a>
                </div>
        </section>
    </div>

</body>
</html>