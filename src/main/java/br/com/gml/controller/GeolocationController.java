package br.com.gml.controller;

import br.com.gml.model.Geolocation;
import br.com.gml.service.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    private GeolocationService service;

    /** Return List<Object> type the class.
     * @return ResponseEntity<List>
     */
    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    /** Return Object type the class
     * @param id Long
     * @return ResponseEntity<Class>
     */
    @GetMapping("{id}")
    public ResponseEntity<Geolocation> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    /** Return Object type the class
     * @param entity Type of Class
     * @return ResponseEntity<Class>
     */
    @PostMapping
    public ResponseEntity<Geolocation> create(@Valid @RequestBody Geolocation entity) {
        return ResponseEntity.ok().body(service.save(entity));
    }

    /** Return Object type the class
     * @param id in url
     * @param entity Type of Class
     * @return ResponseEntity<Class>
     */
    @PutMapping("{id}")
    public ResponseEntity<Geolocation> update(@Valid @PathVariable Long id, @Valid @RequestBody Geolocation entity) {
        return ResponseEntity.ok().body(service.update(entity));
    }

    /** Return Object type the class
     * @param id in url
     * @param entity Type of Class
     * @return ResponseEntity<Class>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Geolocation> delete(@PathVariable Long id, @Valid @RequestBody Geolocation entity) {
        service.delete(entity);
        return ResponseEntity.noContent().build();
    }
}
