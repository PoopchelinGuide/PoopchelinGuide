package dev.changuii.project.controller;


import dev.changuii.project.repository.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class DataController {

    private DataService dataService;

    public DataController(
            @Autowired DataService dataService
    ){
        this.dataService = dataService;
    }


    @GetMapping("/toilet")
    public ResponseEntity<?> getToiletData() throws URISyntaxException {
        this.dataService.storeToiletOpenAPIData();
        return ResponseEntity.status(200).body("asd");
    }


}
