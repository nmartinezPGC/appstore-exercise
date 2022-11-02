package com.stefanini.msvc.ratings.controller;

import com.stefanini.msvc.ratings.entity.RatingEntity;
import com.stefanini.msvc.ratings.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin(origins = "*")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping("")
    public ResponseEntity<List<RatingEntity>> getAllRatings(){
        return ResponseEntity.ok(ratingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingEntity> detailRating(@PathVariable Long id){
        Optional<RatingEntity> ratingOptional = ratingService.findById(id);
        if (ratingOptional.isPresent()){
            return ResponseEntity.ok(ratingOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<?> newRating(@Valid @RequestBody RatingEntity ratingEntity, BindingResult result) {
        if (result.hasErrors()){
            return validateMethod(result);
        }
        RatingEntity categoryDB = ratingService.save(ratingEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRating(@Valid @RequestBody RatingEntity ratingEntity, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return validateMethod(result);
        }

        Optional<RatingEntity> ratingOpt = ratingService.findById(id);
        if (ratingOpt.isPresent()){
            RatingEntity ratingDB = ratingOpt.get();
            ratingDB.setRating(ratingOpt.get().getRating());
            ratingDB.setEmisor(ratingOpt.get().getEmisor());
            return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.save(ratingDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable Long id){
        Optional<RatingEntity> ratingOpt = ratingService.findById(id);
        if (ratingOpt.isPresent()){
            ratingService.delete(ratingOpt.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/avg-ratings-by-application/{applicationId}")
    public ResponseEntity<?> avgRatingsByApplication(@PathVariable Long applicationId){
        return ResponseEntity.ok(ratingService.avgApplication(applicationId));
    }

    @GetMapping("/ratings-by-application")
    public ResponseEntity<?> getRatingsByApplication(@RequestParam List<Long> ids){
        return ResponseEntity.ok(ratingService.findAllById(ids));
    }

    private ResponseEntity<Map<String, String>> validateMethod(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
