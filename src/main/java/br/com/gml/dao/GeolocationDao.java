package br.com.gml.dao;

import br.com.gml.model.Geolocation;
import br.com.gml.repository.GenericRepository;
import br.com.gml.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeolocationDao extends Dao<Geolocation, Long> {
    @Autowired
    private GeolocationRepository repository;

    @Override
    GenericRepository repository() {
        return repository;
    }
}
