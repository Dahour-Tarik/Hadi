package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "client_doc")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ClientDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String nomClientDoc;
    private String description;
    private int quantite;
    private String designation;
    private Date dateCreation;
    private String basePrix;
    private float prixHt;
    private float tauxTva;
    private float prixTtc;
    private float reduction;
    private String typeDoc;

    @ManyToOne
    private Classification classification;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Modele modele;
    @ManyToOne
    private Client client;

    public ClientDoc() {
    }

    public ClientDoc(String nomClientDoc, String description, int quantite, String designation, Date dateCreation, String basePrix, float prixHt, float tauxTva, float prixTtc, float reduction, String typeDoc) {
        this.nomClientDoc = nomClientDoc;
        this.description = description;
        this.quantite = quantite;
        this.designation = designation;
        this.dateCreation = dateCreation;
        this.basePrix = basePrix;
        this.prixHt = prixHt;
        this.tauxTva = tauxTva;
        this.prixTtc = prixTtc;
        this.reduction = reduction;
        this.typeDoc = typeDoc;
    }

    public String getNomClientDoc() {
        return nomClientDoc;
    }

    public void setNomClientDoc(String nomClientDoc) {
        this.nomClientDoc = nomClientDoc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getBasePrix() {
        return basePrix;
    }

    public void setBasePrix(String basePrix) {
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

    public float getReduction() {
        return reduction;
    }

    public void setReduction(float reduction) {
        this.reduction = reduction;
    }

    @JsonBackReference(value="classification-clientDoc")
    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    @JsonBackReference(value="company-clientDoc")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @JsonBackReference(value="modele-client")
    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    @JsonBackReference(value="client-clientDoc")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }
}
