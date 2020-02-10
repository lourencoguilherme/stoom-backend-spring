package br.com.gml.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class Model<ID> implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    public abstract ID getId();
    public abstract void setId(ID id);

}
