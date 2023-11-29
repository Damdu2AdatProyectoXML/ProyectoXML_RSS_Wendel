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

        System.out.println(rss.getChannel().getTitle());
    }
}
