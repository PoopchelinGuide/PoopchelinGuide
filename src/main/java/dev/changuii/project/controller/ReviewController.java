package dev.changuii.project.controller;


import dev.changuii.project.dto.ReviewDTO;
import dev.changuii.project.dto.response.ResponseReviewDTO;
import dev.changuii.project.service.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createReview(@RequestBody ReviewDTO reviewDTO){
        ResponseReviewDTO createReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createReview);
    }

<<<<<<< HEAD
    @GetMapping("{id}")
=======
    @GetMapping("/{id}")
>>>>>>> feat/toilet_service
    public ResponseEntity<?> readReview(@PathVariable Long id){
        ResponseReviewDTO readReview = reviewService.readReivew(id);
        return ResponseEntity.status(HttpStatus.OK).body(readReview);
    }

<<<<<<< HEAD
    @PatchMapping("{id}")
=======
    @PatchMapping("/{id}")
>>>>>>> feat/toilet_service
    public ResponseEntity<?> updateReview(@PathVariable Long id,
                                          @RequestBody ReviewDTO reviewDTO){
        ResponseReviewDTO updateReview = reviewService.updateReview(id,reviewDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updateReview);
    }

<<<<<<< HEAD
    @DeleteMapping("{id}")
=======
    @DeleteMapping("/{id}")
>>>>>>> feat/toilet_service
    public void deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
    }




}
