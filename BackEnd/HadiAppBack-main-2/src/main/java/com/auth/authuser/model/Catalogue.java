package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCatalogue;
    private Long nbArticle;

    @OneToMany(mappedBy="catalogue")
    private List<Article> articleList;

    public Catalogue(){

    }

    public Catalogue(Long nbArticle) {
        this.nbArticle = nbArticle;
    }

    public Long getIdCatalogue() {
        return idCatalogue;
    }

    public void setIdCatalogue(Long idCatalogue) {
        this.idCatalogue = idCatalogue;
    }

    public Long getNbArticle() {
        return nbArticle;
    }

    public void setNbArticle(Long nbArticle) {
        this.nbArticle = nbArticle;
    }

    @JsonManagedReference(value="catalogue-article")
    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
