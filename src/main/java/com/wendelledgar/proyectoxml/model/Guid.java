package com.wendelledgar.proyectoxml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

// Guid (Globally Unique Identifier) representa un identificador único y permanente para un artículo.
public class Guid {
    // isPermaLink indica que la URL proporcionada en el contenido del Guid es
    // considerada una URL permanente y única para identificar ese elemento
    // específico.
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
