package com.wendelledgar.proyectoxml.serviceImpl;

import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.wendelledgar.proyectoxml.model.RssFeed;
import com.wendelledgar.proyectoxml.service.RssService;
import com.wendelledgar.proyectoxml.service.XmlService;
import com.wendelledgar.proyectoxml.util.util;

public class RssServiceImpl implements RssService {
    public static Logger logger = Logger.getLogger(RssServiceImpl.class.getName());

    XmlService servicio = new XmlServiceImpl();

    public static void mostrarInfoPrimerRss(RssFeed rss) {
        if (rss != null && rss instanceof RssFeed) {
            System.out.println("Datos obtenidos de: " + rss.getChannel().getTitle());
            System.out.println("Con fecha: " + rss.getChannel().getLastBuildDate());
            System.out.println("Noticia 1: ");
            if (rss.getChannel().getItems() != null) {
                System.out.println("Titulo noticia: " + rss.getChannel().getItems().get(0).getTitle());
                System.out.println("Link: " + rss.getChannel().getItems().get(0).getLink());
                System.out.println("Fecha de publicación: " + rss.getChannel().getItems().get(0).getPubDate());
                System.out.println("Descripción: " + rss.getChannel().getItems().get(0).getDescription());
            } else {
                logger.warning("Lista de items vacía. Puede deberse a una url incorrecta.");
            }
        } else {
            logger.warning("Error. El objeto rss " + rss + " es null o no contiene información válida.");
        }
    }

    public static void mostrarInfoCompletoRss(RssFeed rss) {
        if (rss != null && rss instanceof RssFeed) {
            System.out.println("Datos obtenidos de: " + rss.getChannel().getTitle());
            System.out.println("Con fecha: " + rss.getChannel().getLastBuildDate());
            if (rss.getChannel().getItems() != null) {

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
                logger.warning("Lista de items vacía. Puede deberse a una url incorrecta.");
            }
        } else {
            logger.warning("Error. El RssFeed " + rss + " no puede ser null.");
        }
    }

    /*
     * Devuelve un objeto RssFeed a partir de la url pasada como parametro.
     */
    @Override
    public RssFeed getRssFeedFromUrl(String url) {
        if (!url.isEmpty() && util.isValidUrl(url)) {
            try {
                InputStreamReader isr = null;
                isr = servicio.getIsrFromUrl(url);

                String xml = servicio.convertToString(isr);

                RssFeed rss = null;
                rss = servicio.getRssFeedFromXml(xml);
                return rss;
            } catch (Exception e) {
                logger.warning("Error. No se ha podido convertir el xml en un objeto RssFeed.");
                e.printStackTrace();
                return null;
            }
        } else {
            logger.warning("Error. La URL debe ser una URL válida.");
            return null;
        }
    }

}
