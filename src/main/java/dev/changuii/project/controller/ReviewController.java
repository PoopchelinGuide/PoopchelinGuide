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
    @RequestMapping("/save")
    public ResponseEntity<?> createReview(@RequestBody ReviewDTO reviewDTO){
        ResponseReviewDTO createReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createReview);
    }

    @GetMapping
    @RequestMapping("/read/{id}")
    public ResponseEntity<?> readReview(@PathVariable Long id){
        ResponseReviewDTO readReview = reviewService.readReivew(id);
        return ResponseEntity.status(HttpStatus.OK).body(readReview);
    }

    @PatchMapping
    @RequestMapping("/update/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id,
                                          @RequestBody ReviewDTO reviewDTO){
        ResponseReviewDTO updateReview = reviewService.updateReview(id,reviewDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updateReview);
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public void deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
    }




}
