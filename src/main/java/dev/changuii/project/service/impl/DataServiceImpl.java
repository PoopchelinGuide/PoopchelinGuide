package dev.changuii.project.repository.impl;

import dev.changuii.project.dao.ToiletDAO;
import dev.changuii.project.entity.ToiletEntity;
import dev.changuii.project.repository.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private ToiletDAO toiletDAO;

    public DataServiceImpl(
            @Autowired ToiletDAO toiletDAO
    ){
        this.toiletDAO = toiletDAO;
    }

    @Override
    public void storeToiletOpenAPIData() throws URISyntaxException {
        String openAPIKey = "63776a527472686c37354d626e557a";
        URI uri = new URI("http://openapi.seoul.go.kr:8088/" +
                openAPIKey+"/json/SearchPublicToiletPOIService/1/1000/");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> http = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LinkedHashMap> response = restTemplate.exchange(uri, HttpMethod.GET, http, LinkedHashMap.class);

        List<LinkedHashMap> data =(List<LinkedHashMap>)((LinkedHashMap) response.getBody().get("SearchPublicToiletPOIService")).get("row");


        for(LinkedHashMap d : data){
            ToiletEntity toilet = ToiletEntity.builder()
                    .coordinateX((Double) d.get("X_WGS84"))
                    .coordinateY((Double) d.get("Y_WGS84"))
                    .name((String) d.get("FNAME")).build();
            this.toiletDAO.createToilet(toilet);
        }

    }
}
