package br.com.gml.service;

import br.com.gml.DemoApplication;
import br.com.gml.model.Geolocation;
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
public class GeolocationServiceTest {

    @Autowired
    public GeolocationService service;

    @Test
    @Order(1)
    @DisplayName("criate Geolocation")
    public void criateGeolocation() {
        Geolocation obj = saveNewGeolocationTest();
        Geolocation objOther = service.findById(obj.getId());
        assertThat(obj.getId()).isEqualTo(objOther.getId());
    }

    @Test
    @Order(2)
    @DisplayName("update Geolocation")
    public void updateGeolocation() {
        Geolocation obj = saveNewGeolocationTest();
        assertThat(obj.getCountry()).isEqualTo(makeNewGeolocation().getCountry());
        obj.setCountry("USA");
        service.update(obj);
        Geolocation objOther = service.findById(obj.getId());
        assertThat(objOther.getCountry()).isNotEqualTo(makeNewGeolocation().getCountry());
    }

    @Test
    @Order(3)
    @DisplayName("delete Geolocation")
    public void deleteGeolocation() {
        Geolocation obj = saveNewGeolocationTest();
        service.delete(obj);
        Geolocation objOther = service.findById(obj.getId());
        assertThat(objOther).isNull();
    }


    public Geolocation saveNewGeolocationTest() {
        Geolocation obj = makeNewGeolocation();
        service.save(obj);
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
        return obj;
    }

}
