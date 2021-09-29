package com.auth.authuser.controller;

import com.auth.authuser.model.ClientDoc;
import com.auth.authuser.service.DocClientService;
import com.auth.authuser.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/docClient")
public class DocClientController {

    @Autowired
    private DocClientService docClientService;

    @Autowired
    private SendEmailService sendEmailService;

    @GetMapping("/{userId}/{nomClientDoc}")
    public ResponseEntity<List<ClientDoc>> getDocsClient(@PathVariable Long userId, @PathVariable String nomClientDoc){
        List<ClientDoc> listDocClient = null;
        if(nomClientDoc != null){
            listDocClient = docClientService.getDocsClient(userId,nomClientDoc);
        }
        else{
            listDocClient = docClientService.getAll(userId);
        }

        return new ResponseEntity<>(listDocClient, HttpStatus.OK);
    }

    @GetMapping("/filtre/{userId}/{clientId}")
    public ResponseEntity<List<ClientDoc>> getByClientId(@PathVariable Long userId, @PathVariable Long clientId){
        List<ClientDoc> listDocClient = null;
        if(clientId != null){
            listDocClient = docClientService.getDocsByClientId(userId,clientId);
        }
        else{
            listDocClient = docClientService.getAll(userId);
        }

        return new ResponseEntity<>(listDocClient, HttpStatus.OK);
    }

    @PostMapping("/addDocClient")
    public ResponseEntity<ClientDoc> addDocClient(@RequestBody ClientDoc clientDoc){
        ClientDoc clientDoc1 = docClientService.addClientDoc(clientDoc);
        sendEmailService.addEmail(new Date(),clientDoc1.getDescription(), clientDoc1.getCompany(),clientDoc1.getCompany().getChefMission(),null,clientDoc1);
        String typedoc = clientDoc1.getDescription().equals("facture") || clientDoc1.getDescription().equals("facture reccurente") ? "une " : "un ";
        sendEmailService.sendEmail(clientDoc1.getCompany().getChefMission().getEmail(), "L'entreprise "+clientDoc1.getCompany().getNomEntreprise()+" vient de deposer "+ typedoc + clientDoc1.getDescription(), "depos fichier");
        return new ResponseEntity<>(clientDoc1, HttpStatus.CREATED);
    }

}
