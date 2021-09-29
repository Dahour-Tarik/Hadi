package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Modele {

    @Id
    private String nomModele;
    private boolean choisi;
    private String color;

    @OneToMany(mappedBy = "modele", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientDoc> clientDocList;

    public Modele(String nomModele, boolean choisi, String color) {
        this.nomModele = nomModele;
        this.choisi = choisi;
        this.color = color;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public boolean isChoisi() {
        return choisi;
    }

    public void setChoisi(boolean choisi) {
        this.choisi = choisi;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @JsonManagedReference(value="modele-client")
    public List<ClientDoc> getClientDocList() {
        return clientDocList;
    }

    public void setClientDocList(List<ClientDoc> clientDocList) {
        this.clientDocList = clientDocList;
    }
}
