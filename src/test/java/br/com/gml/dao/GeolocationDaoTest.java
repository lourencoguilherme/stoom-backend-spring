package br.com.gml.dao;

import br.com.gml.DemoApplication;
import br.com.gml.dto.MapsDto;
import br.com.gml.model.Geolocation;
import br.com.gml.service.MapsService;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles({ "test" })
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GeolocationDaoTest {

    @Autowired
    public GeolocationDao dao;


    @Test
    public void criateGeolocation() {
        Geolocation obj = saveNewGeolocationTest();
        Geolocation objOther = dao.findById(obj.getId());
        assertThat(obj.getId()).isEqualTo(objOther.getId());
    }

    @Test
    public void updateGeolocation() {
        Geolocation obj = saveNewGeolocationTest();
        assertThat(obj.getCountry()).isEqualTo(makeNewGeolocation().getCountry());
        obj.setCountry("USA");
        dao.update(obj);
        Geolocation objOther = dao.findById(obj.getId());
        assertThat(objOther.getCountry()).isNotEqualTo(makeNewGeolocation().getCountry());
    }

    @Test
    public void deleteGeolocation() {
        Geolocation obj = saveNewGeolocationTest();
        dao.remove(obj);
        Geolocation objOther = dao.findById(obj.getId());
        assertThat(objOther).isNull();
    }

    @Test
    public void criateGeolocationWithoutLatAndLong() {
        Geolocation obj = makeNewGeolocation();
        obj.setLongitude(null);
        obj.setLatitude(null);
        obj = dao.save(obj);
        assertThat(obj.getId()).isNotNull();
    }

    public Geolocation saveNewGeolocationTest() {
        Geolocation obj = makeNewGeolocation();
        dao.save(obj);
        return obj;
    }

    public Geolocation makeNewGeolocation() {
        Geolocation obj = new Geolocation();
        obj.setCity("Campinas");
        obj.setCountry("Brasil");
        obj.setLatitude(37.09024);
        obj.setLongitude(-95.712891);
        obj.setComplement("Apto 10");
        obj.setNeighbourhood("Centro");
        obj.setStreetName("Bernardino");
        obj.setNumber(1232);
        obj.setZipcode(13010151L);
        obj.setState("São Paulo");
        return obj;
    }

}
