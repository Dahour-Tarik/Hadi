package com.auth.authuser.service;

import com.auth.authuser.model.ClientDoc;
import com.auth.authuser.repository.DocClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocClientService {

    @Autowired
    private DocClientRepository docClientRepository;

    public List<ClientDoc> getDocsClient(Long idUser, String docName){
        return docClientRepository.getDocClient(idUser,docName);
    }

    public List<ClientDoc> getAll(Long idUser){
        return docClientRepository.findByCompanyId(idUser);
    }

    public ClientDoc addClientDoc(ClientDoc clientDoc){
        return docClientRepository.save(clientDoc);
    }

    public List<ClientDoc> getDocsByClientId(Long idUser, Long clientId){ return docClientRepository.getDocClientByClientName(idUser,clientId);}
}
