package com.wendelledgar.proyectojson.model;

import java.util.List;

import javafx.scene.image.Image;

/*
 * Contiene información sobre el canal RSS, como título, enlace, descripción, 
 * imagen, idioma, derechos de autor, fecha de última construcción (lastBuildDate), 
 * y una lista de objetos Item que representan las entradas individuales.
 */
public class Channel {
     private String title;
    private String link;
    private String description;
    private Image image;
    private String language;
    private String copyright;
    private String lastBuildDate;
    private int ttl;
    private List<Item> items;
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
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getCopyright() {
        return copyright;
    }
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    public String getLastBuildDate() {
        return lastBuildDate;
    }
    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }
    public int getTtl() {
        return ttl;
    }
    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "Channel [title=" + title + ", link=" + link + ", description=" + description + ", image=" + image
                + ", language=" + language + ", copyright=" + copyright + ", lastBuildDate=" + lastBuildDate + ", ttl="
                + ttl + ", items=" + items + "]";
    }
    
}
