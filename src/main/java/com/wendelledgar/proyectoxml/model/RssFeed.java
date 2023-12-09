package com.wendelledgar.proyectoxml.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que representa un feed RSS con su canal asociado.
 * Este es el elemento principal, sólo tiene una propiedad que luego es la que engloba a las demás.
 * Un feed RSS permite a los usuarios recibir actualizaciones de sitios web, blogs u otras fuentes de contenido en un formato estandarizado.
 */

@XmlRootElement(name = "rss")
public class RssFeed {
    private Channel channel;

    @XmlElement
    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
