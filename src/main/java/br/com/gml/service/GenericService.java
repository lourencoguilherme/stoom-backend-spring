package br.com.gml.service;

import br.com.gml.dao.Dao;
import br.com.gml.model.Model;
import br.com.gml.service.exceptions.ObjectNotFoundException;
import br.com.gml.service.utils.Utils;

import java.util.List;

public abstract class GenericService<C extends Model, ID>
        implements GenericServiceImpl<C,ID> {

    public abstract Dao dao();

    @Override
    public List<C> findAll() {
        return dao().findAll();
    }

    @Override
    public C findById(ID id) {
        return (C) dao().findById(id);
    }

    @Override
    public C create(C entity) {

        return (C) dao().save(entity);
    }

    @Override
    public C update(C entity) {
        verifyIdBeforeUpdateAndConfigure(entity);
        return (C) dao().update(entity);
    }

    @Override
    public C delete(C entity) {
        dao().remove(entity);
        return null;
    }


    public C findByIdOrRaiseException(Long id) {
        C obj = (C) dao().findById(id);
        if(obj == null) {
            throw  new ObjectNotFoundException(
                    Utils.pegaKeyPropertiesPadrao("id_not_found", id)
            );
        }

        return obj;
    }

    public void verifyIdBeforeUpdateAndConfigure(C entity) {
        if(findById((ID) entity.getId()) == null) {
            throw  new ObjectNotFoundException(
                    Utils.pegaKeyPropertiesPadrao("id_not_found_before_update", (ID) entity.getId())
            );
        }
    }

}
