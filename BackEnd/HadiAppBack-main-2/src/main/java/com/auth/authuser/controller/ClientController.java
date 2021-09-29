package com.auth.authuser.controller;

import com.auth.authuser.model.Client;
import com.auth.authuser.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = {"/{userId}/{nomClient}", "/{userId}"})
    public ResponseEntity<List<Client>> getClients(@PathVariable Long userId, @PathVariable(required = false) String nomClient){
        List<Client> listClient = null;
        if(nomClient != null){
            listClient = clientService.getClients(userId,nomClient);
        }
        else{
            listClient = clientService.getAll(userId);
        }
        return new ResponseEntity<>(listClient, HttpStatus.OK);
    }

    @PostMapping("/addClient")
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        Client client1 = clientService.addClient(client);
        return new ResponseEntity<>(client1, HttpStatus.CREATED);
    }

    @PutMapping("/updateClient")
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        Client client1 = clientService.updateClient(client);
        return new ResponseEntity<>(client1, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClient/{clientId}")
    public void deleteClient(@PathVariable Long clientId){
        clientService.deleteClient(clientId);
    }
}
