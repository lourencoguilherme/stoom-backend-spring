package br.com.gml.controller;

import br.com.gml.DemoApplication;
import br.com.gml.dto.GeolocationDto;
import br.com.gml.dto.MapsDto;
import br.com.gml.model.Geolocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles({ "test" })
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GeolocationControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllGeolocation() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/geolocations")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
    }

    @Test
    public void getOneGeolocation() throws Exception {
        MockHttpServletResponse response = createGetlocation();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Geolocation location =  objectMapper.readValue(response.getContentAsString(), Geolocation.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/geolocations/"+location.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse otherResponse =  mvc.perform(requestBuilder).andReturn().getResponse();
        Geolocation otherLocation =  objectMapper.readValue(otherResponse.getContentAsString(), Geolocation.class);
        assertEquals(HttpStatus.OK.value(), otherResponse.getStatus());
        assertThat(location.getId()).isEqualTo(otherLocation.getId());
    }

    @Test
    public void postGetlocation() throws Exception {
        MockHttpServletResponse response = createGetlocation();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        Geolocation location =  objectMapper.readValue(response.getContentAsString(), Geolocation.class);

        assertEquals("http://localhost/geolocations/" + location.getId(),response.getHeader(HttpHeaders.LOCATION));
    }

    @Test
    public void putGetlocation() throws Exception {
        MockHttpServletResponse response = createGetlocation();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Geolocation location =  objectMapper.readValue(response.getContentAsString(), Geolocation.class);

        location.setStreetName("Bernardino de Campos");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/geolocations/"+location.getId())
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(location, Geolocation.class))
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse otherResponse =  mvc.perform(requestBuilder).andReturn().getResponse();
        Geolocation otherLocation =  objectMapper.readValue(otherResponse.getContentAsString(), Geolocation.class);
        assertEquals(HttpStatus.NO_CONTENT.value(), otherResponse.getStatus());
        assertThat(location.getId()).isEqualTo(otherLocation.getId());
        assertThat(makeNewGeolocation().getStreetName()).isNotEqualTo(otherLocation.getStreetName());
    }

    @Test
    public void deleteGeolocation() throws Exception {
        MockHttpServletResponse response = createGetlocation();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Geolocation location =  objectMapper.readValue(response.getContentAsString(), Geolocation.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/geolocations/"+location.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse otherResponse =  mvc.perform(requestBuilder).andReturn().getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), otherResponse.getStatus());
        assertThat(otherResponse.getContentAsString()).isEqualTo("");
    }

    public MockHttpServletResponse createGetlocation() throws Exception {
        GeolocationDto dto = makeNewGeolocation();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/geolocations")
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(dto, GeolocationDto.class))
                .contentType(MediaType.APPLICATION_JSON);

        return mvc.perform(requestBuilder).andReturn().getResponse();
    }

    public GeolocationDto makeNewGeolocation() {
        GeolocationDto obj = new GeolocationDto();
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
