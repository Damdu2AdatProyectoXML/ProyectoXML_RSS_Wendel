package com.wendelledgar.proyectojson.model;

/*
 * Contiene información sobre un archivo adjunto a una entrada, 
 * como URL, longitud y tipo.
 */
public class Enclosure {
    private String url;
    private int length;
    private String type;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Enclosure [url=" + url + ", length=" + length + ", type=" + type + "]";
    }
}
