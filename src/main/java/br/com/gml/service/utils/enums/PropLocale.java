package br.com.gml.service.utils.enums;

public enum  PropLocale {
    DEFAULT("DEFAULT", "en", "US");

    private String edc;
    private String language;
    private String pais;

    private PropLocale(String edc, String language, String pais) {
        this.edc = edc;
        this.language = language;
        this.pais = pais;
    }

    public String getEdc() {
        return edc;
    }

    public void setEdc(String edc) {
        this.edc = edc;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public static PropLocale getDefault() {
        return DEFAULT;
    }

    public static PropLocale toEnum(String id) {
        if (id == null) {
            return PropLocale.DEFAULT;
        }
        for (PropLocale x : PropLocale.values()) {
            if (id.equals(x.getEdc())) {
                return x;
            }
        }
        return PropLocale.DEFAULT;
    }
}
