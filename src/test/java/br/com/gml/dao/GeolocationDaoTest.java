package br.com.gml.dao;

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
public class GeolocationDaoTest {

    @Autowired
    public GeolocationDao dao;

    @Test
    @Order(1)
    @DisplayName("criate Geolocation")
    public void criateGeolocation() {
        Geolocation obj = new Geolocation();
        obj.setCity("Campinas");
        obj.setCountry("Brasil");
        obj.setLatitude(37.09024);
        obj.setLongitude(-95.712891);
        obj.setComplement("Apto 10");
        obj.setNeighbourhood("Centro");
        obj.setStreetName("Bernardino");
        obj.setNumber(1232);
        dao.save(obj);
        Geolocation objOther = dao.findById(obj.getId());
        assertThat(obj.getId()).isEqualTo(objOther.getId());
    }

}
