package com.auth.authuser.service;

import com.auth.authuser.model.Adresse;
import com.auth.authuser.model.Company;
import com.auth.authuser.model.MyUserDetails;
import com.auth.authuser.model.User;
import com.auth.authuser.repository.AdresseRepository;
import com.auth.authuser.repository.NatureCompanyRepository;
import com.auth.authuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdresseRepository adresseRepository;

    @Autowired
    NatureCompanyRepository natureCompanyRepository;

    public User getInfos(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Adresse getUserAdresse(Long id){
        return adresseRepository.findUserAdresse(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String getForme(Long id){
        return userRepository.getForme(id);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return user.map(MyUserDetails::new).get();
    }

    public Company updateUser(Company company){
        return userRepository.save(company);
    }

    public void updateForm(String formeJuridique, Long idUser) {
        userRepository.updateForm(formeJuridique, idUser);
    }

    public void updateAdresse(Adresse a) {
         adresseRepository.updateAdresse(a.getAdresse(),a.getCodePostal(), a.getCompementAdresse(),a.getVille(), a.getId());}
    public Company addUser(Company company){
        return userRepository.save(company);
    }

    public void updatePasswordUser(String password, Long idUser){ userRepository.updateUserPassword(password, idUser);}

    @Transactional
    public User updateImageUser(MultipartFile file, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Not found user"));
        try {
            user.setImage(file.getBytes());
            return userRepository.save(user);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getInfoByName(String userName){
        return userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Adresse addAdresse(Adresse adresse){
        return adresseRepository.save(adresse);
    }

}