package com.wendelledgar.proyectoxml.serviceImpl;

import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

import com.wendelledgar.proyectoxml.model.Item;
import com.wendelledgar.proyectoxml.model.RssFeed;
import com.wendelledgar.proyectoxml.service.XmlService;
import com.wendelledgar.proyectoxml.util.util;

public class RssServiceImpl {

    // Logger para la clase RssServiceImpl
    public static Logger logger = Logger.getLogger(RssServiceImpl.class.getName());

    // Instancia de XmlService para realizar operaciones relacionadas con XML
    public static XmlService servicio = new XmlServiceImpl();

    /**
     * Método estático para mostrar información de la primera noticia en el RssFeed
     * 
     * @param rss El objeto RssFeed del cual mostrar la información.
     */
    public static void mostrarInfoRssByNum(RssFeed rss) {
        try {
            // Verifica si el objeto RssFeed no es nulo y es una instancia válida de RssFeed
            if (rss != null && rss instanceof RssFeed) {
                // Imprime el título y la fecha del canal
                System.out.println("Datos obtenidos de: " + rss.getChannel().getTitle());
                System.out.println("Con fecha: " + rss.getChannel().getLastBuildDate());
                System.out.println("Primera noticia del Canal. ");

                System.out.println("Hay " + rss.getChannel().getItems().size() + " noticias en el canal.");

                // Mostrar titulos de las noticias para posteriormente seleccionar una y mostrar informacion mas detallada
                muestraTitulosRss(rss);

                System.out.println(
                        "Que noticia deseas ver? pulsa un numero del 1 al " + rss.getChannel().getItems().size());

                // Leemos por teclado el numero
                int opcion = util.solicitarOpcion() - 1;

                // Verifica si la lista de items no es nula
                if (rss.getChannel().getItems() != null && opcion >= 0
                        && opcion <= (rss.getChannel().getItems().size() - 1)) {
                    // Imprime detalles de la primera noticia si existen items en la lista
                    System.out.println("");
                    System.out.println("Noticia número: " + (opcion + 1));
                    System.out.println("Titulo noticia: " + rss.getChannel().getItems().get(opcion).getTitle());
                    System.out.println("Link: " + rss.getChannel().getItems().get(opcion).getLink());
                    System.out.println("Fecha de publicación: " + rss.getChannel().getItems().get(opcion).getPubDate());
                    System.out.println("Descripción: " + rss.getChannel().getItems().get(opcion).getDescription());
                } else {
                    // Registra una advertencia si la lista de items está vacía
                    logger.warning("Error. Numero incorrecto debe ingresar un numero positivo y en el rango correcto.");
                }
            } else {
                // Registra una advertencia si el objeto RssFeed es nulo o no es una instancia válida de RssFeed
                logger.warning("Error. El objeto rss " + rss + " es null o no contiene información válida.");
            }
        } catch (Exception e) {
            logger.warning("Error inesperado al mostrar información de la primera noticia del RssFeed.");
            e.printStackTrace();
        }
    }

    public static void muestraTitulosRss(RssFeed rss) {
        try {
            // Verifica si el objeto RssFeed y sus componentes no son nulos
            if (rss != null && rss.getChannel() != null && rss.getChannel().getItems() != null) {
                // Obtiene la lista de noticias
                List<Item> items = rss.getChannel().getItems();
                int totalNoticias = items.size();

                // Itera sobre la lista de noticias
                for (int i = 0; i < totalNoticias; i++) {
                    // Obtiene cada noticia individual
                    Item item = items.get(i);
                    // Muestra el título de la noticia junto con su número
                    System.out.println("Noticia número " + (i + 1) + ": " + item.getTitle());
                }
            } else {
                // Imprime un mensaje si el objeto RssFeed o sus componentes son nulos
                System.out.println("El objeto RssFeed o sus componentes son nulos.");
            }
        } catch (Exception e) {
            // Captura y maneja cualquier excepción que pueda ocurrir durante la ejecución
            System.out.println("Se ha producido una excepción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método estático para mostrar información completa de todas las noticias en el
     * RssFeed
     * 
     * @param rss El objeto RssFeed del cual mostrar la información completa.
     */
    public static void mostrarInfoCompletoRss(RssFeed rss) {
        try {
            // Verifica si el objeto RssFeed no es nulo y también si es una instancia válida de RssFeed
            if (rss != null && rss instanceof RssFeed) {
                // Mostrar título y la fecha del canal
                System.out.println("Datos obtenidos de: " + rss.getChannel().getTitle());
                System.out.println("Con fecha: " + rss.getChannel().getLastBuildDate());

                // Verifica si la lista de items no es nula
                if (rss.getChannel().getItems() != null) {
                    // Itera sobre la lista de items y muestra información detallada para cada uno
                    for (int i = 0; i < rss.getChannel().getItems().size(); i++) {
                        System.out.println();
                        System.out.println("Noticia numero: " + i);
                        System.out.println("Titulo noticia: " + rss.getChannel().getItems().get(i).getTitle());
                        System.out.println("Link: " + rss.getChannel().getItems().get(i).getLink());
                        System.out.println("Fecha de publicación: " + rss.getChannel().getItems().get(i).getPubDate());
                        System.out.println("Descripción: " + rss.getChannel().getItems().get(i).getDescription());
                        System.out.println("\n----------");
                    }

                } else {
                    // Registra una advertencia si la lista de items está vacía
                    logger.warning("Lista de items vacía. Puede deberse a una url incorrecta.");
                }
            } else {
                // Registra una advertencia si el objeto RssFeed es nulo o no es una instancia válida de RssFeed
                logger.warning("Error. El RssFeed " + rss + " no puede ser null.");
            }
        } catch (Exception e) {
            // Manejo de excepciones generales, registra la excepción y muestra los errores.
            logger.warning("Error inesperado al mostrar información completa del RssFeed.");
            e.printStackTrace();
        }

    }

    /**
     * Devuelve un objeto RssFeed a partir de la URL pasada como parámetro.
     * 
     * @param url La URL del feed RSS.
     * 
     * @return Un objeto RssFeed obtenido desde la URL, o null en caso de error.
     */
    public static RssFeed getRssFeedFromUrl(String url) {
        // Verifico que la URL no esté vacía y sea válida
        if (!url.isEmpty() && util.isValidUrl(url)) {
            try {
                // Obtengo un InputStreamReader a partir de la URL
                InputStreamReader isr = servicio.getIsrFromUrl(url);

                // Convierto el InputStreamReader a un String
                String xml = servicio.convertToString(isr);

                // Convierto el String XML a un objeto RssFeed
                RssFeed rss = servicio.getRssFeedFromXml(xml);
                return rss;
            } catch (Exception e) {
                // Manejo de errores en caso de problemas al convertir el XML en un objeto RssFeed
                logger.warning("Error. No se ha podido convertir el xml en un objeto RssFeed.");
                e.printStackTrace();
                return null;
            }
        } else {
            // Manejo de errores cuando la URL no es válida o está vacía
            logger.warning("Error. La URL debe ser una URL válida.");
            return null;
        }
    }

}
