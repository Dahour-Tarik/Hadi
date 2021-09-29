package com.auth.authuser.service;

import com.auth.authuser.model.Company;
import com.auth.authuser.model.Doc;
import com.auth.authuser.model.History;
import com.auth.authuser.model.Repo;
import com.auth.authuser.repository.DocRepository;
import com.auth.authuser.repository.HistoryRepository;
import com.auth.authuser.repository.RepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocService {
    @Autowired
    private DocRepository docRepository;
    @Autowired
    private RepRepository repRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    public Doc saveFile(MultipartFile file, Long id, Long idUser, Long size, Date date) throws Exception {
        Repo folder = repRepository.findById(id).orElseThrow(()-> new Exception("Folder not found"));
        Company company = (Company) myUserDetailsService.getInfos(idUser);
        String docName = file.getOriginalFilename();
        try {
            Doc doc = new Doc(docName,file.getContentType(), size, date, file.getBytes(),folder,company);
            return docRepository.save(doc);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Doc> getFile(Integer fileId) {
        return docRepository.findAllByIdDoc(fileId);
    }
    public List<Doc> getFiles(Long idUser){
        return docRepository.findByCompanyId(idUser);
    }
    public Optional<Doc> getFileByName(String name){ return docRepository.getFileByName(name);}
    public Doc updateNameFolder(Doc doc) {
        return docRepository.save(doc);
    }
    public void updatePathDoc(Long destination, Integer idDoc){ docRepository.updatePathDoc(destination, idDoc);}

    public void deleteDoc(Integer docId) throws Exception {
        boolean exist = docRepository.existsById(docId);
        if(!exist){
            throw new IllegalStateException("Doc with id = "+docId+" does not exist");
        }
        History history = historyRepository.findById(Long.valueOf(docId)).orElseThrow(()-> new Exception("history not found"));
        if(history != null) history.setDoc(null);
        //historyRepository.addDeleteHistory(history.getDoc().getIdDoc(),history.getMediaName(),history.getMediaSize(),history.getMediaType(),history.getRepo().getId(),history.getUser().getId(), history.getMediaState(), history.getChangeDate());
        historyRepository.save(history);
        History addHistory = new History(history.getChangeDate(), history.getMediaName(), history.getMediaType(), history.getMediaSize(), "deleted", history.getMediaExtension(), null, history.getRepo(), history.getUser());
        historyRepository.save(addHistory);
        docRepository.deleteById(docId);
    }
}
