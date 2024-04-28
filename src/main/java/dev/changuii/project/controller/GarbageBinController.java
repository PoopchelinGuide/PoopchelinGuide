package dev.changuii.project.controller;

import dev.changuii.project.dto.GarbageBinDTO;
import dev.changuii.project.service.impl.GarbageBinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/garbageBin")
public class GarbageBinController {
    private final GarbageBinServiceImpl garbageBinService;

    public GarbageBinController(
            @Autowired GarbageBinServiceImpl garbageBinService){
        this.garbageBinService = garbageBinService;
    }

    @GetMapping("/range")
    public ResponseEntity<List<GarbageBinDTO>> readAllByBoxRange(
            @RequestParam("x1") Double x1,
            @RequestParam("x2") Double x2,
            @RequestParam("y1") Double y1,
            @RequestParam("y2") Double y2
    ){
        return ResponseEntity.status(HttpStatus.OK).body(garbageBinService.readAllByBoxRange(x1,x2,y1,y2));
    }
}
