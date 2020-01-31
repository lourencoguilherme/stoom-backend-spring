package br.com.gml.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
@RestController
@RequestMapping(value = "/geolocations")
public class GeolocationController {

    /** Return List<Object> type the class.
     * @return ResponseEntity<List>
     */
    @GetMapping
    public ResponseEntity<List<?>> findAll() {

        return ResponseEntity.ok().body(Arrays.asList("teste de response", "response ok"));
    }
}
