package com.stefanini.msvc.applications.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_applications_downloads")
public class ApplicationDownloadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "download_id", unique = true)
    private Long downloadId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Long downloadId) {
        this.downloadId = downloadId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof ApplicationDownloadEntity)){
            return false;
        }
        ApplicationDownloadEntity objIns = (ApplicationDownloadEntity) obj;
        return this.downloadId != null && this.downloadId.equals(objIns.downloadId);
    }
}
