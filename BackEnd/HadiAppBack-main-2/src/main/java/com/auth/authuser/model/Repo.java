package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Repo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rep_name", length = 65)
    private String name;
    @Column(name = "rep_path", length = 65)
    private String path;
    @Column(name = "dateModified")
    private Date dateModified;
    @Column(name = "rep_creation_date")
    private Date repDateCreation;
    @Column(name = "size_repo")
    private Long sizeRepo;
    @Column(name = "hasChild")
    private boolean hasChild;
    @Column(name = "isFile")
    private boolean isFile;
    @Column(name = "filterPath")
    private String filterPath;

    @OneToMany(mappedBy="repo")
    private List<Doc> docs;

    @OneToOne
    @JoinColumn(name="parents_rep_id")
    private Repo parents;

    @OneToMany(mappedBy = "parents", cascade = CascadeType.ALL)
    private List<Repo> enfants = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "repo", cascade = CascadeType.ALL)
    private List<History> historyList;

    public Repo(){

    }

    public Repo(String name, String path, Date dateModified, Date repDateCreation, Long sizeRepo, boolean hasChild, String filterPath, Repo parents, User user) {
        this.name = name;
        this.path = path;
        this.dateModified = dateModified;
        this.repDateCreation = repDateCreation;
        this.sizeRepo = sizeRepo;
        this.hasChild = hasChild;
        this.isFile = false;
        this.filterPath = filterPath;
        this.parents = parents;
        this.user = user;
    }

    @JsonBackReference(value="user-repo")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonManagedReference(value="enfant-parent")
    public List<Repo> getEnfants() {
        return enfants;
    }

    public void setEnfants(List<Repo> enfants) {
        this.enfants = enfants;
    }

    @JsonBackReference(value="enfant-parent")
    public Repo getParents() {
        return parents;
    }

    public void setParents(Repo parents) {
        this.parents = parents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public String getFilterPath() {
        return filterPath;
    }

    public void setFilterPath(String filterPath) {
        this.filterPath = filterPath;
    }

    @JsonManagedReference(value="repo-doc")
    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public Date getRepDateCreation() {
        return repDateCreation;
    }

    public void setRepDateCreation(Date repDateCreation) {
        this.repDateCreation = repDateCreation;
    }

    public Long getSizeRepo() {
        return sizeRepo;
    }

    public void setSizeRepo(Long sizeRepo) {
        this.sizeRepo = sizeRepo;
    }

    @JsonManagedReference(value="repo-history")
    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }


}
