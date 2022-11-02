package com.stefanini.msvc.applications.clients;

import com.stefanini.msvc.applications.models.RatingModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-ratings", url = "localhost:8003/api/v1")
public interface RatingClientRest {
    @GetMapping("/{id}")
    RatingModel detail(@PathVariable Long id );

    @PostMapping("")
    RatingModel create(@RequestBody RatingModel rating);

    @GetMapping("/ratings-by-application")
    List<RatingModel> findAllById(@RequestParam Iterable<Long> ids);

    /*@GetMapping("/avg-ratings-by-application")
    Long avgRatingsByApplication(@PathVariable Long applicationId);*/
}
