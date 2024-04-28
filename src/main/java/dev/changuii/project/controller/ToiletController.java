package dev.changuii.project.controller;


import dev.changuii.project.dao.ToiletDAO;
import dev.changuii.project.dto.ReviewDTO;
import dev.changuii.project.dto.ToiletDTO;
import dev.changuii.project.service.ToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/toilet")
public class ToiletController {

    private final ToiletService toiletService;

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

    @GetMapping("{id}")
    public ResponseEntity<List<ReviewDTO>> readReview(@PathVariable Long id){
        return null;
    }
}
