package com.auth.authuser.controller;

import com.auth.authuser.model.Doc;
import com.auth.authuser.model.History;
import com.auth.authuser.service.DocService;
import com.auth.authuser.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {


    @Autowired
    public HistoryService historyService;
    @Autowired
    public DocService docService;


    @GetMapping("/{userId}")
    public ResponseEntity<List<History>> getHistory(@PathVariable Long userId){
        List<History> listHistory = historyService.getAllUserHistory(userId);
        return new ResponseEntity<>(listHistory, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws Exception {
        Doc doc = docService.getFileByName(fileName).get();
        if(doc != null){
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(doc.getDocType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
                    .body(new ByteArrayResource(doc.getData()));
        }else{
            throw new Exception("File deleted");
        }
    }

}
