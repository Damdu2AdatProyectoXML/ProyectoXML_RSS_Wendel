package com.wendelledgar.proyectojson.model;

/*
 * Representa el feed RSS completo.
 * Contiene un objeto Channel que representa la información principal del canal.
 */
public class RssFeed {
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "RssFeed [channel=" + channel + "]";
    }
    
}
