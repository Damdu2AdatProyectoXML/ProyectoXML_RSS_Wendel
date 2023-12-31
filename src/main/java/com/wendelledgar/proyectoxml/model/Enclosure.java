package com.wendelledgar.proyectoxml.model;

import javax.xml.bind.annotation.XmlAttribute;

// Enclosure se utiliza en un feed RSS para asociar un archivo multimedia, como una imagen o un archivo de audio, con un artículo específico en el feed.
public class Enclosure {
    private String url;
    private int length;
    private String type;

    @XmlAttribute
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlAttribute
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
