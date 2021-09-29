package com.auth.authuser.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EmailHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateSending;
    private String typeDoc;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Accountant accountant;

    @ManyToOne
    private Doc doc;
    @ManyToOne
    private ClientDoc clientDoc;

    public EmailHistory(){

    }

    public EmailHistory(Date dateSending, String typeDoc, Company company, Accountant accountant, Doc doc, ClientDoc clientDoc) {
        this.dateSending = dateSending;
        this.typeDoc = typeDoc;
        this.company = company;
        this.accountant = accountant;
        this.doc = doc;
        this.clientDoc = clientDoc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateSending() {
        return dateSending;
    }

    public void setDateSending(Date dateSending) {
        this.dateSending = dateSending;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Accountant getAccountant() {
        return accountant;
    }

    public void setAccountant(Accountant accountant) {
        this.accountant = accountant;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public ClientDoc getClientDoc() {
        return clientDoc;
    }

    public void setClientDoc(ClientDoc clientDoc) {
        this.clientDoc = clientDoc;
    }
}
