package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Classification {

    @Id
    private String nomClassification;

    @OneToMany(mappedBy="classification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientDoc> ClientDocList;

    public Classification(String nomClassification) {
        this.nomClassification = nomClassification;
    }

    public String getNomClassification() {
        return nomClassification;
    }

    public void setNomClassification(String nomClassification) {
        this.nomClassification = nomClassification;
    }

    @JsonManagedReference(value="classification-clientDoc")
    public List<ClientDoc> getClientDocList() {
        return ClientDocList;
    }

    public void setClientDocList(List<ClientDoc> clientDocList) {
        ClientDocList = clientDocList;
    }
}
