package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class BankAccount {

    @Id
    @Column(name = "iban_account", length = 34)
    private String iban;
    @Column(name = "bic_account", length = 11)
    private String bic;
    @Column(name = "rum", length = 30)
    private String rum;
    @Column(name = "code_banque", length = 5)
    private Long codeBanque;
    @Column(name = "code_guichet", length = 5)
    private Long codeGuichet;
    @Column(name = "date_signature_mandat")
    private Date dateSignatureMandat;
    @Column(name = "numero_carte", length = 16)
    private String numeroCarteBancaire;
    @Column(name = "date_experation")
    private Date dateExperation;
    @Column(name = "cvc", length = 4)
    private Long cvc;
    @Column(name = "type_carte_bancaire")
    private String typeCarteBancaire;
    @Column(name = "nomBanque")
    private String nomBanque;

    @ManyToOne
    private Company company;

    public BankAccount(String iban, String bic, String rum, Long codeBanque, Long codeGuichet, Date dateSignatureMandat, String numeroCarteBancaire, Date dateExperation, Long cvc, String typeCarteBancaire, String nomBanque) {
        this.iban = iban;
        this.bic = bic;
        this.rum = rum;
        this.codeBanque = codeBanque;
        this.codeGuichet = codeGuichet;
        this.dateSignatureMandat = dateSignatureMandat;
        this.numeroCarteBancaire = numeroCarteBancaire;
        this.dateExperation = dateExperation;
        this.cvc = cvc;
        this.typeCarteBancaire = typeCarteBancaire;
        this.nomBanque = nomBanque;
    }

    @JsonBackReference(value="company-compteBanq")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getRum() {
        return rum;
    }

    public void setRum(String rum) {
        this.rum = rum;
    }

    public Long getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(Long codeBanque) {
        this.codeBanque = codeBanque;
    }

    public Long getCodeGuichet() {
        return codeGuichet;
    }

    public void setCodeGuichet(Long codeGuichet) {
        this.codeGuichet = codeGuichet;
    }

    public Date getDateSignatureMandat() {
        return dateSignatureMandat;
    }

    public void setDateSignatureMandat(Date dateSignatureMandat) {
        this.dateSignatureMandat = dateSignatureMandat;
    }

    public String getNumeroCarteBancaire() {
        return numeroCarteBancaire;
    }

    public void setNumeroCarteBancaire(String numeroCarteBancaire) {
        this.numeroCarteBancaire = numeroCarteBancaire;
    }

    public Date getDateExperation() {
        return dateExperation;
    }

    public void setDateExperation(Date dateExperation) {
        this.dateExperation = dateExperation;
    }

    public Long getCvc() {
        return cvc;
    }

    public void setCvc(Long cvc) {
        this.cvc = cvc;
    }

    public String getTypeCarteBancaire() {
        return typeCarteBancaire;
    }

    public void setTypeCarteBancaire(String typeCarteBancaire) {
        this.typeCarteBancaire = typeCarteBancaire;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }
}
