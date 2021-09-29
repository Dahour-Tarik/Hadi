package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;
    private String designation;
    private String description;
    private int basePrix;
    private float prixHt;
    private float tauxTva;
    private float prixTtc;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Catalogue catalogue;

    public Article(){

    }

    public Article(String designation, String description, int basePrix, float prixHt, float tauxTva, float prixTtc, Company company, Catalogue catalogue) {
        this.designation = designation;
        this.description = description;
        this.basePrix = basePrix;
        this.prixHt = prixHt;
        this.tauxTva = tauxTva;
        this.prixTtc = prixTtc;
        this.company = company;
        this.catalogue = catalogue;
    }

    public Long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBasePrix() {
        return basePrix;
    }

    public void setBasePrix(int basePrix) {
        this.basePrix = basePrix;
    }

    public float getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(float prixHt) {
        this.prixHt = prixHt;
    }

    public float getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(float tauxTva) {
        this.tauxTva = tauxTva;
    }

    public float getPrixTtc() {
        return prixTtc;
    }

    public void setPrixTtc(float prixTtc) {
        this.prixTtc = prixTtc;
    }

    @JsonBackReference(value="company-article")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @JsonBackReference(value="catalogue-article")
    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }
}
