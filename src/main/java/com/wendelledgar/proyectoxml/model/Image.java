package com.wendelledgar.proyectoxml.model;

import javax.xml.bind.annotation.XmlElement;

// Este objeto se utiliza para proporcionar información sobre una imagen asociada con el canal o feed en sí.
public class Image {
    private String url;
    private String title;
    private String link;

    @XmlElement
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
