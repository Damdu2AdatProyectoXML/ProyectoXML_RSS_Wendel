package com.wendelledgar.proyectoxml.service;

import com.wendelledgar.proyectoxml.model.RssFeed;

/**
 * Interfaz que define un servicio para obtener un objeto RssFeed a partir de una URL.
 */
public interface RssService {

    /**
     * Obtiene un objeto RssFeed a partir de la URL proporcionada.
     * 
     * @param url La URL del feed RSS.
     * @return Un objeto RssFeed representando la información del feed, o null si hay algún error.
     */
    RssFeed getRssFeedFromUrl(String url);
}
