package br.com.gml.repository;

import br.com.gml.model.Geolocation;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocationRepository extends GenericRepository<Geolocation, Long> {
}
