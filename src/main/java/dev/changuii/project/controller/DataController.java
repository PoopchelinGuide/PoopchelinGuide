package dev.changuii.project.controller;



import dev.changuii.project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    public DataController(
            @Autowired DataService dataService
    ){
        this.dataService = dataService;
    }


    @GetMapping("/toilet")
    public ResponseEntity<?> getToiletData() throws URISyntaxException {
        this.dataService.storeToiletOpenAPIData();
        return ResponseEntity.status(200).body("toilet data set");
    }

    @GetMapping("/garbageBin")
    public ResponseEntity<?> getGarbageBinData() throws IOException, URISyntaxException {
        this.dataService.readExcelData();
        return ResponseEntity.status(200).body("garbageBin");
    }


}
