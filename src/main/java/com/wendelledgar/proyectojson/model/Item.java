package com.wendelledgar.proyectojson.model;

import java.util.List;

/*
 * Representa una entrada individual en el feed RSS.
*  Contiene información como título, enlace, descripción, 
*  un objeto Enclosure que representa un archivo adjunto, 
*  un identificador único (guid), fecha de publicación (pubDate), 
*  y una lista de categorías.
 */
public class Item {
    private String title;
    private String link;
    private String description;
    private Enclosure enclosure;
    private String guid;
    private String pubDate;
    private List<String> categories;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Item [title=" + title + ", link=" + link + ", description=" + description + ", enclosure=" + enclosure
                + ", guid=" + guid + ", pubDate=" + pubDate + ", categories=" + categories + "]";
    }
}
