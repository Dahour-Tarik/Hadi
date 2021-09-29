package com.auth.authuser.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "facture_recurrente")
public class FactureRecurrente extends ClientDoc{

    private static final long serialVersionUID = 1L;

    private Date dateFin;
    private String frequence;

    public FactureRecurrente(){

    }

    public FactureRecurrente(String nomClientDoc, String description, int quantite, String designation, Date dateCreation, String basePrix, float prixHt, float tauxTva, float prixTtc, float reduction, String typeDoc, Date dateFin, String frequence) {
        super(nomClientDoc, description, quantite, designation, dateCreation, basePrix, prixHt, tauxTva, prixTtc, reduction, typeDoc);
        this.dateFin = dateFin;
        this.frequence = frequence;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }
}
