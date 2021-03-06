package br.com.gml.service;

import br.com.gml.model.Model;

import java.util.List;

public interface GenericServiceImpl <C extends Model , ID> {
    List<C> findAll();

    C findById(ID id);

    C create(C entity);

    C update(C entity);

    C delete(C entity);
}