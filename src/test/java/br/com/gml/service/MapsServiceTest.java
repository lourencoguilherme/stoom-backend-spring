package br.com.gml.service;

import br.com.gml.DemoApplication;
import br.com.gml.dto.MapsDto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
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
public class MapsServiceTest {

    @Autowired
    private MapsService mapsService;

    @Test
    public void findGeocodeByAddress() {
        MapsDto location = mapsService.findGeocodeByAddress("1600 Amphitheatre Parkway Mountain View, CA 94043");
        assertThat(location.getLat()).isNotNull();
        assertThat(location.getLng()).isNotNull();
    }


}
