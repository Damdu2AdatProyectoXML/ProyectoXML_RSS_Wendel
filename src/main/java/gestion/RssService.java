package gestion;

import com.wendelledgar.proyectojson.model.RssFeed;

public interface RssService {
    public RssFeed getRssFeedFromUrl(String url);
}
