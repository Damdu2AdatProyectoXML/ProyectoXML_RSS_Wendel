package com.wendelledgar.proyectoxml.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.sun.istack.logging.Logger;

public class util {

            public static Scanner scanner = new Scanner(System.in);


    public static Logger logger = Logger.getLogger(util.class);

    /*
     * Metodo que devuelve el nombre del fichero xml a guardar
     * con la fecha actual y acabado en -list.xml
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
