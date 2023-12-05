package com.wendelledgar.proyectoxml.ui;

import org.w3c.dom.Document;

import com.wendelledgar.proyectoxml.model.RssFeed;
import com.wendelledgar.proyectoxml.service.RssService;
import com.wendelledgar.proyectoxml.serviceImpl.RssServiceImpl;
import com.wendelledgar.proyectoxml.util.util;
import com.wendelledgar.proyectoxml.xml.GestionXML;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        // Nombre del fichero con el que se guardará la información obtenida.
        String nombreFichero = util.getNombreFicheroXml();

        // Ruta URL del xml.
        String url = "https://www.europapress.es/rss/rss.aspx?buscar=inteligencia-artificial";

        if (nombreFichero != null && url != null) {
            // Crear fichero xml vacio de prueba
            // GestionXML.escribirFicheroXML(nombreFichero,
            // GestionXML.crearDocumentXmlPrueba());

            // Instanciar RssServiceImpl
            RssService rssService = new RssServiceImpl();

            // Obtener objeto RssFeed llamando a servicio a partir de una url que contiene
            // el xml.
            RssFeed rss = rssService.getRssFeedFromUrl(url);

            // Mostrar por consola primer rss
            RssServiceImpl.mostrarInfoPrimerRss(rss);

            // Mostrar por consola todos los rss
            RssServiceImpl.mostrarInfoCompletoRss(rss);

            // Crear Document a partir de un xml
            Document doc = GestionXML.crearDocumentoXML(rss);

            // Escribir en nombreFichero el Document creado anteriormente
            GestionXML.escribirFicheroXML(nombreFichero, doc);

            // Leer recursivamente fichero xml
            GestionXML.leerStringBuilder(GestionXML.leerFicheroXML(nombreFichero));
        }

    }

}
