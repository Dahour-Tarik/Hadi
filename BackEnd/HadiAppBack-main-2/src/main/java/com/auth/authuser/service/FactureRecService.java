package com.auth.authuser.service;

import com.auth.authuser.model.FactureRecurrente;
import com.auth.authuser.repository.FactureRecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactureRecService {

    @Autowired
    private FactureRecRepository factureRecRepository;

    public FactureRecurrente addFactureRec(FactureRecurrente factureRecurrente){
        return factureRecRepository.save(factureRecurrente);
    }

}
