package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class NatureCompany {

    @Id
    private String formeJuridique;

    @OneToMany(mappedBy="natureCompany", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> Company;

    public NatureCompany(){

    }

    public NatureCompany(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    @JsonManagedReference(value="ntrCompany-company")
    public List<Company> getCompany() {
        return Company;
    }

    public void setCompany(List<Company> company) {
        Company = company;
    }
}
