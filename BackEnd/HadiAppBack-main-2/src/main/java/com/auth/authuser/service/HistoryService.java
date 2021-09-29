package com.auth.authuser.service;

import com.auth.authuser.model.History;
import com.auth.authuser.model.Repo;
import com.auth.authuser.model.User;
import com.auth.authuser.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    public HistoryRepository historyRepository;

    public void addFileElementHistory(MultipartFile file, Long repo, Long user, Long size, Integer doc, Date date, String state,String name) throws Exception {
        if(file!=null){
            String fileName = file.getOriginalFilename();
            String extension = Optional.ofNullable(fileName)
                    .filter(f -> f.contains("."))
                    .map(f -> f.substring(fileName.lastIndexOf(".") + 1)).orElseThrow(()-> new Exception("We can t get the Name"));

            historyRepository.addCreateHistory(doc,fileName,file.getSize(),extension,repo,user, state, date);
        }
    }
    public List<History> getAllUserHistory(Long idUser){
        return historyRepository.findAllByUserId(idUser);
    }

    public void addFolderElementHistory(MultipartFile file, Repo repo, User user, Long size, Date date, String state,String name) throws Exception {
        History history = new History(date, name, "Folder", size, state, null, null, repo, user);
        historyRepository.save(history);
    }
}
