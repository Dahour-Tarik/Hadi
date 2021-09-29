package com.auth.authuser.controller;

import com.auth.authuser.model.Adresse;
import com.auth.authuser.model.Company;
import com.auth.authuser.model.User;
import com.auth.authuser.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @RequestMapping(value="/")
    public String home(){
        return "home";
    }

    @GetMapping("company/{id}")
    public String get(@PathVariable Long id, Model model) {
        User infos = myUserDetailsService.getInfos(id);
        if(infos.getImage()!= null){
            String picture = Base64.getMimeEncoder().encodeToString(infos.getImage());
            model.addAttribute("base", picture);
        }
        model.addAttribute("infos", infos);
        return "profil";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getInfo(@PathVariable Long id){
        Company userTemp = (Company)myUserDetailsService.getInfos(id);
        return new ResponseEntity<>(userTemp, HttpStatus.OK);
    }
    
    @GetMapping("me")
    public ResponseEntity<?> getLoginInfo(Principal principal){
        
        return new ResponseEntity<>(myUserDetailsService.getInfoByName(principal.getName()), HttpStatus.OK);
    }
    

    @GetMapping("/AdresseUser/{id}")
    public ResponseEntity<Adresse> getAdresseUser(@PathVariable Long id){
        Adresse userTemp = myUserDetailsService.getUserAdresse(id);
        return new ResponseEntity<>(userTemp, HttpStatus.OK);
    }

    @GetMapping("/FormeJuridique/{id}")
    public ResponseEntity<HashMap<String,String>> getFormeJuridique(@PathVariable Long id){
        HashMap<String,String> forme = new HashMap<>();
        forme.put("formeJuridique", myUserDetailsService.getForme(id));
        return new ResponseEntity<>(forme, HttpStatus.OK);
    }


    @PostMapping("/uploadFiles/{id}")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("imageFile") MultipartFile file, @PathVariable Long id) {

        try {
            myUserDetailsService.updateImageUser(file, id);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addUser/")
    public ResponseEntity<Company> addUser(@RequestBody Company company) {
        Company userTemp = (Company) myUserDetailsService.addUser(company);
        return new ResponseEntity<>(userTemp, HttpStatus.CREATED);
    }

    @PutMapping(value="/updateUser")
    public ResponseEntity<Company> updateUser(@RequestBody Company company) {
        Company userTemp = (Company) myUserDetailsService.updateUser(company);
        return new ResponseEntity<>(userTemp, HttpStatus.OK);
    }

    @PutMapping(value="/updateAdresse")
    public ResponseEntity<Boolean> updateAdresse(@RequestBody Adresse adresse) {
        myUserDetailsService.updateAdresse(adresse);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping(value = "/addAddres")
    public ResponseEntity<Adresse> addUser(@RequestBody Adresse adresse) {
        Adresse adresse1 = myUserDetailsService.addAdresse(adresse);
        return new ResponseEntity<>(adresse1, HttpStatus.CREATED);
    }

    @PutMapping(value="/updateForm/{id}/{formeJuridique}")
    public ResponseEntity<Boolean> updateForm(@PathVariable String formeJuridique, @PathVariable Long id) {
        System.out.println(formeJuridique);
        myUserDetailsService.updateForm(formeJuridique,id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

   @PutMapping("/updatePasswordUser/{idUser}")
    public ResponseEntity<String> updatePasswordUser(@RequestBody String password, @PathVariable Long idUser) {
        myUserDetailsService.updatePasswordUser(new BCryptPasswordEncoder().encode(password), idUser);
        return new ResponseEntity<>("Password updated", HttpStatus.OK);
    }


    @RequestMapping(value="/user")
    public String user(){
        return "user";
    }

    @RequestMapping(value="/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }


}
