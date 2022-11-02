package com.stefanini.msvc.comentaries.service;


import com.stefanini.msvc.comentaries.model.entity.ComentaryEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComentaryService {
    List<ComentaryEntity> findAll();
    Optional<ComentaryEntity> findById(Long id);
    ComentaryEntity save(ComentaryEntity categoryEntity);
    void delete(Long id);
    List<ComentaryEntity> findAllById(Iterable<Long> ids);
    List<ComentaryEntity> findAllByApplication(Long applicationId);
}
