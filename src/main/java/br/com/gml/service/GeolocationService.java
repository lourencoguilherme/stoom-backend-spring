package br.com.gml.service;

import br.com.gml.dao.Dao;
import br.com.gml.dao.GeolocationDao;
import br.com.gml.model.Geolocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeolocationService extends GenericService<Geolocation, Long> {

    @Autowired
    private GeolocationDao dao;

    @Override
    public Dao dao() {
        return dao;
    }
}
