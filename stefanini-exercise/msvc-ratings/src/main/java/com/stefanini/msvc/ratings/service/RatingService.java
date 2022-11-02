package com.stefanini.msvc.ratings.service;

import com.stefanini.msvc.ratings.entity.RatingEntity;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    List<RatingEntity> findAll();
    Optional<RatingEntity> findById(Long id);
    RatingEntity save(RatingEntity categoryEntity);
    void delete(Long id);
    List<RatingEntity> findAllById(Iterable<Long> ids);
    Long avgApplication(Long applicationId);
}
