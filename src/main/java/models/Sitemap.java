package models;

public class Sitemap {
    private String descr;
    private String path;
    private String id;

    public Sitemap(String descr, String path) {
        this.descr = descr;
        this.path = path;
    }

    public String getDescr() {
        return descr;
    }

    public String getPath() {
        return path;
    }

    public String getId() {
        return id;
    }
}
