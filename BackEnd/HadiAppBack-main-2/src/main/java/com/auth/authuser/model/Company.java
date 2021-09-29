package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@DiscriminatorValue("1")
public class Company extends User {
    private String nomEntreprise;
    private Date dateNaissance;
    private String situationDossier;
    private String emailEntreprise;
    private String telephone;
    private String lienSite;
    private String codeApe;
    private String numTvaIntracommunautaire;
    private String numSiret;
    private String numSiren;
    private String capitalSocial;
    private String codeRcs;
    private String immatriculationAuRcs;
    private Date dateImmatriculation;
    private Date dateDebutActiviteKbis;
    private String activiteKbis;
    private Date dateCotureExerciceSocial;

    @ManyToOne
    private NatureCompany natureCompany;

    @ManyToOne
    private Accountant chefMission;

    public Company() {
    }

    public Company(String userName, String password, String email, boolean active, String role, byte[] image, String nomEntreprise, Date dateNaissance, String situationDossier, String emailEntreprise, String telephone, String lienSite, String codeApe, String numTvaIntracommunautaire, String numSiret, String numSiren, String capitalSocial, String codeRcs, String immatriculationAuRcs, Date dateImmatriculation, Date dateDebutActiviteKbis, String activiteKbis, Date dateCotureExerciceSocial, NatureCompany natureCompany, Accountant chefMission) {
        super(userName, password, email, active, role, image);
        this.nomEntreprise = nomEntreprise;
        this.dateNaissance = dateNaissance;
        this.situationDossier = situationDossier;
        this.emailEntreprise = emailEntreprise;
        this.telephone = telephone;
        this.lienSite = lienSite;
        this.codeApe = codeApe;
        this.numTvaIntracommunautaire = numTvaIntracommunautaire;
        this.numSiret = numSiret;
        this.numSiren = numSiren;
        this.capitalSocial = capitalSocial;
        this.codeRcs = codeRcs;
        this.immatriculationAuRcs = immatriculationAuRcs;
        this.dateImmatriculation = dateImmatriculation;
        this.dateDebutActiviteKbis = dateDebutActiviteKbis;
        this.activiteKbis = activiteKbis;
        this.dateCotureExerciceSocial = dateCotureExerciceSocial;
        this.natureCompany = natureCompany;
        this.chefMission = chefMission;
    }



    @JsonBackReference(value = "ntrCompany-company")
    public NatureCompany getNatureCompany() {
        return natureCompany;
    }

    public void setNatureCompany(NatureCompany natureCompany) {
        this.natureCompany = natureCompany;
    }

    @JsonBackReference(value = "company-accountant")
    public Accountant getChefMission() {
        return chefMission;
    }

    public void setChefMission(Accountant chefMission) {
        this.chefMission = chefMission;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSituationDossier() {
        return situationDossier;
    }

    public void setSituationDossier(String situationDossier) {
        this.situationDossier = situationDossier;
    }

    public String getEmailEntreprise() {
        return emailEntreprise;
    }

    public void setEmailEntreprise(String emailEntreprise) {
        this.emailEntreprise = emailEntreprise;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLienSite() {
        return lienSite;
    }

    public void setLienSite(String lienSite) {
        this.lienSite = lienSite;
    }

    public String getCodeApe() {
        return codeApe;
    }

    public void setCodeApe(String codeApe) {
        this.codeApe = codeApe;
    }

    public String getNumTvaIntracommunautaire() {
        return numTvaIntracommunautaire;
    }

    public void setNumTvaIntracommunautaire(String numTvaIntracommunautaire) {
        this.numTvaIntracommunautaire = numTvaIntracommunautaire;
    }

    public String getNumSiret() {
        return numSiret;
    }

    public void setNumSiret(String numSiret) {
        this.numSiret = numSiret;
    }

    public String getNumSiren() {
        return numSiren;
    }

    public void setNumSiren(String numSiren) {
        this.numSiren = numSiren;
    }

    public String getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(String capitalSocial) {
        this.capitalSocial = capitalSocial;
    }

    public String getCodeRcs() {
        return codeRcs;
    }

    public void setCodeRcs(String codeRcs) {
        this.codeRcs = codeRcs;
    }

    public String getImmatriculationAuRcs() {
        return immatriculationAuRcs;
    }

    public void setImmatriculationAuRcs(String immatriculationAuRcs) {
        this.immatriculationAuRcs = immatriculationAuRcs;
    }

    public Date getDateImmatriculation() {
        return dateImmatriculation;
    }

    public void setDateImmatriculation(Date dateImmatriculation) {
        this.dateImmatriculation = dateImmatriculation;
    }

    public Date getDateDebutActiviteKbis() {
        return dateDebutActiviteKbis;
    }

    public void setDateDebutActiviteKbis(Date dateDebutActiviteKbis) {
        this.dateDebutActiviteKbis = dateDebutActiviteKbis;
    }

    public String getActiviteKbis() {
        return activiteKbis;
    }

    public void setActiviteKbis(String activiteKbis) {
        this.activiteKbis = activiteKbis;
    }

    public Date getDateCotureExerciceSocial() {
        return dateCotureExerciceSocial;
    }

    public void setDateCotureExerciceSocial(Date dateCotureExerciceSocial) {
        this.dateCotureExerciceSocial = dateCotureExerciceSocial;
    }

}