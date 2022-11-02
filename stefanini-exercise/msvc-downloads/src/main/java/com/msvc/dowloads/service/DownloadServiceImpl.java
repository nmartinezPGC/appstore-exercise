package com.msvc.dowloads.service;

import com.msvc.dowloads.entity.DownloadEntity;
import com.msvc.dowloads.repository.DownloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DownloadServiceImpl implements DownloadService {
    @Autowired
    private DownloadRepository downloadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DownloadEntity> findAll() {
        return (List<DownloadEntity>) downloadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DownloadEntity> findById(Long id) {
        return downloadRepository.findById(id);
    }

    @Override
    @Transactional
    public DownloadEntity save(DownloadEntity DownloadEntity) {
        return downloadRepository.save(DownloadEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        downloadRepository.deleteById(id);
    }

    @Override
    public Long countDownloadsApplication(Long applicationId) {
        return downloadRepository.countDownloadsApplication(applicationId);
    }

    @Override
    public List<DownloadEntity> findAllById(Iterable<Long> ids) {
        return (List<DownloadEntity>) downloadRepository.findAllById(ids);
    }

}
