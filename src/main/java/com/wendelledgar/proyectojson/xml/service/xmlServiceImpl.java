package com.wendelledgar.proyectojson.xml.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.wendelledgar.proyectojson.model.RssFeed;

public class xmlServiceImpl implements xmlService {

    @Override
    public InputStreamReader getIsrFromUrl(String url) {

        /*
         * Comprobación de que la url no está vacia, en caso contrario
         * devolvemos null.
         */
        if (!url.isEmpty()) {
            URL direccion = null;
            try {
                // Definicion de la url con JSON-Strings
                direccion = new URL(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            InputStreamReader reader = null;
            try {
                // InputStreamReader responsable de abrir y consumir la url.
                reader = new InputStreamReader(direccion.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return reader;
        } else {
            return null;
        }
    }

    @Override
    public RssFeed getRssFeedFromXml(String xml) {
        // Comprobación que el objeto no es null
        if (xml != null) {
            // Comprobacion que el parámetro es una instancia de InputStreamReader
            if (xml instanceof String) {
                try {

                    // Convertir el String a un objeto RssFeed utilizando JAXB
                    JAXBContext jaxbContext = JAXBContext.newInstance(RssFeed.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                    // Deserializar el XML a un objeto RssFeed
                    StringReader reader = new StringReader(xml);
                    RssFeed rssFeed = (RssFeed) jaxbUnmarshaller.unmarshal(reader);

                    return rssFeed;
                } catch (Exception e) {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    // Función para convertir InputStreamReader a String
    @Override
    public String convertToString(InputStreamReader reader) {
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            // Lee las líneas del InputStreamReader y las concatena en un solo String
            return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
