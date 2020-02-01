package br.com.gml.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class GenericController {
    public ResponseEntity<?> responseTocreated(Object id, Object obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.LOCATION,uri.toString());
        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(obj);
    }
}
