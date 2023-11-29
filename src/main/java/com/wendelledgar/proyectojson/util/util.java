package com.wendelledgar.proyectojson.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class util {

    /*
     * Metodo que devuelve el nombre del fichero xml a guardar
     * con la fecha actual y acabado en -list.xml
     * @Return String
     */
    public static String nombreFicheroXml(){
        Date currDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = simpleDateFormat.format(currDate);
        return formatedDate + "-list.xml";
    }

    public static boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            System.out.println("Url no válida.");
            return false;
        }
    }

}
