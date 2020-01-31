package br.com.gml.service;

import br.com.gml.dao.Dao;
import br.com.gml.model.Model;

import java.util.List;

public abstract class GenericService<C extends Model, ID>
        implements GenericServiceImpl<C, ID> {

    public abstract Dao dao();

    @Override
    public List<C> findAll() {
        return null;
    }

    @Override
    public C findById(ID id) {
        return (C) dao().findById(id);
    }

    @Override
    public C save(C entity) {
        return (C) dao().save(entity);
    }

    @Override
    public C update(C entity) {
        return (C) dao().update(entity);
    }

    @Override
    public C delete(C entity) {
        dao().remove(entity);
        return null;
    }

}
