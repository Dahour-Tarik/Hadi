package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nomClient;
    private String prenomClient;
    private String telephone_Client;
    private String emailClient;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adresse> adresseList;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientDoc> clientDocList;

    public Client() {
    }

    public Client(String nomClient, String prenomClient, String telephone_Client, String emailClient, Company company) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.telephone_Client = telephone_Client;
        this.emailClient = emailClient;
        this.company = company;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getTelephone_Client() {
        return telephone_Client;
    }

    public void setTelephone_Client(String telephone_Client) {
        this.telephone_Client = telephone_Client;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    @JsonBackReference(value="company-client")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @JsonManagedReference(value="client-adresse")
    public List<Adresse> getAdresseList() {
        return adresseList;
    }

    public void setAdresseList(List<Adresse> adresseList) {
        this.adresseList = adresseList;
    }

    @JsonManagedReference(value="client-clientDoc")
    public List<ClientDoc> getClientDocList() {
        return clientDocList;
    }

    public void setClientDocList(List<ClientDoc> clientDocList) {
        this.clientDocList = clientDocList;
    }
}
