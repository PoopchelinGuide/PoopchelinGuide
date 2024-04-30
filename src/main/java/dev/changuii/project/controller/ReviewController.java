package dev.changuii.project.controller;


import dev.changuii.project.dto.ReviewDTO;
import dev.changuii.project.dto.response.ResponseReviewPageDTO;
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
    public ResponseEntity<?> createReview(
            @RequestBody @Valid ReviewDTO reviewDTO){
        ResponseReviewDTO createReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readReview(
            @PathVariable Long id){
        ResponseReviewDTO readReview = reviewService.readReview(id);
        return ResponseEntity.status(HttpStatus.OK).body(readReview);
    }

    @GetMapping("/tg/total/{id}")
    public ResponseEntity<ResponseReviewPageDTO> readByPageDataByToiletOrGarbageBin(
            @RequestParam("type") boolean type,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.reviewService.readAllReviewPageDataByToieltOrGarbageBin(type, id));
    }

    @GetMapping("/tg/popover/{id}")
    public ResponseEntity<ResponseReviewPageDTO> readSummaryByToiletORGarbageBin(
            @RequestParam("type") boolean type,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.reviewService.readSummaryByToiletORGarbageBin(type, id));
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
    public ResponseEntity<ResponseReviewDTO> updateReview(
            @PathVariable("id") Long id,
            @RequestBody ReviewDTO reviewDTO){
        ResponseReviewDTO updateReview = reviewService.updateReview(id,reviewDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updateReview);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(
            @PathVariable("id") Long id,
            @RequestParam("password") String password){
        reviewService.deleteReview(id, password);
    }

}
