package com.wendelledgar.proyectoxml.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "item")
public class Item {
    private String title;
    private String link;
    private String description;
    private Enclosure enclosure;
    private Guid guid;
    private String pubDate;
    private List<String> categories;

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
    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    @XmlElement
    public Guid getGuid() {
        return guid;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    @XmlElement
    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
