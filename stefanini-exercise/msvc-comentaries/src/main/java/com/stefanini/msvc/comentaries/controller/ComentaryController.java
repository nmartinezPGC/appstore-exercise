package com.stefanini.msvc.comentaries.controller;

import com.stefanini.msvc.comentaries.model.entity.ComentaryEntity;
import com.stefanini.msvc.comentaries.service.ComentaryService;
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
public class ComentaryController {
    @Autowired
    private ComentaryService comentaryService;

    @GetMapping("")
    public ResponseEntity<List<ComentaryEntity>> getAllComentaries(){
        return ResponseEntity.ok(comentaryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentaryEntity> detailComentary(@PathVariable Long id){
        Optional<ComentaryEntity> categoryOptional = comentaryService.findById(id);
        if (categoryOptional.isPresent()){
            return ResponseEntity.ok(categoryOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<?> newComentary(@Valid @RequestBody ComentaryEntity comentaryEntity, BindingResult result) {
        if (result.hasErrors()){
            return validateMethod(result);
        }
        ComentaryEntity comentaryEntityDB = comentaryService.save(comentaryEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentaryEntityDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComentary(@Valid @RequestBody ComentaryEntity comentaryEntity, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return validateMethod(result);
        }

        Optional<ComentaryEntity> comentaryOpt = comentaryService.findById(id);
        if (comentaryOpt.isPresent()){
            ComentaryEntity categoryDB = comentaryOpt.get();
            categoryDB.setDescription(comentaryEntity.getDescription());
            categoryDB.setEmisor(comentaryEntity.getEmisor());
            return ResponseEntity.status(HttpStatus.CREATED).body(comentaryService.save(categoryDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComentary(@PathVariable Long id){
        Optional<ComentaryEntity> comentaryOpt = comentaryService.findById(id);
        if (comentaryOpt.isPresent()){
            comentaryService.delete(comentaryOpt.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/comentaries-by-application")
    public ResponseEntity<?> getListComentaryByApplication(@RequestParam List<Long> ids){
        return ResponseEntity.ok(comentaryService.findAllById(ids));
    }

    @GetMapping("/opt-comentaries-by-application")
    public ResponseEntity<?> getComentaryByApplication(@PathVariable Long applicationId){
        return null;
    }

    private ResponseEntity<Map<String, String>> validateMethod(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
