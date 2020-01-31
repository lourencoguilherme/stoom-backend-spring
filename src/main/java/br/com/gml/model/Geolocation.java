package br.com.gml.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@ToString
@Entity
public class Geolocation extends GenericEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String streetName;

    private Long number;

    private String complement;

    private String neighbourhood;

    private String city;

    private String state;

    private String country;

    private Long zipcode;

    @Column(precision=10, scale=2)
    private Double latitude;

    @Column(precision=10, scale=2)
    private Double longitude;


}
