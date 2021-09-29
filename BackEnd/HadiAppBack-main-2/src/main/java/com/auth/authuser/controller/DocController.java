package com.auth.authuser.controller;

import com.auth.authuser.model.Doc;
import com.auth.authuser.model.Repo;
import com.auth.authuser.service.DocService;
import com.auth.authuser.service.HistoryService;
import com.auth.authuser.service.RepService;
import com.auth.authuser.service.SendEmailService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {
    @Autowired
    private DocService docService;
    @Autowired
    private RepService repService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private SendEmailService sendEmailService;


    @GetMapping(value = {"/folders/{idRepo}/{idUser}", "/"})
    public String get(@PathVariable(required = false) Long idRepo,@PathVariable(required = false) Long idUser, Model model) {
        List<Doc> docs = null;
        List<Repo> folders = null;
        if(idRepo == null || idUser == null){
            docs = docService.getFiles(1L);
            folders = repService.getRepos(1L);
        }
        else{
            docs = docService.getFiles(idUser);
            folders = repService.getRepos(idUser);
        }
        model.addAttribute("docs", docs);
        model.addAttribute("repos", folders);
        return "doc";
    }

    @GetMapping("/getDocUser/{idUser}")
    public ResponseEntity<List<Doc>> getDocsUser(@PathVariable Long idUser){
        List<Doc> docs = docService.getFiles(idUser);
        return new ResponseEntity<>(docs, HttpStatus.OK);
    }

    @GetMapping("/getdocs/{idUser}")
    public ResponseEntity<HashMap<String, Object>> getFilesAndFolders(@PathVariable Long idUser) throws JSONException {
        List<Doc> docs = docService.getFiles(idUser);
        List<Repo> folders = repService.getRepos(idUser);

        HashMap<String, Object> sampleObject = new HashMap<>();
        HashMap<String, Object> fileMap = new HashMap<>();
        sampleObject.put("rootFolderId", "1");
        for(int j = 0; j < folders.size(); j++){
            List<String> childs = new ArrayList<>();
            HashMap<Object, Object> obj = new HashMap<>();
            for(int i = 0; i < folders.get(j).getEnfants().size(); i++){
                childs.add(String.valueOf(folders.get(j).getEnfants().get(i).getId()));
            }
            for(int i = 0; i < folders.get(j).getDocs().size(); i++){
                childs.add("doc_"+folders.get(j).getDocs().get(i).getIdDoc());
            }
            obj.put("childrenIds", childs);
            obj.put("id",String.valueOf(folders.get(j).getId()));
            obj.put("name",folders.get(j).getName());
            obj.put("isDir",!folders.get(j).isFile());
            if(folders.get(j).getId() != 1){
                if(folders.get(j).getRepDateCreation() != null) {obj.put("modDate", folders.get(j).getRepDateCreation());}
                else{ obj.put("modDate", folders.get(j).getRepDateCreation()); }
                if(folders.get(j).getParents().getId() != null) obj.put("parentId",String.valueOf(folders.get(j).getParents().getId()));
            }
            obj.put("childrenCount",folders.get(j).getEnfants().size());
            String o = String.valueOf(folders.get(j).getId());
            fileMap.put(o,obj);
        }

        for(int j = 0; j < docs.size(); j++){
            HashMap<Object, Object> obj = new HashMap<>();

            obj.put("id","doc_"+docs.get(j).getIdDoc());
            obj.put("name",docs.get(j).getDocName());
            obj.put("size",docs.get(j).getDocSize());
            obj.put("parentId",String.valueOf(docs.get(j).getRepo().getId()));
            if(docs.get(j).getDocCreationDate() != null) {obj.put("modDate", docs.get(j).getDocCreationDate());}
            else{ obj.put("modDate", docs.get(j).getDocCreationDate()); }
            String o = "doc_"+docs.get(j).getIdDoc();
            fileMap.put(o,obj);
        }
        sampleObject.put("fileMap", fileMap);
        System.out.println(sampleObject);

        return new ResponseEntity<>(sampleObject, HttpStatus.OK);
    }


    @PostMapping("/uploadFiles")
    public List<Doc> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                      @RequestParam("idRep") Long idRep,
                                      @RequestParam("idUser") Long idUser ) throws Exception {
    	List<Doc> docs=new ArrayList<Doc>();
    			
        for (MultipartFile file: files) {
            Doc doc = docService.saveFile(file, idRep, idUser,file.getSize(),new Date());
            docs.add(doc);
//            historyService.addFileElementHistory(file, idRep, idUser, file.getSize(),doc.getIdDoc(), new Date(), "created",doc.getDocName());
//            sendEmailService.addEmail(new Date(),"Document", doc.getCompany(),doc.getCompany().getChefMission(),doc,null);
//            sendEmailService.sendEmail(doc.getCompany().getChefMission().getEmail(), "l'entreprise "+doc.getCompany().getNomEntreprise()+" vient de deposer un documernt", "depos fichier");
        }
        return docs;
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId){
        Integer id = Integer.parseInt(fileId);
        Doc doc = (Doc) docService.getFile(id).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
                .body(new ByteArrayResource(doc.getData()));
    }

    @PutMapping("/updateName/{docId}")
    public ResponseEntity<Doc> updateDocName(@RequestBody Doc doc){
        Doc docTemp = docService.updateNameFolder(doc);
        return new ResponseEntity<>(docTemp, HttpStatus.OK);
    }

    @PutMapping("/updatePath/{destinationId}/{docId}")
    public void chPlaceDoc(@PathVariable Long destinationId, @PathVariable Integer docId){
      docService.updatePathDoc(destinationId, docId);
    }

    @DeleteMapping("deleteDoc/{docId}")
    public String deleteDoc(@PathVariable Integer docId) throws Exception {
        docService.deleteDoc(docId);
        return "doc";
    }
}
