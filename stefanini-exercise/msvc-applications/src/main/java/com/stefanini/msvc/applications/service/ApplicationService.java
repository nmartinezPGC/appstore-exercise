package com.stefanini.msvc.applications.service;

import com.stefanini.msvc.applications.models.ComentaryModel;
import com.stefanini.msvc.applications.models.DownloadModel;
import com.stefanini.msvc.applications.models.RatingModel;
import com.stefanini.msvc.applications.models.entity.ApplicationComentaryEntity;
import com.stefanini.msvc.applications.models.entity.ApplicationEntity;
import com.stefanini.msvc.applications.models.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    List<ApplicationEntity> findAll();
    Optional<ApplicationEntity> findById(Long id);
    ApplicationEntity save(ApplicationEntity application);
    void delete(Long id);
    Optional<ApplicationEntity> findByTittle(String tittle);

    Optional<ApplicationEntity> findByIdWithApplication(Long id);

    List<ApplicationEntity> findByCategory(CategoryEntity category);

    // Connect to msvc-comentaries
    Optional<ComentaryModel> assingComentaryToApplication(ComentaryModel comentaryModel, Long applicationId);
    Optional<ComentaryModel> createComentaryToApplication(ComentaryModel comentaryModel, Long applicationId);
    Optional<ComentaryModel> removeComentaryToApplication(ComentaryModel comentaryModel, Long applicationId);

    // Connect to msvc-ratings
    Optional<RatingModel> createRatingToApplication(RatingModel ratingModel, Long applicationId);
    Optional<RatingModel> removeRatingToApplication(RatingModel ratingModel, Long applicationId);

    // Connect to msvc-downloads
    Optional<DownloadModel> createDownloadToApplication(DownloadModel downloadModel, Long applicationId);
    Optional<DownloadModel> removeDownloadToApplication(DownloadModel downloadModel, Long applicationId);
}
