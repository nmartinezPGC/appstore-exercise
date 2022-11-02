package com.stefanini.msvc.applications.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_applications_ratings")
public class ApplicationRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rating_id", unique = true)
    private Long ratingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof ApplicationRatingEntity)){
            return false;
        }
        ApplicationRatingEntity objIns = (ApplicationRatingEntity) obj;
        return this.ratingId != null && this.ratingId.equals(objIns.ratingId);
    }
}
