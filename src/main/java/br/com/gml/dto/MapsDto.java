package br.com.gml.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MapsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(precision=10, scale=2)
    private Double lat;

    @Column(precision=10, scale=2)
    private Double lng;
}
