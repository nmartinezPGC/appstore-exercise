package com.stefanini.msvc.applications.repository;

import com.stefanini.msvc.applications.models.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}