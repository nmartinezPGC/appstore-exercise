package com.msvc.dowloads.service;


import com.msvc.dowloads.entity.DownloadEntity;

import java.util.List;
import java.util.Optional;

public interface DownloadService {
    List<DownloadEntity> findAll();
    Optional<DownloadEntity> findById(Long id);
    DownloadEntity save(DownloadEntity categoryEntity);
    void delete(Long id);

    Long countDownloadsApplication(Long applicationId);

    List<DownloadEntity> findAllById(Iterable<Long> ids);
}
