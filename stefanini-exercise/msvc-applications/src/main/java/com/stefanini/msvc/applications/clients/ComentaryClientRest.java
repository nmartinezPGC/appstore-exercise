package com.stefanini.msvc.applications.clients;

import com.stefanini.msvc.applications.models.ComentaryModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-comentaries", url = "localhost:8002/api/v1")
public interface ComentaryClientRest {
    @GetMapping("/{id}")
    ComentaryModel detail(@PathVariable Long id );

    @PostMapping("")
    ComentaryModel create(@RequestBody ComentaryModel comentary);

    @GetMapping("/comentaries-by-application")
    List<ComentaryModel> findAllById(@RequestParam Iterable<Long> ids);
}
