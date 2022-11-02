package com.msvc.dowloads.controller;

import com.msvc.dowloads.entity.DownloadEntity;
import com.msvc.dowloads.service.DownloadService;
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
public class DownloadController {
    @Autowired
    private DownloadService downloadService;

    @GetMapping("")
    public ResponseEntity<List<DownloadEntity>> getAllDownloads(){
        return ResponseEntity.ok(downloadService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DownloadEntity> detailDownload(@PathVariable Long id){
        Optional<DownloadEntity> downloadOptional = downloadService.findById(id);
        if (downloadOptional.isPresent()){
            return ResponseEntity.ok(downloadOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<?> newDownload(@Valid @RequestBody DownloadEntity DownloadEntity, BindingResult result) {
        if (result.hasErrors()){
            return validateMethod(result);
        }
        DownloadEntity downloadDB = downloadService.save(DownloadEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(downloadDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDownload(@Valid @RequestBody DownloadEntity DownloadEntity, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return validateMethod(result);
        }

        Optional<DownloadEntity> downloadOpt = downloadService.findById(id);
        if (downloadOpt.isPresent()){
            DownloadEntity downloadDB = downloadOpt.get();
            downloadDB.setApplicationName(downloadOpt.get().getApplicationName());
            return ResponseEntity.status(HttpStatus.CREATED).body(downloadService.save(downloadDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDownload(@PathVariable Long id){
        Optional<DownloadEntity> downloadOpt = downloadService.findById(id);
        if (downloadOpt.isPresent()){
            downloadService.delete(downloadOpt.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/downloads-by-application")
    public ResponseEntity<?> getDownloadsByApplication(@RequestParam List<Long> ids){
        return ResponseEntity.ok(downloadService.findAllById(ids));
    }

    @GetMapping("/count-downloads-by-application/{applicationId}")
    public ResponseEntity<?> countDownloadsByApplication(@PathVariable Long applicationId){
        return ResponseEntity.ok(downloadService.countDownloadsApplication(applicationId));
    }

    private ResponseEntity<Map<String, String>> validateMethod(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
