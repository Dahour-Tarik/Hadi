package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class History {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="change_date")
    private Date changeDate;
    @Column(name="media_name")
    private String mediaName;
    @Column(name="media_type")
    private String mediaType;
    @Column(name="media_size")
    private Long mediaSize;
    @Column(name="media_state")
    private String mediaState;
    @Column(name="media_extension")
    private String mediaExtension;


    @ManyToOne
    private Doc doc;

    @ManyToOne
    private Repo repo;

    @ManyToOne
    private User user;

    public History(){

    }

    public History(Date changeDate, String mediaName, String mediaType, Long mediaSize, String mediaState, String mediaExtension, Doc doc, Repo repo, User user) {
        this.changeDate = changeDate;
        this.mediaName = mediaName;
        this.mediaType = mediaType;
        this.mediaSize = mediaSize;
        this.mediaState = mediaState;
        this.mediaExtension = mediaExtension;
        this.doc = doc;
        this.repo = repo;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Long getMediaSize() {
        return mediaSize;
    }

    public void setMediaSize(Long mediaSize) {
        this.mediaSize = mediaSize;
    }

    public String getMediaState() {
        return mediaState;
    }

    public void setMediaState(String mediaState) {
        this.mediaState = mediaState;
    }

    public String getMediaExtension() {
        return mediaExtension;
    }

    public void setMediaExtension(String mediaExtension) {
        this.mediaExtension = mediaExtension;
    }

    @JsonBackReference(value="doc-history")
    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    @JsonBackReference(value="repo-history")
    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    @JsonBackReference(value="user-history")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
