package com.stefanini.msvc.ratings.repository;

import com.stefanini.msvc.ratings.entity.RatingEntity;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<RatingEntity, Long> {
    @Query(value = "select AVG(c.rating) " +
            "from tbl_applications_ratings a, " +
            "tbl_applications b, " +
            "tbl_ratings c " +
            "where a.application_id = b.id and a.rating_id = c.id " +
            "   and a.application_id = :applicationId", nativeQuery = true)
    Long avgApplication(@Param("applicationId") Long applicationId);
}