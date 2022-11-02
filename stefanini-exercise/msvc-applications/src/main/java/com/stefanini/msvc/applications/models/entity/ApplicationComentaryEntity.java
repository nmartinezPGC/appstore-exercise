package com.stefanini.msvc.applications.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_applications_comentaries")
public class ApplicationComentaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "comentary_id", unique = true)
    private Long comentaryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComentaryId() {
        return comentaryId;
    }

    public void setComentaryId(Long comentaryId) {
        this.comentaryId = comentaryId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof ApplicationComentaryEntity)){
            return false;
        }
        ApplicationComentaryEntity objIns = (ApplicationComentaryEntity) obj;
        return this.comentaryId != null && this.comentaryId.equals(objIns.comentaryId);
    }
}
