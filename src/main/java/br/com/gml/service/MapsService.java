package br.com.gml.service;

import br.com.gml.dto.MapsDto;
import br.com.gml.service.exceptions.ObjectNotFoundException;
import br.com.gml.service.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MapsService {

    @Value("${maps.api.key}") private String apiKey;

    public MapsDto findGeocodeByAddress(String address) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context, address).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(results.length == 0) {
            throw  new ObjectNotFoundException(
                    Utils.pegaKeyPropertiesPadrao("address_not_found", address, MapsService.class.getName())
            );
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        MapsDto location = gson.fromJson(gson.toJson(results[0].geometry.location), MapsDto.class);

        return location;
    }
}
