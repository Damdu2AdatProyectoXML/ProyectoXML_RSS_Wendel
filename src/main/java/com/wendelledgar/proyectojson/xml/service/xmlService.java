package com.wendelledgar.proyectojson.xml.service;

import java.io.InputStreamReader;

import com.wendelledgar.proyectojson.model.RssFeed;

public interface xmlService {

    // Devuelve un objeto InputStreamReader a partir de la url pasada como parámetro.
    InputStreamReader getIsrFromUrl(String url);

    // Devuelve un objeto RssFeed a partir de un String con el xml.
    RssFeed getRssFeedFromXml(String xml);

    // Convertir InputStreamReader a String.
    String convertToString(InputStreamReader reader);
    
}
