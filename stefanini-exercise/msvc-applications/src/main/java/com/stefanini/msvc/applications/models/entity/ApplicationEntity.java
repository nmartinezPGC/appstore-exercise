package com.stefanini.msvc.applications.models.entity;

import com.stefanini.msvc.applications.models.ComentaryModel;
import com.stefanini.msvc.applications.models.DownloadModel;
import com.stefanini.msvc.applications.models.RatingModel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_applications")
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tittle", length = 150, unique = true)
    @NotEmpty
    private String tittle;

    @Column(name = "developer", length = 150)
    @NotEmpty
    private String developer;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "price", columnDefinition = "double default 0")
    @NumberFormat
    private Double price;

    @Column(name = "img", length = 200)
    @NotEmpty
    private String img;

    @Column(name = "rating", columnDefinition = "integer default 0")
    private Integer rating = 0;

    @Column(name = "instaled", columnDefinition = "boolean default false")
    private Boolean instaled = false;

    // Audit field
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = new Date();

    // Relationals
    @ManyToOne
    private CategoryEntity category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "application_id")
    private List<ApplicationComentaryEntity> applicationComentaries;

    @Transient
    private List<ComentaryModel> comentaries;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "application_id")
    private List<ApplicationRatingEntity> applicationRatings;

    @Transient
    private List<RatingModel> ratings;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "application_id")
    private List<ApplicationDownloadEntity> applicationDownloads;

    @Transient
    private List<DownloadModel> downloads;

    // Constructor
    public ApplicationEntity(){
        applicationComentaries = new ArrayList<>();
        applicationRatings = new ArrayList<>();
        applicationDownloads = new ArrayList<>();
        comentaries = new ArrayList<>();
        ratings = new ArrayList<>();
        downloads = new ArrayList<>();
    }

    // Methods externals
    public void addApplicationComentary(ApplicationComentaryEntity applicationComentaryEntity){
        applicationComentaries.add(applicationComentaryEntity);
    }

    public void removeApplicationComentary(ApplicationComentaryEntity applicationComentaryEntity){
        applicationComentaries.remove(applicationComentaryEntity);
    }

    public void addApplicationRating(ApplicationRatingEntity applicationRatingEntity){
        applicationRatings.add(applicationRatingEntity);
    }

    public void removeApplicationCRating(ApplicationRatingEntity applicationRatingEntity){
        applicationRatings.remove(applicationRatingEntity);
    }

    public void addApplicationDownload(ApplicationDownloadEntity applicationDownloadEntity){
        applicationDownloads.add(applicationDownloadEntity);
    }

    public void removeApplicationDownload(ApplicationDownloadEntity applicationDownloadEntity){
        applicationDownloads.remove(applicationDownloadEntity);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<ApplicationComentaryEntity> getApplicationComentaries() {
        return applicationComentaries;
    }

    public void setApplicationComentaries(List<ApplicationComentaryEntity> applicationComentaries) {
        this.applicationComentaries = applicationComentaries;
    }

    public List<ComentaryModel> getComentaries() {
        return comentaries;
    }

    public void setComentaries(List<ComentaryModel> comentaries) {
        this.comentaries = comentaries;
    }

    public List<ApplicationRatingEntity> getApplicationRatings() {
        return applicationRatings;
    }

    public void setApplicationRatings(List<ApplicationRatingEntity> applicationRatings) {
        this.applicationRatings = applicationRatings;
    }

    public List<RatingModel> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingModel> ratings) {
        this.ratings = ratings;
    }

    public List<ApplicationDownloadEntity> getApplicationDownloads() {
        return applicationDownloads;
    }

    public void setApplicationDownloads(List<ApplicationDownloadEntity> applicationDownloads) {
        this.applicationDownloads = applicationDownloads;
    }

    public List<DownloadModel> getDownloads() {
        return downloads;
    }

    public void setDownloads(List<DownloadModel> downloads) {
        this.downloads = downloads;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Boolean getInstaled() {
        return instaled;
    }

    public void setInstaled(Boolean instaled) {
        this.instaled = instaled;
    }
}