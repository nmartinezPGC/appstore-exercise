package com.stefanini.msvc.ratings.service;

import com.stefanini.msvc.ratings.entity.RatingEntity;
import com.stefanini.msvc.ratings.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<RatingEntity> findAll() {
        return (List<RatingEntity>) ratingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RatingEntity> findById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    @Transactional
    public RatingEntity save(RatingEntity ratingEntity) {
        return ratingRepository.save(ratingEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RatingEntity> findAllById(Iterable<Long> ids) {
        return (List<RatingEntity>) ratingRepository.findAllById(ids);
    }

    @Override
    public Long avgApplication(Long applicationId) {
        return ratingRepository.avgApplication(applicationId);
    }
}
