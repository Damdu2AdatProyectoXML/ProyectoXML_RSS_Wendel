package com.wendelledgar.proyectoxml.service;

import java.io.InputStreamReader;

import com.wendelledgar.proyectoxml.model.RssFeed;

/**
 * Interfaz que define servicios relacionados con operaciones XML.
 */
public interface XmlService {

    /**
     * Obtiene un objeto InputStreamReader a partir de la URL proporcionada.
     * 
     * @param url La URL para la cual se obtendrá el InputStreamReader.
     * @return Un objeto InputStreamReader para la URL, o null si hay algún error.
     */
    InputStreamReader getIsrFromUrl(String url);

    /**
     * Obtiene un objeto RssFeed a partir de una cadena XML.
     * 
     * @param xml La cadena XML a partir de la cual se construirá el objeto RssFeed.
     * @return Un objeto RssFeed representando la información del feed, o null si
     *         hay algún error.
     */
    RssFeed getRssFeedFromXml(String xml);

    /**
     * Convierte un objeto InputStreamReader a una cadena de texto.
     * 
     * @param reader El InputStreamReader a convertir.
     * @return Una cadena de texto representando el contenido del InputStreamReader,
     *         o null si hay algún error.
     */
    String convertToString(InputStreamReader reader);
}
