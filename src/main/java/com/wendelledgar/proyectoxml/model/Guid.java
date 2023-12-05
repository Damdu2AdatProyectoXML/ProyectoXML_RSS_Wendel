package com.wendelledgar.proyectoxml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Guid {
    private boolean isPermaLink;
    private String value;

    @XmlAttribute(name = "isPermaLink")
    public boolean isPermaLink() {
        return isPermaLink;
    }

    public void setPermaLink(boolean permaLink) {
        isPermaLink = permaLink;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
