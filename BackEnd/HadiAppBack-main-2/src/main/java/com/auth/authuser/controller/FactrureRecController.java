package com.auth.authuser.controller;

import com.auth.authuser.model.FactureRecurrente;
import com.auth.authuser.service.FactureRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/factureRec")
public class FactrureRecController {
    @Autowired
    private FactureRecService factureRecService;

    @PostMapping("/addFactureRec")
    public ResponseEntity<FactureRecurrente> addFactureRec(@RequestBody FactureRecurrente factureRecurrente){
        FactureRecurrente factureRecurrente1 = factureRecService.addFactureRec(factureRecurrente);
        return new ResponseEntity<>(factureRecurrente1, HttpStatus.CREATED);
    }
}
