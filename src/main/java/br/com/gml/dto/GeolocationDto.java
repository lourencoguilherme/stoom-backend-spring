package br.com.gml.dto;

import br.com.gml.model.Geolocation;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ToString
public class GeolocationDto {

    @NotEmpty(message = "not_null")
    private String streetName;

    @NotNull(message = "not_null")
    private Integer number;

    private String complement;

    @NotEmpty(message = "not_null")
    private String neighbourhood;

    @NotEmpty(message = "not_null")
    private String city;

    @NotEmpty(message = "not_null")
    private String state;

    @NotEmpty(message = "not_null")
    private String country;

    @NotNull(message = "not_null")
    private Long zipcode;

    @Column(precision=10, scale=2)
    private Double latitude;

    @Column(precision=10, scale=2)
    private Double longitude;

    public Geolocation fromEntity() {
        Geolocation obj = new Geolocation();
        obj.setStreetName(getStreetName());
        obj.setNumber(getNumber());
        obj.setComplement(getComplement());
        obj.setNeighbourhood(getNeighbourhood());
        obj.setCity(getCity());
        obj.setState(getState());
        obj.setCountry(getCountry());
        obj.setZipcode(getZipcode());
        obj.setLatitude(getLatitude());
        obj.setLongitude(getLongitude());
        return obj;
    }
}
