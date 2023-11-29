package com.wendelledgar.proyectojson.model;

/*
 * Contiene información sobre la imagen asociada al canal RSS, 
 * como URL, título y enlace.
 */
public class Image {
    private String url;
    private String title;
    private String link;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    @Override
    public String toString() {
        return "Image [url=" + url + ", title=" + title + ", link=" + link + "]";
    }
}