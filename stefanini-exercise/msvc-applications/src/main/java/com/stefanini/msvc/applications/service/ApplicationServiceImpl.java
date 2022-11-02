package com.stefanini.msvc.applications.service;

import com.stefanini.msvc.applications.clients.ComentaryClientRest;
import com.stefanini.msvc.applications.clients.DownloadClientRest;
import com.stefanini.msvc.applications.clients.RatingClientRest;
import com.stefanini.msvc.applications.models.ComentaryModel;
import com.stefanini.msvc.applications.models.DownloadModel;
import com.stefanini.msvc.applications.models.RatingModel;
import com.stefanini.msvc.applications.models.entity.*;
import com.stefanini.msvc.applications.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service()
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ComentaryClientRest comentaryClientRest;

    @Autowired
    private RatingClientRest ratingClientRest;

    @Autowired
    private DownloadClientRest downloadClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationEntity> findAll() {
        return (List<ApplicationEntity>) applicationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationEntity> findById(Long id) {
        return applicationRepository.findById(id);
    }

    @Override
    @Transactional
    public ApplicationEntity save(ApplicationEntity application) {
        return applicationRepository.save(application);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public Optional<ApplicationEntity> findByTittle(String tittle) {
        return applicationRepository.findByTittle(tittle);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationEntity> findByIdWithApplication(Long id) {
        Optional<ApplicationEntity> opt = applicationRepository.findById(id);
        if (opt.isPresent()){
            ApplicationEntity comentary = opt.get();
            if (! comentary.getApplicationComentaries().isEmpty()){
                List<Long> ids = comentary.getApplicationComentaries().stream().map(pc -> pc.getComentaryId())
                        .collect(Collectors.toList());
                List<ComentaryModel> comentaryModels = comentaryClientRest.findAllById(ids);
                comentary.setComentaries(comentaryModels);
            }
            return Optional.of(comentary);
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationEntity> findByCategory(CategoryEntity category) {
        return applicationRepository.findByCategory(category);
    }

    // Comentaries Section ************************
    @Override
    public Optional<ComentaryModel> assingComentaryToApplication(ComentaryModel comentaryModel, Long applicationId) {
        Optional<ApplicationEntity> opt = applicationRepository.findById(applicationId);
        if (opt.isPresent()){
            ComentaryModel comentaryMsvc = comentaryClientRest.detail(comentaryModel.getId());
            ApplicationEntity application = opt.get();
            ApplicationComentaryEntity applicationComentaryEntity = new ApplicationComentaryEntity();
            applicationComentaryEntity.setComentaryId(comentaryMsvc.getId());

            application.addApplicationComentary(applicationComentaryEntity);
            applicationRepository.save(application);
            return Optional.of(comentaryMsvc);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ComentaryModel> createComentaryToApplication(ComentaryModel comentaryModel, Long applicationId) {
        Optional<ApplicationEntity> opt = applicationRepository.findById(applicationId);
        if (opt.isPresent()){
            ComentaryModel comentaryNewMsvc = comentaryClientRest.create(comentaryModel);
            ApplicationEntity application = opt.get();
            ApplicationComentaryEntity applicationComentaryEntity = new ApplicationComentaryEntity();
            applicationComentaryEntity.setComentaryId(comentaryNewMsvc.getId());

            application.addApplicationComentary(applicationComentaryEntity);
            applicationRepository.save(application);
            return Optional.of(comentaryNewMsvc);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ComentaryModel> removeComentaryToApplication(ComentaryModel comentaryModel, Long applicationId) {
        Optional<ApplicationEntity> opt = applicationRepository.findById(applicationId);
        if (opt.isPresent()){
            ComentaryModel comentaryRemoveMsvc = comentaryClientRest.detail(comentaryModel.getId());
            ApplicationEntity application = opt.get();
            ApplicationComentaryEntity applicationComentaryEntity = new ApplicationComentaryEntity();
            applicationComentaryEntity.setComentaryId(comentaryRemoveMsvc.getId());

            application.removeApplicationComentary(applicationComentaryEntity);
            applicationRepository.save(application);
            return Optional.of(comentaryRemoveMsvc);
        }
        return Optional.empty();
    }


    // Ratings Section ************************
    @Override
    public Optional<RatingModel> createRatingToApplication(RatingModel ratingModel, Long applicationId) {
        Optional<ApplicationEntity> opt = applicationRepository.findById(applicationId);
        if (opt.isPresent()){
            RatingModel ratingNewMsvc = ratingClientRest.create(ratingModel);
            ApplicationEntity application = opt.get();
            ApplicationRatingEntity applicationRatingEntity = new ApplicationRatingEntity();
            applicationRatingEntity.setRatingId(ratingNewMsvc.getId());

            application.addApplicationRating(applicationRatingEntity);
            applicationRepository.save(application);
            return Optional.of(ratingNewMsvc);
        }
        return Optional.empty();
    }

    @Override
    public Optional<RatingModel> removeRatingToApplication(RatingModel ratingModel, Long applicationId) {
        Optional<ApplicationEntity> opt = applicationRepository.findById(applicationId);
        if (opt.isPresent()){
            RatingModel ratingRemoveMsvc = ratingClientRest.detail(ratingModel.getId());
            ApplicationEntity application = opt.get();
            ApplicationRatingEntity applicationRatingEntity = new ApplicationRatingEntity();
            applicationRatingEntity.setRatingId(ratingRemoveMsvc.getId());

            application.removeApplicationCRating(applicationRatingEntity);
            applicationRepository.save(application);
            return Optional.of(ratingRemoveMsvc);
        }
        return Optional.empty();
    }

    // Downloads Section ************************
    @Override
    public Optional<DownloadModel> createDownloadToApplication(DownloadModel downloadModel, Long applicationId) {
        Optional<ApplicationEntity> opt = applicationRepository.findById(applicationId);
        if (opt.isPresent()){
            DownloadModel downloadNewMsvc = downloadClientRest.create(downloadModel);
            ApplicationEntity application = opt.get();
            ApplicationDownloadEntity applicationDownloadEntity = new ApplicationDownloadEntity();
            applicationDownloadEntity.setDownloadId(downloadNewMsvc.getId());

            application.addApplicationDownload(applicationDownloadEntity);
            applicationRepository.save(application);
            return Optional.of(downloadNewMsvc);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DownloadModel> removeDownloadToApplication(DownloadModel downloadModel, Long applicationId) {
        Optional<ApplicationEntity> opt = applicationRepository.findById(applicationId);
        if (opt.isPresent()){
            DownloadModel downloadRemoveMsvc = downloadClientRest.detail(downloadModel.getId());
            ApplicationEntity application = opt.get();
            ApplicationDownloadEntity applicationDownloadEntity = new ApplicationDownloadEntity();
            applicationDownloadEntity.setDownloadId(downloadRemoveMsvc.getId());

            application.removeApplicationDownload(applicationDownloadEntity);
            applicationRepository.save(application);
            return Optional.of(downloadRemoveMsvc);
        }
        return Optional.empty();
    }
}
