package com.auth.authuser.service;

import com.auth.authuser.model.Repo;
import com.auth.authuser.repository.RepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepService {

    @Autowired
    private RepRepository repRepository;

    public List<Repo> getRepos(Long userId){
        List<Repo> repos = repRepository.findByUserId(userId);
        return repos;
    }

    public List<Repo> getAll(){
        List<Repo> repos = repRepository.findAll();
        return repos;
    }
    public Repo addRepo(Repo repo){
        return repRepository.save(repo);
   }

    public void deleteRep(Long repId) {
        boolean exist = repRepository.existsById(repId);
        if(!exist){
            throw new IllegalStateException("Folder with id = "+repId+" does not exist");
        }
        repRepository.deleteById(repId);
    }

    public Repo updateNameFolder(Repo repo) {
        return repRepository.save(repo);
    }

    public Boolean moveFolder(String folderId, String newFolderId){
        Long _folderId = Long.valueOf(folderId);
        Long _newFolderId = Long.valueOf(newFolderId);
        try{
            repRepository.moveFolder(_folderId,_newFolderId);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }
}
