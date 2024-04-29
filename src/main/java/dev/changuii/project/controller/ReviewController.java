package dev.changuii.project.controller;


import dev.changuii.project.dto.ReviewDTO;
import dev.changuii.project.dto.response.ResponseReviewDTO;
import dev.changuii.project.service.impl.ReviewServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    public ReviewController(
            @Autowired ReviewServiceImpl reviewService)
    {
        this.reviewService = reviewService;
    }
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody @Valid ReviewDTO reviewDTO){
        ResponseReviewDTO createReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readReview(@PathVariable Long id){
        ResponseReviewDTO readReview = reviewService.readReview(id);
        return ResponseEntity.status(HttpStatus.OK).body(readReview);
    }


    // type
    // true garbageBin
    // false Toilet
    @GetMapping("/tg/{id}")
    public ResponseEntity<List<ResponseReviewDTO>> readAllReviewByToiletORGarbageBin(
        @RequestParam("type") boolean type,
        @PathVariable("id") Long id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.reviewService.readAllReviewByToiletORGarbageBin(type, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id,
                                          @RequestBody ReviewDTO reviewDTO){
        ResponseReviewDTO updateReview = reviewService.updateReview(id,reviewDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updateReview);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
    }




}
