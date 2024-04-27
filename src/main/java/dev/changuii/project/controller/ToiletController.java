package dev.changuii.project.controller;


import dev.changuii.project.dao.ToiletDAO;
import dev.changuii.project.dto.ToiletDTO;
import dev.changuii.project.service.ToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/toilet")
public class ToiletController {

    private ToiletService toiletService;

    public ToiletController(
            @Autowired ToiletService toiletService
    ){
        this.toiletService = toiletService;
    }

    @GetMapping("/range")
    public ResponseEntity<List<ToiletDTO>> readAllByBoxRange(
            @RequestParam("x1") Double x1,
            @RequestParam("x2") Double x2,
            @RequestParam("y1") Double y1,
            @RequestParam("y2") Double y2
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body( this.toiletService.readAllByBoxRange(x1, x2, y1, y2));
    }

}
