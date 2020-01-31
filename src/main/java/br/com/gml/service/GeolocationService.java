package br.com.gml.service;

import br.com.gml.dao.Dao;
import br.com.gml.dao.GeolocationDao;
import br.com.gml.dto.MapsDto;
import br.com.gml.model.Geolocation;
import br.com.gml.service.exceptions.ObjectNotFoundException;
import br.com.gml.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeolocationService extends GenericService<Geolocation, Long> {

    @Autowired
    private GeolocationDao dao;

    @Autowired
    private MapsService mapsService;

    @Override
    public Dao dao() {
        return dao;
    }

    @Override
    public Geolocation create(Geolocation entity) {
        geolocationTreatment(entity);
        return super.create(entity);
    }

    @Override
    public Geolocation update(Geolocation entity) {
        geolocationTreatment(entity);
        return super.update(entity);
    }

    private void geolocationTreatment(Geolocation entity) {
        if(entity.getLatitude() == null || entity.getLongitude() == null) {
            MapsDto location = mapsService.
                    findGeocodeByAddress(
                            addressFormat(entity)
                    );
            entity.setLatitude(location.getLat());
            entity.setLongitude(location.getLng());
        }
    }

    private String addressFormat(Geolocation entity) {
        StringBuilder str = new StringBuilder();
        str.append(entity.getStreetName()).append(", ");
        str.append(entity.getNumber()).append(" - ");
        str.append(entity.getNeighbourhood()).append(", ");
        str.append(entity.getCity()).append(" - ");;
        str.append(entity.getState()).append(", ");
        str.append(Utils.cepFormatString(entity.getZipcode().toString())).append(", ");;
        str.append(entity.getCountry());
        return str.toString();
    }
}
