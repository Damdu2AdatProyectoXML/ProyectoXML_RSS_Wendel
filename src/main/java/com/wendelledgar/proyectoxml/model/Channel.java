package com.wendelledgar.proyectoxml.model;

import javax.xml.bind.annotation.*;
import java.util.List;

// Channel es una parte fundamental de la estructura del feed. Contiene información sobre el canal en sí, 
// que es básicamente el sitio web o fuente de contenido que genera el feed. 
@XmlRootElement(name = "channel")
public class Channel {
    private String title;
    private String link;
    private String description;
    private Image image;
    private String language;
    private String copyright;
    private String lastBuildDate;

    // El elemento ttl se refiere a "time to live" (tiempo de vida) y especifica la
    // duración de tiempo en minutos durante la cual se considera
    // que el feed es válido y puede ser almacenado en caché antes de que sea
    // necesario volver a obtener la información más reciente del servidor.
    private int ttl;

    private List<Item> items;

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

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @XmlElement
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @XmlElement
    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @XmlElement(name = "lastBuildDate")
    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    @XmlElement
    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    @XmlElement(name = "item")
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
