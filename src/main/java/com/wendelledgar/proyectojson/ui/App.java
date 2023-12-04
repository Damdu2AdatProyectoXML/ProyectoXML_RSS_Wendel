package com.wendelledgar.proyectojson.ui;

import com.wendelledgar.proyectojson.model.RssFeed;
import com.wendelledgar.proyectojson.service.RssService;
import com.wendelledgar.proyectojson.serviceImpl.RssServiceImpl;

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

        RssService rssService = new RssServiceImpl();

        RssFeed rss = rssService.getRssFeedFromUrl(url);

        RssServiceImpl.mostrarInfoPrimerRss(rss);

    }



}
