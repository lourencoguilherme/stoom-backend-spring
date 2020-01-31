package br.com.gml.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Model<ID> implements Serializable {
    private static final long serialVersionUID = 1L;
    public abstract ID getId();
    public abstract void setId(ID id);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Geolocation)) return false;
        Geolocation that = (Geolocation) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
