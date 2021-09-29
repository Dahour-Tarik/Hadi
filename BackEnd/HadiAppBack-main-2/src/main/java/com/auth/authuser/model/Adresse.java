package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresse")
    private Long idAdresse;
    private String adresse;
    private String compementAdresse;
    private String ville;
    private String codePostal;

    @ManyToOne
    private User user;

    @ManyToOne
    private Client client;

    public Adresse(){

    }
    public Adresse(String adresse, String compementAdresse, String ville, String codePostal, User user) {
        this.adresse = adresse;
        this.compementAdresse = compementAdresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.user = user;
    }

    @JsonBackReference(value="user-adresse")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return idAdresse;
    }

    public void setId(Long id) {
        this.idAdresse = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCompementAdresse() {
        return compementAdresse;
    }

    public void setCompementAdresse(String compementAdresse) {
        this.compementAdresse = compementAdresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @JsonBackReference(value="client-adresse")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "adresse='" + adresse + '\'' +
                ", compementAdresse='" + compementAdresse + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                '}';
    }
}
