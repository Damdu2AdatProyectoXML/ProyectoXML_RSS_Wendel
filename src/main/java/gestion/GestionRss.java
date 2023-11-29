package gestion;

import java.io.InputStreamReader;

import com.wendelledgar.proyectojson.model.RssFeed;
import com.wendelledgar.proyectojson.xml.service.xmlService;
import com.wendelledgar.proyectojson.xml.service.xmlServiceImpl;

public class GestionRss implements RssService {

    xmlService servicio = new xmlServiceImpl();

    public static void mostrarInfoPrimerRss(RssFeed rss) {
        System.out.println("Datos obtenidos de: " + rss.getChannel().getTitle());
        System.out.println("Con fecha: " + rss.getChannel().getLastBuildDate());
        System.out.println("Noticia 1: ");
        System.out.println("Titulo noticia: " + rss.getChannel().getItems().get(0).getTitle());
        System.out.println("Link: " + rss.getChannel().getItems().get(0).getLink());
        System.out.println("Fecha de publicación: " + rss.getChannel().getItems().get(0).getPubDate());
        System.out.println("Descripción: " + rss.getChannel().getItems().get(0).getDescription());
    }

    /*
     * Devuelve un objeto RssFeed a partir de la url pasada como parametro.
     */
    @Override
    public RssFeed getRssFeedFromUrl(String url) {
        if (!url.isEmpty()) {
            try {
                InputStreamReader isr = null;
                isr = servicio.getIsrFromUrl(url);

                String xml = servicio.convertToString(isr);

                RssFeed rss = null;
                rss = servicio.getRssFeedFromXml(xml);
                return rss;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

}
