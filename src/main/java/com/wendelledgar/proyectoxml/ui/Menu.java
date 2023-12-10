package com.wendelledgar.proyectoxml.ui;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

import org.w3c.dom.Document;

import com.wendelledgar.proyectoxml.model.RssFeed;
import com.wendelledgar.proyectoxml.serviceImpl.RssServiceImpl;
import com.wendelledgar.proyectoxml.util.Util;
import com.wendelledgar.proyectoxml.xml.GestionXML;

public class Menu {

    // Logger para registrar información y errores
    public static Logger logger = Logger.getLogger(Menu.class.getName());

    // Scanner para leer la entrada del usuario desde la consola
    public static Scanner scanner = new Scanner(System.in);

    // Objeto RssFeed para almacenar datos de la fuente RSS
    public static RssFeed rss;

    // Número asociado a la opción de salida
    public static int SALIR = 6;

    // Nombre del fichero con el que se guardará la información obtenida.
    public static String nombreFichero = Util.getNombreFicheroXml();

    // Método para lanzar y mantener en ejecución el menú
    public static void lanzarMenu() {
        // Bucle que se ejecuta continuamente hasta que se elige la opción de salida
        while (true) {

            // Muestra el menú de opciones al usuario
            mostrarMenu();

            // Solicita al usuario que ingrese una opción
            int opcion = Util.solicitarOpcion();

            // Ejecuta la opción seleccionada por el usuario
            ejecutarOpcion(opcion);
        }
    }

    // Método para mostrar el menú de opciones
    public static void mostrarMenu() {
        System.out.println();
        System.out.println("Seleccione una opción:");
        System.out.println("1. Obtener datos de una Rss");
        System.out.println("2. Guardar datos Rss en fichero xml");
        System.out.println("3. Mostrar una noticia de la Rss");
        System.out.println("4. Leer el xml completo");
        System.out.println("5. Leer RssFeed completo desde cache.");
        System.out.println("6. Salir");
    }

    /**
     * Método para ejecutar la opción seleccionada por el usuario, según la opción
     * pasada como parámetro ejecutamos un trozo de código diferente.
     * 
     * @param opcion - entero con la opcion seleccionada
     */
    public static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                // Obtener datos de una Rss
                String url = obtenerRutaUrlRss();
                rss = RssServiceImpl.getRssFeedFromUrl(url);
                break;
            case 2:
                // Guardar datos Rss en fichero xml
                guardarDatosRssEnFicheroXml();
                break;
            case 3:
                // Mostrar una noticia de la Rss
                mostrarNoticiasDeRss();
                break;
            case 4:
                // Leer el xml completo
                leerXmlCompleto();
                break;
            case 5:
                // Leer RssFeed completo desde cache
                leerRssFeedFromCache();
                break;
            case 6:
                // Salir
                System.out.println();
                System.out.println("Saliendo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

    // Método privado para obtener la ruta URL de la Rss
    private static String obtenerRutaUrlRss() {
        System.out.print(
                "Escribe palabras clave sobre lo que desees buscar. (presione Enter para la URL predeterminada): ");
        String inputUrl = scanner.nextLine().trim();
        inputUrl = inputUrl.replace(" ", "-");

        String palabraPorDefecto = "inteligencia-artificial";
        String urlPorDefecto = "https://www.europapress.es/rss/rss.aspx?buscar=";

        // Construir la URL final
        String url = inputUrl.isEmpty() ? urlPorDefecto + palabraPorDefecto : urlPorDefecto + inputUrl;

        System.out.println();
        System.out.println("Rss guardada en caché.");

        return url;
    }

    // Método privado para guardar datos Rss en fichero xml
    private static void guardarDatosRssEnFicheroXml() {
        System.out.println();
        System.out.println("Guardando fichero xml");

        // Crear Document a partir de un xml
        Document doc = GestionXML.crearDocumentoXML(rss);

        // Escribir en nombreFichero el Document creado anteriormente
        GestionXML.escribirFicheroXML(nombreFichero, doc);
    }

    // Método privado para mostrar noticias de la Rss
    private static void mostrarNoticiasDeRss() {
        System.out.println();
        System.out.println("Mostrar noticia rss");

        // Mostrar por consola primer rss
        RssServiceImpl.mostrarInfoRssByNum(rss);
    }

    // Método privado para leer el xml completo
    private static void leerXmlCompleto() {
        System.out.println();

        File file = new File(nombreFichero);
        if (file.exists() && file.isFile()) {
            logger.info("Leyendo fichero xml.");
            // Leer recursivamente fichero xml en vez de leer desde la caché
            GestionXML.leerStringBuilder(GestionXML.leerFicheroXML(nombreFichero));
        } else {
            logger.warning("El fichero xml no existe.");
        }
    }

    // Método privado para leer RssFeed completo desde cache
    private static void leerRssFeedFromCache() {
        try {
            if (rss != null && rss.getChannel() != null && rss.getChannel().getItems().size() > 0) {
                // Mostrar por consola todos los rss del objeto guardado en caché RssFeed
                RssServiceImpl.mostrarInfoCompletoRss(rss);
            } else {
                logger.warning(
                        "Url inválida o todavía no se ha cargado en caché. Pulsa la opción 1: Obtener datos de una Rss.");
            }
        } catch (Exception e) {
            logger.warning("Error al leer RssFeed desde cache. Detalles: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
