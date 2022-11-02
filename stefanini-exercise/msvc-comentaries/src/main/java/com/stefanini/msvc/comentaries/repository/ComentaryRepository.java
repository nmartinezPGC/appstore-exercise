package com.stefanini.msvc.comentaries.repository;

import com.stefanini.msvc.comentaries.model.entity.ComentaryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ComentaryRepository extends CrudRepository<ComentaryEntity, Long> {
}