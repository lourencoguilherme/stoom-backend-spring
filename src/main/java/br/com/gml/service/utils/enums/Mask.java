package br.com.gml.service.utils.enums;

public enum Mask {
    CEP("cep", "#####-###");

    private String cod;
    private String value;

    private Mask(String cod, String descricao) {
        this.cod = cod;
        this.value = descricao;
    }

    public String getCod() {
        return cod;
    }

    public String getValue() {
        return value;
    }

    public static Mask toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (Mask x : Mask.values()) {
            if (id.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid Mask " + id);
    }
}
