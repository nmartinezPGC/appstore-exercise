package com.stefanini.msvc.applications.repository;

import com.stefanini.msvc.applications.models.entity.ApplicationComentaryEntity;
import com.stefanini.msvc.applications.models.entity.ApplicationEntity;
import com.stefanini.msvc.applications.models.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {
    Optional<ApplicationEntity> findByTittle(String tittle);

    List<ApplicationEntity> findByCategory(CategoryEntity category);

    @Query(value = "select * from tbl_applications_comentaries a " +
            "inner join tbl_applications b on a.application_id = b.id " +
            "inner join tbl_applications c on a.comentary_id = c.id " +
            "where a.application_id = ?1 ", nativeQuery = true)
    List<ApplicationComentaryEntity> getComentariesByApplication(Long applicationId);
}