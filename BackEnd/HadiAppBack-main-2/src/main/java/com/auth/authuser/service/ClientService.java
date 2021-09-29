package com.auth.authuser.service;

import com.auth.authuser.model.Client;
import com.auth.authuser.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClients(Long idUser, String docName){
        return clientRepository.getClients(idUser,docName);
    }

    public List<Client> getAll(Long idUser){
        return clientRepository.findClientByUserId(idUser);
    }

    public Client addClient(Client client){
        return clientRepository.save(client);
    }

    public Client updateClient(Client client){
        return clientRepository.save(client);
    }

    public void deleteClient(Long id){
        boolean exist = clientRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Client with id = "+id+" does not exist");
        }
        clientRepository.deleteById(id);
    }
}
