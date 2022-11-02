package com.stefanini.msvc.applications.clients;

import com.stefanini.msvc.applications.models.DownloadModel;
import com.stefanini.msvc.applications.models.RatingModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-downloads", url = "localhost:8004/api/v1")
public interface DownloadClientRest {
    @GetMapping("/{id}")
    DownloadModel detail(@PathVariable Long id );

    @PostMapping("/")
    DownloadModel create(@RequestBody DownloadModel download);

    @GetMapping("/downloads-by-application")
    List<DownloadModel> findAllById(@RequestParam Iterable<Long> ids);
}
