package br.com.gml.dao;

import br.com.gml.model.Model;
import br.com.gml.repository.GenericRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class Dao <T extends Model, ID>{

    protected Logger logger =
            LogManager.getLogger(this.getClass());

    abstract GenericRepository repository();

    public List<T> findAll(){
        return repository().findAll();
    }

    public T findById(ID id){
        return (T) repository().findById(id).orElse(null);
    }

    public T save (T entity){
        repository().save(entity);
        return entity;
    }

    public T update (T entity){
        repository().save(entity);
        return entity;

    }

    public void remove (T entity){
        repository().delete(entity);
    }
}

