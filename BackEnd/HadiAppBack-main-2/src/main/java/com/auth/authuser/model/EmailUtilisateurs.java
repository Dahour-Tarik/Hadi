package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class EmailUtilisateurs {
    @Id
    @Column(name="email_utilisateur")
    private String emailUtilisateur;

    @ManyToOne(cascade = CascadeType.ALL)
    private Company emailUtilisateurCompany;

    public EmailUtilisateurs(String emailUtilisateur, Company emailUtilisateurCompany) {
        this.emailUtilisateur = emailUtilisateur;
        this.emailUtilisateurCompany = emailUtilisateurCompany;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    @JsonBackReference(value="company-emailu")
    public Company getEmailUtilisateurCompany() {
        return emailUtilisateurCompany;
    }

    public void setEmailUtilisateurCompany(Company emailUtilisateurCompany) {
        this.emailUtilisateurCompany = emailUtilisateurCompany;
    }
}
