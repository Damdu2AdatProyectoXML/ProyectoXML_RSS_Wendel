package com.wendelledgar.proyectojson.util;

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
}
