package com.wendelledgar.proyectojson.ui;

import java.io.InputStreamReader;

import com.wendelledgar.proyectojson.model.RssFeed;
import com.wendelledgar.proyectojson.util.util;
import com.wendelledgar.proyectojson.xml.service.xmlService;
import com.wendelledgar.proyectojson.xml.service.xmlServiceImpl;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        xmlService servicio = new xmlServiceImpl();
        String url = "https://www.europapress.es/rss/rss.aspx?buscar=inteligencia-artificial";
        InputStreamReader isr = null;
        isr = servicio.getIsrFromUrl(url);

        String xml = servicio.convertToString(isr);
        
        RssFeed rss = null;
        rss = servicio.getRssFeedFromXml(xml);

        System.out.println("Datos obtenidos de: " + rss.getChannel().getTitle());
        System.out.println("Con fecha: " + rss.getChannel().getLastBuildDate());
        System.out.println("Noticia 1: ");
        System.out.println("Titulo noticia: " + rss.getChannel().getItems().get(0).getTitle());
        System.out.println("Link: " + rss.getChannel().getItems().get(0).getLink());
        System.out.println("Fecha de publicación: " + rss.getChannel().getItems().get(0).getPubDate());
        System.out.println("Descripción: " + rss.getChannel().getItems().get(0).getDescription());
    }
}
