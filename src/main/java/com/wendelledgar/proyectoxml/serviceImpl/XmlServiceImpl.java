package com.wendelledgar.proyectoxml.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import java.net.URL;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.sun.istack.logging.Logger;
import com.wendelledgar.proyectoxml.model.RssFeed;
import com.wendelledgar.proyectoxml.service.XmlService;

public class XmlServiceImpl implements XmlService {

    public static Logger logger = Logger.getLogger(XmlServiceImpl.class);

    /**
     * Sobrescribe el método de la interfaz para obtener un InputStreamReader a
     * partir de una URL.
     * Verifica que la URL no esté vacía; en caso contrario, devuelve null.
     * 
     * @param url - La cadena que representa la URL desde la cual se obtendrá el
     *            InputStreamReader.
     * 
     * @return InputStreamReader - El lector de entrada asociado a la URL o null si
     *         la URL está vacía o no se pudo procesar.
     *
     */
    @Override
    public InputStreamReader getIsrFromUrl(String url) {

        // Comprobación de que la url no está vacia, en caso contrario devolvemos null.
        if (!url.isEmpty()) {
            URL direccion = null;
            try {
                direccion = new URL(url);
            } catch (Exception e) {
                logger.warning("Error. No se ha podido convertir el String URL en un objeto URL de Java.");
                e.printStackTrace();
            }
            InputStreamReader reader = null;
            try {
                // InputStreamReader responsable de abrir y consumir la url.
                reader = new InputStreamReader(direccion.openStream());
            } catch (IOException e) {
                logger.warning("Error. No se ha podido abrir la conexión o consumir la URL.");
                e.printStackTrace();
            }
            return reader;
        } else {
            return null;
        }
    }

    /**
     * Convierte una representación XML en formato String a un objeto RssFeed
     * utilizando JAXB.
     *
     * @param xml La cadena XML que se convertirá en un objeto RssFeed.
     * @return RssFeed El objeto RssFeed obtenido a partir del XML, o null si la
     *         entrada es nula o no se puede convertir correctamente.
     * 
     */
    @Override
    public RssFeed getRssFeedFromXml(String xml) {
        // Comprobación que el objeto no es null
        if (xml != null) {
            // Comprobacion que el parámetro es una instancia de String
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
                    logger.info("Error al convertir el xml en un objeto RssFeed.");
                    e.printStackTrace();
                    return null;
                }
            } else {
                logger.info("El objeto no es un String.");
                return null;
            }
        } else {
            logger.info("El objeto es null.");
            return null;
        }
    }

    /**
     * Convierte un objeto InputStreamReader a una cadena de texto.
     *
     * @param reader El objeto InputStreamReader que se convertirá en una cadena de
     *               texto.
     * @return String con la cadena de texto del contenido del InputStreamReader, o
     *         null si se produce un error durante la conversión.
     * 
     */
    @Override
    public String convertToString(InputStreamReader reader) {
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            // Lee las líneas del InputStreamReader y las concatena en un solo String
            return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            logger.warning("Error. No se ha podido convertir el objeto InputStreamReader en un String.");
            e.printStackTrace();
            return null;
        }
    }

}
