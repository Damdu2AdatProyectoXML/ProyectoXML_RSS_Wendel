package com.wendelledgar.proyectojson.ui;

import com.wendelledgar.proyectojson.model.RssFeed;
import gestion.GestionRss;
import gestion.RssService;

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

        String url = "https://www.europapress.es/rss/rss.aspx?buscar=inteligencia-artificial";

        RssService rssService = new GestionRss();

        RssFeed rss = rssService.getRssFeedFromUrl(url);

        GestionRss.mostrarInfoPrimerRss(rss);

    }
}
