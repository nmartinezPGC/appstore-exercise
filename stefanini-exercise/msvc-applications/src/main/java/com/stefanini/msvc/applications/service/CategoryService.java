package com.stefanini.msvc.applications.service;

import com.stefanini.msvc.applications.models.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryEntity> findAll();
    Optional<CategoryEntity> findById(Long id);
    CategoryEntity save(CategoryEntity categoryEntity);
    void delete(Long id);
}
