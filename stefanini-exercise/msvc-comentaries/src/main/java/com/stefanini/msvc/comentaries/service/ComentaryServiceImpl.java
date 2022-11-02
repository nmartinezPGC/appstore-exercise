package com.stefanini.msvc.comentaries.service;

import com.stefanini.msvc.comentaries.model.entity.ComentaryEntity;
import com.stefanini.msvc.comentaries.repository.ComentaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComentaryServiceImpl implements ComentaryService {
    @Autowired
    private ComentaryRepository comentaryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ComentaryEntity> findAll() {
        return (List<ComentaryEntity>) comentaryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ComentaryEntity> findById(Long id) {
        return comentaryRepository.findById(id);
    }

    @Override
    @Transactional
    public ComentaryEntity save(ComentaryEntity comentaryEntity) {
        return comentaryRepository.save(comentaryEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        comentaryRepository.deleteById(id);
    }

    @Override
    public List<ComentaryEntity> findAllById(Iterable<Long> ids) {
        return (List<ComentaryEntity>) comentaryRepository.findAllById(ids);
    }

    @Override
    public List<ComentaryEntity> findAllByApplication(Long applicationId) {
        return null;
    }
}
