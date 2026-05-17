package controllers;

import daos.ClienteDAO;
import daos.GeneroDAO;
import daos.LibroDAO;
import daos.MetodoPagoDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import models.Cliente;
import models.Genero;
import models.Libro;
import models.MetodoPago;
import models.Rol;
import util.JPAUtil;
import util.PasswordUtil;

@WebListener
public class SeedListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            JPAUtil.getInstance();

            ClienteDAO clienteDAO = new ClienteDAO();
            GeneroDAO generoDAO = new GeneroDAO();
            LibroDAO libroDAO = new LibroDAO();
            MetodoPagoDAO metodoPagoDAO = new MetodoPagoDAO();

            if (clienteDAO.buscarPorCorreo("admin@hojasueltas.com") == null) {
                Cliente admin = new Cliente(
                        "Admin", "Hoja Suelta",
                        "admin@hojasueltas.com",
                        PasswordUtil.hash("admin123"),
                        "6441234567",
                        "Calle Principal 123, Cd. Obreg\u00f3n",
                        Rol.ADMIN
                );
                clienteDAO.guardar(admin);
            }

            if (clienteDAO.buscarPorCorreo("cliente@hojasueltas.com") == null) {
                Cliente cliente = new Cliente(
                        "Mar\u00eda", "Lectora",
                        "cliente@hojasueltas.com",
                        PasswordUtil.hash("cliente123"),
                        "6447654321",
                        "Av. de los Libros 456, Cd. Obreg\u00f3n",
                        Rol.CLIENTE
                );
                clienteDAO.guardar(cliente);
            }

            String[] tiposPago = {"TARJETA", "TRANSFERENCIA", "CONTRA_ENTREGA"};
            for (String t : tiposPago) {
                if (metodoPagoDAO.buscarPorTipo(t) == null) {
                    metodoPagoDAO.guardar(new MetodoPago(t));
                }
            }

            String[][] generos = {
                {"Ficci\u00f3n", "ficcion"},
                {"No ficci\u00f3n", "no-ficcion"},
                {"Fantas\u00eda", "fantasia"},
                {"Misterio", "misterio"},
                {"Romance", "romance"},
                {"Ciencia ficci\u00f3n", "scifi"},
                {"Cl\u00e1sicos", "clasicos"},
                {"Infantil", "infantil"}
            };
            for (String[] g : generos) {
                if (generoDAO.buscarPorNombre(g[0]) == null) {
                    generoDAO.guardar(new Genero(g[0]));
                }
            }

            if (libroDAO.listar().isEmpty()) {
                Genero ficcion = generoDAO.buscarPorNombre("Ficci\u00f3n");
                Genero fantasia = generoDAO.buscarPorNombre("Fantas\u00eda");
                Genero clasicos = generoDAO.buscarPorNombre("Cl\u00e1sicos");
                Genero misterio = generoDAO.buscarPorNombre("Misterio");
                Genero scifi = generoDAO.buscarPorNombre("Ciencia ficci\u00f3n");
                Genero romance = generoDAO.buscarPorNombre("Romance");
                Genero noFiccion = generoDAO.buscarPorNombre("No ficci\u00f3n");
                Genero infantil = generoDAO.buscarPorNombre("Infantil");

                Libro[] libros = {
                    libro("9786073102063", "Cien a\u00f1os de soledad", "Gabriel Garc\u00eda M\u00e1rquez",
                            "La saga de la familia Buend\u00eda en el m\u00edtico pueblo de Macondo, una obra cumbre del realismo m\u00e1gico latinoamericano.",
                            349.0, 25, 1967, "Editorial Sudamericana",
                            "https://m.media-amazon.com/images/I/81oG9G3VHzL._SL1500_.jpg", true, ficcion),
                    libro("9788491051787", "1984", "George Orwell",
                            "Una sociedad totalitaria dominada por el Gran Hermano, donde la libertad y el pensamiento son perseguidos.",
                            299.0, 30, 1949, "Debolsillo",
                            "https://m.media-amazon.com/images/I/71kxa1-0mfL._SL1500_.jpg", true, scifi),
                    libro("9788445077528", "El nombre del viento", "Patrick Rothfuss",
                            "La historia de Kvothe, m\u00fasico, mago y aventurero, narrada por \u00e9l mismo en una fonda escondida.",
                            459.0, 18, 2007, "Plaza & Jan\u00e9s",
                            "https://m.media-amazon.com/images/I/91MtMPnLIcL._SL1500_.jpg", true, fantasia),
                    libro("9786073805438", "Don Quijote de la Mancha", "Miguel de Cervantes",
                            "Las aventuras del ingenioso hidalgo y su fiel escudero Sancho Panza, considerada la primera novela moderna.",
                            399.0, 12, 1605, "Real Academia Espa\u00f1ola",
                            "https://m.media-amazon.com/images/I/81vLVhFnyWL._SL1500_.jpg", false, clasicos),
                    libro("9788408172178", "El c\u00f3digo Da Vinci", "Dan Brown",
                            "Robert Langdon investiga un asesinato en el Louvre que lo llevar\u00e1 por una pista de s\u00edmbolos antiguos.",
                            329.0, 22, 2003, "Planeta",
                            "https://m.media-amazon.com/images/I/81kNuG-yLnL._SL1500_.jpg", false, misterio),
                    libro("9786077358640", "Orgullo y prejuicio", "Jane Austen",
                            "La historia de Elizabeth Bennet y el se\u00f1or Darcy, un cl\u00e1sico de la literatura rom\u00e1ntica.",
                            279.0, 28, 1813, "Penguin Cl\u00e1sicos",
                            "https://m.media-amazon.com/images/I/91HHxxtA1wL._SL1500_.jpg", true, romance),
                    libro("9788478886456", "Sapiens", "Yuval Noah Harari",
                            "Un recorrido fascinante por la historia de la humanidad desde la edad de piedra hasta la actualidad.",
                            429.0, 20, 2011, "Debate",
                            "https://m.media-amazon.com/images/I/713jIoMO3UL._SL1500_.jpg", false, noFiccion),
                    libro("9788490661079", "El principito", "Antoine de Saint-Exup\u00e9ry",
                            "Un peque\u00f1o pr\u00edncipe de un asteroide visita la Tierra y comparte profundas lecciones sobre la vida y el amor.",
                            199.0, 40, 1943, "Salamandra",
                            "https://m.media-amazon.com/images/I/71OZY035QKL._SL1500_.jpg", true, infantil),
                    libro("9788466353175", "Crimen y castigo", "Fi\u00f3dor Dostoyevski",
                            "Un estudiante comete un crimen para probar su teor\u00eda y debe lidiar con las consecuencias morales.",
                            369.0, 15, 1866, "Alianza Editorial",
                            "https://m.media-amazon.com/images/I/81jQOL2vwIL._SL1500_.jpg", false, clasicos),
                    libro("9788478886425", "Fahrenheit 451", "Ray Bradbury",
                            "En un futuro donde los libros est\u00e1n prohibidos, un bombero encargado de quemarlos comienza a cuestionar su misi\u00f3n.",
                            289.0, 26, 1953, "Minotauro",
                            "https://m.media-amazon.com/images/I/41Z9dKGPLEL.jpg", false, scifi),
                    libro("9788499085951", "Los pilares de la Tierra", "Ken Follett",
                            "La construcci\u00f3n de una catedral en la Inglaterra del siglo XII, un \u00e9pico relato hist\u00f3rico.",
                            489.0, 14, 1989, "Plaza & Jan\u00e9s",
                            "https://m.media-amazon.com/images/I/91GDYSQX6PL._SL1500_.jpg", false, ficcion),
                    libro("9788490326220", "Matar a un ruise\u00f1or", "Harper Lee",
                            "En el sur de Estados Unidos, una ni\u00f1a observa c\u00f3mo su padre defiende a un hombre negro injustamente acusado.",
                            319.0, 19, 1960, "HarperCollins",
                            "https://m.media-amazon.com/images/I/81GlZj1GenL._SL1500_.jpg", false, clasicos)
                };
                for (Libro l : libros) {
                    libroDAO.guardar(l);
                }
            }
        } catch (Exception e) {
            System.err.println("error en seed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Libro libro(String isbn, String titulo, String autor, String desc, double precio,
                        int stock, int anio, String editorial, String img, boolean dest, Genero g) {
        return new Libro(isbn, titulo, autor, desc, precio, stock, anio, editorial, img, dest, g);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JPAUtil.getInstance().close();
    }
}
