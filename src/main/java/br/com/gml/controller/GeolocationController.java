package br.com.gml.controller;

import br.com.gml.controller.exceptions.StandardError;
import br.com.gml.controller.exceptions.ValidationError;
import br.com.gml.dto.GeolocationDto;
import br.com.gml.model.Geolocation;
import br.com.gml.service.GeolocationService;
import br.com.gml.service.exceptions.ObjectNotFoundException;
import br.com.gml.service.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
@Api(value = "Geolocation registration")
@RestController
@RequestMapping(value = "/geolocations")
public class GeolocationController {

    @Autowired
    private GeolocationService service;

    /** Return List<Object> type the class.
     * @return ResponseEntity<List>
     */
    @ApiOperation(value = "Find All Geolocations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Geolocation.class, responseContainer = "List")
            ,
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class)
            ,
            @ApiResponse(code = 422, message = "Validation Erro", response = ValidationError.class)
    })
    @GetMapping
    public ResponseEntity<List<Geolocation>>  findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    /** Return Object type the class
     * @param id Long
     * @return ResponseEntity<Class>
     */
    @ApiOperation(value = "Find One Geolocation By Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Geolocation.class)
            ,
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class)
            ,
            @ApiResponse(code = 422, message = "Validation Erro", response = ValidationError.class)
    })
    @GetMapping("{id}")
    public ResponseEntity<Geolocation> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.findByIdOrRaiseException(id));
    }

    /** Return Object type the class
     * @param dto Type of Class
     * @return ResponseEntity<Class>
     */
    @ApiOperation(value = "Create Geolocation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created", response = Geolocation.class)
            ,
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class)
            ,
            @ApiResponse(code = 422, message = "Validation Erro", response = ValidationError.class)
    })
    @PostMapping
    public ResponseEntity<Geolocation> create(@Valid @RequestBody GeolocationDto dto) {
        return ResponseEntity.ok().body(service.create(dto.fromEntity()));
    }

    /** Return Object type the class
     * @param id in url
     * @param dto Type of Class
     * @return ResponseEntity<Class>
     */
    @ApiOperation(value = "Update Geolocations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Geolocation.class)
            ,
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class)
            ,
            @ApiResponse(code = 422, message = "Validation Erro", response = ValidationError.class)
    })
    @PutMapping("{id}")
    public ResponseEntity<Geolocation> update(@Valid @PathVariable @NotNull(message = "not_null") Long id, @Valid @RequestBody GeolocationDto dto) {
        return ResponseEntity.ok().body(service.update(fromDto(id,dto)));
    }

    /** Return Object type the class
     * @param entity Type of Class
     * @return ResponseEntity<Class>
     */
    @ApiOperation(value = "Delete Geolocations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
            ,
            @ApiResponse(code = 404, message = "Not Found", response = ObjectNotFoundException.class)
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@Valid @RequestBody Geolocation entity) {
        service.delete(entity);
        return ResponseEntity.noContent().build();
    }

    private Geolocation fromDto(Long id, GeolocationDto dto) {
        Geolocation entity = dto.fromEntity();
        entity.setId(id);

        return entity;
    }
}
