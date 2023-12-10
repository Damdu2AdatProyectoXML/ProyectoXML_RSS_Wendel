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

    // Se crea una instancia de Logger para la clase GestionXML.
    // Logger es una clase de registro que proporciona mensajes informativos,
    public static Logger logger = Logger.getLogger(GestionXML.class);

    /**
     * Escribe un Documento XML en un fichero en la ruta especificada.
     * 
     * @param ruta     La ruta del fichero XML.
     * @param document El Document XML que se escribirá en el fichero.
     * @return true si la operación de escritura se realiza con éxito, false si hay
     *         algún error.
     * 
     */
    public static boolean escribirFicheroXML(String ruta, Document document) {
        // Verificar que la ruta y el Document no sean nulos
        if (ruta != null && document != null) {
            try {
                logger.info("Entrando en escribirFicheroXML.");

                // Crea un objeto File para la ruta especificada
                File fichero = new File(ruta);

                // Verifica si el fichero ya existe y es un archivo
                if (fichero.exists() && fichero.isFile()) {
                    System.out.println("El fichero ya existe. ¿Desea sobreescribir? Y/N.");
                    Scanner sc = new Scanner(System.in);
                    String respuesta = sc.nextLine();
                    sc.close();

                    // Verifica si se desea sobreescribir el fichero
                    if (respuesta.equalsIgnoreCase("y")) {
                        logger.info("Sobreescribiendo fichero XML con ruta " + ruta + " .");
                    } else {
                        logger.info("No se va a sobrescribir el fichero XML " + ruta + " .");
                        return true; // No se realiza ninguna operación si no se sobrescribe
                    }
                } else {
                    logger.info("El fichero no existe. Creando nuevo fichero XML con ruta " + ruta + " .");
                }

                // Crea un TransformerFactory para transformar el Document en el fichero
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                // Especifica la fuente del documento DOM
                DOMSource source = new DOMSource(document);

                // Especifica el destino del documento XML en el archivo
                Result result = new StreamResult(new File(ruta));

                // Transforma y guarda el documento XML
                transformer.transform(source, result);
                logger.info("Operación de escritura completada con éxito.");

                return true;
            } catch (Exception e) {
                // Manejo de excepciones generales, registra el error y devuelve false
                logger.warning("Error al escribir el fichero XML: " + e.getMessage());
                return false;
            }
        } else {
            // Registra una advertencia si la ruta o el Document son nulos y devuelve false
            logger.warning("Error. La ruta o el Document no pueden ser null.");
            return false;
        }
    }

    /**
     * Lee el contenido de un archivo XML y lo devuelve como un StringBuilder.
     * 
     * @param xml La ruta del archivo XML a leer.
     * @return Un StringBuilder que contiene el contenido del archivo XML, o null si
     *         hay algún error.
     * 
     */
    public static StringBuilder leerFicheroXML(String xml) {
        // Inicializa un StringBuilder para almacenar el contenido del archivo XML
        StringBuilder contenidoXML = new StringBuilder();

        // Se utiliza un bloque try-with-resources para abrir y gestionar
        // automáticamente
        // un recurso, en este caso, un BufferedReader para leer el contenido del
        // archivo XML.
        try (BufferedReader br = new BufferedReader(new FileReader(xml))) {
            // Lee cada línea del archivo y la agrega al StringBuilder
            String linea;
            while ((linea = br.readLine()) != null) {
                // Mientras haya líneas (se comprueba que no sea null), se agrega cada línea al
                // StringBuilder contenidoXML
                contenidoXML.append(linea).append("\n");
            }
        } catch (IOException e) {
            // Manejo de excepciones en caso de error durante la lectura del archivo
            logger.warning("Error al leer el archivo XML: " + e.getMessage());
            return null; // Devuelve null en caso de error
        }

        return contenidoXML;
    }

    /**
     * Crea y devuelve un Document XML de prueba.
     * 
     * @return Un objeto Document que representa un documento XML de prueba, o null
     *         si hay algún error.
     * 
     */
    public static Document crearDocumentXmlPrueba() {
        try {
            logger.info("Entrando en crearDocumentXML.");

            // Crea un nuevo Document usando DocumentBuilderFactory
            Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            // Configura la versión del XML
            xml.setXmlVersion("1.0");

            // Crea un elemento principal llamado "prueba"
            Element main = xml.createElement("prueba");

            // Crea un nodo de texto con el contenido "prueba" y lo agrega al elemento
            // principal
            Text textoMain = xml.createTextNode("prueba");
            main.appendChild(textoMain);

            // Agrega el elemento principal al Document
            xml.appendChild(main);

            return xml;
        } catch (Exception e) {
            // Manejo de excepciones en caso de error durante la creación del documento XML
            logger.warning("Error al crear el documento XML: " + e.getMessage());
            return null;
        }
    }

    /**
     * Crea y devuelve un Document XML a partir de un objeto RssFeed.
     * 
     * @param rss El objeto RssFeed del cual se generará el documento XML.
     * @return Un objeto Document que representa el documento XML creado, o null si
     *         hay algún error.
     */
    public static Document crearDocumentoXML(RssFeed rss) {
        // Verifica que el objeto RssFeed no sea nulo y sea una instancia válida
        if (rss != null && rss instanceof RssFeed) {
            try {
                // Crea un nuevo Document usando DocumentBuilderFactory
                Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                xml.setXmlVersion("1.0");

                // Elemento raíz "rss"
                Element elementRss = xml.createElement("rss");
                xml.appendChild(elementRss);

                // Elemento "datos" con el título del canal
                Element elementDatos = xml.createElement("datos");
                Text textDatos = xml.createTextNode(rss.getChannel().getTitle());
                elementDatos.appendChild(textDatos);
                elementRss.appendChild(elementDatos);

                // Elemento "Titulo" con el título del canal
                Element elementTitle = xml.createElement("Titulo");
                Text textTitle = xml.createTextNode(rss.getChannel().getTitle());
                elementTitle.appendChild(textTitle);
                elementRss.appendChild(elementTitle);

                // Elemento "Fecha" con la fecha de la última construcción
                Element elementFecha = xml.createElement("Fecha");
                Text textFecha = xml.createTextNode(rss.getChannel().getLastBuildDate());
                elementFecha.appendChild(textFecha);
                elementRss.appendChild(elementFecha);

                // Elemento "noticias"
                Element noticias = xml.createElement("noticias");
                elementRss.appendChild(noticias);

                // Verifica si la lista de items no es nula
                if (rss.getChannel().getItems() != null) {
                    for (int i = 0; i < rss.getChannel().getItems().size(); i++) {
                        // Elemento "noticia"
                        Element noticia = xml.createElement("noticia");

                        // Atributo "numero" de la noticia
                        Attr attrNumeroNoticia = xml.createAttribute("numero");
                        Text textAttr = xml.createTextNode(String.valueOf(i));
                        attrNumeroNoticia.appendChild(textAttr);
                        noticia.setAttributeNode(attrNumeroNoticia);

                        // Elemento "title" de la noticia
                        Element elementTitulo = xml.createElement("title");
                        Text textTitle1 = xml.createTextNode(rss.getChannel().getItems().get(i).getTitle());
                        elementTitulo.appendChild(textTitle1);
                        noticia.appendChild(elementTitulo);

                        // Elemento "link" de la noticia
                        Element elementLink = xml.createElement("link");
                        Text textLink = xml.createTextNode(rss.getChannel().getItems().get(i).getLink());
                        elementLink.appendChild(textLink);
                        noticia.appendChild(elementLink);

                        // Elemento "Date" de la noticia
                        Element elementDate = xml.createElement("Date");
                        Text textFecha1 = xml.createTextNode(rss.getChannel().getItems().get(i).getPubDate());
                        elementDate.appendChild(textFecha1);
                        noticia.appendChild(elementDate);

                        // Elemento "Description" de la noticia
                        Element elementDescription = xml.createElement("Description");
                        Text textElementDescription = xml
                                .createTextNode(rss.getChannel().getItems().get(i).getDescription());
                        elementDescription.appendChild(textElementDescription);
                        noticia.appendChild(elementDescription);

                        // Agrega la noticia al elemento "noticias"
                        noticias.appendChild(noticia);
                    } // For

                    // retornamos el objeto Document.
                    return xml;

                } else {
                    logger.warning("Lista de items vacía. Puede deberse a una url incorrecta.");
                    return null;
                }

            } catch (ParserConfigurationException e) {
                // Manejo de excepciones en caso de error durante la configuración del parser
                logger.warning("Error al crear el documento XML: " + e.getMessage());
                return null;
            }
        } else {
            // Registra una advertencia si el objeto RssFeed es nulo o no es una instancia
            // válida
            logger.warning("Error. El RssFeed " + rss + " no puede ser null.");
            return null;
        }
    }

    /**
     * Realiza la lectura jerárquica de un XML representado por un StringBuilder.
     * 
     * @param stringBuilder El StringBuilder que contiene el XML a ser leído.
     */
    public static void leerStringBuilder(StringBuilder stringBuilder) {
        // Verifica que el StringBuilder no sea nulo y tenga contenido
        if (stringBuilder != null && stringBuilder.length() > 0) {
            try {
                // Crea un DocumentBuilder para parsear el XML
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                // Crea un Document a partir del contenido del StringBuilder
                Document document = documentBuilder.parse(new InputSource(new StringReader(stringBuilder.toString())));

                // Inicia la lectura jerárquica
                leerNodo(document.getDocumentElement(), 0);

                System.out.println("Lectura jerárquica del XML realizada con éxito.");
            } catch (Exception e) {
                // Manejo de excepciones en caso de error durante la lectura jerárquica
                logger.warning("Error al realizar la lectura jerárquica del XML: " + e.getMessage());
            }
        } else {
            // Registra una advertencia si el StringBuilder es nulo o vacío
            logger.warning("El StringBuilder proporcionado es nulo o vacío.");
        }
    }

    /**
     * Realiza la lectura jerárquica de un nodo XML y sus hijos.
     * 
     * @param nodo             El nodo XML a ser leído.
     * @param nivelIndentacion El nivel de indentación para imprimir la información
     *                         del nodo.
     * 
     */
    private static void leerNodo(Node nodo, int nivelIndentacion) {
        // Verifica que el nodo y el nivel de indentación sean válidos
        if (nodo != null && nivelIndentacion >= 0) {
            // Imprime la información del nodo
            imprimirNodo(nodo, nivelIndentacion);

            // Recorre los hijos del nodo actual
            NodeList hijos = nodo.getChildNodes();
            for (int i = 0; i < hijos.getLength(); i++) {
                // Creamos objeto Node con el item que estamos iterando.
                Node hijo = hijos.item(i);

                // Verifica el tipo del nodo antes de procesarlo
                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                    // Llama recursivamente a leerNodo para procesar el nodo hijo
                    leerNodo(hijo, nivelIndentacion + 1);
                } else if (hijo.getNodeType() == Node.TEXT_NODE) {
                    // Procesa nodos de texto aquí si es necesario
                    imprimirNodo(hijo, nivelIndentacion + 1);
                }
            }
        } else {
            // Registra una advertencia si el nodo o el nivel de indentación son inválidos
            logger.warning("Los objetos " + nodo + " y " + nivelIndentacion + " no pueden ser null o inválidos.");
        }
    }

    /**
     * Imprime la información de un nodo XML, incluyendo su nombre, atributos y
     * contenido (si es un nodo de texto).
     * 
     * @param nodo             El nodo XML cuya información se imprimirá.
     * @param nivelIndentacion El nivel de indentación para imprimir la información
     *                         del nodo.
     * 
     */
    private static void imprimirNodo(Node nodo, int nivelIndentacion) {
        // Verifica que el nodo y el nivel de indentación sean válidos
        if (nodo != null && nivelIndentacion >= 0) {
            // Imprime el nombre del nodo y sus atributos (si los tiene)
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
                // Imprime el contenido del nodo de texto (si es un nodo de texto)
                String contenido = nodo.getNodeValue().trim();
                if (!contenido.isEmpty()) {
                    System.out.println(getIndentacion(nivelIndentacion + 1) + contenido);
                }
            }
        } else {
            // Registra una advertencia si el nodo o el nivel de indentación son inválidos
            logger.warning("Los objetos " + nodo + " y " + nivelIndentacion + " no pueden ser null o inválidos.");
        }
    }

    /**
     * Genera y devuelve una cadena de caracteres que representa la indentación
     * según el nivel proporcionado.
     * 
     * @param nivel El nivel de indentación.
     * @return Una cadena de caracteres con espacios para representar la
     *         indentación, o null si el nivel es < 0.
     */
    private static String getIndentacion(int nivel) {
        // Verifica que el nivel de indentación sea válido
        if (nivel >= 0) {
            StringBuilder indentacion = new StringBuilder();
            for (int i = 0; i < nivel; i++) {
                indentacion.append("  "); // Dos espacios por nivel de indentación
            }
            return indentacion.toString();
        } else {
            // Registra una advertencia si el nivel de indentación es inválido
            logger.warning("El nivel pasado como parámetro en getIndentacion debe ser >= 0.");
            return null;
        }
    }

}
