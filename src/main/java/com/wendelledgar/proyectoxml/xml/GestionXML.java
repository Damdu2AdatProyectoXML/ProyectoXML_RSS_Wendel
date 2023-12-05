package com.wendelledgar.proyectoxml.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Result;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import com.sun.istack.logging.Logger;
import com.wendelledgar.proyectoxml.model.RssFeed;

public class GestionXML {

    public static Logger logger = Logger.getLogger(GestionXML.class);

    public static boolean escribirFicheroXML(String ruta, Document document) {
        if (ruta != null && document != null) {
            try {
                logger.info("Entrando en escribirFicheroXML con parámetros " + ruta + " y Document.");
    
                File fichero = new File(ruta);
                if (fichero.exists() && fichero.isFile()) {
                    System.out.println("El fichero ya existe. ¿Desea sobreescribir? Y/N.");
                    Scanner sc = new Scanner(System.in);
                    String respuesta = sc.nextLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        logger.info("Sobreescribiendo fichero XML con ruta " + ruta + " .");
                        // Crear un TransformerFactory
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
    
                        // Especificar la fuente del documento DOM
                        DOMSource source = new DOMSource(document);
    
                        // Especificar el destino del documento XML en el archivo
                        Result result = new StreamResult(new File(ruta));
    
                        // Transformar y guardar el documento XML
                        transformer.transform(source, result);
                        logger.info("El fichero se ha sobreescrito con éxito.");
                    } else {
                        logger.info("No se va a sobrescribir el fichero XML " + ruta + " .");
                    }
                } else {
                    logger.info("El fichero no existe. Creando nuevo fichero XML con ruta " + ruta + " .");
                    // Crear un TransformerFactory
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
    
                    // Especificar la fuente del documento DOM
                    DOMSource source = new DOMSource(document);
    
                    // Especificar el destino del documento XML en el archivo
                    Result result = new StreamResult(new File(ruta));
    
                    // Transformar y guardar el documento XML
                    transformer.transform(source, result);
                    logger.info("El nuevo fichero XML se ha creado con éxito.");
                }
                return true;
            } catch (Exception e) {
                logger.warning("Error al escribir el fichero XML: " + e.getMessage());
                return false;
            }
        } else {
            logger.warning("Error. La ruta o el Document no pueden ser null.");
            return false;
        }
    }

    public static StringBuilder leerFicheroXML(String xml) {
        StringBuilder contenidoXML = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(xml))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenidoXML.append(linea).append("\n");
            }
        } catch (IOException e) {
            logger.warning("Error. No se ha podido convertir el documento xml en un StringBuilder." + e.getMessage());
        }

        return contenidoXML;
    }

    public static Document crearDocumentXmlPrueba() {
        try {
            logger.info("Entrando en crearDocumentXML.");
            Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            xml.setXmlVersion("1.0");
            Element main = xml.createElement("prueba");
            Text textoMain = xml.createTextNode("prueba");
            main.appendChild(textoMain);
            xml.appendChild(main);
            return xml;
        } catch (Exception e) {
            logger.warning("Error al crear el documento XML: " + e.getMessage());
            return null;
        }
    }

    public static Document crearDocumentoXML(RssFeed rss) {
        if (rss != null && rss instanceof RssFeed) {
            try {
                Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                xml.setXmlVersion("1.0");

                // Elemento raíz rss
                Element elementRss = xml.createElement("rss");
                xml.appendChild(elementRss);

                // Elemento datos
                Element elementDatos = xml.createElement("datos");
                Text textDatos = xml.createTextNode(rss.getChannel().getTitle());
                elementDatos.appendChild(textDatos);
                elementRss.appendChild(elementDatos);

                // Elemento Title
                Element elementTitle = xml.createElement("Titulo");
                Text textTitle = xml.createTextNode(rss.getChannel().getTitle());
                elementTitle.appendChild(textTitle);
                elementRss.appendChild(elementTitle);

                // Elemento fecha
                Element elementFecha = xml.createElement("Fecha");
                Text textFecha = xml.createTextNode(rss.getChannel().getLastBuildDate());
                elementFecha.appendChild(textFecha);
                elementRss.appendChild(elementFecha);

                // Elemento noticias
                Element noticias = xml.createElement("noticias");
                elementRss.appendChild(noticias);

                if (rss.getChannel().getItems() != null) {
                    for (int i = 0; i < rss.getChannel().getItems().size(); i++) {
                        // Elemento noticia
                        Element noticia = xml.createElement("noticia");

                        // Atributo numero de noticia
                        Attr attrNumeroNoticia = xml.createAttribute("numero");
                        Text textAttr = xml.createTextNode(String.valueOf(i));
                        attrNumeroNoticia.appendChild(textAttr);
                        noticia.setAttributeNode(attrNumeroNoticia);

                        // Elemento titulo de la noticia
                        Element elementTitulo = xml.createElement("title");
                        Text textTitle1 = xml.createTextNode(rss.getChannel().getItems().get(i).getTitle());
                        elementTitulo.appendChild(textTitle1);
                        noticia.appendChild(elementTitulo);

                        // Elemento Link de noticia
                        Element elementLink = xml.createElement("link");
                        Text textLink = xml.createTextNode(rss.getChannel().getItems().get(i).getLink());
                        elementLink.appendChild(textLink);
                        noticia.appendChild(elementLink);

                        // Elemento fecha de la noticia
                        Element elementDate = xml.createElement("Date");
                        Text textFecha1 = xml.createTextNode(rss.getChannel().getItems().get(i).getPubDate());
                        elementDate.appendChild(textFecha1);
                        noticia.appendChild(elementDate);

                        // Elemento descripcion de la noticia
                        Element elementDescription = xml.createElement("Description");
                        Text textElementDescription = xml
                                .createTextNode(rss.getChannel().getItems().get(i).getDescription());
                        elementDescription.appendChild(textElementDescription);
                        noticia.appendChild(elementDescription);

                        // Agregar noticia en noticias
                        noticias.appendChild(noticia);
                    } // For

                    return xml;

                } else {
                    logger.warning("Lista de items vacía. Puede deberse a una url incorrecta.");
                    return null;
                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            logger.warning("Error. El RssFeed " + rss + " no puede ser null.");
            return null;
        }
    }

    public static void leerStringBuilder(StringBuilder stringBuilder) {
        if (stringBuilder != null && stringBuilder.length() > 0) {
            try {
                // Crear un DocumentBuilder para parsear el XML
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                // Crear un Document a partir del contenido del StringBuilder
                Document document = documentBuilder.parse(new InputSource(new StringReader(stringBuilder.toString())));

                // Iniciar la lectura jerárquica
                leerNodo(document.getDocumentElement(), 0);

                System.out.println("Lectura jerárquica del XML realizada con éxito.");
            } catch (Exception e) {
                logger.warning(
                        "Error al realizar la lectura jerárquica del XML. Podria deberse a que el XML no tiene ningun elemento padre : "
                                + e.getMessage());
            }
        } else {
            logger.warning("El StringBuilder proporcionado es nulo o vacío.");
        }
    }

    private static void leerNodo(Node nodo, int nivelIndentacion) {
        if (nodo != null && nivelIndentacion >= 0) {
            // Imprimir la información del nodo
            imprimirNodo(nodo, nivelIndentacion);

            // Recorrer los hijos del nodo actual
            NodeList hijos = nodo.getChildNodes();
            for (int i = 0; i < hijos.getLength(); i++) {
                Node hijo = hijos.item(i);

                // Verificar el tipo del nodo antes de procesarlo
                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                    leerNodo(hijo, nivelIndentacion + 1);
                } else if (hijo.getNodeType() == Node.TEXT_NODE) {
                    // Procesar nodos de texto aquí si es necesario
                    imprimirNodo(hijo, nivelIndentacion + 1);
                }
            }
        } else {
            logger.warning("Los objetos " + nodo + " y " + nivelIndentacion + " no pueden ser null.");
        }

    }

    private static void imprimirNodo(Node nodo, int nivelIndentacion) {
        if (nodo != null && nivelIndentacion >= 0) {
            // Imprimir el nombre del nodo y sus atributos (si los tiene)
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) nodo;
                System.out.print(getIndentacion(nivelIndentacion) + "<" + elemento.getNodeName());

                NamedNodeMap atributos = elemento.getAttributes();
                for (int i = 0; i < atributos.getLength(); i++) {
                    Attr atributo = (Attr) atributos.item(i);
                    System.out.print(" " + atributo.getName() + "=\"" + atributo.getValue() + "\"");
                }
                System.out.println(">");
            } else if (nodo.getNodeType() == Node.TEXT_NODE) {
                // Imprimir el contenido del nodo de texto (si es un nodo de texto)
                String contenido = nodo.getNodeValue().trim();
                if (!contenido.isEmpty()) {
                    System.out.println(getIndentacion(nivelIndentacion + 1) + contenido);
                }
            }
        } else {
            logger.warning("Los objetos " + nodo + " y " + nivelIndentacion + " no pueden ser null.");
        }
    }

    private static String getIndentacion(int nivel) {
        if (nivel >= 0) {
            StringBuilder indentacion = new StringBuilder();
            for (int i = 0; i < nivel; i++) {
                indentacion.append("  "); // Dos espacios por nivel de indentación
            }
            return indentacion.toString();
        }
        logger.warning("El nivel pasado como parametro en getIndentacion debe ser >= 0.");
        return null;
    }

}
