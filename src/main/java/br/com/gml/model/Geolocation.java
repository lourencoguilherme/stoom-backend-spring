package br.com.gml.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@NoArgsConstructor
@ToString
@Entity
public class Geolocation extends Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

}
