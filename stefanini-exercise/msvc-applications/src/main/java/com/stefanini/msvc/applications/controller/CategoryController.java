package com.stefanini.msvc.applications.controller;

import com.stefanini.msvc.applications.models.entity.CategoryEntity;
import com.stefanini.msvc.applications.service.CategoryService;
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
@RequestMapping(path = "/api/v1/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryEntity>> getAllCategories(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> detailCategory(@PathVariable Long id){
        Optional<CategoryEntity> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()){
            return ResponseEntity.ok(categoryOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<?> newCategory(@Valid @RequestBody CategoryEntity categoryEntity, BindingResult result) {
        if (result.hasErrors()){
            return validateMethod(result);
        }
        CategoryEntity categoryDB = categoryService.save(categoryEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryEntity categoryEntity, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return validateMethod(result);
        }

        Optional<CategoryEntity> categoryOpt = categoryService.findById(id);
        if (categoryOpt.isPresent()){
            CategoryEntity categoryDB = categoryOpt.get();
            categoryDB.setName(categoryEntity.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<CategoryEntity> categoryOpt = categoryService.findById(id);
        if (categoryOpt.isPresent()){
            categoryService.delete(categoryOpt.get().getId());
            return ResponseEntity.noContent().build();
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
