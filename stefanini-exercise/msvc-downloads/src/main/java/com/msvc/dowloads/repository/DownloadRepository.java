package com.msvc.dowloads.repository;

import com.msvc.dowloads.entity.DownloadEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DownloadRepository extends CrudRepository<DownloadEntity, Long> {
    @Query(value = "select COUNT(*) " +
                " from tbl_applications_downloads a, " +
                " tbl_applications b, " +
                " tbl_downloads c " +
                " where a.application_id = b.id and a.download_id = c.id " +
                "   and a.application_id = :applicationId", nativeQuery = true)
    Long countDownloadsApplication(@Param("applicationId") Long applicationId);
}