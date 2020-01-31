package br.com.gml.model;

import java.io.Serializable;

public abstract class GenericEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public abstract T getId();
}
