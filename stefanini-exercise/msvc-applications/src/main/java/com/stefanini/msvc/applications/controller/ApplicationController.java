package com.stefanini.msvc.applications.controller;

import com.stefanini.msvc.applications.models.ComentaryModel;
import com.stefanini.msvc.applications.models.DownloadModel;
import com.stefanini.msvc.applications.models.RatingModel;
import com.stefanini.msvc.applications.models.entity.ApplicationEntity;
import com.stefanini.msvc.applications.models.entity.CategoryEntity;
import com.stefanini.msvc.applications.service.ApplicationService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/application")
@CrossOrigin(origins = "*")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("")
    public List<ApplicationEntity> findAllApplications() {
        return applicationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailApplication(@PathVariable Long id){
         Optional<ApplicationEntity> applicationOptional = applicationService.findByIdWithApplication(id);
         if (applicationOptional.isPresent()){
             return ResponseEntity.ok(applicationOptional.get());
         }
         return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> newApplication(@Valid @RequestBody ApplicationEntity applicationEntity, BindingResult result) {
        if (!applicationEntity.getTittle().isEmpty() && applicationService.findByTittle(applicationEntity.getTittle()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Ya existe la Aplicación con ese título"));
        }

        if (result.hasErrors()){
            return validateMethod(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.save(applicationEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateApplication(@Valid @RequestBody ApplicationEntity applicationEntity, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return validateMethod(result);
        }

        Optional<ApplicationEntity> applicationOptional = applicationService.findById(id);
        if (applicationOptional.isPresent()){
            ApplicationEntity applicationDB = applicationOptional.get();
            if (!applicationEntity.getTittle().isEmpty() && !applicationEntity.getTittle().equalsIgnoreCase(applicationEntity.getTittle()) && applicationService.findByTittle(applicationEntity.getTittle()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Ya existe la Aplicación con ese título"));
            }

            applicationDB.setTittle(applicationEntity.getTittle());
            applicationDB.setDescription(applicationEntity.getDescription());
            applicationDB.setImg(applicationEntity.getImg());
            applicationDB.setPrice(applicationEntity.getPrice());
            applicationDB.setRating(applicationEntity.getRating());
            return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.save(applicationDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id){
        Optional<ApplicationEntity> optionalApplication = applicationService.findById(id);
        if (optionalApplication.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/category/{id}")
    public List<ApplicationEntity> findAllApplicationsByCategory(CategoryEntity category) {
        return applicationService.findByCategory(category);
    }

    // Section of Comentary ***********************
    @PutMapping("assing-comentary/{applicationId}")
    public ResponseEntity<?> assingComentary(@RequestBody ComentaryModel comentaryModel, @PathVariable Long applicationId){
        Optional<ComentaryModel> opt;
        try {
            opt = applicationService.assingComentaryToApplication(comentaryModel, applicationId);
        } catch (FeignException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error",
                            "No existe el comentario por el id o error en la comunicación " + e.getMessage()));
        }

        if (opt.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("create-comentary/{applicationId}")
    public ResponseEntity<?> createComentary(@RequestBody ComentaryModel comentaryModel, @PathVariable Long applicationId){
        Optional<ComentaryModel> opt;
        try {
            opt = applicationService.createComentaryToApplication(comentaryModel, applicationId);
        } catch (FeignException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error",
                            "No pudo crear el comentario o error en la comunicación " + e.getMessage()));
        }

        if (opt.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("remove-comentary/{applicationId}")
    public ResponseEntity<?> removeComentary(@RequestBody ComentaryModel comentaryModel, @PathVariable Long applicationId){
        Optional<ComentaryModel> opt;
        try {
            opt = applicationService.removeComentaryToApplication(comentaryModel, applicationId);
        } catch (FeignException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error",
                            "No existe el comentario por el id o error en la comunicación " + e.getMessage()));
        }

        if (opt.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Section of Rating ***********************
    @PostMapping("create-rating/{applicationId}")
    public ResponseEntity<?> createRating(@RequestBody RatingModel ratingModel, @PathVariable Long applicationId){
        Optional<RatingModel> opt;
        try {
            opt = applicationService.createRatingToApplication(ratingModel, applicationId);
        } catch (FeignException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error",
                            "No pudo crear la Calificación o error en la comunicación " + e.getMessage()));
        }

        if (opt.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("remove-rating/{applicationId}")
    public ResponseEntity<?> removeRating(@RequestBody RatingModel ratingModel, @PathVariable Long applicationId){
        Optional<RatingModel> opt;
        try {
            opt = applicationService.removeRatingToApplication(ratingModel, applicationId);
        } catch (FeignException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error",
                            "No existe la calificación por el id o error en la comunicación " + e.getMessage()));
        }

        if (opt.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Section of Downloads ***********************
    @PostMapping("create-download/{applicationId}")
    public ResponseEntity<?> createDownload(@RequestBody DownloadModel downloadModel, @PathVariable Long applicationId){
        Optional<DownloadModel> opt;
        try {
            opt = applicationService.createDownloadToApplication(downloadModel, applicationId);
        } catch (FeignException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error",
                            "No pudo crear la Descarga o error en la comunicación " + e.getMessage()));
        }

        if (opt.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("remove-download/{applicationId}")
    public ResponseEntity<?> removeRating(@RequestBody DownloadModel downloadModel, @PathVariable Long applicationId){
        Optional<DownloadModel> opt;
        try {
            opt = applicationService.removeDownloadToApplication(downloadModel, applicationId);
        } catch (FeignException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error",
                            "No existe la descarga por el id o error en la comunicación " + e.getMessage()));
        }

        if (opt.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validateMethod(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
