package com.auth.authuser.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("0")
public class Accountant extends User{
    private String matricule;

    public Accountant() {
    }

    public Accountant(String userName, String password, String email, boolean active, String role, byte[] image, String matricule) {
        super(userName, password, email, active, role, image);
        this.matricule = matricule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
