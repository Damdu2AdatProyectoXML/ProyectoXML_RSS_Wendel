package com.wendelledgar.proyectoxml.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.sun.istack.logging.Logger;

// Proporciona métodos de utilidad para operaciones comunes
public class Util {

    public static Scanner scanner = new Scanner(System.in);

    public static Logger logger = Logger.getLogger(Util.class);

    /*
     * Metodo que devuelve el nombre del fichero xml a guardar
     * con la fecha actual y acabado en -list.xml
     * Utiliza la clase Date para obtener la fecha actual.
     * Si hay un error, se registra en el logger y se devuelve null.
     * 
     * @Return String
     */
    public static String getNombreFicheroXml() {
        try {
            Date currDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String formatedDate = simpleDateFormat.format(currDate);
            return ".//src//main//java//com//wendelledgar//proyectoxml//output//" + formatedDate + "-list.xml";
        } catch (Exception e) {
            logger.warning("Error al obtener el nombre del fichero.");
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Método que verifica si una cadena es una URL válida.
     * Intenta crear una URL a partir de la cadena de texto. Si tiene éxito,
     * devuelve true,
     * de lo contrario, registra un mensaje en el logger, imprime la excepción y
     * devuelve false.
     *
     * @param url - Cadena que se verifica como URL.
     * 
     * @return boolean - true si la cadena es una URL válida, false de lo contrario.
     */
    public static boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            logger.warning("Url no válida.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * Método que solicita al usuario introducir un número. Si el usuario introduce
     * un número, lo devuelve.
     * De lo contrario, maneja la excepción y solicita nuevamente introducir un
     * número.
     *
     * @return int - Número ingresado por el usuario.
     */
    public static int solicitarOpcion() {
        try {
            System.out.print("Ingrese el número de la opción deseada: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido.");
            return solicitarOpcion();
        }
    }

}
